-- --------------------------------------------------------
-- 主机:                           49.234.61.134
-- Server version:               5.5.60-MariaDB - MariaDB Server
-- Server OS:                    Linux
-- HeidiSQL 版本:                  9.5.0.5317
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for GPF_dev
CREATE DATABASE IF NOT EXISTS `GPF_dev` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `GPF_dev`;

-- Dumping structure for table GPF_dev.application
CREATE TABLE IF NOT EXISTS `application` (
  `application_id` int(11) NOT NULL AUTO_INCREMENT,
  `applicant_email` varchar(255) DEFAULT NULL,
  `application_content` varchar(255) DEFAULT NULL,
  `application_reason` varchar(255) DEFAULT NULL,
  `application_time` datetime DEFAULT NULL,
  `review_reason` varchar(255) DEFAULT NULL,
  `review_result` bit(1) NOT NULL,
  `review_time` datetime DEFAULT NULL,
  `reviewer_email` varchar(255) DEFAULT NULL,
  `section_introduction` varchar(255) DEFAULT NULL,
  `section_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.application: ~0 rows (approximately)
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.attention
CREATE TABLE IF NOT EXISTS `attention` (
  `attention_id` int(11) NOT NULL AUTO_INCREMENT,
  `attention_time` datetime DEFAULT NULL,
  `concerned_user_email` varchar(255) DEFAULT NULL,
  `concerned_user_nick_name` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`attention_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.attention: ~0 rows (approximately)
/*!40000 ALTER TABLE `attention` DISABLE KEYS */;
/*!40000 ALTER TABLE `attention` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.banned
CREATE TABLE IF NOT EXISTS `banned` (
  `banned_id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` datetime DEFAULT NULL,
  `moderator_email` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `section_id` int(11) DEFAULT NULL,
  `section_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`banned_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.banned: ~0 rows (approximately)
/*!40000 ALTER TABLE `banned` DISABLE KEYS */;
/*!40000 ALTER TABLE `banned` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.browse_record
CREATE TABLE IF NOT EXISTS `browse_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `browse_time` datetime DEFAULT NULL,
  `browse_url` varchar(255) DEFAULT NULL,
  `post_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.browse_record: ~0 rows (approximately)
/*!40000 ALTER TABLE `browse_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `browse_record` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.collection
CREATE TABLE IF NOT EXISTS `collection` (
  `collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `collection_time` varchar(255) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `post_title` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.collection: ~0 rows (approximately)
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.comment
CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `parent_comment_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `respondent_user_email` varchar(255) DEFAULT NULL,
  `respondent_user_nick_name` varchar(255) DEFAULT NULL,
  `target_comment_id` int(11) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_nick_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.comment: ~45 rows (approximately)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT IGNORE INTO `comment` (`comment_id`, `comment_time`, `content`, `parent_comment_id`, `post_id`, `respondent_user_email`, `respondent_user_nick_name`, `target_comment_id`, `user_email`, `user_nick_name`) VALUES
	(1, '2019-07-21 11:47:29', '还行', NULL, 1, '1194688236@qq.com', 'BadlySad', NULL, 'a648186412@gmail.com', '多洗牙'),
	(2, '2019-07-21 11:55:31', '对', NULL, 1, '1194688236@qq.com', 'BadlySad', NULL, '2639682042@qq.com', 'mortal'),
	(3, '2019-07-21 11:56:18', '也还行', 1, 1, 'a648186412@gmail.com', '多洗牙', NULL, '2639682042@qq.com', 'mortal'),
	(4, '2019-07-21 12:01:16', '不是这么可以', 1, 1, 'a648186412@gmail.com', '多洗牙', NULL, '1500896297@qq.com', 'Forevery'),
	(5, '2019-07-21 12:27:12', '没错', NULL, 1, '1194688236@qq.com', 'BadlySad', NULL, '2639682042@qq.com', 'mortal'),
	(6, '2019-07-21 12:29:54', '没有2', NULL, 1, '1194688236@qq.com', 'BadlySad', NULL, '2639682042@qq.com', 'mortal'),
	(7, '2019-07-21 12:54:32', '亮：必不负主公托孤遗志！', NULL, 1, '1194688236@qq.com', 'BadlySad', NULL, '651858890@qq.com', '大哥'),
	(8, '2019-07-21 13:55:26', '配图hso', NULL, 6, '651858890@qq.com', '大哥', NULL, '1037671592@qq.com', '五水硫酸铜'),
	(9, '2019-07-21 15:01:31', 'hh', 1, 1, 'a648186412@gmail.com', '多洗牙', NULL, '1484850996@qq.com', '11223'),
	(10, '2019-07-21 15:01:59', '11', 2, 1, '2639682042@qq.com', 'mortal', NULL, '1484850996@qq.com', '11223'),
	(11, '2019-07-21 15:08:11', '哈哈哈哈哈哈', NULL, 9, '1037671592@qq.com', '五水硫酸铜', NULL, '1484850996@qq.com', '梦蝶儿'),
	(12, '2019-07-21 15:08:21', '好好好好好', 11, 9, '1484850996@qq.com', '11223', NULL, '1484850996@qq.com', '11223'),
	(13, '2019-07-21 15:08:32', '哈哈哈哈', 11, 9, '1484850996@qq.com', '11223', NULL, '1484850996@qq.com', '11223'),
	(14, '2019-07-21 15:09:38', '顶顶顶顶', NULL, 4, '1500896297@qq.com', 'Forevery', NULL, '1484850996@qq.com', '11223'),
	(15, '2019-07-21 15:09:48', '哈哈哈哈', 14, 4, '1484850996@qq.com', '11223', NULL, '1484850996@qq.com', '11223'),
	(16, '2019-07-21 15:09:57', '斤斤计较', 14, 4, '1484850996@qq.com', '11223', NULL, '1484850996@qq.com', '11223'),
	(17, '2019-07-21 15:12:14', '这个好吃吗？', NULL, 13, '1194688236@qq.com', 'BadlySad', NULL, '2639682042@qq.com', 'mortal'),
	(18, '2019-07-21 15:15:52', 'haihao', NULL, 1, '1194688236@qq.com', 'BadlySad', NULL, '1194688236@qq.com', 'BadlySad'),
	(19, '2019-07-21 15:16:47', 'asd', NULL, 1, '1194688236@qq.com', 'BadlySad', NULL, '1194688236@qq.com', 'BadlySad'),
	(20, '2019-07-21 15:25:18', 'hhhhhhhhhhhhhhhhhhhhh', NULL, 14, '1484850996@qq.com', '11223', NULL, '1484850996@qq.com', '梦蝶儿'),
	(21, '2019-07-21 15:29:10', '到的撒多', 8, 6, '1037671592@qq.com', '五水硫酸铜', NULL, '1484850996@qq.com', '梦蝶儿'),
	(22, '2019-07-21 17:49:32', '我方水晶正在被攻击', NULL, 18, '1194688236@qq.com', 'BadlySad', NULL, '2639682042@qq.com', 'mortal'),
	(23, '2019-07-21 17:52:31', '真的惨', NULL, 21, '648186412@qq.com', '多洗牙', NULL, '648186412@qq.com', '多洗牙'),
	(24, '2019-07-21 17:52:57', '好吃得鸭皮', 17, 13, '2639682042@qq.com', 'mortal', NULL, '648186412@qq.com', '多洗牙'),
	(25, '2019-07-21 17:53:36', '表哥', NULL, 21, '648186412@qq.com', '多洗牙', NULL, '1037671592@qq.com', '五水硫酸铜'),
	(26, '2019-07-21 17:54:30', '表表哥', 25, 21, '1037671592@qq.com', '五水硫酸铜', NULL, '648186412@qq.com', '多洗牙'),
	(27, '2019-07-21 17:59:13', '利', NULL, 8, '1037671592@qq.com', '五水硫酸铜', NULL, '1037671592@qq.com', '五水硫酸铜'),
	(28, '2019-07-21 18:02:30', '要按照基本法和选举法去产生', NULL, 7, '1037671592@qq.com', '五水硫酸铜', NULL, '1037671592@qq.com', '五水硫酸铜'),
	(29, '2019-07-21 19:02:06', '我随便一发就是标准的十五个字。', 25, 21, '1037671592@qq.com', '五水硫酸铜', NULL, '1484850996@qq.com', '梦蝶儿'),
	(30, '2019-07-21 19:07:13', '跳楼价只要888', NULL, 13, '1194688236@qq.com', 'BadlySad', NULL, '2639682042@qq.com', 'mortal'),
	(31, '2019-07-21 19:09:33', 'haha', NULL, 21, '648186412@qq.com', '多洗牙', NULL, '648186412@qq.com', '多洗牙'),
	(32, '2019-07-21 19:12:59', '好吃', NULL, 13, '1194688236@qq.com', 'BadlySad', NULL, '2639682042@qq.com', 'mortal'),
	(33, '2019-07-21 19:13:36', 'what', NULL, 6, '651858890@qq.com', '大哥', NULL, '2639682042@qq.com', 'mortal'),
	(34, '2019-07-21 19:56:09', '你看我随手一发就是标准的十五个字', 25, 21, '1484850996@qq.com', '梦蝶儿', NULL, '1037671592@qq.com', '五水硫酸铜'),
	(35, '2019-07-21 22:31:21', 'what the hell ?', NULL, 21, '648186412@qq.com', '多洗牙', NULL, '648186412@qq.com', '多洗牙'),
	(36, '2019-07-21 22:34:37', '妙啊', NULL, 19, '1037671592@qq.com', '五水硫酸铜', NULL, '648186412@qq.com', '多洗牙'),
	(37, '2019-07-21 22:35:42', '图呢？', NULL, 14, '1484850996@qq.com', '11223', NULL, '648186412@qq.com', '多洗牙'),
	(38, '2019-07-21 22:42:42', '??', NULL, 2, '1500896297@qq.com', 'Forevery', NULL, '648186412@qq.com', '多洗牙'),
	(39, '2019-07-21 22:42:45', '??', NULL, 2, '1500896297@qq.com', 'Forevery', NULL, '648186412@qq.com', '多洗牙'),
	(40, '2019-07-21 23:31:11', '国', NULL, 8, '1037671592@qq.com', '五水硫酸铜', NULL, '648186412@qq.com', '多洗牙'),
	(41, '2019-07-21 23:32:08', '真实', NULL, 20, '1037671592@qq.com', '五水硫酸铜', NULL, '648186412@qq.com', '多洗牙'),
	(42, '2019-07-21 23:51:22', '我慢慢睡着', NULL, 25, '1194688236@qq.com', 'BadlySad', NULL, '1194688236@qq.com', 'BadlySad'),
	(43, '2019-07-21 23:51:36', '天刚刚破晓', 42, 25, '1194688236@qq.com', 'BadlySad', NULL, '1194688236@qq.com', 'BadlySad'),
	(44, '2019-07-21 23:51:51', '。', 42, 25, '1194688236@qq.com', 'BadlySad', NULL, '1194688236@qq.com', 'BadlySad'),
	(45, '2019-07-22 09:43:24', 'whats your problem?', NULL, 21, '648186412@qq.com', '多洗牙', NULL, '3266300848@qq.com', '3266300848@qq.com');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.moderator
CREATE TABLE IF NOT EXISTS `moderator` (
  `moderator_id` int(11) NOT NULL AUTO_INCREMENT,
  `appointment_time` datetime DEFAULT NULL,
  `moderator_avatar` varchar(255) DEFAULT NULL,
  `moderator_email` varchar(255) DEFAULT NULL,
  `moderator_nick_name` varchar(255) DEFAULT NULL,
  `section_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`moderator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.moderator: ~5 rows (approximately)
/*!40000 ALTER TABLE `moderator` DISABLE KEYS */;
INSERT IGNORE INTO `moderator` (`moderator_id`, `appointment_time`, `moderator_avatar`, `moderator_email`, `moderator_nick_name`, `section_name`) VALUES
	(1, '2019-07-17 06:14:33', '/img/no_avatar.png', '1194688236@qq.com', 'sad', '膜'),
	(2, '2019-07-18 16:16:36', '/img/no_avatar.png', '648186412@qq.com', '多洗牙', '美食'),
	(3, '2019-07-18 16:16:36', '/img/no_avatar.png', '1194688236@qq.com', 'sad', '旅游'),
	(4, '2019-07-18 16:16:36', '/img/no_avatar.png', '1037671592@qq.com', '昵称长度不能大于10', '动漫'),
	(5, '2019-07-18 16:16:36', '/img/no_avatar.png', '1037671592@qq.com', '昵称长度不能大于10', '音乐');
/*!40000 ALTER TABLE `moderator` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.post
CREATE TABLE IF NOT EXISTS `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `author_email` varchar(255) DEFAULT NULL,
  `author_nick_name` varchar(255) DEFAULT NULL,
  `browse_number` int(11) DEFAULT NULL,
  `commentable` bit(1) NOT NULL,
  `content` varchar(10000) DEFAULT NULL,
  `first_img` varchar(10000) DEFAULT NULL,
  `invisible` bit(1) NOT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `post_status` varchar(255) DEFAULT NULL,
  `section_name` varchar(255) DEFAULT NULL,
  `star_number` int(11) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.post: ~23 rows (approximately)
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT IGNORE INTO `post` (`post_id`, `author_email`, `author_nick_name`, `browse_number`, `commentable`, `content`, `first_img`, `invisible`, `last_edit_time`, `post_status`, `section_name`, `star_number`, `summary`, `title`) VALUES
	(2, '1500896297@qq.com', 'Forevery', 7, b'0', '<p><span style="">埙是开口吹奏乐器，音色朴拙抱素独为天籁，在世界原始艺术史中占有重要的地位。埙的早期雏形是狩猎用的石头。有的石头上有自然形成的空腔，当先民们用这样的石头投击猎物时，石上空腔由于气流的作用而产生哨音。这种哨音启发了古代先民制作乐器的灵感，于是早期的埙就产生了。</span></p><p><span style=""><img alt="Image" src="https://tse2-mm.cn.bing.net/th?id=OIP.3V-06JbNK7KnpiGWm7X2ygHaFj&amp;w=232&amp;h=174&amp;c=7&amp;o=5&amp;dpr=1.25&amp;pid=1.7" width="290" height="217"><br></span></p><p><span style="">陶制的埙是古代就流行的乐器之一，属于吹奏鸣响乐器，早在新石器时代的红山文化时期，埙的演奏就很盛行，延续至今。埙在古代用陶土烧制的一种吹奏乐器，圆形或椭圆形，有六孔(现在有八孔，九孔，十孔，双八度等，六孔埙目前市场上不常见)。亦称“陶埙”。以陶制最为普通，也有石制和骨制等。</span></p>', 'https://tse2-mm.cn.bing.net/th?id=OIP.3V-06JbNK7KnpiGWm7X2ygHaFj&w=232&h=174&c=7&o=5&dpr=1.25&pid=1.7', b'0', '2019-07-21 11:50:54', '正常', '音乐', 0, '埙是开口吹奏乐器，音色朴拙抱素独为天籁，', '埙'),
	(5, '651858890@qq.com', '大哥', 5, b'0', '<p><img alt="Image" src="https://tse3-mm.cn.bing.net/th?id=OIP.2XP1gVA8fGjHxx6ZhYKXqgHaD7&amp;w=300&amp;h=159&amp;c=7&amp;o=5&amp;dpr=1.25&amp;pid=1.7" width="375" height="198"><br></p><p>《眷思量》是一部由赵禹晴编制、执导的古风浪漫3D作品，讲述了神秘岛屿上倔强少女的武侠绮梦和曲折爱情。</p>', 'https://tse3-mm.cn.bing.net/th?id=OIP.2XP1gVA8fGjHxx6ZhYKXqgHaD7&w=300&h=159&c=7&o=5&dpr=1.25&pid=1.7', b'0', '2019-07-21 12:02:03', '正常', '动漫', 0, '《眷思量》是一部由赵禹晴编制、执导的古风', '卷思量'),
	(6, '651858890@qq.com', '大哥', 19, b'0', '<p><img alt="Image" src="https://tse4-mm.cn.bing.net/th?id=OIP.uCso-92qfUCprJgrqecwYQHaEo&amp;w=292&amp;h=177&amp;c=7&amp;o=5&amp;dpr=1.25&amp;pid=1.7" width="365" height="221"><br></p><p><span style="color: rgb(51, 51, 51);">《血色苍穹》是</span><a href="https://wapbaike.baidu.com/item/%E5%8C%97%E5%B7%B7">北巷</a><span style="color: rgb(51, 51, 51);">编创的漫画作品。讲述了平凡上班族李鸣洋，因扫描一个奇怪的二维码而陷入一个神秘世界“血色苍穹”，并在此展开的一系列惊心动魄的冒险故事。</span><br></p>', 'https://tse4-mm.cn.bing.net/th?id=OIP.uCso-92qfUCprJgrqecwYQHaEo&w=292&h=177&c=7&o=5&dpr=1.25&pid=1.7', b'0', '2019-07-21 12:06:17', '正常', '动漫', 2, '《血色苍穹》是北巷编创的漫画作品。讲述了', '血色苍穹'),
	(7, '1037671592@qq.com', '五水硫酸铜', 8, b'0', '<p>好</p><p>支持吗</p><p>支持</p><p>欧盟呢最近发表了一篇调查是说北京会。。。。</p>', '0', b'0', '2019-07-21 14:05:13', '正常', '膜', 1, '好支持吗支持欧盟呢最近发表了一篇调查是说', '董先生连任好不好'),
	(8, '1037671592@qq.com', '五水硫酸铜', 7, b'0', '<p>林则徐有一句名言呐：苟</p>', '0', b'0', '2019-07-21 14:07:12', '正常', '膜', 2, '林则徐有一句名言呐：苟', '林则徐名言'),
	(10, '1194688236@qq.com', 'BadlySad', 1, b'0', '<p><img src="https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1564295653&amp;di=dc9261adc27e7d69fcfddea23b80aba3&amp;imgtype=jpg&amp;er=1&amp;src=http%3A%2F%2Ffiles.vivo.com.cn%2Fvivobbs%2Fattachment%2Fforum%2F201607%2F10%2F093253vmq55hab6mbxbbrf.jpeg" width="500" height="333.4636434714621"><br></p><p>说走就走的旅行</p>', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564295653&di=dc9261adc27e7d69fcfddea23b80aba3&imgtype=jpg&er=1&src=http%3A%2F%2Ffiles.vivo.com.cn%2Fvivobbs%2Fattachment%2Fforum%2F201607%2F10%2F093253vmq55hab6mbxbbrf.jpeg', b'0', '2019-07-21 14:36:05', '正常', '旅游', 0, '说走就走的旅行', '旅行'),
	(11, '1194688236@qq.com', 'BadlySad', 3, b'0', '<p><img src="https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1564295833&amp;di=fede8558ffd6b29000d70243266f66e6&amp;imgtype=jpg&amp;er=1&amp;src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201507%2F30%2F20150730193029_RVuQP.jpeg" width="500" height="354.0868454661558"><br></p><p>生活不止眼前的苟且</p>', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564295833&di=fede8558ffd6b29000d70243266f66e6&imgtype=jpg&er=1&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201507%2F30%2F20150730193029_RVuQP.jpeg', b'0', '2019-07-21 14:37:51', '正常', '旅游', 0, '生活不止眼前的苟且', '去看看海'),
	(12, '1194688236@qq.com', 'BadlySad', 0, b'0', '<p><img src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=343816981,2432040629&amp;fm=26&amp;gp=0.jpg"><br></p>', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=343816981,2432040629&fm=26&gp=0.jpg', b'0', '2019-07-21 14:39:34', '正常', '美食', 0, '', '苜蓿肉'),
	(13, '1194688236@qq.com', 'BadlySad', 33, b'0', '<p><img src="https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1563701319467&amp;di=21edbcc019fae74a8aede6a70ce57915&amp;imgtype=0&amp;src=http%3A%2F%2Fqcloud.dpfile.com%2Fpc%2FDPKONnsNSbJDZ0cGollBvLID8C4n1tc4H04tZDH4Kq_f6T3DZYkiYIz6Ij9_2SuSTYGVDmosZWTLal1WbWRW3A.jpg" width="500" height="307.6171875"><br></p><p>鱼香肉丝</p>', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563701319467&di=21edbcc019fae74a8aede6a70ce57915&imgtype=0&src=http%3A%2F%2Fqcloud.dpfile.com%2Fpc%2FDPKONnsNSbJDZ0cGollBvLID8C4n1tc4H04tZDH4Kq_f6T3DZYkiYIz6Ij9_2SuSTYGVDmosZWTLal1WbWRW3A.jpg', b'0', '2019-07-21 14:42:18', '正常', '美食', 3, '鱼香肉丝', '鱼香肉丝'),
	(14, '1484850996@qq.com', '11223', 18, b'0', '<p><img src="http://pic37.nipic.com/20140113/8800276_184927469000_2.png"><br></p>', 'http://pic37.nipic.com/20140113/8800276_184927469000_2.png', b'0', '2019-07-21 15:03:58', '正常', '美食', 1, '', '这个马看起来又大又圆又好吃'),
	(15, '2639682042@qq.com', 'mortal', 4, b'0', '<p><img alt="Image" src="https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_jpg/NVvB3l3e9aEsR973nugEo7bpvFIptJqfU8OxD2BHN6RWJwoAkvOibrRAdn2ic3dKqPGDI5gmEpK9kSFtcXyS2M2g/640?wx_fmt=jpeg" width="568" height="939.3037037037037"><br></p><p>日常霸榜</p>', 'https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_jpg/NVvB3l3e9aEsR973nugEo7bpvFIptJqfU8OxD2BHN6RWJwoAkvOibrRAdn2ic3dKqPGDI5gmEpK9kSFtcXyS2M2g/640?wx_fmt=jpeg', b'0', '2019-07-21 15:17:11', '正常', '膜', 1, '日常霸榜', 'GitHub-996项目被程序员霸榜'),
	(17, '2639682042@qq.com', 'mortal', 10, b'0', '<p><img alt="Image" src="https://p1.img.cctvpic.com/cportal/img/2019/7/20/1563603201213_277_1080x950.jpg" width="568" height="499.6296296296296"><br><br></p><p>东西部结对牵手、协作扶贫，中国的脱贫攻坚，为全球减贫事业贡献了中国实践。</p><p>我国贫困人口从2012年的9899万人减少到2018年的1660万人，6年时间减少了8000多万人，连续6年平均每年减贫1300多万人。今年7月，国务院扶贫办公布，今年我国宣布脱贫摘帽的贫困县有283个。至此，全国已经有436个贫困县脱贫摘帽，占全部贫困县的52.4%。贫困县摘帽进程过半，解决区域性整体贫困步伐加快。</p>', 'https://p1.img.cctvpic.com/cportal/img/2019/7/20/1563603201213_277_1080x950.jpg', b'0', '2019-07-21 15:26:28', '正常', '膜', 0, '东西部结对牵手、协作扶贫，中国的脱贫攻坚', '东西部“携手奔小康”，总书记指示这么干！'),
	(19, '1037671592@qq.com', '五水硫酸铜', 18, b'0', '<p><img src="https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=b7bceb4280d6277ffd1f3a6a49517455/b90e7bec54e736d11f93f3f19c504fc2d4626942.jpg"><br></p><p>我清早起床睡意浓　睡眼尚迷濛</p><p>我忽然想起今日是　假日好时光</p><p>打电话约了密斯陈　在公园碰头</p><p>谁知我身上穿汗衫　左鞋右脚套</p><p>哈哈哈哈哈哈哈哈呵哈哈哈哈</p><p>哈哈哈哈哈哈哈哈哈哈呵哈哈</p><p>嘿嘿嘿嘿嘿嘿哈哈嘿嘿哈哈哈</p><p>啊哈哈哈哈呵哈哈哈哈哈哈哈</p><p>我匆匆赶到公园口　见她已来到</p><p>你看她身材多窈窕　脸儿比花娇</p><p>我有心表示对她好我上前就拥抱</p><p>她转身赏我一耳光　原来认错了</p><p>哈哈哈哈哈哈哈哈呵哈哈哈哈</p><p>哈哈哈哈哈哈哈哈哈哈呵哈哈</p><p>嘿嘿嘿嘿嘿嘿哈哈嘿嘿哈哈哈</p><p>啊哈哈哈哈呵哈哈哈哈哈哈哈</p><p>我等到红日当头照　她姗姗来到</p><p>我气在心头向她问　为何要迟到</p><p>又看她衣裙穿颠倒　拖鞋没换掉</p><p>她说为约会心急燥　跟我一样妙</p><p>哈哈哈哈哈哈哈哈呵哈哈哈哈</p><p>哈哈哈哈哈哈哈哈哈哈呵哈哈</p><p>嘿嘿嘿嘿嘿嘿哈哈嘿嘿哈哈哈</p><p>啊哈哈哈哈呵哈哈哈哈哈哈哈</p><p>我和她甜甜又蜜蜜也同渡好春宵</p><p>又同游共舞一整天　咖啡厅里泡</p><p>到临别时候那一招　情形更好笑</p><p>她不姓陈来是姓萧　我也不姓赵</p><p>哈哈哈哈哈哈哈哈呵哈哈哈哈</p><p>哈哈哈哈哈哈哈哈哈哈呵哈哈</p><p>嘿嘿嘿嘿嘿嘿哈哈嘿嘿哈哈哈</p><p>啊哈哈哈哈呵哈哈哈哈哈哈哈</p>', 'https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=b7bceb4280d6277ffd1f3a6a49517455/b90e7bec54e736d11f93f3f19c504fc2d4626942.jpg', b'0', '2019-07-21 17:42:44', '正常', '音乐', 2, '我清早起床睡意浓　睡眼尚迷濛我忽然想起今', '奇妙的约会'),
	(20, '1037671592@qq.com', '五水硫酸铜', 7, b'0', '<p><span style=""><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQt-BftspZ2Pt-UtYIELcOQo0_MD0h9kCQBvhS9id9_bXnw0kI"><br></span></p><p><span style="">一杯茶，一包烟</span></p><p>一行代码改一天</p>', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQt-BftspZ2Pt-UtYIELcOQo0_MD0h9kCQBvhS9id9_bXnw0kI', b'0', '2019-07-21 17:51:47', '正常', '旅游', 2, '一杯茶，一包烟一行代码改一天', '旅游是不可能旅游的'),
	(21, '648186412@qq.com', '多洗牙', 71, b'0', '<p><span style="color: rgb(51, 51, 51);"><img src="https://y.gtimg.cn/music/photo_new/T002R300x300M0000040WSs24YVv3S.jpg?max_age=2592000" width="300" height="300"><br></span></p><p><span style="color: rgb(51, 51, 51);">作曲 : 黄明志</span></p><p><span style="color: rgb(51, 51, 51);">作词 : 黄明志</span></p><p><span style="color: rgb(51, 51, 51);">编曲人: 杨朝焰 Chow Yarn</span></p><p><span style="color: rgb(51, 51, 51);">昨夜没回家&nbsp; 就连电话也不答</span></p><p><span style="color: rgb(51, 51, 51);">雨停了&nbsp; 我还在想着她</span></p><p><span style="color: rgb(51, 51, 51);">突然停止通话&nbsp; 背景音乐是酒吧</span></p><p><span style="color: rgb(51, 51, 51);">她却说&nbsp; 是手机没电啦</span></p><p><span style="color: rgb(51, 51, 51);">试图刻意隐藏着什么</span></p><p><span style="color: rgb(51, 51, 51);">她的生活充满层层的密码</span></p><p><span style="color: rgb(51, 51, 51);">为何把爱情变的复杂</span></p><p><span style="color: rgb(51, 51, 51);">朋友都劝我该悬崖勒马&nbsp; 忘了她</span></p><p>怎么办&nbsp; 我真的没有办法</p><p>怎么办&nbsp; 我依然爱着她</p><p>虽然她&nbsp; 总是满嘴脏话</p><p>从不洗衣做饭</p><p>生活糜烂像个破麻</p><p>怎么办&nbsp; 是真的没有办法</p><p>怎么办&nbsp; 还依然很爱她</p><p>虽然她&nbsp; 拜金虚荣放荡</p><p>菸酒不离四处为家</p><p>我忍受着她</p><p>昨夜没回家&nbsp; 就连电话也不答</p><p>雨停了&nbsp; 我还在想着她</p><p>突然停止通话&nbsp; 背景音乐是酒吧</p><p>她却说&nbsp; 是手机没电啦</p><p>试图刻意隐藏着什么</p><p>她的生活充满层层的密码</p><p>为何把爱情变的复杂</p><p>朋友都劝我该悬崖勒马&nbsp; 忘了她</p><p>怎么办&nbsp; 我真的没有办法</p><p>怎么办&nbsp; 我依然爱着她</p><p>虽然她&nbsp; 总是满嘴脏话</p><p>从不洗衣做饭</p><p>生活糜烂像个破麻</p><p>怎么办&nbsp; 是真的没有办法</p><p>怎么办&nbsp; 还依然很爱她</p><p>虽然她&nbsp; 拜金虚荣放荡</p><p>菸酒不离四处为家</p><p>我愿守着她</p><p>她房里又脏又乱满地头发</p><p>手机里都是男人未接电话</p><p>但她说这就是她</p><p>谁叫我爱上她没有办法</p><p>怎么办&nbsp; 我真的没有办法</p><p>怎么办&nbsp; 我依然爱着她</p><p>虽然她&nbsp; 总是满嘴脏话</p><p>从不洗衣做饭</p><p>生活糜烂像个破麻</p><p>怎么办&nbsp; 是真的没有办法</p><p>怎么办&nbsp; 还依然很爱她</p><p>虽然她&nbsp; 拜金虚荣放荡</p><p>菸酒不离四处为家</p><p>我依然守着她</p><p>守着她</p><p>我依然守着她</p><p>守着她</p>', 'https://y.gtimg.cn/music/photo_new/T002R300x300M0000040WSs24YVv3S.jpg?max_age=2592000', b'0', '2019-07-21 17:51:58', '正常', '音乐', 4, '作曲 : 黄明志作词 : 黄明志编曲人:', '怎么办  黄明志 / 动力火车'),
	(22, '1037671592@qq.com', '五水硫酸铜', 2, b'0', '<p>你们看不见</p>', '0', b'1', '2019-07-21 17:52:39', '正常', '旅游', 0, '你们看不见', '牛皮的帖子'),
	(23, '1037671592@qq.com', '五水硫酸铜', 2, b'0', '<p><span style="">全国爱牙日</span></p><p><span style="">爱牙日年年有</span></p><p><span style="">今年又双叒叕来了！</span></p><p><span style="">1989年起</span></p><p><span style=""><span style="">每年的9月20日被定为“</span><span style="">全国爱牙日</span><span style="">”</span></span></p><p><img src="http://5b0988e595225.cdn.sohucs.com/images/20180920/91a622d7e9a24034914f0e11a3c190b1.gif"></p><p>牙齿问题影响全身健康</p>', 'http://5b0988e595225.cdn.sohucs.com/images/20180920/91a622d7e9a24034914f0e11a3c190b1.gif', b'0', '2019-07-21 17:55:57', '正常', '美食', 0, '全国爱牙日爱牙日年年有今年又双叒叕来了！', '多洗牙、少拔牙、常漱口，这些你以为的护牙常识可能都错了…'),
	(24, '1484850996@qq.com', '梦蝶儿', 8, b'0', '<p><img src="https://b-ssl.duitang.com/uploads/item/201704/05/20170405112756_BiXdc.thumb.700_0.jpeg" width="400" height="384.57142857142856"><br></p><p>要结束啦哈哈哈哈哈哈哈哈哈哈哈</p>', 'https://b-ssl.duitang.com/uploads/item/201704/05/20170405112756_BiXdc.thumb.700_0.jpeg', b'0', '2019-07-21 18:57:43', '正常', '动漫', 0, '要结束啦哈哈哈哈哈哈哈哈哈哈哈', '实习妈个叽'),
	(25, '1194688236@qq.com', 'BadlySad', 11, b'0', '<p><img alt="Image" src="http://mmbiz.qpic.cn/mmbiz_jpg/K1G4Rz9uHkFOcWxhaibTLREXXmQJ8kpdNMogSAl5OJ6sqicTBPiaDXm7l7jPaGDJMzREa3NwacicKe6xoA64eI2uTQ/640?wx_fmt=jpeg" width="500" height="375.3475440222428"><br></p><p>Ave Maria grazia ricevuta per la mia famiglia</p><p>Con risentito con un\'amorevole divino amen</p><p>Grazie chiedo a te o signore divino</p><p>In questo giorno di grazia prego per te</p><p>Ave Maria piena di grazia</p><p>Il signore e con te</p><p>Sia fatta la tua volonta</p><p>Così in cielo e così in terra neil nome</p><p>Del padre del figliolo e dello spirito santo amen</p><p>微凉的晨露 沾湿黑礼服</p><p>石板路有雾 父在低诉</p><p>无奈的觉悟 只能更残酷</p><p>一切都为了通往圣堂的路</p><p>吹不散的雾 隐没了意图</p><p>谁轻柔踱步 停住</p><p>还来不及哭</p><p>穿过的子弹就带走温度</p><p>我们每个人都有罪</p><p>犯着不同的罪</p><p>我能决定谁对</p><p>谁又该要沉睡</p><p>争论不能解决</p><p>在永无止境的夜</p><p>关掉你的嘴</p><p>唯一的恩惠</p><p>挡在前面的人都有罪</p><p>后悔也无路可退</p><p>以父之名判决</p><p>那感觉没有适合字汇</p><p>就像边笑边掉泪</p><p>凝视着完全的黑</p><p>阻挡悲剧蔓延的悲剧会让我沉醉</p><p>低头亲吻我的左手</p><p>换取被宽恕的承诺</p><p>老旧管风琴在角落</p><p>一直一直一直伴奏</p><p>黑色帘幕被风吹动</p><p>阳光无言地穿透</p><p>洒向那群被我驯服后的兽</p><p>沉默地喊叫 沉默地喊叫</p><p>孤单开始发酵</p><p>不停对着我嘲笑</p><p>回忆逐渐延烧</p><p>曾经纯真的画面</p><p>残忍地温柔出现</p><p>脆弱时间到</p><p>我们一起来祷告</p><p>仁慈的父我已坠入</p><p>看不见罪的国度</p><p>请原谅我的自负</p><p>Ah ya ya check it check it ah ya</p><p>没人能说没人可说</p><p>好难承受</p><p>荣耀的背后刻着一道孤独</p><p>Ah ya ya check it check it ah ya</p><p>闭上双眼我又看见</p><p>当年那梦的画面</p><p>天空是蒙蒙的雾</p><p>Ah ya ya check it check it ah ya</p><p>父亲牵着我的双手</p><p>轻轻走过</p><p>清晨那安安静静的石板路</p><p>Ah ya ya check it check it ah ya</p><p>Pie Jesu</p><p>Qui tollis peccata</p><p>Dona eis requiem</p><p>低头亲吻我的左手</p><p>换取被宽恕的承诺</p><p>老旧管风琴在角落</p><p>一直一直一直伴奏</p><p>黑色帘幕被风吹动</p><p>阳光无言地穿透</p><p>洒向那群被我驯服后的兽</p><p>沉默地喊叫 沉默地喊叫</p><p>孤单开始发酵</p><p>不停对着我嘲笑</p><p>回忆逐渐延烧</p><p>曾经纯真的画面</p><p>残忍地温柔出现</p><p>脆弱时间到</p><p>我们一起来祷告</p><p>仁慈的父我已坠入</p><p>看不见罪的国度</p><p>请原谅我的自负</p><p>Ah ya ya check it check it ah ya</p><p>没人能说没人可说</p><p>好难承受</p><p>荣耀的背后刻着一道孤独</p><p>Ah ya ya check it check it ah ya</p><p>仁慈的父 我已坠入</p><p>看不见罪的国度</p><p>请原谅我 我的自负</p><p>刻着一道孤独</p><p>仁慈的父我已坠入</p><p>看不见罪的国度</p><p>请原谅我的自负</p><p>Ah ya ya check it check it ah ya</p><p>没人能说没人可说</p><p>好难承受</p><p>荣耀的背后刻着一道孤独</p><p>Ah ya ya check it check it ah ya</p><p>那斑驳的家徽 我擦拭了一夜 (闭上双眼我又看见)</p><p>孤独的光辉 我才懂的感觉 (当年那梦的画面)</p><p>烛光 不 不 停的摇晃 (天空是蒙蒙的雾)</p><p>猫头鹰在窗棂上</p><p>对着远方眺望</p><p>通向大厅的长廊 (父亲牵着我的双手)</p><p>一样 说不出的沧桑 (轻轻走过)</p><p>没有喧嚣 只有宁静围绕 (清晨那安安静静的石板路)</p><p>我 慢慢睡着</p><p>天 刚刚破晓</p>', 'http://mmbiz.qpic.cn/mmbiz_jpg/K1G4Rz9uHkFOcWxhaibTLREXXmQJ8kpdNMogSAl5OJ6sqicTBPiaDXm7l7jPaGDJMzREa3NwacicKe6xoA64eI2uTQ/640?wx_fmt=jpeg', b'0', '2019-07-21 23:31:42', '正常', '音乐', 1, 'Ave Maria grazia ric', '以父之名'),
	(26, '1194688236@qq.com', 'BadlySad', 5, b'0', '<p><img alt="Image" src="http://img4.imgtn.bdimg.com/it/u=2655126472,3009420676&amp;fm=26&amp;gp=0.jpg" width="500" height="333"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p><p>&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; </p><p><span style="color: rgb(51, 51, 51);">止战之殇</span></p><p><span style="color: rgb(51, 51, 51);">作词：</span><a href="https://www.baidu.com/s?wd=%E6%96%B9%E6%96%87%E5%B1%B1&amp;tn=SE_PcZhidaonwhc_ngpagmjz&amp;rsv_dl=gh_pc_zhidao" target="_blank">方文山</a><span style="color: rgb(51, 51, 51);">&nbsp;作曲：</span><a href="https://www.baidu.com/s?wd=%E5%91%A8%E6%9D%B0%E4%BC%A6&amp;tn=SE_PcZhidaonwhc_ngpagmjz&amp;rsv_dl=gh_pc_zhidao" target="_blank">周杰伦</a></p><p><span style="color: rgb(51, 51, 51);">演唱：</span><a href="https://www.baidu.com/s?wd=%E5%91%A8%E6%9D%B0%E4%BC%A6&amp;tn=SE_PcZhidaonwhc_ngpagmjz&amp;rsv_dl=gh_pc_zhidao" target="_blank">周杰伦</a></p><p><span style="color: rgb(51, 51, 51);">光 轻如纸张 光 散落地方</span></p><p><span style="color: rgb(51, 51, 51);">光 在掌声渐息中他慌忙</span></p><p><span style="color: rgb(51, 51, 51);">他在传唱 不堪的伤</span></p><p><span style="color: rgb(51, 51, 51);">脚本在台上 演出最后一场</span></p><p><span style="color: rgb(51, 51, 51);">而全村人们在座位上</span></p><p><span style="color: rgb(51, 51, 51);">静静的看时间如何遗弃这剧场</span></p><p><span style="color: rgb(51, 51, 51);">战火弄脏他的泪光 谁在风中吵着吃糖</span></p><p><span style="color: rgb(51, 51, 51);">这故事一开始的镜头灰尘就已经遮蔽了阳光</span></p><p><span style="color: rgb(51, 51, 51);">恐惧刻在孩子们脸上</span></p><p><span style="color: rgb(51, 51, 51);">麦田已倒向战车经过的方向</span></p><p><span style="color: rgb(51, 51, 51);">蒲公英的形状在飘散 它绝望的飞翔</span></p><p><span style="color: rgb(51, 51, 51);">她只唱只想这首止战之殇</span></p><p><span style="color: rgb(51, 51, 51);">恶夜燃烛光 天破息战乱</span></p><p><span style="color: rgb(51, 51, 51);">殇歌传千里 家乡平饥荒</span></p><p><span style="color: rgb(51, 51, 51);">天真在这条路上 跌跌撞撞</span></p><p><span style="color: rgb(51, 51, 51);">他被芒草割伤</span></p><p><span style="color: rgb(51, 51, 51);">孩子们眼中的希望 是什么形状</span></p><p><span style="color: rgb(51, 51, 51);">是否醒来有面包跟早餐 再喝碗浓汤</span></p><p><span style="color: rgb(51, 51, 51);">农夫们烧毁土地跟村庄终于拿起枪</span></p><p><span style="color: rgb(51, 51, 51);">他却慢慢习惯放弃了抵抗</span></p><p><span style="color: rgb(51, 51, 51);">孩子们眼中的希望 是什么形状</span></p><p><span style="color: rgb(51, 51, 51);">是否院子有秋千可以荡 口袋里有糖</span></p><p><span style="color: rgb(51, 51, 51);">刺刀的光被仇恨所擦亮在远方野蛮</span></p><p><span style="color: rgb(51, 51, 51);">而他却微笑着不知道慌张</span></p><p><span style="color: rgb(51, 51, 51);">恐惧刻在孩子们脸上</span></p><p><span style="color: rgb(51, 51, 51);">麦田已倒向战车经过的方向</span></p><p><span style="color: rgb(51, 51, 51);">蒲公英的形状在飘散它绝望的飞翔</span></p><p><span style="color: rgb(51, 51, 51);">她只唱只想这首止战之殇</span></p><p><span style="color: rgb(51, 51, 51);">恶夜燃烛光 天破息战乱</span></p><p><span style="color: rgb(51, 51, 51);">家乡平饥荒 殇歌传千里</span></p><p><span style="color: rgb(51, 51, 51);">天真在这条路上 跌跌撞撞</span></p><p><span style="color: rgb(51, 51, 51);">他被芒草割伤</span></p><p><span style="color: rgb(51, 51, 51);">孩子们眼中的希望 是什么形状</span></p><p><span style="color: rgb(51, 51, 51);">是否醒来有面包跟早餐 再喝碗浓汤</span></p><p><span style="color: rgb(51, 51, 51);">农夫们烧毁土地跟村庄终于拿起枪</span></p><p><span style="color: rgb(51, 51, 51);">他却慢慢习惯放弃了抵抗</span></p><p><span style="color: rgb(51, 51, 51);">孩子们眼中的希望 是什么形状</span></p><p><span style="color: rgb(51, 51, 51);">是否院子有秋千可以荡 口袋里有糖</span></p><p><span style="color: rgb(51, 51, 51);">刺刀的光被仇恨所擦亮在远方野蛮</span></p><p><span style="color: rgb(51, 51, 51);">而他却微笑着不知道慌张</span></p><p><span style="color: rgb(51, 51, 51);">孩子们眼中的希望 是什么形状</span></p><p><span style="color: rgb(51, 51, 51);">是否醒来有面包跟早餐(天真在这条路上)</span></p><p><span style="color: rgb(51, 51, 51);">再喝碗浓汤(跌跌撞撞 他被芒草割伤)</span></p><p><span style="color: rgb(51, 51, 51);">农夫们烧毁土地跟村庄终于拿起枪</span></p><p><span style="color: rgb(51, 51, 51);">他却慢慢习惯(天真在这条路上)</span></p><p><span style="color: rgb(51, 51, 51);">放弃了抵抗(跌跌撞撞)</span></p><p><span style="color: rgb(51, 51, 51);">他被芒草割伤</span></p><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</p>', 'http://img4.imgtn.bdimg.com/it/u=2655126472,3009420676&fm=26&gp=0.jpg', b'0', '2019-07-21 23:43:43', '正常', '音乐', 0, ' &nbsp; &nbsp; &nbsp', '止战之殇'),
	(27, '1194688236@qq.com', 'BadlySad', 4, b'0', '<p><img alt="Image" src="http://p.store.itangyuan.com/p/book/cover/4B6UEBMue-/Eg6SE_2Se_EWEtju4gbseTuHECmIjMq3jA.jpg" width="640" height="400"><br></p><p><a href="https://www.baidu.com/s?wd=%E5%A4%9C%E7%9A%84%E7%AC%AC%E4%B8%83%E7%AB%A0&amp;tn=SE_PcZhidaonwhc_ngpagmjz&amp;rsv_dl=gh_pc_zhidao" target="_blank">夜的第七章</a></p><p><span style="color: rgb(51, 51, 51);">1983年小巷12月晴朗</span></p><p><span style="color: rgb(51, 51, 51);">打字机继续推向</span></p><p><span style="color: rgb(51, 51, 51);">接近事实的那下一行</span></p><p><span style="color: rgb(51, 51, 51);">石楠烟斗的雾 飘向枯萎的树</span></p><p><span style="color: rgb(51, 51, 51);">沉默的对我哭诉</span></p><p><span style="color: rgb(51, 51, 51);">贝克街旁的圆形广场</span></p><p><span style="color: rgb(51, 51, 51);">盔甲骑士背上</span></p><p><span style="color: rgb(51, 51, 51);">鸢尾花的徽章 微亮</span></p><p><span style="color: rgb(51, 51, 51);">无人马车声响</span></p><p><span style="color: rgb(51, 51, 51);">深夜的拜访</span></p><p><span style="color: rgb(51, 51, 51);">邪恶 在</span><a href="https://www.baidu.com/s?wd=%E7%BB%B4%E5%A4%9A%E5%88%A9%E4%BA%9A&amp;tn=SE_PcZhidaonwhc_ngpagmjz&amp;rsv_dl=gh_pc_zhidao" target="_blank">维多利亚</a><span style="color: rgb(51, 51, 51);">的月光下</span></p><p><span style="color: rgb(51, 51, 51);">血色的开场</span></p><p><span style="color: rgb(51, 51, 51);">消失的手枪 焦黑的手杖 融化的蜡像</span></p><p><span style="color: rgb(51, 51, 51);">谁不在场 珠宝箱上 符号的假象</span></p><p><span style="color: rgb(51, 51, 51);">矛盾通往 他堆砌的死巷</span></p><p><span style="color: rgb(51, 51, 51);">证据被完美埋葬</span></p><p><span style="color: rgb(51, 51, 51);">那嘲弄苏格兰警场 的嘴角上扬</span></p><p><span style="color: rgb(51, 51, 51);">女声：如果邪恶是华丽残酷的乐章</span></p><p><span style="color: rgb(51, 51, 51);">（那么正义是深沉无奈的惆怅）</span></p><p><span style="color: rgb(51, 51, 51);">女声：它的终场我会亲手写上</span></p><p><span style="color: rgb(51, 51, 51);">（那我就点亮在灰烬中的微光）</span></p><p><span style="color: rgb(51, 51, 51);">女声：晨曦的光 风吹干最后一行忧伤</span></p><p><span style="color: rgb(51, 51, 51);">（那么雨滴 会洗净黑暗的高墙）</span></p><p><span style="color: rgb(51, 51, 51);">女声：黑色的墨 染上安详</span></p><p><span style="color: rgb(51, 51, 51);">（散场灯关上 红色的布幕下降）</span></p><p><span style="color: rgb(51, 51, 51);">事实只能穿向 没有脚印的土壤</span></p><p><span style="color: rgb(51, 51, 51);">突兀的细微花香 刻意显眼的服装</span></p><p><span style="color: rgb(51, 51, 51);">每个人为不同的理由戴着面具说谎</span></p><p><span style="color: rgb(51, 51, 51);">动机也只有一种名字那叫做欲望</span></p><p><span style="color: rgb(51, 51, 51);">fafafafadefafa</span></p><p><span style="color: rgb(51, 51, 51);">dedefafadefafa</span></p><p><span style="color: rgb(51, 51, 51);">越过人性的沼泽 谁真的刻意不被弄脏</span></p><p><span style="color: rgb(51, 51, 51);">我们可以 遗忘 原谅</span></p><p><span style="color: rgb(51, 51, 51);">但必须知道真相</span></p><p><span style="color: rgb(51, 51, 51);">被移动过的铁床</span></p><p><span style="color: rgb(51, 51, 51);">那最后一块图终于拼上</span></p><p><span style="color: rgb(51, 51, 51);">我听见脚步声 预料的软皮鞋跟</span></p><p><span style="color: rgb(51, 51, 51);">他推开门晚风晃了煤油灯一阵</span></p><p><span style="color: rgb(51, 51, 51);">打字机停在凶手的名称我转身</span></p><p><span style="color: rgb(51, 51, 51);">西敏寺的夜空 开始沸腾</span></p><p><span style="color: rgb(51, 51, 51);">在胸口绽放 艳丽的死亡</span></p><p><span style="color: rgb(51, 51, 51);">我品尝着最后一口甜美的真相</span></p><p><span style="color: rgb(51, 51, 51);">微笑回想正义只是安静的伸张</span></p><p><span style="color: rgb(51, 51, 51);">提琴在泰晤士</span></p><p><span style="color: rgb(51, 51, 51);">女声：如果邪恶是华丽残酷的乐章</span></p><p><span style="color: rgb(51, 51, 51);">女声：它的终场我会亲手写上</span></p><p><span style="color: rgb(51, 51, 51);">（打字机停在凶手的名称我转身）</span></p><p><span style="color: rgb(51, 51, 51);">（西敏寺的夜空 开始沸腾）</span></p><p><span style="color: rgb(51, 51, 51);">黑色的墨 染上安详</span></p><p><span style="color: rgb(51, 51, 51);">如果邪恶是华丽残酷的乐章</span></p><p><span style="color: rgb(51, 51, 51);">它的终场我会亲手写上</span></p><p><span style="color: rgb(51, 51, 51);">晨曦的光 风吹干最后一行忧伤</span></p><p><span style="color: rgb(51, 51, 51);">黑色的墨 染上安详</span></p>', 'http://p.store.itangyuan.com/p/book/cover/4B6UEBMue-/Eg6SE_2Se_EWEtju4gbseTuHECmIjMq3jA.jpg', b'0', '2019-07-21 23:48:33', '正常', '音乐', 1, '夜的第七章1983年小巷12月晴朗打字机', '威廉古堡');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.report
CREATE TABLE IF NOT EXISTS `report` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `report_reason` varchar(255) DEFAULT NULL,
  `report_time` datetime DEFAULT NULL,
  `report_type` varchar(255) DEFAULT NULL,
  `reported_link` varchar(255) DEFAULT NULL,
  `review_reason` varchar(255) DEFAULT NULL,
  `review_result` bit(1) NOT NULL,
  `review_time` datetime DEFAULT NULL,
  `reviewer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.report: ~0 rows (approximately)
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.section
CREATE TABLE IF NOT EXISTS `section` (
  `section_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `section_name` varchar(255) DEFAULT NULL,
  `section_post_number` varchar(255) DEFAULT NULL,
  `section_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.section: ~5 rows (approximately)
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT IGNORE INTO `section` (`section_id`, `create_time`, `description`, `section_name`, `section_post_number`, `section_status`) VALUES
	(1, '2019-07-17 04:56:09', '只要对国家有利，即使牺牲自己生命也心甘情愿，绝不会因为自己可能受到祸害而躲开。', '膜', '0', '正常'),
	(2, '2019-07-18 04:56:09', '音乐可以分为声乐和器乐两大类型，又可以粗略的分为古典音乐、民间音乐、原生态音乐、现代音乐（包括流行音乐）等。', '音乐', '0', '正常'),
	(3, '2019-07-18 18:33:59', '动漫，即动画、漫画的合称，指动画与漫画的集合，取这两个词的第一个字合二为一称之为“动漫”，与游戏无关，并非专业术语。', '动漫', '0', '正常'),
	(4, '2019-07-17 12:56:09', '“旅”是旅行，外出，即为了实现某一目的而在空间上从甲地到乙地的行进过程；“游”是外出游览、观光、娱乐，即为达到这些目的所作的旅行。二者合起来即旅游。所以，旅行偏重于行，旅游不但有“行”，且有观光、娱乐含义。', '旅游', '0', '正常'),
	(5, '2019-07-18 08:32:00', '美食，顾名思义就是美味的食物，贵的有山珍海味，便宜的有街边小吃。其实美食是不分贵贱的，只要是自己喜欢的，都可以称之为美食。', '美食', '0', '正常');
/*!40000 ALTER TABLE `section` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.star
CREATE TABLE IF NOT EXISTS `star` (
  `star_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `post_title` varchar(255) DEFAULT NULL,
  `star_time` datetime DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_nick_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`star_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.star: ~20 rows (approximately)
/*!40000 ALTER TABLE `star` DISABLE KEYS */;
INSERT IGNORE INTO `star` (`star_id`, `post_id`, `post_title`, `star_time`, `user_email`, `user_nick_name`) VALUES
	(1, 1, '我们要有自己的判断', '2019-07-21 11:47:10', 'a648186412@gmail.com', '多洗牙'),
	(2, 1, '我们要有自己的判断', '2019-07-21 12:03:07', '1500896297@qq.com', 'Forevery'),
	(3, 1, '我们要有自己的判断', '2019-07-21 11:55:12', '2639682042@qq.com', 'mortal'),
	(4, 1, '我们要有自己的判断', '2019-07-21 12:53:33', '651858890@qq.com', '大哥'),
	(5, 13, '鱼香肉丝', '2019-07-21 17:48:46', '648186412@qq.com', '多洗牙'),
	(6, 13, '鱼香肉丝', '2019-07-21 15:11:57', '2639682042@qq.com', 'mortal'),
	(7, 18, 'sdskdk', '2019-07-21 17:49:03', '2639682042@qq.com', 'mortal'),
	(8, 15, 'GitHub-996项目被程序员霸榜', '2019-07-21 17:50:38', '2639682042@qq.com', 'mortal'),
	(9, 13, '鱼香肉丝', '2019-07-21 18:25:17', '1194688236@qq.com', 'BadlySad'),
	(12, 21, '怎么办  黄明志 / 动力火车', '2019-07-21 18:15:45', '1194688236@qq.com', 'BadlySad'),
	(13, 21, '怎么办  黄明志 / 动力火车', '2019-07-21 17:54:07', '1037671592@qq.com', '五水硫酸铜'),
	(14, 21, '怎么办  黄明志 / 动力火车', '2019-07-21 19:03:39', '1484850996@qq.com', '梦蝶儿'),
	(15, 13, '鱼香肉丝', '2019-07-21 18:53:44', '1484850996@qq.com', '梦蝶儿'),
	(16, 7, '董先生连任好不好', '2019-07-21 19:48:37', '1037671592@qq.com', '五水硫酸铜'),
	(17, 8, '林则徐名言', '2019-07-21 17:59:11', '1037671592@qq.com', '五水硫酸铜'),
	(18, 19, '奇妙的约会', '2019-07-21 17:49:15', '648186412@qq.com', '多洗牙'),
	(19, 19, '奇妙的约会', '2019-07-21 17:45:17', '1037671592@qq.com', '五水硫酸铜'),
	(20, 21, '怎么办  黄明志 / 动力火车', '2019-07-21 23:32:51', '648186412@qq.com', '多洗牙'),
	(21, 25, '以父之名', '2019-07-21 23:49:41', '1194688236@qq.com', 'BadlySad'),
	(22, 27, '威廉古堡', '2019-07-22 09:33:35', '1194688236@qq.com', 'BadlySad');
/*!40000 ALTER TABLE `star` ENABLE KEYS */;

-- Dumping structure for table GPF_dev.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_email` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `hobby` varchar(255) DEFAULT NULL,
  `last_log_time` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `reg_time` datetime DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `this_log_time` datetime DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_post_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table GPF_dev.user: ~13 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT IGNORE INTO `user` (`user_email`, `avatar`, `birthday`, `hobby`, `last_log_time`, `location`, `nick_name`, `permission`, `reg_time`, `sex`, `signature`, `this_log_time`, `user_password`, `user_post_number`) VALUES
	('1037671592@qq.com', 'http://49.234.61.134:7999/cd686a8a-0483-47d3-9855-efbbd1432a040.jpg', '2012-12-12', '音乐 动漫 读书 数码 ', NULL, '重庆市渝北区', '五水硫酸铜', NULL, '2019-07-21 12:06:42', '男', '哈哈哈', '2019-07-21 12:06:42', '18138372fad4b94533cd4881f03dc6c69296dd897234e0cee83f727e2e6b1f63', NULL),
	('1194688236@qq.com', 'http://49.234.61.134:7999/f0a17252-8f53-455d-889e-9fc2c6a6aab8image4.jpg', '1998-07-24', '音乐 电影 动漫 ', NULL, '安徽省滁州市', 'BadlySad', NULL, '2019-07-21 11:10:41', '男', '一杯茶，一包烟，一行代码敲一天', '2019-07-21 11:10:41', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL),
	('1484850996@qq.com', 'http://49.234.61.134:7999/303ca298-76e7-4b43-8d83-a505472094e9房子.png', '1997-09-21', '音乐 电影 动漫 美食 ', NULL, '重庆市铜梁县', '梦蝶儿', NULL, '2019-07-21 14:58:57', '女', '此生此夜不长好，明月明年何处看。', '2019-07-21 14:58:57', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL),
	('1493357007@qq.com', '/img/no_avatar.png', NULL, NULL, NULL, NULL, '1493357007@qq.com', NULL, '2019-07-22 09:41:03', NULL, NULL, '2019-07-22 09:41:03', 'e205e20b25b63ac3048c6351965b67a7e89148f6302a3a652fbdd250da4cd51d', NULL),
	('1500896297@qq.com', '/img/no_avatar.png', '2019-07-16', '音乐 电竞 ', NULL, '重庆市大渡口区', 'Forevery', NULL, '2019-07-21 11:18:23', '男', '只好色不花心', '2019-07-21 11:18:23', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', NULL),
	('2639682042@qq.com', 'http://49.234.61.134:7999/e8b6f284-0d38-4911-8d3b-2941eba2708dtest2.png', '2019-07-21', '音乐 动漫 电竞 ', NULL, '陕西省渭南市', 'mortal', NULL, '2019-07-21 11:30:10', '男', '123456', '2019-07-21 11:30:10', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL),
	('3134696053@qq.com', '/img/no_avatar.png', NULL, NULL, NULL, NULL, '3134696053@qq.com', NULL, '2019-07-21 17:37:56', NULL, NULL, '2019-07-21 17:37:56', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL),
	('3266300848@qq.com', '/img/no_avatar.png', NULL, NULL, NULL, NULL, '3266300848@qq.com', NULL, '2019-07-22 09:41:44', NULL, NULL, '2019-07-22 09:41:44', 'e38d4d79fcda5f1b5eb1fc3df0915054d6c29e20d5ba97f87ffdf1d07fe2229c', NULL),
	('583175160@qq.com', 'http://49.234.61.134:7999/9a68c46d-796b-420d-a5ca-39a55c640c73image4.jpg', '2019-07-11', '音乐 动漫 电竞 读书 ', NULL, '上海市徐汇区', 'sda', NULL, '2019-07-22 09:02:30', '男', 'sda', '2019-07-22 09:02:30', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL),
	('648186412@qq.com', 'http://49.234.61.134:7999/ad8315ee-8701-4597-85b1-2b6ba4862162dosia.jpg', '1998-10-16', '音乐 电影 动漫 读书 摄影 美食 ', NULL, '重庆市渝中区', '多洗牙', NULL, '2019-07-21 13:24:12', '男', '多洗牙有益健康', '2019-07-21 13:24:12', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', NULL),
	('651858890@qq.com', 'http://49.234.61.134:7999/c043eb2a-3795-40c6-a80c-2c0e9fa7722cc.jpg', '1997-09-07', '音乐 电影 动漫 电竞 ', NULL, '重庆市彭水苗族土家族自治县', '大哥', NULL, '2019-07-21 11:11:17', '男', '钢铁侠！！！！', '2019-07-21 11:11:17', 'fbfb386efea67e816f2dda0a8c94a98eb203757aebb3f55f183755a192d44467', NULL),
	('a648186412@gmail.com', 'http://49.234.61.134:7999/e62df1ee-5908-4f69-bc0a-f061feccd01ano_avatar.png', '', '电影 电竞 数码 摄影 美食 ', NULL, '重庆市渝中区', '多洗牙', NULL, '2019-07-21 11:17:26', '男', 'hello : )', '2019-07-21 11:17:26', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', NULL),
	('rainfall0212@gmail.com', '/img/no_avatar.png', NULL, NULL, NULL, NULL, 'rainfall0212@gmail.com', NULL, '2019-07-22 09:37:05', NULL, NULL, '2019-07-22 09:37:05', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
