public class HashTable {
    //采用除数取余法以及线性探查法解决问题
    public int[] key;
    public int nullKey = -1;
    public int count = 0;
    public HashTable() {
        this.key  = new int[50];
    }

    public void insertHT(HashTable ha, int key, int p, int m) {
        int i, adr;
        //哈希函数 H(Key) = Key%p (p<=m)
        adr = key % p;
        if (ha.key[adr] == nullKey){
            ha.key[adr] = key;
            ha.count = 1;
        } else {
            i = 1;
            do {
                adr = (adr + 1) % m;
            } while (ha.key[adr] != nullKey);
            ha.key[adr] = key;
            ha.count = i;
        }
    }

    public void  createHT(HashTable ha, int [] a, int n, int m, int p) {
        int i;
        for (i = 0; i < m; i++){
            ha.key[i] = nullKey;
            ha.count = 0;
        }
        for (i = 0; i < n; i++) {
            insertHT(ha, a[i], p, m);
        }
    }

    public int searchHT(HashTable ha, int p, int m, int a) {
        int adr;
        adr = a % p;
        while (ha.key[adr] != nullKey && ha.key[adr] != a) {
            adr = (adr + 1) % m;
        }
        if (ha.key[adr] == a) {
            return adr;
        } else {
            return -1;
        }
    }
}
