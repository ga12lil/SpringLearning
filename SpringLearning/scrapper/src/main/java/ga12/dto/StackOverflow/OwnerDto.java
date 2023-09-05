package ga12.dto.StackOverflow;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public record OwnerDto(
        @JsonProperty("account_id") Long accountId,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("profile_image") URI image,
        @JsonProperty("display_name") String name,
        @JsonProperty("link") URI link
        ) {
}
