package hello.core.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoTestClass2 {

    @Autowired(required = false)
    private AutoTestClass1 autoTestClass1;

}
