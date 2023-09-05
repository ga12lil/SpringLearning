package ga12.configuration;

import ga12.httpclient.GitHubClient;
import ga12.httpclient.StackOverflowClient;
import ga12.httpclient.StackOverflowClientImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfiguration {
    @Bean
    GitHubClient gitHubClient(ApplicationConfig appConfig){
        WebClient client = WebClient
                .builder()
                .baseUrl(appConfig.githubApiPath().toString())
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client))
                .build();

        return factory.createClient(GitHubClient.class);
    }

    @Bean
    StackOverflowClient stackOverflowClient(ApplicationConfig applicationConfig){
        WebClient client = WebClient.builder()
                .baseUrl(applicationConfig.stackExchangeApiPath().toString())
                .build();

        return StackOverflowClientImplementation.builder()
                .webClient(client)
                .apiVersion(applicationConfig.stackOverflowApiVersion())
                .build();
    }
}
