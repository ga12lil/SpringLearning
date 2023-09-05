package ga12;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import ga12.TgBot.Command.ListCommand;
import ga12.dto.links.LinkResponse;
import ga12.dto.links.ListLinksResponse;
import ga12.httpclient.ScrapperClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.net.URI;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommandsTest {
    @Mock
    ScrapperClient scrapperClient;

    @Test
    void listCommandWhenEmpty_shouldReturnSpecialMessageTest() {
        //given
        var command = new ListCommand(scrapperClient);
        var update = new Update();
        var message = new Message();
        var chat = new Chat();
        ReflectionTestUtils.setField(update, "message", message);
        ReflectionTestUtils.setField(message, "chat", chat);
        ReflectionTestUtils.setField(chat, "id", 1L);
        when(scrapperClient.getAllLinks(any())).thenReturn(new ListLinksResponse(null, 0));

        //when
        var processResponse = command.process(update);

        //then
        assertAll("Assert process response",
                () -> assertThat(processResponse.getParameters().get("text")).isEqualTo("Ничего не отслеживается! Используй /track, чтобы добавить ссылки"));
    }

    @Test
    void listCommandWhenHasElements_thenReturnThem() {
        //given
        var command = new ListCommand(scrapperClient);
        var link = new LinkResponse(1L, URI.create("https://test.com"));
        var link2 = new LinkResponse(2L, URI.create("https://test.com"));
        var linksResponse = new ListLinksResponse(List.of(link, link2), 2);

        var update = new Update();
        var message = new Message();
        var chat = new Chat();
        ReflectionTestUtils.setField(update, "message", message);
        ReflectionTestUtils.setField(message, "chat", chat);
        ReflectionTestUtils.setField(chat, "id", 1L);
        when(scrapperClient.getAllLinks(any())).thenReturn(linksResponse);

        //when
        var processResponse = command.process(update);

        //then
        assertThat(processResponse.getParameters().get("text")).isEqualTo(link.url().toString() + "\n" + link2.url().toString());
    }

}
