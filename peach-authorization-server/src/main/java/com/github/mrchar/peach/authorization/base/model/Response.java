package com.github.mrchar.peach.authorization.base.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.springframework.lang.NonNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Schema(title = "返回值")
@Data
public class Response<T> {
    @NonNull
    @Schema(title = "响应码")
    @JsonProperty("code")
    private String code;

    @Schema(title = "响应消息")
    @JsonProperty("message")
    @JsonInclude(NON_NULL)
    private String message;

    @Schema(title = "返回内容")
    @JsonProperty("data")
    @JsonInclude(NON_NULL)
    private T data;

    public Response(@NonNull String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Response<Null> success() {
        return success(null);
    }

    public static <T> Response<T> success(T data) {
        return success("", data);
    }

    public static <T> Response<T> success(String message, T data) {
        return new Response<>(CODE.SUCCESS.VALUE, message, data);
    }

    public static <T> Response<T> failure(String code, String message) {
        return failure(code, message, null);
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
