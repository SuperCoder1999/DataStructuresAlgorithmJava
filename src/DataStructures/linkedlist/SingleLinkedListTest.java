package DataStructures.linkedlist;

/*
    单链表的实现

    技巧：在方法体外不能使用 new HeroNode(0,"","").var
 */

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

        //加入
        singleLinkedList.addNode(hero1);
        singleLinkedList.addNode(hero4);
        singleLinkedList.addNode(hero2);
        singleLinkedList.addNode(hero3);

        // 测试一下单链表的反转功能
        System.out.println("原来链表的情况~~");
        singleLinkedList.list();
    }
}

//定义SingleLinkedList 管理节点，最终形成单链表
class SingleLinkedList {
    //先初始化一个头节点，头节点不能动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

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
        return "HeroNode [no=" + no + ", name=" + name +", nickname=" + nickname + "]" + next;
        /*
            因为 next指向HeroNode对象，所以 next 调用的是toString。这样如同递归一样，直到 next=null；结束
         */
    }
}
