package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        /* Memory
        return new MemoryMemberRepository(); */

        /* 순수 JDBC
        return new JdbcMemberRepository(dataSource); */

        /* JDBC Template
        return new JdbcTemplateMemberRepository(dataSource); */

        /* JPA */
        return new JpaMemberRepository(em);
    }
}
