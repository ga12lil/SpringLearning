package ga12.TgBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateProcessorChain {
    private final List<UpdateProcessor> updateProcessorList;
    private final TelegramBot telegramBot;

    public void process(Update update) {
        for (UpdateProcessor updateProcessor : updateProcessorList) {
            var processResponse = updateProcessor.tryProcess(update);
            if (processResponse.isPresent()) {
                telegramBot.execute(processResponse.get());
                return;
            }
        }
    }
}
