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
        System.out.println("跟新后链表的情况~~");
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
                这里也可能是和前一个比较，需要根据单向链表的递增递减性判断
                位置找到，就在temp的后面插入*/
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
