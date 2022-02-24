import java.util.Scanner;

public class kmp {
    //target 为模式字符串，string 为定长字符串
    //当匹配成功时，返回在定长字符串中的与模式字符串一致的字符串起始下标
    //匹配失败返回 -1
    public static void getNext(int[] next,char[] target){
        int frist = 0;
        next[0] = 0;
        for(int last = 1;last < target.length; last++){
            while(frist > 0 && target[frist] != target[last]){
                frist = next[frist-1];
            }
            if(target[frist] == target[last]){
                frist++;
            }
            next[last] = frist;
        }
    }
    public static int getFristStr(char[] target,char[] string){
        if(target.length == 0){
            return 0;
        }
        int next[] = new int[target.length];
        getNext(next,target);
        int targetPointer = 0;
        for(int stringPointer = 0; stringPointer < string.length; stringPointer++){
            while(targetPointer > 0 && target[targetPointer] != string[stringPointer]){
                targetPointer = next[targetPointer-1];
            }
            if(target[targetPointer] == string[stringPointer]){
                targetPointer++;
            }
            if(targetPointer == target.length){
                return (stringPointer - target.length + 1);
            }
        }
        return -1;
    }


    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String str1 = input.next();
        String str2 = input.next();
        char[] string = str1.toCharArray();
        char[] target = str2.toCharArray();
        System.out.println(getFristStr(target,string));
    }
}
