package questions;

/*
    调试时，跳出for循环。
 */

public class Test08 {
    public static void main(String[] args) {
        forJump();
    }

    static void forJump() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
        System.out.println("跳出");
    }
}
