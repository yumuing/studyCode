import java.util.Scanner;
public class SequenceValue {
    public static void main(String[] args) {
        long n,a,b,c,k;
        Scanner input = new Scanner(System.in);
        n = input.nextLong();
        for (int i = 0; i < n; i++) {
            a = input.nextLong();
            b = input.nextLong();
            c = input.nextLong();
            k = input.nextLong();
            System.out.println(value(a,b,c,k));
        }
    }

    private static long value(long a, long b, long c, long k) {
        long result ;
        //等差数列公式：An = A1 + (n-1)*d （d 为公差）
        //等比数列公式：An = A1 * r^(n-1) （r 为公比）
        //等比数列累乘可能会超时，需要运用快速幂求解幂运算。
        if((c-b)==(b-a)) {
            result=(a+(k-1)%200907*(b-a)%200907)%200907;
        }else {
            result=a%200907*qp((b/a)%200907,k-1,200907)%200907;
        }
        return result;
    }
    public static long qp(long base,long power,long p){
        long result = 1;
        while (power > 0) {
            if((power & 1) == 1) {
                //(power & 1) == 1 等同于 power % 2 == 1 判断是否为奇数
                // 位运算效率高于模运算
                result = result * base % p;
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

}