package ga12.TgBot.Command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import ga12.dto.links.ListLinksResponse;
import ga12.httpclient.ScrapperClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListCommand implements Command{
    private final ScrapperClient scrapperClient;
    @Override
    public String name() {
        return "/list";
    }

    @Override
    public String description() {
        return "Список отслеживаемых ссылок";
    }

    @Override
    public SendMessage process(Update update) {
        ListLinksResponse response = scrapperClient.getAllLinks(update.message().chat().id());
        if (response.size() == 0) {
            return new SendMessage(update.message().chat().id(), "Ничего не отслеживается! Используй /track, чтобы добавить ссылки")
                    .parseMode(ParseMode.HTML);
        } else {
            String[] links = response.items().stream().map(linkResponse -> linkResponse.url().toString()).toArray(String[]::new);
            return new SendMessage(update.message().chat().id(), String.join("\n", links));
        }
    }
}
