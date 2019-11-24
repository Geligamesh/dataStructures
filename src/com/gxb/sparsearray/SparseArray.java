package com.gxb.sparsearray;

import java.io.*;

/**
 * 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0：表示没有棋子，1：表示黑子 2：表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][6] = 1;
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        //将二维数组转稀疏数组的思想
        //1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        System.out.println(sum);
        //2.创建对应得到稀疏数组
        int[][] sparseArray = new int[sum+1][3];
        //3.给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //用于记录是第几个非0数据
        int count = 0;
        //4.遍历二维数组，将非0的值存放到sparseArray
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("得到的稀疏数组为:");
         for (int i = 0; i < sparseArray.length; i++) {
             System.out.printf("%d\t%d\t%d\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
         }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("map.data"));
            oos.writeObject(sparseArray);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 将稀疏数组-->恢复成原始的二维数组
        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArray[0][0]][sparseArray[0][1]];
         for (int i = 1; i < sparseArray.length; i++) {
             chessArr2[sparseArray[i][0]][sparseArray[i][1]] =  sparseArray[i][2];
         }
        // 输出恢复之后的二维数组
        System.out.println("恢复之后的二维数组:");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
        System.out.println();

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("map.data"));
            int[][] sparseArr = (int[][]) ois.readObject();
            System.out.println("从文件从恢复之后的稀疏数组:");
            for (int[] row : sparseArr) {
                for (int data : row) {
                    System.out.print(data + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
