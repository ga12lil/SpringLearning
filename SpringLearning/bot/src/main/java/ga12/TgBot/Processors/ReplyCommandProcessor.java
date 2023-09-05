package ga12.TgBot.Processors;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ga12.TgBot.Command.replyCommand.ReplyCommand;
import ga12.TgBot.UpdateProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@Order(2)
@RequiredArgsConstructor
public class ReplyCommandProcessor implements UpdateProcessor {
    private final List<ReplyCommand> replyCommands;

    @Override
    public Optional<SendMessage> tryProcess(Update update) {
        if (!isReply(update))
            return Optional.empty();

        for (ReplyCommand replyCommand : replyCommands) {
            if(replyCommand.canProcessReply(update)) {
                return Optional.of(replyCommand.processReply(update));
            }
        }

        return Optional.empty();
    }

    private boolean isReply(Update update) {
        return update.message().replyToMessage() != null
                && update.message().replyToMessage().from().isBot();
    }
}
