package DataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
    冒泡排序的难点 是 两层循环的 循环次数。---- 恰巧两层都是array.length-1次。
    1. 第一层是 array.length-1
    2. 第二层在第一层的基础上 每次减少 层数 [依次减少量为 1, 2, ……, array.length-1].
       第二层循环量为：[array.length-1, array.length-2, ……， 2, 1, 0]
    因为 第二层每次调用两个元素，所以最后一次的前一个必须是 倒数第二个节点。

    客服难点的方法是：保证每次写冒泡排序时 第一层，第二层的循环变量起始相同，判断表达式的 符号相同 "<"
    第二层难以一眼看出每次外层循环时 内层的循环次数，这时只要看 第一次循环的次数即可，满足array.length-1。
 */

public class BubbleSort {
    public static void main(String[] args) {
        A a1 = new A("abc");
        A a2 = new A("bcd");
        A a3 = new A("def");
        A a4 = new A("efg");
        A[] array1 = {a1, a3, a2, a4};
        bubbleSort(array1);
        System.out.println(Arrays.toString(array1));

        /*int[] array2 = {2, 1, 4 ,2, 56, 7, 4, 3};
        bubbleSort(array2);
        System.out.println(Arrays.toString(array2));*/

        //创建8w随机数组，进行测试运行时间
        //创建数组
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] array2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array2[i] = (int)(Math.random() * 80000 + 1);//[1, 80001)
        }
        //记录开始时间
        Date date1 = new Date();
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("冒泡排序开始时时间：" + date1Str);

        bubbleSort(array2);

        Date date2 = new Date();
        System.out.println("结束时间:" + simpleDateFormat.format(date2));
    }

    public static void bubbleSort(int[] array) {
        int temp = 0;//辅助变量，用于交换整数
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                /*
                1. 重新写一个 交换数字的方法 性价比太低。因为int不能用指针，
                但是可以swap(array, j)这样来创造方法 交换。
                2. 但是如果是引用类型，单独写一个方法是不二之选。
                 */
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag)
                break;
        }
    }

    //这里 是重载了 bubbleSort()方法
    public static void bubbleSort(A[] array) {
        A temp = null;//辅助变量，用于交换 A类的对象
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                //对于不同的数据类型，需要不同的比较方法.这里最终效果是升序
                if (array[j].compare(array[j + 1]) > 0) {
                    //但是如果是引用类型，在此交换步骤复杂。可以选择单独写一个方法。
                    //这种交换应该是可以的。因为数组的每个元素是在堆中，是各个栈共同引用的。
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag)
                break;
        }
    }
}



class A {
    public String name;

    public A(String name) {
        this.name = name;
    }

    public int compare(A a2) {
        return this.name.compareTo(a2.name);
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                '}';
    }
}