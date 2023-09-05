package ga12.TgBot;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.Optional;

public interface UpdateProcessor {
    Optional<SendMessage> tryProcess(Update update);
}
