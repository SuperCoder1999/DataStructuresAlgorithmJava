package DataStructures.linkedlist;

/*
    实现环形单链表
 */

public class Joseph {
    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(25);// 加入5个小孩节点
        circleSingleLinkedList.showBoy();

        //测试Joseph问题
        System.out.println("测试Joseph问题----");
        circleSingleLinkedList.countBoy(1, 2, 25);
    }
}

class CircleSingleLinkedList {
    private Boy first = null;//相当于单链表中的头节点。标记了环形链表的一个位置。

    //添加多个节点，一次性形成环形链表。至于在现存环形链表中添加节点方法差不多
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("最少添加一个节点");
            return;
        }
        Boy curBoy = null;
        //由于first不能动，而添加节点是在最后一个，所以需要辅助变量一直指向最后一个节点
        for (int i = 1; i <= nums ; i++) {//
            Boy boy = new Boy(i);
            if (i == 1) {//第一个节点较特殊，自己指向自己
                first = boy;
                first.setNext(boy);
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
            }
            curBoy = boy;
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //遍历同样需要辅助变量
        Boy curBoy = first;//first就是第一个节点
        while (true) {//判断条件不能在while中。因为判断最后出一个节点也要将其输出
            System.out.println(curBoy.getNo());
            curBoy = curBoy.getNext();
            if (curBoy == first)
                //最后一个节点的标志是：while用到的 curBoy 指向 first。
                // 注意由于curBoy = curBoy.getNext();现在的curBoy就是指向下一个节点
                break;
        }
    }

    //根据用户的输入，计算出圈的顺序

    /**
     * @param startNo 表示从第几个节点开始数
     * @param countNum 表示数几次
     * @param nums 表示最初有几个节点
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //因为一直有节点出去，所以first节点是不固定的，也就没有存在的价值了。
        // 而这时不妨将first节点作为辅助变量，来遍历找到出圈的节点。
        //由于每次出圈涉及三个节点，而从first辅助变量得不到前一个节点。所以
        // 还需要一个赋值变量来一直指向first(出圈)节点前一个节点
        Boy helper = first;//将helper 带入环形链表中，即helper指向first节点
        //寻找helper的初始位置
        while (helper.getNext() != first) {//while结束后helper指向最后一个节点即first前一个节点
            helper = helper.getNext();
        }
        //将first、helper同时移动到开始位置 startNo
        // （其实也可以只移动first，helper通过跟随的方式找到first后面）
        for (int i = 0; i < startNo - 1; i++) {//因为first本身在第一个位置，所以只要移动startNo-1个位置
            first = first.getNext();
            helper = helper.getNext();
        }
        //准备就绪，开始出圈
        while (helper != first) {// while结束，环形单向链表只剩一个节点。
            // 至于只剩下一个时的情况是如何的，可以从剩三个节点的情况推导.
            for (int i = 0; i < countNum - 1; i++) {
                //因为每次first指向的节点都会数一个数字，所以first后移countNum-1次
                first = first.getNext();
                helper = helper.getNext();
            }
            //找到出圈的节点，开始出圈
            System.out.println(first.getNo());//将出圈的节点展示出来
            first = first.getNext();//first向后移动到 下一轮 的第一个节点
            helper.setNext(first);//helper 指向 first 后面的节点，从而删除原本的first节点
        }
        //最后一个节点出圈- 本质没有在链表删除，但是在问题场景中，该节点是出圈的。所以输出展示出来
        System.out.println(first.getNo());//也可以用helper.getNo()。两者指向相同
    }
}


//环形单链表中的节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}