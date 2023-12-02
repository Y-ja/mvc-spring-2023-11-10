package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 목록 조회 중간처리
    public List<BoardListResponseDTO> getList() {
        return boardRepository.findAll()
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(Collectors.toList())
                ;
    }

    public void register(BoardListResponseDTO dto) {
       Board board =  new Board(dto);



       boardRepository.save(board);
    }

    public void delete(int boardNo) {
        boardRepository.deleteByNo(boardNo);
    }

    public Object getDetail(int boardNo) {
        Board board = boardRepository.findOne(boardNo);

        board.upViewCount();

        return null;
    }

    //

}
