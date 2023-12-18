package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 목록 조회 중간처리
    public List<BoardListResponseDTO> getList(Page page) {
       return boardRepository.findAll()
               .stream()
               .map(BoardListResponseDTO::new)
               .collect(Collectors.toList())
       ;
    }

    // 글 쓰기 중간처리
    public void register(BoardListResponseDTO dto) throws SQLException {
        // dto 를 엔터티로 변환
        Board board = new Board((ResultSet) dto);
        boardRepository.save(board);
    }


    public void delete(int boardNo) {
        boardRepository.deleteByNo(boardNo);
    }

    public BoardDetailResponseDTO getDetail(int bno) {
        Board board = boardRepository.findOne(bno);

        // 조회수 상승처리
//        board.upViewCount();
        boardRepository.updateViewCount(bno);

        return new BoardDetailResponseDTO(board);
    }

    public int getCount() {
        return boardRepository.count();
    }
}
