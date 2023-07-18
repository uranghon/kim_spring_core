package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest2 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
//        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);
        PrototypeBean prototypeBean1 = clientBean1.getPrototypeBean();

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
//        ClientBean clientBean2 = ac2.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        PrototypeBean prototypeBean2 = clientBean2.getPrototypeBean();

        System.out.println("clientBean1 = " + clientBean1);
        System.out.println("count1 = " + count1);
        System.out.println("내부Bean1 = " + prototypeBean1);
        System.out.println("clientBean2 = " + clientBean2);
        System.out.println("count2 = " + count2);
        System.out.println("내부Bean2 = " + prototypeBean2);

//        assertThat(clientBean1).isSameAs(clientBean2);

//        assertThat(count2).isEqualTo(1);
//        assertThat(count2).isEqualTo(2);
    }


    @Scope("singleton")
    static class ClientBean {

        // 무식하게 ApplicationContext 를 받아오는 방식으로 하면
        // 매번 logic 을 실행할 때마다 PrototypeBean 생성해서 쓸 수 있다.
        // 하지만 지저분하다.
//        @Autowired
//        private ApplicationContext applicationContext;
        private final PrototypeBean prototypeBean;

        ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public PrototypeBean getPrototypeBean() {
            return prototypeBean;
        }

        public int logic() {
//            PrototypeBean bean = applicationContext.getBean(PrototypeBean.class);
//            bean.addCount();
//            int count = bean.getCount();

            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy " + this);
        }
    }
}
