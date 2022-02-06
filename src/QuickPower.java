import java.util.Scanner;

public class QuickPower {
/**
 * 若求某一个指数的最后几位数，几位数，取模数即为 10 的几倍
 * @param base 底数
 * @param power  指数
 * @param p 取模数
 * 算法思路：
 * (a * b * c) % d = (a % d * b % d * c % d) % d;
 * a^b * a^b = (a * a)^b
 * 5^10 % 3 =（5^5 * 5 * 5）% 3
 *          = 25^5 % 3
 *          =（25 * 25^4）% 3
 *          =（25 % 3） *（25^4 % 3））% 3
 * result = 25 % 3 保存每次奇数时，单独乘的底数取模
 */
    public static int qp(long base,long power,int p){
        int result = 1;
        while (power > 0) {
            if((power & 1) == 1) {
                //(power & 1) == 1 等同于 power % 2 == 1 判断是否为奇数
                // 位运算效率高于模运算
                result = (int) (result * base % p);
            }
            //指数缩小为原来的一半，
            //若为奇数，即结果减一再折半，如下
            //power = (power -1) / 2
            //(power >>= 1) == 1 等同于 power /= 2
            //位运算效率高于模运算
            power >>= 1;
            //底数扩大为原来的平方
            base = (base * base) % p;
        }
        return result;
    }
    public static void main(String[] args) {
        long a,b;
        int p;
        Scanner input = new Scanner(System.in);
        while(true){
            a = input.nextLong();
            b = input.nextLong();
            p = input.nextInt();
            System.out.println(qp(a,b,p));
        }
    }
}
