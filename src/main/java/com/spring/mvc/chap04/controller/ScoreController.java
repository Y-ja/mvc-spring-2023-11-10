package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.dto.ScoreRequesDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {
    //저장소에 의존 하여 데이터 처리 위임
    private final ScoreRepository repository ;

//    public ScoreController(ScoreRepository repository){
//        this.repository = repository;
//    }
    /*
    * 컨트롤러
    * 클라이언트의 요청을 받아서 처리하고 응답을 제공하는 객체
    * # 1. 학생 성적정보 등록폼 화면을 보여주고 동시에 지금까지 저장되어 있는 성적정보 조회 #
    * # 2. 학생 성적정보를 데이터베이스에 저장하는 요청 #
    * # 3. 학생 성적정보를 데이터베이스에 삭제하는 요청 #
    * # 4. 학생 성적정보를 데이터베이스에 조회하는 요청 #
    * */
    @GetMapping("/list")
    public String list(Model model){
        /*
        * 성적폼 + 목록조회
        * - 저장된 성적정보 리스트를 jsp에 보내줌
        *
        *
        * */

        System.out.println("/score/list Get!");
        List<Score> scoreList = repository.findAll();
        System.out.println(scoreList);
        model.addAttribute("sList",scoreList);
        return "chap04/score-list";
    }
    @PostMapping("/register")
    public String register(ScoreRequesDTO score) {
        System.out.println("/score/register POST !!");
        System.out.println("score = " + score);

        // DTO를 엔터티로 변환 -> 데이터 생성
        Score savedScore = new Score(score);

        repository.save(savedScore);

        /*
            # forward vs redirect
            - 포워드는 요청 리소스를 그대로 전달해줌.
            - 따라서 URL이 변경되지 않고 한번의요청과 한번의 응답만 이뤄짐

            - 리다이렉트는 요청후에 자동응답이 나가고
              2번째 자동요청이 들어오면서 2번째 응답을 내보냄
            - 따라서 2번째 요청의 URL로 자동 변경됨
         */

        // forward할때는 포워딩할 파일의 경로를 적는것
        // ex) /WEB-INF/views/chap04/score-list.jsp

        // redirect할때는 리다이렉트 요청 URL을 적는것
        // ex) http://localhost:8080/score/detail

        return "redirect:/score/list";
    }

    @RequestMapping(value = "/remove/{stuNum}", method = {RequestMethod.GET, RequestMethod.POST})
    public String remove(HttpServletRequest request , @PathVariable int stuNum){
        System.out.printf("/score/RequestMapping %s remove!", request.getMethod());
        System.out.println("삭제할 학번 -> " + stuNum);

        repository.delete(stuNum);

        return "redirect:/score/list";
    }
    @GetMapping("/detail")
    public String detail(int stuNum){
        System.out.println("/score/detail Get!");
        Score score = repository.findOne(stuNum);

        return "chap04/score-detail";
    }
}
