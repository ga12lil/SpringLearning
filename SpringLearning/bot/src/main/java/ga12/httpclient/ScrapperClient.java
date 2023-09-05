package ga12.httpclient;

import ga12.dto.links.AddRemoveLinkRequest;
import ga12.dto.links.LinkResponse;
import ga12.dto.links.ListLinksResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;

@HttpExchange
public interface ScrapperClient {
    @GetExchange("/links")
    ListLinksResponse getAllLinks(@RequestHeader(value="Tg-Chat-Id") Long tgChatId);

    @PostExchange("/links")
    LinkResponse addLink(@RequestHeader(value="Tg-Chat-Id") Long tgChatId, @RequestBody AddRemoveLinkRequest request);

    @DeleteExchange("/links")
    LinkResponse removeLink(@RequestHeader(value="Tg-Chat-Id") Long tgChatId, @RequestBody AddRemoveLinkRequest request);

    @PostExchange("/tg-chat/{id}")
    void registerChat(@PathVariable Long id);

    @DeleteExchange("/tg-chat/{id}")
    void removeChat(@PathVariable Long id);
}
