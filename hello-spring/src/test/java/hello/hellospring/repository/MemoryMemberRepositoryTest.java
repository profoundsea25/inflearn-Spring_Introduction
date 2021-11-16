package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 동작이 끝날 때마다 작동 (콜백함수)
    public void afterEach() {
        repository.clearStore();
    }
    // 왜 설정? 테스트는 각각 의존관계가 있으면 안 됨.
    // 즉, 테스트 실행 순서에 따라 영향을 받으면 안 됨.
    // 아까 전에는 변수를 비워주는 함수가 없었음.
    // 콜백함수를 통해 각 테스트가 끝날 때마다 변수를 clear해줘서 테스트의 독립성을 보장


    // 자동 줄바꿈 or ; 달아주기 : ctrl+shift+enter

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("sprint1");
        repository.save(member1);

        // shift + F6 : 한번에 rename하기
        Member member2 = new Member();
        member2.setName("sprint2");
        repository.save(member2);

        // 자동완성 ctrl+alt+v
        Member result = repository.findByName("sprint1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
