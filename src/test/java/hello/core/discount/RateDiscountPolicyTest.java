package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    @Test
    @DisplayName("vip 할인테스트 : 10%")
    void vip_Discount() {
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();
        int discount = rateDiscountPolicy.discount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("BASIC 할인테스트 : 0%")
    void basic_Discount() {
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();
        int discount = rateDiscountPolicy.discount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(0);
    }
}