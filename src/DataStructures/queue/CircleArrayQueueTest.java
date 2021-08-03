package DataStructures.queue;

/*
    数组模拟环形队列

    规律：对于一般复用、环形数据结构。都会用到 (rear - front + maxSize) 加 maxSize 的操作

    问题：不空出一个位置的代码暂时不知道怎么写。
 */

import java.util.Scanner;

@SuppressWarnings({"all"})
public class CircleArrayQueueTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);

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
                    circleArrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数字:");
                    int val = 0;
                    val = scanner.nextInt();
                    circleArrayQueue.addQueue(val);
                    break;
                case 'g':
                    try {
                        int res = circleArrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleArrayQueue.headQueue();
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

class CircleArrayQueue {
    //环形队列中的元素
    private int maxSize;//表示数组的最大容量
    private int front;//front指向队列的第一个元素
    private int rear;//rear指向队列的最后一个元素的下一个位置。因此会导致rear后面的位置一直为空
    private int[] array;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        //因为front、rear初始化为0，而成员属性的默认值就是0，所以省略赋值
    }

    //环形队列的一些操作
    //1. 判断队列是否为满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
        /*
			因为rear指向的是最后一个数据的下一个位置，所以实际最后一个数据的下标是(rear - 1 + maxSize) % maxSize 。
			所以判断满的条件本应该是：rear % maxSize == front.
			所以可以知道这里空出了一个位置(空出的位置是rear指向的那个)，
			因此在isFull\isEmpty中的条件：判断isEmpty()时，可以使用rear == front。如果不空出一个位置，
			则这个条件和isFull()判断条件相同了。
		 */
    }

    //2. 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
        /*
        因为，取出最后一个数据后 front向后移动一位。而原本rear就指向了最后一个数据的后一个位置
         */
    }

    //3. 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能添加");
            return;
        }
        array[rear] = n;
        rear = (rear + 1) % maxSize;//将 rear 后移, 这里必须考虑取模
    }

    //4. 取出队列的数据
    public int getQueue() {
        //判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法取出");
        }
        //return array[front++];没有考虑到取模情况

        // 这里 front是指向队列的第一个元素,因此取出后需要后移
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int ret = array[front];
        front = (front + 1) % maxSize;
        return ret;
    }

    //5. 显示队列所有有效数据
    public void showQueue() {
        //判断是否为空
        if (isEmpty()) {
            System.out.println("队列为空,无法显示");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("array[%d] = %d\n", (i % maxSize), array[i % maxSize]);
            /*
				arr[i % maxSize]的原因是 现在在for中相当于是一个新的队列，队列头是front，尾是front+size()。
				但是，front以后的数据至maxSize-1 的数据都是原本的下标。而maxSize及以后的数据都是下标以0开头的。
				为了将后半部分的以0开头的下标表示出来，要用到 i % maxSize;
			 */
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
        /*
			情况一：rear>front ，此时因为rear指向最后一个数据的下一位，所以rear-front等于数据个数
			情况二：front>rear, 此时rear+maxSize，相当于rear从0开始重新排序。因为rear原本是从0开始，所以现在
			数组第一个小标为arr.length，所以即front和rear重新排序成为：情况一。
		 */
    }

    //6. 显示队列的头数据
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("队列为空，没有数据");
        return array[front];
    }
}