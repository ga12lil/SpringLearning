package ga12.dto.GitHub;

import java.net.URI;

public record GitHubUserResponse(
        String login,
        Long id,
        URI url
) {
}
