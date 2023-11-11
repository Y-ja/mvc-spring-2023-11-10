package com.spring.mvc.chap01;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.datatransfer.DataFlavor;

//@Component //-->빈 등록
@Controller
public class ControllerV1 {
    //컨트롤러 --> 클라이언트의 요청을 받아서 처리 후 응답을 보내주는 역활

    //세부 요청처리는 메서드 등록
    @RequestMapping("/hello")
    public String home(){
        System.out.println("Welcome To My Page");

        //어떤 JSP로 포워딩 경로 설정

        return "index";
    }
    @RequestMapping("/food")
    public String food(){
        System.out.println("Welcome To My Page");

        //어떤 JSP로 포워딩 경로 설정

        return "chap01/food";
    }

    //요청 파라미터 읽기
    @RequestMapping("/person")
    public String person(HttpServletRequest request){
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name --> " + name);
        System.out.println("age --> " + age);
        return "";
    }
    @RequestMapping("/major")
    public String major(String stu, @RequestParam("major") String major, Integer grade ){
        System.out.println("stu --> "+ stu);
        System.out.println("stu --> "+ major);
        System.out.println("stu --> "+ grade);
        return "";
    }
    @RequestMapping("/order")
    public String order(OrderRequestDTO dto){
        System.out.println("dto ---> " + dto);
        System.out.println(dto.getGoodsName());
        return "";
    }
    @RequestMapping("/member/{userName}/{userNo}")
    public String member(@PathVariable String userName ,@PathVariable int userNo){
        System.out.println(" user -->" + userName);
        System.out.println(" userNo -->" + userNo);
        return "";
    }
    //Post 요청 데이터 읽기
//    @RequestMapping(value = "food-select" , method = RequestMethod.POST)
    @PostMapping("food-select")
    public String select(){
        return "index";
    }
}
