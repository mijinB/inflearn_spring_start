package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired      // 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
    
    // 스프링 데이터 JPA 할 때 주석
//    @Bean
//    public MemberRepository memberRepository() {
//        /* Memory
//        return new MemoryMemberRepository(); */
//
//        /* 순수 JDBC
//        return new JdbcMemberRepository(dataSource); */
//
//        /* JDBC Template
//        return new JdbcTemplateMemberRepository(dataSource); */
//
//        /* JPA
//        return new JpaMemberRepository(em); */
//        
//        /* 스프링 데이터 JPA는 memberService() Bean 수정 */
//    }
}
