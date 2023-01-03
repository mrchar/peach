package com.github.mrchar.peach.authorization.base.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
public class Response<T> {
    @JsonProperty("code")
    @JsonInclude(NON_NULL)
    private String code;
    @JsonProperty("message")
    @JsonInclude(NON_NULL)
    private String message;

    @JsonProperty("content")
    @JsonInclude(NON_NULL)
    private T data;

    public Response(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return success("", data);
    }

    public static <T> Response<T> success(String message, T data) {
        return new Response<>(CODE.SUCCESS.VALUE, message, data);
    }

    public static <T> Response<T> failure(String code, T data) {
        return failure(code, null, data);
    }

    public static <T> Response<T> failure(String code, String message, T data) {
        return new Response<>(code, message, data);
    }


    public enum CODE {
        SUCCESS("Success");

        public final String VALUE;

        CODE(String value) {
            VALUE = value;
        }
    }
}
