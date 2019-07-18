package com.internship.gpforum.configure;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UploadPhotoResult {
    private boolean success;
    private Object data;
    private String msg;

    public UploadPhotoResult(){

    }

    public UploadPhotoResult(boolean success, Object data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }
}

