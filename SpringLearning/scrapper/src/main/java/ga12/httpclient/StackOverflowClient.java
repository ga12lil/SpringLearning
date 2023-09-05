package ga12.httpclient;

import ga12.dto.StackOverflow.StackOverflowQuestionResponse;

public interface StackOverflowClient {
    StackOverflowQuestionResponse fetchQuestion(Long id);
}
