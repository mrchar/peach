package net.mrchar.peach.authorization.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.NonNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Schema(title = "可以包含消息的响应")
public class MessageResponse {
    @Schema(title = "响应码")
    @JsonProperty("code")
    private final String code;

    @Schema(title = "响应消息")
    @JsonProperty("message")
    @JsonInclude(NON_NULL)
    private final String message;

    public MessageResponse(@NonNull String code, String message) {
        this.code = code;
        this.message = message;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static MessageResponse success() {
        return success(null);
    }

    public static MessageResponse success(String message) {
        return new MessageResponse("success", message);
    }

    public static MessageResponse failure(String code, String message) {
        return new MessageResponse(code, message);
    }
}
