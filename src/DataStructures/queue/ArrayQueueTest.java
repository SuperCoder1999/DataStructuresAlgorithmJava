package DataStructures.queue;

/*
    数组模拟队列

    问题：1. 何时提醒，何时报错？
         2. showQueue中无效元素也都输出了

    知识点：Scanner需要关闭。
    Scanner平时配合System.in标准输入流来使用，in是InputStream类型，一个字节流。
    scanner的close()调用的是Inputstream的close()。在平时自己学习时可以不加。
    在工作上开发程序时，一定要加上，因为不关闭连接可能导致I/O，线程阻塞、系统文件描述符打开过多等问题
 */

import java.util.Scanner;

public class ArrayQueueTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayQueue arrayQueue = new ArrayQueue(3);
        //测试程序
        char key = ' ';//接收用户输入
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数字:");
                    int val = 0;
                    val = scanner.nextInt();
                    arrayQueue.addQueue(val);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class ArrayQueue {
    //队列中的元素：
    private int maxSize;//队列的最大容量，并不是最大下标
    private int front;//队列头
    private int rear;//队列尾
    private int[] array;//存放数据的数组，模拟队列

    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = -1;//队列头指向队列头部。规定指向第一个数据前一个位置。因为取出数据后front向后移动，
        // 需要指向rear才能判断出 isEmpty()这个条件
        rear = -1;// 队列尾指向队列尾部，指向队列的最后一个数据。
        //front、rear初始值都是 -1 ，是为了后面add、remove 时操作后恰好可以表示队列空、满的情况
    }

    // 基本方法：
    //1. 判断队列是否为满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //2. 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //3. 添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已经满");
            return;
        }
        rear++;// rear后移。一旦启动队列，rear 就一直指向最后一个数据
        array[rear] = n;
    }

    //4. 获取队列的数据，出队列。这个数据就从队列中删除了
    public int getQueue() {
        //判断队列是否为空，即是否可以取出数据
        if (isEmpty()) {
            //通过抛出异常来终止操作
            throw new RuntimeException("队列为空，无法取出");
            // throw 就会终止程序，不会再执行下去，所以不用 return。
        }
        front++;//front后移，但是还是指向第一个数据前一个位置
        return this.array[front];
    }

    // 5. 显示队列的所有数据
    public void showQueue() {
        //判断队列是否为空，即是否可以显示
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        //为什么空白数据也取出来？
        for (int i = 0; i < array.length; i++) {// 用fori好些，因为需要在输出时，需要显示序号
            System.out.printf("array[%d] = %d\n", i, array[i]);
        }
        /* 我认为的：不能使用fori，因为队列的数据只在 front、rear 之间一段
        for (int i = front + 1; i <= rear; i++) {
        }*/
    }

    //6. 显示队列的头数据。注意只是显示，还存在队列中
    public int headQueue() {
        //判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return array[front + 1];
    }
}