package ga12.controller;



import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tg-chat/{id}")
public class TgChatController {
    @PostMapping
    public void registerChat(@PathVariable Long id) {
    }

    @DeleteMapping
    public void removeChat(@PathVariable Long id){
    }
}
