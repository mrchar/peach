package net.mrchar.peach.authorization.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.NonNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Schema(title = "包含一个单例返回值的响应")
public class SingletonResponse<T> extends MessageResponse {
    @Schema(title = "返回内容")
    @JsonProperty("data")
    @JsonInclude(NON_NULL)
    private T data;

    public SingletonResponse(@NonNull String code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public static <T> SingletonResponse<T> success(T data) {
        return success("", data);
    }

    public static <T> SingletonResponse<T> success(String message, T data) {
        return new SingletonResponse<>("success", message, data);
    }

    public static <T> SingletonResponse<T> failure(String code, String message, T data) {
        return new SingletonResponse<>(code, message, data);
    }
}
