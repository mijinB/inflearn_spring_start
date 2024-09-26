package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    /* 여기와 MemberService 클래스에서 memberRepository를 각각 new로 생성하고 있어서 다른 repository이기 때문에 문제다.(MemberService 클래스에서 static이라 지금은 문제가 생기지않지만 고쳐줘야 한다.)
        ⇒ 각각 new로 생성하지 말고 MemberService 클래스에서 외부로 받게끔 생성자를 만들고 @BeforeEach를 통해 넣어주는 방식으로 해결. (어려워서 이해가 잘 안된다..)
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); */
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        if (memberService.findOne(saveId).isPresent()){
            Member findMember = memberService.findOne(saveId).get();
            assertThat(member.getName()).isEqualTo(findMember.getName());
        }
    }

    // 회원가입(회원 저장)도 중요하지만, 예외 처리(중복 확인)도 중요하니까 테스트가 필요하다.
    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /* try catch문을 사용하기는 애매하다. 위 처럼 assertThrows 사용
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        } */

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}