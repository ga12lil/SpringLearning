package ga12.controller;

import ga12.dto.GitHub.GitHubRepositoryResponse;
import ga12.httpclient.GitHubClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestGitHubRequest {

    private final GitHubClient gitHubClient;

    @GetMapping("/test1")
    public GitHubRepositoryResponse getRep(){
        return gitHubClient.fetchRepository("Corenlix","tinkoff-java-course");
    }
}
