package ga12.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {
    @Bean
    Long schedulerIntervalMs(ApplicationConfig applicationConfig) {
        return applicationConfig.schedulerInterval().toMillis();
    }
}
