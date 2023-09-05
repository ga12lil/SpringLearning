package ga12.TgBot.Command.replyCommand;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ga12.dto.links.AddRemoveLinkRequest;
import ga12.httpclient.ScrapperClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
@RequiredArgsConstructor
public class UntrackCommand extends ReplyCommand{
    private final ScrapperClient scrapperClient;

    @Override
    public String name() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "Прекратить отслеживание ссылки";
    }

    @Override
    public String replyText() {
        return "Введите ссылку для удаления:";
    }

    @Override
    public SendMessage processReply(Update update) {
        String link = update.message().text();
        AddRemoveLinkRequest request = new AddRemoveLinkRequest(link);
        try {
            scrapperClient.removeLink(update.message().chat().id(), request);
        }
        catch (WebClientResponseException.NotFound ex) {
            return new SendMessage(update.message().chat().id(), "Ссылка не найдена");
        }
        catch (WebClientResponseException.BadRequest ex) {
            return new SendMessage(update.message().chat().id(), "При удалении произошла ошибка");
        }
        return new SendMessage(update.message().chat().id(), "Ссылка успешно удалена");
    }
}
