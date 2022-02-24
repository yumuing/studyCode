import java.util.Scanner;

public class PrimeNumbers {
    //求从2到n的质数
    //要求速度快，2<n<100000000
    public static void primeBNumbers(long b){
        boolean[] flag = new boolean[100000000];
        for(int i = 2 ;i * i < b;i++){
            if (flag[i]){
                //把他所有的倍数都做标记
                for(int j = i * i; j < b; j += i){
                    flag[j] = true;
                }
            }
        }
        for (int i = 2;i < b;i++){
            if (!flag[i]){
                System.out.println(i);
            }
        }
    }
    public static boolean IsPrime(int a){
        //单一数字效率客观，范围内多个数字判断效率较低，采用素数筛选法解决
        if (a == 1){
            //1既不是质数也不是合数
            return false;
        }
        // 在 2 到 sqrt(a) 中，找不到可以整除的数就为质数
        // 利用i*i<=a,代替i <= sqrt(a),提高效率
        for (int i = 2 ;i*i<=a;i++){
            if (a%i==0){
                return false;
            }
        }
        return true;
    }
    private static void f(long a){
        for (int i = 2;i<a;i++){
            if (IsPrime(i)){
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();    //获取开始时间
        //对x的倍数进行标记时只要从x*x开始筛选标记即可
        long b;
        Scanner input = new Scanner(System.in);
        b = input.nextLong();
//        primeBNumbers(b);
        f(b);
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
}