public class UseHash {
    public static void main(String[] args) {
        int[] a= {7, 4, 1, 14, 100, 30, 5, 9, 20, 134};
        HashTable ht = new HashTable();
        ht.createHT(ht, a, a.length, 15, 13);

        int x = ht.searchHT(ht, 13, 15, 100);
        if (x==-1) {
            System.out.println("查找失败，不存在该元素");
        } else {
            System.out.println("查找成功，该元素在哈希表中的地址为：" + x);
        }
    }
}
