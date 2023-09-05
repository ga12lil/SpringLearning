package ga12.controller;

import ga12.dto.LinkUpdate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updates")
public class UpdatesController {

    @PostMapping
    public void addUpdate(@RequestBody LinkUpdate linkUpdate){

    }
}
