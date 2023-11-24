package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
@Component
public class ScoreRepositoryImpl implements ScoreRepository {

    // 인메모리 저장공간 해시맵
    // key: 학번, value: 성적정보
    private static final Map<Integer, Score> scoreMap;
    private static int  seq;
    // 객체 초기화는 직접하는 거보다 주입받거나 생성자를 통해 처리하는게 좋다.
    static {
        scoreMap = new HashMap<>();
        Score s1 = new Score("뽀로로", 100, 88, 33, ++seq, 0, 0.0, Grade.F);
        Score s2 = new Score("춘식이", 33, 99, 11, ++seq, 0, 0.0, Grade.F);
        Score s3 = new Score("쿠로미", 66, 55, 22, ++seq, 0, 0.0, Grade.F);

        scoreMap.put(s1.getStuNum(), s1);
        scoreMap.put(s2.getStuNum(), s2);
        scoreMap.put(s3.getStuNum(), s3);

    }

    @Override
    public List<Score> findAll() {
//        List<Score> temp = new ArrayList<>();
//        for(Integer key : scoreMap.keySet()){
//            Score score = scoreMap.get(key);
//            temp.add(score);
//        }

        return new ArrayList<>(scoreMap.values())
                .stream()
                .sorted(comparing(Score::getStuNum))
                .collect(toList())
                ;

    }

    @Override
    public boolean save(Score score) {
        score.setStuNum(++seq);
        if(scoreMap.containsKey(score.getStuNum())) return false;

        scoreMap.put(score.getStuNum(),score);
        return false;
    }

    @Override
    public boolean delete(int stuNum) {
        if(!scoreMap.containsKey(stuNum)) return false;

        scoreMap.remove(stuNum);
        return true;
    }

    @Override
    public Score findOne(int stuNum) {
        return scoreMap.get(stuNum);
    }
}
