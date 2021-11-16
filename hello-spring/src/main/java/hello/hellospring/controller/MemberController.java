package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 스프링이 컨테이너에서 memberService를 MemberController와 연결해준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // Autowired으로 의존관계 주입(DI) 완료
    // autowired 선언 시 new로 새로운 객체를 만들면 그 객체는 작동 안 한다.

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findAll();
        // DB에서 불러온 멤버 데이터를 model의 "members"라는 곳에 넣겠다.
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
