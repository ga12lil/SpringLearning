package ga12.TgBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SetMyCommands;
import ga12.TgBot.Command.Command;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TelegramUpdatesListener implements UpdatesListener {
    UpdateProcessorChain updateProcessorChain;
    public TelegramUpdatesListener(TelegramBot telegramBot, List<Command> commands, UpdateProcessorChain updateProcessorChain){
        this.updateProcessorChain = updateProcessorChain;
        telegramBot.setUpdatesListener(this);
        telegramBot.execute(buildSetCommandsRequest(commands));
    }

    public int process(List<Update> list){
        list.forEach(updateProcessorChain::process);
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    private SetMyCommands buildSetCommandsRequest(List<Command> commands) {
        BotCommand[] botCommands = commands.stream()
                .map(command -> new BotCommand(command.name(), command.description()))
                .toArray(BotCommand[]::new);
        return new SetMyCommands(botCommands);
    }
}
