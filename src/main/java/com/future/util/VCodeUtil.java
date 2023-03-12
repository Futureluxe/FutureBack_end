package com.future.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Random 类提供了丰富的随机数生成方法，可以产生 boolean、int、long、float、byte 数组以及 double 类型的随机数，
 * 这是它与 random() 方法最大的不同之处。
 * random() 方法只能产生 double 类型的 0~1 的随机数
 */
@Component
public class VCodeUtil {
    /*
     * 生成n位随机验证码，包括数字和大小写字母
     * @param 验证码长度
     * @return n位验证码
     */
    public  String verifyCode() {
        StringBuilder strB = new StringBuilder();
        Random rand = new Random();

        for(int i = 0; i < 6; i++) {
            int r1 = rand.nextInt(3);// rand.nextInt(int n)
            //生成一个随机的 int 值，该值介于 [0,n)，包含 0 而不包含 n。如果想生成
            //指定区间的 int 值，也需要进行一定的数学变换
            int r2 = 0;
            switch (r1) {  // r2为ascii码值
                case 0: // 数字
                    r2 = rand.nextInt(10) + 48;  // 数字：48-57的随机数
                    break;
                case 1:
                    r2 = rand.nextInt(26) + 65;  // 大写字母
                    break;
                case 2:
                    r2 = rand.nextInt(26) + 97;  // 小写字母
                    break;
                default:
                    break;
            }
            strB.append((char)r2);
            //用于将char参数的字符串表示形式附加到给定序列。 char参数附加到此StringBuilder序列的内容之后。
            /*
                 参数：该方法接受单个参数a，该参数是Char值，该值将附加字符串表示形式。
                 返回值：执行附加操作后，该方法返回一个字符串对象。
             */
        }
        return strB.toString();
    }
}
