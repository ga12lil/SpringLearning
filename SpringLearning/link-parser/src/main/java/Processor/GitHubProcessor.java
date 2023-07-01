package Processor;

import Response.GitHubResponse;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URISyntaxException;

public class GitHubProcessor implements ParseProcessor{
    @Override
    public GitHubResponse parse(String link){
        try {
            URI uri = new URI(link);
            if (!isGitHubDomain(uri)) {
                return null;
            }

            return extractDataFromUri(uri);
        } catch (URISyntaxException e) {
            return null;
        }
    }
    private boolean isGitHubDomain(URI uri){
        return uri.getAuthority().equals("github.com");
    }
    @Nullable
    private GitHubResponse extractDataFromUri(URI uri){
        String[] pathSegments = uri.getPath().split("/");
        if (pathSegments.length != 3) {
            return null;
        }
        String username = pathSegments[1];
        String repositoryName = pathSegments[2];

        return new GitHubResponse(username, repositoryName);
    }
}
