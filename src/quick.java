public class quick{
    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 23, 22, 76,0, -1, 22};
        quickSort(arr);
        for (int it:arr) {
            System.out.print(it+" ");
        }
    }
    /**
     * 快速排序
     * @param array 排序数组
     */
    public static void quickSort(int[] array) {
        int len;
        if(array == null
                || (len = array.length) == 0
                || len == 1) {
            return ;
        }
        sort(array, 0, len - 1);
    }

    /**
     * 快排核心算法，递归实现
     * @param array 排序数组
     * @param left 最左位置
     * @param right 最右位置
     */
    public static void sort(int[] array, int left, int right) {
        if(left > right) {
            return;
        }
        // base中存放基准数,默认最左边一个数
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while(array[j] > base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while(array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;
        System.out.println("主数组");
        for (int k = left;k <= right;k++){
            if (k != right){
                System.out.print(array[k]+" ");
            }
            else {
                System.out.println(array[k]);
            }
        }
        System.out.println("左数组");
        for (int k = left;k <= i-1;k++){
            if (k != i-1){
                System.out.print(array[k]+" ");
            }
            else {
                System.out.println(array[k]);
            }
        }
        System.out.println("右数组");
        for (int k = i+1;k <= right;k++){
            if (k != right){
                System.out.print(array[k]+" ");
            }
            else {
                System.out.println(array[k]);
            }
        }
        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }
}

