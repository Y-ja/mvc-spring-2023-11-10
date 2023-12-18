package com.spring.mvc.chap05.entity;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.PageMaker;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/borad")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(Model model){
        System.out.println("/board/list : GET!");
        Page Page = null;
        List<BoardListResponseDTO> doList = boardService.getList(Page);
        PageMaker maker = new PageMaker(Page, boardService.getCount());
        model.addAttribute("bList",doList);
        return "chap05/list";
    }
    // 2. 글쓰기 화면요청 (/board/write : GET)
    @GetMapping("/write")
    public String write(){
        System.out.println("/board/list : GET!");
        return "chap05/write";
    }
    // 3. 글쓰기 등록요청 (/board/write : POST)
    @PostMapping("/write")
    public String write(BoardListResponseDTO dto) throws SQLException {
        System.out.println("/board/write : POST! -" + dto);
        boardService.register(dto);
        return "chap05/write";
    }
    // 4. 글 삭제 요청 (/board/delete : GET)
    @GetMapping("/delete")
    public String delete(@RequestParam("bno") int bno) {
        System.out.println("/board/delete : GET");
        boardService.delete(bno);
        return "redirect:/board/list";
    }
    // 5. 글 상세보기 요청 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(int bno, Model model) {
        System.out.println("/board/detail : GET");
        model.addAttribute("b", boardService.getDetail(bno));
        return "chap05/detail";
    }
}
