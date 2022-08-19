package com.doger.nacosorder.dto;

import lombok.Data;

@Data
public class APIResponse<T> {

    private String code;

    private String message;

    private T data;


    public APIResponse(){
        new APIResponse(null,null);
    }


    public APIResponse(String code, String message){
        new APIResponse(code,message,null);
    }

    public APIResponse(String code, String message, T data){
        this.code=code;
        this.message=message;
        this.data=data;

    }

    public static APIResponse ok() {
        APIResponse response = new APIResponse();
        response.setCode("000000");
        response.setMessage("success");
        return response;
    }
    public static APIResponse ok(Object data) {
        APIResponse response = new APIResponse();
        response.setCode("000000");
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    public static APIResponse fail() {
        APIResponse response = new APIResponse();
        response.setCode("000001");
        response.setMessage("fail");
        return response;
    }

    public static APIResponse fail(String message) {
        APIResponse response = new APIResponse();
        response.setCode("000001");
        response.setMessage(message);
        return response;
    }
    public static APIResponse fail(String message,Object data) {
        APIResponse response = new APIResponse();
        response.setCode("000001");
        response.setMessage(message);
        response.setData(data);
        return response;
    }


}
