import java.util.Scanner;

public class math_game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long PNum = sc.nextLong();// 3
        Long max = sc.nextLong();// 13
        Long count = sc.nextLong();// 3
        int n = 1;// 控制循环的次数
        Long prime = 1L;// 记录首项
        Long tail = 0L;// 记录尾项
        Long x = 1L;// 记录第i次东东的数目
        Long sum = 1L;//记录和
        while (n < count) {
            //尾项=首项+（n-1）* d ,d=1
            tail = prime + (PNum - 1);//每一次的尾项
            x = (x + (prime + tail) * PNum / 2) % max;
            prime += PNum;//每次更新首项prime，第一次：1 第二次：4 第三次：7
            sum += x;
            n++;
        }
        System.out.println(sum);
    }
}
