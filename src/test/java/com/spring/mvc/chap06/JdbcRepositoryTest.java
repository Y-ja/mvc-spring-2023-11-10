package com.spring.mvc.chap06;

import com.spring.mvc.chap06.jdbc.JdbcRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JdbcRepositoryTest {
    @Autowired
    JdbcRepository repository;
    @Test
    @DisplayName("DB 접속성공ㅋ")
    void connectTest(){
        try {
            Connection connection = repository.getConnection();

            assertNotNull(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}