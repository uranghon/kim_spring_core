package hello.core.singleton;

public class StatefulService {
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    // 무상태설계 예시 ( 이 메소드 빼고 다 없앰 )
    /*
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
    */
    public int getPrice() {
        return price;
    }
}
