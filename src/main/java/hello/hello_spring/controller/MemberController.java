package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    // @Autowired private MemberService memberService;      이렇게 하는 필드 주입도 있지만 권장하지 않는다. IntelliJ IDE도 경고를 준다.

    /* 이런 식으로 선언한 후에 세팅해주는 세터 주입도 있지만, 권장하지 않는다. 한번 세팅되면 수정할 일이 없는데 public으로 해줘야 하기 때문.
    private MemberService memberService;
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
     */

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member); // 회원가입

        return "redirect:/";        // 홈 화면으로 redirect
    }
}
