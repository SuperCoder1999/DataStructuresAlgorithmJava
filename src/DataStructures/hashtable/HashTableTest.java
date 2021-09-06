package DataStructures.hashtable;

import java.util.Scanner;

public class HashTableTest {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入name");
                    String name = scanner.next();
                    //创建节点
                    Emp emp = new Emp(id, name);
                    //添加元素
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                case "find":
                    System.out.println("输入查找的id");
                    int id02 = scanner.nextInt();
                    hashTab.findEmp(id02);
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 哈希表 管理多条链表
 */
class HashTab {
    public EmpLinkedList[] empLinkedListArray;
    private int size;
    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //这里需要将每一个元素都实例化，因为目前数组中所有元素都是null
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加元素（节点）
    public void add(Emp emp) {
        //找到合适的链表插入
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
            //因为每一条链表输出的只是当前链表的所有元素，所以换行的操作需要在链表数组中操作
            System.out.println();
        }
    }

    //根据输入的id，查找节点信息
    public void findEmp(int id) {
        int empLinkedListNo = hashFun(id);
        //查找 empLinkedListNo节点
        empLinkedListArray[empLinkedListNo].findById(id);
    }

    //编写散列函数，使用一个简单的取模法
    public int hashFun(int id) {
        return id % size;
    }
}

/**
 * 每一个链表就相当于普通的链表。只是同时存在多条这样的链表
 */
class EmpLinkedList {
    //头指针。这里的链表没有头节点。即head指向第一个节点
    private Emp head;

    /**
     *  添加雇员到链表 -- 清楚每一个操作是在哪一个数据结构中执行的
     */
    public void add(Emp emp) {
        //如果是空链表
        if (head == null) {
            head = emp;
            return;
        }
        //不是空链表，需要找到最后一个节点 -- 这里采用乱序插入法
        Emp curEmp = head;//辅助变量
        while (true) {
            if (curEmp.next == null) {
                curEmp.next = emp;
                return;
            }
            curEmp = curEmp.next;//后移
        }

        /*
            合并两种情况 -- 此时head作为头节点，是没有数据的。
            此时需要提前将head实例化 --- head = new Emp(0,"");
            curEmp = head;
            while(true) {
                if (curEmp.next == null) {
                    curEmp.next = emp;
                    return;
                    }
                curEmp = curEmp.next;
            }
         */
    }

    /**
     * 遍历链表的信息
     */
    public void list(int no) {
        if (head == null) {
            System.out.print("第 " + (no + 1) + " 链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + " 链表 ");
        Emp curEmp = head;//辅助变量
        while (curEmp != null) {
            System.out.print("=> " + curEmp.id + "" + curEmp.name + " ");
            curEmp = curEmp.next;
        }
    }

    /**
     * 查找该链表中的节点
     */
    public void findById(int id) {
        Emp curEmp = head;
        while (curEmp != null) {
            if (curEmp.id == id) {
                System.out.println("所查找的id的信息是：" + curEmp.id + curEmp.name);
                return;
            }
            curEmp = curEmp.next;
        }
        System.out.println("查不到该id");
    }
}

/**
 * 每一个链表中的节点
 */
class Emp {
    public int id;
    public String name;
    public Emp next;
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}