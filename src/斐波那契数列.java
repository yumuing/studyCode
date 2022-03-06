import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;

public class 斐波那契数列 {
//    f(x)  =  1  ....  (x=1,2)
//    f(x)  =  f(x-1)  +  f(x-2)  ....  (x> 2)
//    public static long f1(long n){
//        long res = 0;
//        if (n==0||n==1){
//            return n;
//        }
//        else {
//            return f1(n-1)+f1(n-2);
//        }
//    }
    public static BigInteger f1(BigInteger n){
        BigInteger res = new BigInteger("0");
        BigInteger[] num = new BigInteger[5];
        num[0] = BigInteger.ONE;
        num[1] = new BigInteger("2");
        BigInteger i = new BigInteger("2");
        for (;i!=i.max(n);i.add(BigInteger.ONE)){
            num[2] = num[0].add(num[1]) ;
            res = res.add(num[2]) ;
            num[1] = num[2];
            num[0] = num[1];
        }
        return res;
    }
    public static BigInteger f2(BigInteger n,BigInteger m,BigInteger p){
        BigInteger res1[]  = f1(n).divideAndRemainder(f1(m));
        BigInteger res2[] = res1[1].divideAndRemainder(new BigInteger("p"));
        return res2[1];
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BigInteger n = in.nextBigInteger();
        BigInteger m = in.nextBigInteger();
        BigInteger p = in.nextBigInteger();
        System.out.println(f2(n,m,p));
    }
}
