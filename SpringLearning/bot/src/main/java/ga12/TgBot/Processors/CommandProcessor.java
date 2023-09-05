package ga12.TgBot.Processors;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ga12.TgBot.Command.Command;
import ga12.TgBot.UpdateProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Order(1)
public class CommandProcessor implements UpdateProcessor {
    private final Map<String, Command> commands;

    public CommandProcessor(List<Command> commands){
        this.commands = commands.stream().collect(Collectors.toMap(Command::name, command -> command));
    }

    public Optional<SendMessage> tryProcess(Update update) {
        if(!isCommand(update))
            return Optional.empty();

        Command command = commands.get(update.message().text());
        SendMessage message = command != null ? command.process(update) : getUnknownCommandMessage(update);

        return Optional.of(message);
    }

    private boolean isCommand(Update update) {
        return update.message().text().startsWith("/");
    }

    private SendMessage getUnknownCommandMessage(Update update) {
        return new SendMessage(update.message().chat().id(), "Команда неизвестна");
    }
}
