package com.spring.mvc.chap06.spring_jdbc;

import com.spring.mvc.chap06.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StringJdbcRepository {
    //데이터소스 설정
    //application.properties
    //JdbcTemplate
    private final JdbcTemplate template;

   public void  save(Person p){
       String sql = "INSERT INTO person " + "(id,person_name,person_age)" + "VALUE(?,?,?)";
       template.update(sql,p.getId(),p.getPersonName(),p.getPersonAge());
   }
   public void remove(String id){
       String sql = "DELETE FROM person WHERE id = ?";
       template.update(sql,id);
   }

    public void modify(Person p) {
        String sql = "UPDATE person" + "SET person_name=?, person_age=?" + "WHERE id=?";
        template.update(sql,p.getPersonName(),p.getPersonName(),p.getPersonAge());
    }
    public List<Person> findAll(){
       String sql = "SELECT * FROM person";
       return template.query(sql, (rs, rowNum) ->  new Person(rs));
    }
    public  Person findOne(String id){
       String sql = "SELECT * FROM person WHERE id = ?";
       return template.queryForObject(sql,(rs, rn) -> new Person(rs) , id);
    }
}