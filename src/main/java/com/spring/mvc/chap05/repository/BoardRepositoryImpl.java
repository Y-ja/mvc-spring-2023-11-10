package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.BoardController;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BoardRepositoryImpl implements  BoardRepository{
    private static final Map<Integer, Board> boardMap;
    private static int sequence = 0;

    static {
        boardMap = new HashMap<>();
       Board b1 =  new Board(++sequence,"오늘 장보러 갈건데 ㅋ", "뭐 살지 추천좀 ㅋ");
       Board b2 =  new Board(++sequence,"해피송", "뭐 살지 추천좀 ㅋ");
       Board b3 =  new Board(++sequence,"해피해피해피", "뭐 살지 추천좀 ㅋ");
    }
    @Override
    public List<Board> findAll() {
        return boardMap.values()
                .stream()
                .sorted(Comparator.comparing(Board::getBoardNo).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Board findOne(int boardNo) {
        return boardMap.get(boardNo);
    }

    @Override
    public boolean save(Board board) {
        board.setBoardNo(++sequence);
        boardMap.put(board.getBoardNo(), board);
        return true;
    }

    @Override
    public boolean deleteByNo(int boardNo) {
        boardMap.remove(boardNo);
        return true;
    }

}
