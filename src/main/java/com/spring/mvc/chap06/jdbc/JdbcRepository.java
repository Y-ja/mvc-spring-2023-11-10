package com.spring.mvc.chap06.jdbc;
import com.spring.mvc.chap06.entity.Person;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcRepository {

    // db 연결 설정정보
    private String url = "jdbc:mariadb://localhost:3307/spring"; // 데이터베이스 URL
    private String username = "root";
    private String password = "mariadb";
    private JdbcRepository repository;

    // JDBC 드라이버 로딩
    public JdbcRepository() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 데이터베이스 커넥션 얻기
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // INSERT 기능
    public void save(Person person) {

        Connection conn = null;

        //1. DB연결하고 연결 정보를 얻어와야 함
        try {

            conn = DriverManager.getConnection(url, username, password);
            // 2. 트랜잭션을 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "INSERT INTO person " +
                    "(id, person_name, person_age) " +
                    "VALUES (?, ?, ?)";

            // 4. SQL을 실행시켜주는 객체 얻어와야 함
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            pstmt.setString(1, person.getId());
            pstmt.setString(2, person.getPersonName());
            pstmt.setInt(3, person.getPersonAge());

            // 6. SQL 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = pstmt.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if(conn != null) conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     @Test
     @DisplayName("사람 객체정보를 데이터베이스에 삽입")
    void saveTest(){
        Person p = new Person("1","망둥어",10);
        repository.save(p);
     }

    // UPDATE 기능
    public void update(Person person) {

        Connection conn = null;

        //1. DB연결하고 연결 정보를 얻어와야 함
        try {

            conn = DriverManager.getConnection(url, username, password);
            // 2. 트랜잭션을 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "UPDATE person " +
                    "SET person_name = ?, person_age = ? " +
                    "WHERE id = ?";

            // 4. SQL을 실행시켜주는 객체 얻어와야 함
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            pstmt.setString(1, person.getPersonName());
            pstmt.setInt(2, person.getPersonAge());
            pstmt.setString(3, person.getId());

            // 6. SQL 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = pstmt.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if(conn != null) conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    @DisplayName("사람 객체정보를 데이터베이스에 수정")
    void updateTest(){
        String id = "1";
        String newName = "개굴이";
        int newAge = 15;

        Person p = new Person("1","망둥어",10);
        repository.save(p);
    }
    //DELETE 기능
    public void delete(String id) {

        Connection conn = null;

        //1. DB연결하고 연결 정보를 얻어와야 함
        try {

            conn = DriverManager.getConnection(url, username, password);
            // 2. 트랜잭션을 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            // 3. SQL을 생성
            String sql = "DELETE FROM person " +
                    "WHERE id = ?";

            // 4. SQL을 실행시켜주는 객체 얻어와야 함
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. ? 파라미터 세팅
            pstmt.setString(1, id);

            // 6. SQL 실행 명령
            // executeUpdate - 갱신, executeQuery - 조회
            int result = pstmt.executeUpdate();// 리턴값은 성공한 쿼리의 개수

            // 7. 트랜잭션 처리
            if (result == 1) conn.commit();
            else conn.rollback();

        } catch (SQLException e) {
            try {
                if(conn != null) conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     @Test
     @DisplayName("회원번호가 1인 회원 삭제")
     void delete(){
        String id = "1";
        repository.delete(id);
     }


    // 전체 회원 조회
    public List<Person> findAll() {

        List<Person> people = new ArrayList<>();

        try {
            // 1. 데이터베이스 연결해서 연결정보 획득
            Connection conn = DriverManager.getConnection(url, username, password);

            // 2. SQL 생성
            String sql = "SELECT * FROM person";

            // 3. SQL실행을 위한 객체 획득
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 파라미터 채우기
            // 5. SQL 실행 명령
            ResultSet rs = pstmt.executeQuery();

            // 6. 결과집합 조작하기
            rs.next(); // 커서를 한행씩 이동시키는 기능

            // 커서에 위치한 데이터 레코드 읽기
            String personName = rs.getString("person_name");
            int personAge = rs.getInt("person_age");

            System.out.println("personName = " + personName);
            System.out.println("personAge = " + personAge);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return people;
    }
    @Test
    @DisplayName("랜덤 회원 아이드를 가진을 10명 등록")
    void bulkInserTest(){
        for (int i = 0; i < 10; i++) {
            Person p = new Person("" + Math.random(),"랄라라"+i,i+10);
            repository.save(p);
        }
    }
    // 단일 조회 (아이디로 조회)
    public Person findOne(String id) {
        try {
            // 1. 데이터베이스 연결해서 연결정보 획득
            Connection conn = DriverManager.getConnection(url, username, password);

            // 2. SQL 생성
            String sql = "SELECT * FROM person WHERE id = ?";

            // 3. SQL실행을 위한 객체 획득
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 파라미터 채우기
            pstmt.setString(1, id);
            // 5. SQL 실행 명령
            ResultSet rs = pstmt.executeQuery();

            // 6. 결과집합 조작하기
            while (rs.next()) { // 커서를 한행씩 이동시키는 기능

                // 커서에 위치한 데이터 레코드 읽기
                String pid = rs.getString("id");
                String personName = rs.getString("person_name");
                int personAge = rs.getInt("person_age");

                return new Person(pid, personName, personAge);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}