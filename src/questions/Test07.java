package questions;

/*
    将一个二进制（其他进制）转换成整数
 */

public class Test07 {
    public static void main(String[] args) {
        String strByte = "10101000";
        System.out.println((byte)Integer.parseInt(strByte, 2));
    }
}
