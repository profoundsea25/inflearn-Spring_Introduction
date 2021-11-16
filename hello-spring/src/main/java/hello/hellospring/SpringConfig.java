package hello.hellospring;

import hello.hellospring.aop.TimeTraceApp;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
// 코드로 빈 등록하기 (Autowired, Service, Repository 어노테이션 안쓰고 등록하는 방법)
public class SpringConfig {
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    // aop를 사용한다는 것을 명시하기 위해 써주는 것.
//    // 굳이 이렇게 안 해도 되긴 함.
//    public TimeTraceApp timeTraceApp() {
//        return new TimeTraceApp();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        // return new JpaMemberRepository(em);
    }
    // DB가 바뀌었지만! 다른 코드는 하나도 손대지 않았다.
    // 이것이 Spring을 쓰는 이유.
    // 스프링 DI(의존성 주입)을 사용하면 기존 코드는 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
    // <개방 폐쇄 원칙>

