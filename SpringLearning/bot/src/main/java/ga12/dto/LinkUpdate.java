package ga12.dto;

import java.util.List;

public record LinkUpdate(Integer id, String url, String description, List<Long> tgChatIds) {
}
