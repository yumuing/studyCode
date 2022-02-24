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
 * 10 = 1 * 2^3 + 1 * 2^1        1010
 * 5^10 % 3 =（5^5 * 5^5）% 3     101  0 2^0    result不乘
 *          = 25^5 % 3
 *          =（25 * 25^4）% 3     10   1  2^1     result*5^2=1*25
 *          =（25 % 3） *（25^4 % 3））% 3      1    0  2^2  result不乘
 *          =（25 % 3） *（625^2 % 3））% 3
 *          =（25 % 3） *（390625 % 3））% 3    0    1  2^3  result*5^(2^3)=25*390625
 *          power = 0 循环结束 即 5^10 = 5^2 * 5^8 = 9765625
 * result = 25 % 3 保存每次奇数时，单独乘的底数取模
 */
    public static long qp(long base,long power,Long p){
        long result = 1;
        while (power > 0) {
            if((power & 1) == 1) {
                //(power & 1) == 1
                // 等同于 power % 2 == 1 判断是否为奇数
                // 位运算效率高于模运算
                result = result * base % p;
            }
            //指数缩小为原来的一半，
            //若为奇数，即结果减一再折半，如下
            //power = (power -1) / 2
            //(power >>= 1) == 1 等同于 power /= 2
            //位运算效率高于模运算,右移
            power >>= 1;
            //底数扩大为原来的平方
            base = (base * base) % p;
        }
        return result;
    }

    public static void main(String[] args) {
        long a,b,p;
        Scanner input = new Scanner(System.in);
        a = input.nextLong();
        b = input.nextLong();
        p = input.nextLong();
//        以下代码即可打印出b的逆序二进制
//        while (b>0){
//            System.out.print((b & 1)+" ");
//            b >>= 1;
//        }
        System.out.println(qp(a,b,p));
    }
}
