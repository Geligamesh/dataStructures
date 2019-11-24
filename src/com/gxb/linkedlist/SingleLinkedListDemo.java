package com.gxb.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江","及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义","玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用","智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲","豹子头");

        System.out.println("反转之前：");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.list();
        System.out.println("逆序打印：");
        SingleLinkedList.reversePrint(singleLinkedList.getHead());
        System.out.println("反转之后：");
        singleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        // System.out.println("修改之后：");
        // HeroNode newHeroNode = new HeroNode(3, "无用", "智障");
        // singleLinkedList.update(newHeroNode);
        // singleLinkedList.list();
        // System.out.println("删除之后：");
        // singleLinkedList.delete(1);
        // singleLinkedList.delete(4);
        // singleLinkedList.delete(5);
        // singleLinkedList.list();
        //
        // //测试一下求单链表中有效节点的个数
        // System.out.println(SingleLinkedList.getLength(singleLinkedList.getHead()));

    }
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点,头结点不要动,不存放具体的内容
    private HeroNode head = new HeroNode(0, "", "");

    //返回头结点
    public HeroNode getHead() {
        return head;
    }

    //方式2：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果.
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表中的所有节点都压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的元素进行打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //将单链表进行反转
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针（变量）,帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next;
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            //先暂时保存当前节点的下一个节点，因为后面需要使用
            next = cur.next;
            //将cur的下一个加点指向链表的最前端
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        //将head.next指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }


    //查找单链表中的倒数第k个节点【新浪面试题】
    //思路：
    //1.编写一个方法，接收head节点，同时接收一个index
    //2.index表示是倒数第index个节点
    //3.先把链表从头到尾遍历， 得到链表的总的长度getLength
    //4.得到size后，我们从链表的第一个开始遍历（size-index）个，
    public HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        //第一次遍历得到链表的长度(节点个数)
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        //第二次遍历size-index位置，也就是我们倒数的第k个位置
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     *
     * @param head 链表的头结点
     * @return 返回的是就是有效节点的个数
     */
    //方法：获取到单链表的节点的个数（如果是带头节点的链表，需求不统计头结点）
    public static int getLength(HeroNode head) {
        //空链表
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //添加节点到单向链表
    //当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认为false
        while(true) {
            if(temp.next == null) {//说明temp已经在链表的最后
                break; //
            }
            if(temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在

                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        //判断flag 的值
        if(flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    public void update(HeroNode newHeroNode) {
        HeroNode temp = this.head.next;
        boolean flag = false;

        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.printf("没有找到编号为%d的英雄\n",newHeroNode.no);
        }
    }

    //删除节点
    //1.head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2.说明我们在比较是，是temp.next.no和需要删除的节点的no比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点
        while (true) {
            if (temp.next == null) {//已经遍历到链表的最后了
                break;
            }
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的节点%d不存在\n",no);
        }
    }
}

//定义一个HeroNode，每一个HeroNode对象为一个节点
class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
        this.next = null;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
