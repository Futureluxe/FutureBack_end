package com.future;

import com.future.util.SnowflakeIDAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

@SpringBootTest(classes = FutureApplication.class)
public class FutureApplicationTests {
    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

    @Test
    void ID(){
        SnowflakeIDAlgorithm snowflakeIDAlgorithm = new SnowflakeIDAlgorithm(15, 15);
        System.out.println(snowflakeIDAlgorithm.nextId());
    }

    @Test
    void c(){
        int INF = Integer.MAX_VALUE / 2; // 无穷大
        int[][] dire = {{0, -1}, {0, -2}, {0, -3}, {-1, 0}, {-1, -1}, {-1, -2}, {-2, 0}, {-2, -1}, {-3, 0}}; // 方向数组
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt(), m = scan.nextInt(); // n行m列
            int[][] g = new int[n][m]; // 保存地图
            int[][] dp = new int[n][m]; // 保存最大值
            for (int i = 0; i < n; i++) { // 输入地图
                for (int j = 0; j < m; j++) {
                    g[i][j] = scan.nextInt();
                    dp[i][j] = -INF; // 初始化为负无穷
                }
            }
            dp[0][0] = g[0][0]; // 起点
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == 0 && j == 0) continue; // 起点
                    for (int[] d : dire) { // 遍历方向数组
                        int dx = d[0], dy = d[1]; // 方向
                        int nx = i + dx, ny = j + dy; // 新坐标
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m) { // 判断是否越界
                            dp[i][j] = Math.max(dp[i][j],dp[nx][ny]); // 更新最大值
                        }
                    }
                    dp[i][j] += g[i][j]; // 加上当前点的值
                }
            }

            System.out.println(dp[n - 1][m - 1]);
            scan.close();
        }

}
