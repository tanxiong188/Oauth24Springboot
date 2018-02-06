package com.bestaone.springboot.oauth2.resource.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
public class ViewData implements Serializable{

    private static final long serialVersionUID = 7408790903212368997L;

    private Integer status = 1;

    private String message;

    private Object data;

    public ViewData(){}

    public ViewData(Object data) {
        this.data = data;
    }

    public ViewData(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ViewData(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ViewData ok() {
        return new ViewData();
    }

    public static ViewData ok(Object data) {
        return new ViewData(data);
    }

    public static ViewData error(String msg) {
        return new ViewData(-1, msg);
    }

    public static ViewData error(Integer code, String msg) {
        return new ViewData(code, msg);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}