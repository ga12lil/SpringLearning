package ga12.httpclient;

import ga12.dto.StackOverflow.StackOverflowQuestionResponse;
import ga12.dto.StackOverflow.StackOverflowQuestionsResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.stream.Stream;

@Builder
@RequiredArgsConstructor
public class StackOverflowClientImplementation implements StackOverflowClient{

    private final String site = "stackoverflow";
    private final String apiVersion;
    private final WebClient webClient;
    @Override
    public StackOverflowQuestionResponse fetchQuestion(Long id) {
        return fetchQuestions(id).questions().get(0);
    }

    private StackOverflowQuestionsResponse fetchQuestions(Long...ids){
        String[] params = Stream.concat(
                        Stream.of("questions"),
                        Arrays.stream(ids).map(String::valueOf))
                .toArray(String[]::new);

        return getResponse(StackOverflowQuestionsResponse.class, params);
    }
    private <T> T getResponse(Class<T> responseClass, String... params) {

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment(apiVersion)
                        .pathSegment(params)
                        .queryParam("site", site)
                        .build())
                .retrieve()
                .bodyToMono(responseClass)
                .block();
    }
}
