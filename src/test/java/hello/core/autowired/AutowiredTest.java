package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void autowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 호출 안됨. 왜? Member 가 스프링빈이 아니라서!!
        @Autowired(required = false)
        void setNoBean1(Member member) {
            System.out.println("setNobBean1 = " + member);
        }

        // 얜 호출은 됨!! 대신 null 값 들어감
        @Autowired
        void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = " + member);
        }

        // Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
        @Autowired
        void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = " + member);
        }
    }

}
