package ga12.TgBot.Command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface Command {
    String name();
    String description();
    SendMessage process(Update update);

}
