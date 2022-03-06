import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class RPN {
    public static void main(String[] args) {
        // 完成一个中缀表达式转换成后缀表达式
        // 说明
        // 1. 1+((2+3)*4)-5 => 1 2 3 + 4 * + 5 -
        // 2.因为直接对str进行操作，不方便，因此先将“1+((2+3)*4)-5” =>转成中缀表达式
        // 即“1+((2+3)*4)-5” => ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        // 3. 将得到的中缀表达式对应的list转成后缀表达式对应的list
        // 即 ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] =>  ArrayList[1, 2 ,3, +, 4, *, +, 5, -]
        Scanner in = new Scanner(System.in);
        String expression2 = in.next();
        List<String> infixExpressionList = toInfixExpressionList(expression2);
        System.out.println("中缀表达式对应List" + infixExpressionList);

        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的list" + parseSuffixExpressionList);
        System.out.println(expression2 + "结果是" + calculate(parseSuffixExpressionList));

    }

    // 方法： 将得到的中缀表达式对应的list 转换成 后缀表达式对应的list
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        // 定义两个栈
        Stack<String> s1 = new Stack<String>(); // 符号栈
        List<String> s2 = new ArrayList<String>(); // 存储中间结果的栈，因为该栈一直没有弹出过，所以使用List存储
        // 遍历ls
        for (String item : ls) {
            //如果是一个数就入栈，入栈s2,\\d+ 匹配数字且至少出现一次
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                // 如果是左括号就入栈，入栈s1
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号，则依次弹出S1栈顶的运算符，并压入S2，直到遇到左括号为止
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); // 将 ( 弹出s1栈，消除括号，小括号
            } else {
                // 当 item 的优先级，小于或者等于栈顶运算符的优先级的时候，就应该将s1栈顶的运算夫弹出并压入s2中
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将item压入栈
                s1.push(item);
            }
        }
        // 将s1中剩余的元素压入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; // 因为是存放到List，因此按顺组输出就是对应的逆波兰表达式的List

    }

    // 方法：将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String s) {
        // 定义一个List，存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0; //  这个相当于一个指针，用于遍历中缀表达式字符串s
        String str = ""; // 用于对多位数的拼接工作
        char c; // 每遍历到一个字符就放到c
        do {
            // 如果c事一个非数字，我们就需要加入到ls中去
            if ((c = s.charAt(i)) < '0' || (c = s.charAt(i)) > '9') {
                ls.add("" + c);
                i++; // 需要后移
            } else {
                // 如果是数字，需要考虑多位数的问题
                str = ""; // 先将 str 置成空串
                while (i < s.length() && ((c = s.charAt(i)) >= '0' && (c = s.charAt(i)) <= '9')) {
                    str += c; // 拼接
                    i++;
                }
                ls.add(str);
            }
        }
        while (i < s.length());
        return ls;
    }

    // 将一个逆波兰表达式，依次将数据和运算符 放入ArrayList
    public static List<String> getListString(String expression) {
        // 将 expression 分割
        String[] split = expression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }
    // 完成对逆波兰表达式的运算
    /*
      1. 从左至右扫描，将 3 和 4 压入堆栈
     2. 遇到 + 运算符，因此弹出 4 和 3 （4为栈顶元素，3 为次顶元素），计算出 3 + 4 的值，得 7， 再将 7 入栈
     3. 将 5 入栈
     4. 接下来是 X 运算符，因此弹出 5 和 7，计算出 7 X 5 = 35， 将 35 入栈
     5. 将 6 入栈
     6. 最后是 - 运算符，计算出 35 - 6（减法运算或者除法运算的时候，后缀表达式是 次顶元素  减去或除以  堆顶元素） 的值，即 29 ，由此得出最终结果
     */

    public static int calculate(List<String> ls) {
        // 创建一个栈,只需要一个栈即可
        Stack<String> stack = new Stack<>();
        // 遍历 ls
        for (String item : ls) {
            // 这里使用一个正则表达式取出数
            if (item.matches("\\d+")) {
                // 匹配的是多位数
                stack.push(item);
            } else {
                // pop 出两个数并运算，在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("符号有问题");
                }
                // 把res入栈,入栈的时候要将res转换成字符，因为我们的栈是字符串类型的
                stack.push(res + "");
            }
        }
        // 最后留在stack的数据是运算结果
        return Integer.parseInt(stack.pop());
    }

}

// 编写一个类Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 3;

    // 写一个方法，返回对应的
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
        }
        return result;
    }
}
