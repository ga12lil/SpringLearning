package ga12.controller;

import ga12.dto.links.AddRemoveLinkRequest;
import ga12.dto.links.LinkResponse;
import ga12.dto.links.ListLinksResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/links")
public class LinksController {

    @GetMapping
    public ListLinksResponse getAllLinks(@RequestHeader(value = "Tg-Chat-Id") Long TgChatId){
        return new ListLinksResponse(Collections.emptyList(),0);
    }

    @PostMapping
    public LinkResponse addLink(@RequestHeader(value = "Tg-Chat-Id") Long TgChatId, @RequestBody AddRemoveLinkRequest request){
        return new LinkResponse(0L,request.link());
    }

    @DeleteMapping
    public LinkResponse removeLink(@RequestHeader(value = "Tg-Chat-Id") Long TgChatId, @RequestBody AddRemoveLinkRequest request){
        return new LinkResponse(0L, request.link());
    }
}
