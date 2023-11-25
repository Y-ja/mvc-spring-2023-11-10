package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import java.util.List;

public interface ScoreRepository {
    /*
    * 성적 정보를 잘 저장하고 조회하고 수정하고 삭제한느 역활
    * 데이터베이스와 같은 저장소에 접근 하는 객체
    * 파일로 저장, 인메모리 저장, 관계형 DB
    * */
    //성적
    List<Score> findAll();

    List<Score> findAll(String sort);

    //성적 정보 등록
    boolean save(Score score);

    //성적 정보 삭제
    boolean delete(int stNum);
    //성적 정보 개별 조회
    Score findOne(int stuNum);
}
