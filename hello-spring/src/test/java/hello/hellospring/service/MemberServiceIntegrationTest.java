package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 좋은 테스트는 통합 테스트보다 단위 테스트일 가능성이 높다.
// 테스트 설계를 잘 해야 실력. 단위별로 쪼개서 하자. 스프링 컨테이너 되도록 올리지 말자.

@SpringBootTest // 스프링을 띄워서 테스트
@Transactional // 테스트 이후 DB를 항상 롤백해준다.
// = 다른 테스트에 영향을 주지 않으며, 테스트를 반복해서 실행할 수 있게 해준다.
// 이전 실행 다시 실행하기 shift+f10
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    // Test는 그냥 위처럼 필드 주입을 사용하기도 한다.

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1. setName("spring");

        Member member2 = new Member();
        member2. setName("spring");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // then
    }

}