public class quick2 {
    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 23, 22, 76,0, -1, 22};
        quickSort(arr,0, arr.length-1);
        for (int it:arr) {
            System.out.print(it+" ");
        }
    }
    public static int[] quickSort(int[] array,int begin,int last){
        if(begin > last || array.length == 0){
            return null;
        }
        if (array.length == 1){
            return array;
        }
        //临时存储，方便子序列的递归边界值的确定
        int a = begin;
        int b = last;
        //定义基准值
        int pivot = array[begin];
        while (begin < last) {
            //从右至左获取小于基准值的数
            while (begin < last && pivot <= array[last]){
                last--;
            }
            //等于不移动，右边交换后，交替移动左边
            if(pivot > array[last]){
                array[begin] = array[last];
                begin++;
            }
            //右边遍历到左指针重合，全部大于基准值，无需移动左指针，循环结束
            if(begin == last){
                array[begin] = pivot;
                break;
            }
            //从左至右获取大于基准值的数
            while (begin < last && array[begin] < pivot){
                begin++;
            }
            //
            if(pivot < array[begin]){
                array[last] = array[begin];
                last--;
            }
            //左边遍历到右指针重合，左右分堆结束
            if(begin == last){
                array[begin] = pivot;
            }
        }
        //递归调用左右子序列，
        // last 小于等于 a（begin） 无右子序列，begin 小于等于 b（last） 无左子序列
        if(last > a){
            quickSort(array,a,last-1);
        }
        if(b>begin){
            quickSort(array,begin+1,b);
        }

        return array;
    }
}
