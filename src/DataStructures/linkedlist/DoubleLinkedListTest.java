package DataStructures.linkedlist;

/*
    双向链表的实现

    重点：双向链表中间插入节点时，彼此的指向需要认真考虑。
    往往有一对操作的前后顺序是固定的

    难点：addByOrder() 中 判断条件
    */

public class DoubleLinkedListTest {
    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        Node hero1 = new Node(1, "宋江", "及时雨");
        Node hero2 = new Node(2, "卢俊义", "玉麒麟");
        Node hero3 = new Node(3, "吴用", "智多星");
        Node hero4 = new Node(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        Node newHeroNode = new Node(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();

        //测试顺序添加后的效果
        Node newHeroNode1 = new Node(4, "new", "入云龙");
        Node newHeroNode2 = new Node(6, "公孙胜", "入云龙");
        Node newHeroNode3 = new Node(5, "公孙胜", "入云龙");
        Node newHeroNode4 = new Node(7, "公孙胜", "入云龙");

        System.out.println("顺序添加后的链表情况~~");
        doubleLinkedList.addByOrder(newHeroNode1);
        doubleLinkedList.addByOrder(newHeroNode2);
        doubleLinkedList.addByOrder(newHeroNode3);
        doubleLinkedList.addByOrder(newHeroNode4);
        doubleLinkedList.list();

    }
}

class DoubleLinkedList {
    public Node head = new Node(0, "", "");

    public Node getHead() {
        return head;
    }

    //删除一个节点
    public void del(int no) {
        //辅助变量，遍历双链表
        Node temp = head.next;//为了便捷，直接从第一个节点开始
        while (temp != null) {
            if (temp.no == no) {
                temp.pre.next = temp.next;
                if (temp.next != null)
                    //如果删除的是最后一个，那么temp后一个节点是不存在的
                    temp.next.pre = temp.pre;
                return;
            }
            temp = temp.next;
        }
        System.out.println("没找到");//退出说明，找到结尾了。
    }

    //修改一个节点的内容。
    public void update(Node node) {
        if (node == null) {
            System.out.println("输入的节点不能为null");
            return;
        }
        //辅助变量遍历节点，找到修改的位置
        Node temp = head.next;//为了便捷，直接赋值第一个节点
        while (temp != null) {
            if (temp.no == node.no) {
                temp.name = node.name;
                temp.nickName = node.nickName;
                return;
            }
            temp = temp.next;
        }
        System.out.println("未找到节点");
    }

    //按照编号添加节点
    /*
        因为 temp 可以和前后都有联系。
        因此 在找位置 比较的时候，temp.next.no还是temp.no都可以。
     */
    public void addByOrder(Node node) {
        if (node == null) {
            System.out.println("添加的节点不能为null");
            return;
        }
        //辅助变量，遍历
        Node temp = head;//只能用head，因为while判断条件中，必须使用temp
        // 在进行比较的时候，大可以将temp想作为不动的，而将node在链表中一直往后移动
        while (temp.next != null) {
        /* 这里必须使用temp.next
        因为如果遇到添加到结尾的情况需要使用到temp.pre
        也因为while中判断的是temp.next。所以 比较大小也应该是用temp.next
        简洁地说：就是和单链表一样。都需要利用指向地节点的下一个作为判断。
            */
            if (temp.next.no == node.no) {//递增的顺序，那么应该先出现相等的情况
                System.out.println("节点已经存在");
                return;
            } else if (node.no < temp.next.no) {//说明找到了，且应该插入到temp和temp.next之间
                node.next = temp.next;
                temp.next = node;
                node.pre = temp;
                temp.pre = node;
                //重点：双向链表中间插入节点时，彼此的指向需要认真考虑。
                // 往往有一对操作的前后顺序是固定的
                return;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    //添加节点
    public void add(Node node) {
        if (node == null) {
            System.out.println("添加的节点不能为空");
            return;
        }
        /*//辅助变量找 链表的结尾。
        Node temp = head.next;
        while (temp != null) {
            temp = temp.next;
        }
        temp = node;
        node.pre = temp.pre; */

        Node temp = head;//鉴于temp=head.next比较繁琐。所以赋值temp=head
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    // 遍历输出双链表
    public void list() {
        if (head.next == null) {
            System.out.println("双链表为空");
            return;
        }
        //辅助变量
        Node temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class Node {
    public int no;
    public String name;
    public String nickName;
    public Node pre;//指向前一个节点，默认为null
    public Node next;//指向后一个节点，默认为null

    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "[no=" + no + ",name=" + name + ",nickName=" + nickName + "]";
    }
}