import java.util.*;

public class repeatString {

    public static void main(String[] args) {
        //输入数据
        String[] arr = new String[10000];
        int N;//字符串个数
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        //校验数据
        if (N < 1 || N > 100000) {
            System.out.println("Input error!");
            System.exit(0);
        }
        //输入字符串
        for(int i = 0; i<N; i++){
            arr[i] = input.next();
        }
        //算法程序
        //利用Map集合内元素不可重复的特性计算每个字符串重复次数,且hashMap效率较高
        Map<String,Integer> count = new HashMap<>();
        int maxValue = 0;
        for (String str : arr) {
            if (str==null){
                continue;
            }
            Integer num = count.get(str);
            //若重复次数为空，则为1，否则加一
            count.put(str, num == null ? 1 : num + 1);
            //获取最大value值
            num = count.get(str);
            if (num != null && num > maxValue){
                maxValue =num;
            }
        }
        //i为重复次数，allCount为此重复次数下存在的不同字符串数
        int i, allCount;
        for (i = 1,allCount=0; i <= maxValue; i++){
            Set set = count.entrySet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
                if (entry.getValue()==i){
                    allCount++;
                }
            }
            //输出结果
            if (allCount!=0 && i<maxValue){
                System.out.println(i+" "+allCount);
                allCount = 0;
            }
            //保证最后无换行符
            if (allCount!=0 && i==maxValue){
                System.out.print(i+" "+allCount);
            }
        }
    }
}

