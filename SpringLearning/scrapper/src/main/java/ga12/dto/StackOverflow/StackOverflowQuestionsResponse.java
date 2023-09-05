package ga12.dto.StackOverflow;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StackOverflowQuestionsResponse(
        @JsonProperty("items") List<StackOverflowQuestionResponse> questions
) {
}
