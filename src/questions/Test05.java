package questions;

/*
    String.getBytes()。得到的一个个字节，默认是什么类型?
    ---答：byte 是整数类型。所以上面得到的字节都是整数
 */

public class Test05 {
    public static void main(String[] args) {
        String name = "Tom";
        byte[] bytes = name.getBytes();
        System.out.println(bytes[1]);
    }
}
