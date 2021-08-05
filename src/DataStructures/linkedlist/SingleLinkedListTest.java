package DataStructures.linkedlist;

/*
    单链表的实现

    未掌握的基础：
        //head = reverseHead;
        只改变了reverseList栈中的head中的地址.
        而head本身是main中复制来的。
        本质上main中的head也是getHead()中复制来的。
        所以只有在SingleLinkedList 类中才能修改head的指向

        也不一定非得找最底层的head 再修改其指向。只要在head被用到的地方
        更改其方向即可。

    技巧：在方法体外不能使用 new HeroNode(0,"","").var

    课后作业：合并两个有序的单链表，合并之后的链表依然有序（较好的方法是
    创建一个newHead ，再用两个指针从两个链表中依次从头向后遍历。每次比较两个指针
    指向的节点大小，将合适的添加到newHead链表后，被拆的那个原始链表的指针向后移动。
    直至一个链表的指针为null。将另一个链表全部添加到newHead链表中。

    难点：addByOrder() 中 判断条件
 */

import java.util.Stack;

public class SingleLinkedListTest {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        /*//普通的加入
        singleLinkedList.addNode(hero1);
        singleLinkedList.addNode(hero4);
        singleLinkedList.addNode(hero2);
        singleLinkedList.addNode(hero3);*/

        //顺序加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        // 测试一下单链表的反转功能
        System.out.println("原来链表的情况~~");
        singleLinkedList.list();

        // 修改 no 2 的信息
        HeroNode newHeroNode = new HeroNode(2, "卢", "麒麟++++");;
        singleLinkedList.update(newHeroNode);
        System.out.println("更新后链表的情况~~");
        singleLinkedList.list();

        /*//删除节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("删除后的链表情况~~");
        singleLinkedList.list();

        //测试一下 求单链表中有效节点的个数
        System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));//2

        //测试 得到 倒数第 k个节点
        System.out.println("倒数第2的节点: " +
                getLastIndexNode(singleLinkedList.getHead(), 2));*/

        // 测试一下单链表的反转功能 - 关闭其他测试
        System.out.println("原来链表的情况~~");
        singleLinkedList.list();
		System.out.println("反转单链表~~");
		reverseList(singleLinkedList.getHead());
		singleLinkedList.list();

		//测试反转打印
        System.out.println("测试逆序打印单链表, 没有改变链表的结构~~");
        reversePrint(singleLinkedList.getHead());

        //测试合并
        System.out.println("测试合并~~");
        testMerge();

    }

    //测试合并
    public static void testMerge() {
        SingleLinkedList s1 = new SingleLinkedList();
        s1.addNode(new HeroNode(1,"1","1"));
        s1.addNode(new HeroNode(3,"20","20"));
        s1.addNode(new HeroNode(4,"4","4"));
        s1.addNode(new HeroNode(10,"10","10"));

        SingleLinkedList s2 = new SingleLinkedList();
        s2.addNode(new HeroNode(3,"1","1"));
        s2.addNode(new HeroNode(5,"20","20"));
        s2.addNode(new HeroNode(20,"4","4"));
        s2.addNode(new HeroNode(50,"10","10"));

        s1.head = merge(s1.getHead(),s2.getHead());
        s1.list();
    }

    /*课后作业：合并两个有序的单链表，合并之后的链表依然有序（较好的方法是
    创建一个newHead ，再用两个指针从两个链表中依次从头向后遍历。每次比较两个指针
    指向的节点大小，将合适的添加到newHead链表后，被拆的那个原始链表的指针向后移动。
    直至一个链表的指针为null。将另一个链表全部添加到newHead链表中。
    当然较差的方法是：将一个链表用addByOrder()的方式添加到另一个链表中*/
    public static HeroNode merge(HeroNode head1, HeroNode head2) {
        HeroNode newHead = new HeroNode(0, "", "");
        HeroNode temp = newHead;//用于遍历新的链表
        HeroNode temp1 = head1.next;//为了便捷，直接指向第一个节点
        HeroNode temp2 = head2.next;
        boolean loop = true;
        boolean key1 = true;//表示第一个链表还没有遍历完
        boolean key2 = true;
        while (loop) {
            if (temp1 == null) {
                key1 = false;
                break;
            }
            if (temp2 == null) {
                key2 = false;
                break;
            }
            if (temp1.no < temp2.no) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else if (temp1.no > temp2.no) {
                temp.next = temp2;
                temp2 = temp2.next;
            } else {//相等的情况比较特殊，需要单独处理。
                temp.next = temp1;//只添加其中一个就行
                temp1 = temp1.next;
                temp2 = temp2.next;//两个指针都向后移动
            }
            temp = temp.next;
        }
        if (key1)
            temp.next = temp1;
        if (key2)
            temp.next = temp2;
        return newHead;
    }

    //将单链表逆序打印
    /*
        有两种方式：1. 先逆序。再打印 - 这样会破坏原始链表。
        2. 使用栈 将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
     */
    public static void reversePrint(HeroNode head) {
        Stack<HeroNode> stack = new Stack();
        HeroNode temp = head.next;//辅助变量，为了便捷直接指向head.next
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;//后移
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    // 方法：获取单链表的节点数量（如果是带头节点的链表，不统计头节点)
    public static int getLength(HeroNode head) {
        /*暂时不考虑head=null的情况
        if (head == null) {
            System.out.println("输入头节点不能为空");
            return -1;
        }*/
        int length = 0;//计数器
        //辅助变量，遍历单链表
        HeroNode temp = head.next;//为了便捷，直接赋值第一个节点
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    // 新浪面试题：查找单链表中的倒数第 K个节点
    /*
        思路：两次遍历单链表 - 可利用getLength()
        第一次求得单链表长度。第二次 由总长度及 K 得到 该节点顺序序号
     */
    public static HeroNode getLastIndexNode(HeroNode head, int index) {
        int length = getLength(head);
        if (index > length) {
            System.out.println("输入数字超出范围");
            return null;
        }
        HeroNode temp = head;//这里也可以 指向 head.next;
        for (int i = 0; i < length - index + 1; i++) {
            // 这里的判断条件随着 temp的指向情况而变化
            temp = temp.next;// temp 一直往后遍历，根据for的次数 即可指向倒数 第 k个
        }
        return temp;
    }

    // 将单链表反转
    //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端、head的后面
    public static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null)
            return;
        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode cur = head.next;
        HeroNode curNext = null;
        while (cur != null) {// 直到原始链表遍历完，才算结束
            curNext = cur.next;
            //将原始链表中的cur 放在 reverseHead前面
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端/head的后面
            reverseHead.next = cur;//将cur接在head后面
            cur = curNext;//将原始链表的cur后移
        }
        head.next = reverseHead.next;
        //head = reverseHead;

        /*只改变了reverseList栈中的head中的地址.
        而head本身是main中复制来的。
        本质上main中的head也是getHead()中复制来的。
        所以只有在SingleLinkedList 类中才能修改head的指向
         */
    }
}

//定义SingleLinkedList 管理节点，最终形成单链表
class SingleLinkedList {
    //先初始化一个头节点，头节点不能动，不存放具体的数据
    public HeroNode head = new HeroNode(0, "", "");

    // 返回头节点
    public HeroNode getHead() {
        return head;
    }

    //单链表基本方法：
    //1. 添加节点到单项链表
    //思路，当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next 指向 新的节点
    public void addNode(HeroNode heroNode) {
        //赋值变量，来遍历链表，找到链表尾部
        HeroNode temp = head;//为了变量能够一直找下去，初始值赋head 通过temp = temp.next 即可找下去
        //遍历链表，找到链表尾部
        while (true) {
            if (temp.next == null)//这里判断条件要根据temp的初始值来决定。如果temp初值时head.next这里就用temp
                break;
            //如果没找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点，因为这里temp是引用类型，所以temp代表链表结尾的那个节点
        temp.next = heroNode;
    }

    //1. 按顺序插入节点
    public void addByOrder(HeroNode heroNode) {
        if (heroNode == null) {
            System.out.println("输入节点不能为null");
            return;
        }
        //需要一个变量来遍历链表
        /*因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        因为单链表，所以我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        因为这是单向的链表，所以只能直到两个节点的信息，因为辅助变量只有一个，
        又因为单向量表有方向性，只有通过前一个节点才能
        得到后一个节点信息。所以temp找的是插入间隙的前一个节点*/
        HeroNode temp = head;//必须指向 head
        //赋值head也是因为找的是前一个节点，防止在head后面添加节点
        while (true) {
            if (temp.next == null)//说明temp已经在链表的最后
                break;
            if (heroNode.no == temp.next.no) {//说明希望添加的heroNode的编号已然存在
                System.out.println("节点已经存在,无法添加");
                return;// 这里本来是 使用 flag 的变量来作为开关。
            } else
                if (heroNode.no < temp.next.no) //说明heroNode添加在temp 和 temp.next之间
                /*需要和后一个比较，这是防止由于单向的原因，找不到插入位置。
                这里只能和后一个比较。这里细节较为难理解。*/
                break;
            temp = temp.next;
        }
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    //2. 修改节点的信息，根据 no 编号来修改。编号不能变
    public void update(HeroNode heroNode) {
        if (heroNode == null) {
            System.out.println("所输入要修改的节点不能为 null");
            return;
        }
        /*if (head.next == null) { --------- 其实这种情况可以和找不到并在一起
            System.out.println("链表为空，无数据修改");
            return;
        }*/

        HeroNode temp = head.next;//直接寻找head下一个数据 快捷
        boolean flag = false;//判断是否找到了
        while (true) {
            if (temp == null)
                break;// 如果找到末尾，就退出。
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else
            System.out.println("没找到要修改的节点");
    }

    //3. 删除节点
    public void del(int no) {
        //需要辅助变量进行遍历。因为删除一个节点，涉及到前后共3个节点，而单向链表的方向确定，
        //所以辅助变量需要指向 要删除的节点 的上一个节点
        HeroNode temp = head;// 必须指向head,因为如果删除第一个节点，就要temp指向head。
        //或许这就是为什么必须要一个head 链表头部
        boolean loop = true;
        while (loop) {
            if (temp.next == null) {//从第一次temp=head可以推断，检测链表尾部的条件是temp.next==null
                System.out.println("遍历到尾部，未找到要修改的节点");
                return;
            }
            if (temp.next.no == no) {//找到了
                loop = false;
                break;//必须跳出循环，不能再执行 temp=temp.next
            }
            temp = temp.next;// 向后遍历
        }
        temp.next = temp.next.next;//删除节点的操作
    }

    //显示链表【遍历】
    public void list() {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，需要一个辅助变量来遍历
        HeroNode temp = head.next;//这里为了方便直接赋值head.next。也可以赋值head，此时while中的temp需要变化
        while (temp != null) {//这里也可以设置为死循环，用if来判断结束循环
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}


// 定义HeroNode，每一个HeroNode对象都是一个节点
class HeroNode {
    // 每个节点中的元素可以是多个
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点
    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示方便，重写toString
    @Override
    public String toString() {
        //return "HeroNode [no=" + no + ", name=" + name +", nickname=" + nickname + "]";
        return "HeroNode [no=" + no + ", name=" + name +", nickname=" + nickname + "]";
        /*
            因为 next指向HeroNode对象，所以 next 调用的是toString。这样如同递归一样，直到 next=null；结束
         */
    }
}
