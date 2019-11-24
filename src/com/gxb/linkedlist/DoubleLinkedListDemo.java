package com.gxb.linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("双向链表的测试：");
        DoubleLinkedList.HeroNode heroNode1 = new DoubleLinkedList.HeroNode(5, "宋江","及时雨");
        DoubleLinkedList.HeroNode heroNode2 = new DoubleLinkedList.HeroNode(6, "卢俊义","玉麒麟");
        DoubleLinkedList.HeroNode heroNode3 = new DoubleLinkedList.HeroNode(7, "吴用","智多星");
        DoubleLinkedList.HeroNode heroNode4 = new DoubleLinkedList.HeroNode(8, "林冲","豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.addByOrder(heroNode2);
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.addByOrder(heroNode1);
        doubleLinkedList.list();

        // System.out.println("修改之后：");
        // DoubleLinkedList.HeroNode heroNode5 = new DoubleLinkedList.HeroNode(5,"公孙胜","入云龙");
        // doubleLinkedList.update(heroNode5);
        // doubleLinkedList.list();
        // System.out.println("删除之后：");
        // doubleLinkedList.delete(8);
        // doubleLinkedList.list();
    }
}

class DoubleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;//标志是否找到待删除节点
        while (true) {
            if (temp == null) {//已经遍历到链表的最后了
                break;
            }
            if (temp.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            //单向链表
            // temp.next = temp.next.next;
            temp.pre.next = temp.next;
            //这句代码有危险
            //如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的节点%d不存在\n",no);
        }
    }

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
            heroNode.pre = temp;
            if (heroNode.next != null) {
                heroNode.next.pre = heroNode;
            }
        }
    }

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
        heroNode.pre = temp;
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

    static class HeroNode {
        private int no;
        private String name;
        private String nickname;
        private HeroNode next;//指向下一个节点,默认为null
        private HeroNode pre; //指向前一个节点，默认为null

        public HeroNode(int no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
            this.next = null;
            this.pre = null;
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
}
