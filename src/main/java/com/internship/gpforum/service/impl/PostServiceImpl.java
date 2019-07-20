package com.internship.gpforum.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.dal.PostRepository;
import com.internship.gpforum.dal.StarRepository;
import com.internship.gpforum.dal.entity.Post;
import com.internship.gpforum.service.PostService;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private StarRepository starRepository;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private JSONObject json = new JSONObject();

    private static String HOTWORDS = "";

    static String FILEPATH = "C:\\Users\\Administrator\\Desktop\\post_content.txt";

    public Page<Post> getByEdiTime(String sectionName, PageRequest pageRequest) {
        Page<Post> postList = postRepository.findBySectionNameAndInvisibleOrderByLastEditTimeDesc(sectionName, pageRequest, false);

        return postList;
    }

    @Override
    public Page<Post> getByStarNumber(String sectionName, PageRequest pageRequest) {
        Page<Post> postList = postRepository.findBySectionNameAndInvisibleOrderByStarNumberDesc(sectionName, pageRequest, false);
        return postList;
    }

    @Override
    public Post getDetail(Integer postId) {
        return postRepository.findByPostId(postId);
    }

    @Override
    public Page<Post> getHisPost(String userEmail, PageRequest pageRequest) {
        return postRepository.findByAuthorEmailOrderByLastEditTimeDesc(userEmail, pageRequest);
    }

    @Override
    public List<Post> getPosts(String email) {
        List<Post> postList = postRepository.findByAuthorEmail(email);
        return postList;
    }


    @Override
    public List<Post> findInTitleAndContent(String keyword) {
        return postRepository.findInTitleAndContent(keyword);
    }


    @Override
    public void update(Integer id, Integer number, Integer type) {
        Post post = postRepository.findByPostId(id);
        if (type == 0) {
            post.setStarNumber(number);
        } else if (type == 1)
            post.setBrowseNumber(number);
        postRepository.saveAndFlush(post);
    }


    public void writeContent(String author_email, String authorNickname, String section_name, String title, String summary, String content, boolean invisible, String post_status, Date lastEditTime) {
        Post post = new Post();
        post.setAuthorEmail(author_email);
        post.setSectionName(section_name);
        post.setTitle(title);
        post.setSummary(summary);
        post.setContent(content);
        post.setFirstImg(content);
        if (post.getFirstImg().equals("")) {
            post.setFirstImg("0");
        }
        post.setInvisible(invisible);
        post.setPostStatus(post_status);
        post.setLastEditTime(lastEditTime);
        post.setAuthorNickName(authorNickname);
        post.setBrowseNumber(0);
        post.setStarNumber(0);
        postRepository.save(post);
        Integer id = post.getPostId();
        redisTemplate.opsForHash().put("stars", id + "", 0);
        redisTemplate.opsForHash().put("browseNumber", id + "", 0);
        redisTemplate.opsForZSet().add("scores", post.getPostId() + "", 0);
    }



    @Scheduled(cron = "0/30 * * * * ? ")
    public void getVeryHotWords() {

        List<Post> posts = postRepository.findAll();

        Path target = Paths.get(FILEPATH);

        File file = new File(FILEPATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            byte[] bytes = Files.readAllBytes(target);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
        String regSpace1 = "&nbsp;";
        String regSpace2 = "\\s*|\t|\r|\n|";
        String regFuHao = "-";

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Post post : posts) {
            String postContent = post.getContent();

            String repContent = postContent.replaceAll(regEx_script, "");
            repContent = repContent.replaceAll(regEx_style, "");
            repContent = repContent.replaceAll(regEx_html, "");
            repContent = repContent.replaceAll(regSpace1, "");
            repContent = repContent.replaceAll(regSpace2, "");
            repContent = repContent.replaceAll(regFuHao, "");


            try {
                if (fileWriter != null) {
                    fileWriter.write(repContent);
                }
            } catch (IOException e) {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = null;
        try {
            result = wordFrequency(FILEPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HOTWORDS = result;

        System.out.println(HOTWORDS);

    }

    @Override
    public String getHotWords() {
        return HOTWORDS;
    }

    private static String getString(String FILEPATH) throws IOException {

        FileInputStream inputStream = new FileInputStream(new File(FILEPATH));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder strBuilder = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            strBuilder.append(line);
        }

        reader.close();
        inputStream.close();

        return strBuilder.toString();

    }

    private static Map.Entry<String, Integer> getMax(Map<String, Integer> map) {

        if (map.size() == 0) {
            return null;
        }
        Map.Entry<String, Integer> maxEntry = null;
        boolean flag = false;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (!flag) {
                maxEntry = entry;
                flag = true;
            }
            if (entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        map.remove(maxEntry.getKey());
        return maxEntry;
    }

    private static String wordFrequency(String FILEPATH) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        String article = getString(FILEPATH);
        String result = ToAnalysis.parse(article).toStringWithOutNature();
        String[] words = result.split(",");
        for (String word : words) {
            String str = word.trim();
            // 过滤空白字符
            if (str.equals(""))
                continue;
                // 过滤一些高频率的符号
            else if (str.matches("[）|（|.|，|。|+|-|“|”|：|？|\\s]"))
                continue;
                // 此处过滤长度为1的str
            else if (str.length() < 2)
                continue;
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                int n = map.get(word);
                map.put(word, ++n);
            }

        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        Map.Entry<String, Integer> entry;
        int i = 0;
        while ((entry = getMax(map)) != null) {
            list.add(entry);
            i++;
            if (i == 10) {
                break;
            }
        }
        return JSON.toJSONString(list.toArray());
    }

    @Override
    public void deleteByPostId(Integer postId) {
        postRepository.deleteByPostId(postId);
    }
}
