package ga12.repository.jdbc;

import ga12.domain.ChatEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcChatRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<ChatEntity> rowMapper = new DataClassRowMapper<>(ChatEntity.class);

    private static final String ADD_QUERY = """
            INSERT INTO chat (id) VALUES (?)
            """;
    private static final String REMOVE_QUERY = """
            DELETE FROM chat
            WHERE id = ?
            """;
    private static final String FIND_ALL_QUERY = """
            SELECT id FROM chat
            """;

    public int add(Long id){
        return jdbcTemplate.update(ADD_QUERY,id);
    }

    public int removeById(Long id){
        return jdbcTemplate.update(REMOVE_QUERY,id);
    }
    public List<ChatEntity> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY,rowMapper);
    }

}
