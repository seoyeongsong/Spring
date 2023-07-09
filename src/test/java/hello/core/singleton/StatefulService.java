package hello.core.singleton;

public class StatefulService {

    // 상태를 유지하는 필드
    private int price;

    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);

        // 여기가 문제점! : 클라이언트가 공유하기 때문에
//        this.price = price;

        return price;
    }



    public int getPrice() {
        return price;
    }
}
