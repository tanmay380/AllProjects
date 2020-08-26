package com.company;

import javax.swing.*;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3},
                        {4, 5, 6}};
        int arr1[][] = {{1, 2, 3},
                        {4, 5, 6}};
        int[][] sum = new int[3][3];
        System.out.println(arr.length);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sum[i][j] = arr[i][j] + arr1[i][j];
            }
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(sum[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
