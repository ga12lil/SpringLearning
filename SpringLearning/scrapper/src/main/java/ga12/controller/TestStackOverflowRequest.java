package ga12.controller;

import ga12.dto.StackOverflow.StackOverflowQuestionResponse;
import ga12.httpclient.StackOverflowClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestStackOverflowRequest {

    private final StackOverflowClient stackOverflowClient;

    @GetMapping("/test2")
    public StackOverflowQuestionResponse getResponse(){
        return stackOverflowClient.fetchQuestion(123L);
    }
}
