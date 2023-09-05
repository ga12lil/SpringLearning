package ga12.dto.links;

import java.util.List;

public record ListLinksResponse(List<LinkResponse> items, Integer size) {
}
