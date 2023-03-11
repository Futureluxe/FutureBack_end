package com.future;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

@SpringBootTest
class FutureApplicationTests {

    @Test
    void contextLoads() {
    }

    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }*/
    public static void main(String[] args) {
        FutureApplicationTests f = new FutureApplicationTests();
        int[] num = new int[]{2,7,11,15};//2,7,11,15 ; 3,2,4
        num = new int[]{2,7,3,3};
        int tag = 9;//9 ; 6
//        f.ArrayNumSum(num, tag);
        System.out.println("HashMap");
        tag = 6;
        f.HashNumSum(num, tag);
    }

    void ArrayNumSum(int[] num , int tag){
        for (int i = 0; i < num.length; i++) {
            for (int j = i+1; j < num.length; j++) {
                if (tag == num[i]+num[j]){
                    System.out.println(i+"和"+j);
                }
            }
        }
    }

    void HashNumSum(int[] sum,int tag){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < sum.length; i++) {
            if (map.containsKey(tag-sum[i])) {
                System.out.println(map.get(tag-sum[i])+"和"+i);
            }
            map.put(sum[i],i);
        }
    }

    void palindrome(String str){
        char[] c ;

    }
}
