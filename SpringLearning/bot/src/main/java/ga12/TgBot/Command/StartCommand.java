package ga12.TgBot.Command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ga12.httpclient.ScrapperClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartCommand implements Command{
    private final ScrapperClient scrapperClient;

    @Override
    public String name() {
        return "/start";
    }

    @Override
    public String description() {
        return "Регистрация";
    }

    @Override
    public SendMessage process(Update update){
        scrapperClient.registerChat(update.message().chat().id());
        return new SendMessage(update.message().chat().id(), "Привет! Напиши /help чтобы получить список комманд");
    }
}
