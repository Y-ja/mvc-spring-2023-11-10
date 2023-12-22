package com.spring.mvc.chap06.spring_jdbc;

import com.spring.mvc.chap06.entity.Person;
import org.testng.annotations.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StringJdbcRepositoryTest {
    @Autowired
    StringJdbcRepository repository;
    @Test
    @DisplayName("사람정보를 데이터베이스에 저장")
    void saveTest () {
        //given
        Person p = new Person("99","말똥이",30);
        //when

        //then
    }
    @Test
    @DisplayName("사람정보를 데이터베이스에 저장")
    void modifyTest () {
        //given
        String id = "99";
        String newName = "수정수정이";
        int newAge = 50;
        Person p = new Person(id, newName, newAge);
        //when
        repository.modify(p);
        //then

    }

    @Test
    @DisplayName("99번을 삭제한다")
    void removeTest() {
        //given
        String id = "99";
        //when
        repository.remove(id);
        //then
    }
    @Test
    @DisplayName("99번을 삭제한다")
    void findTest() {
        //given

        //when
        List<Person> people = repository.findAll();
        //then
        people.forEach(System.out::println);
    }

    @Test
    @DisplayName("30번을 조회한다")
    void findOneTest() {
        //given
        Person p = new Person("30","두껍이",23);
        //when
        repository.save(p);
        Person foundPerson = repository.findOne("30");
        //then
        assertEquals("두껍이",foundPerson.getPersonName());
        System.out.println(foundPerson);
    }
}