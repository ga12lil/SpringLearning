package ga12.domain;


import java.time.OffsetDateTime;

public record LinkEntity(Long id, String url, OffsetDateTime updatedAt) {
}
