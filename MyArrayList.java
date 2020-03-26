import java.util.Arrays;
//顺序表：
// 静态顺序表：使用定长数组存储。
//动态顺序表：使用动态开辟的数组存储。
//静态顺序表适用于确定知道需要存多少数据的场景
public class MyArrayList {

    private int[] elem;//null
    private int usedSize;//0

    //设置默认容量
    private static final int DEFAULT_SIZE = 3;

    public MyArrayList() {
        this.elem = new int[DEFAULT_SIZE];
        this.usedSize = 0;
    }

    //动态顺序表：
    // 打印顺序表
    public void display() {
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(this.elem[i]+" ");
        }
        System.out.println();
    }

    private void grow() {
        this.elem =  Arrays.copyOf(this.elem,this.elem.length*2);
    }

    // 在 pos 位置新增元素
    public void add(int pos, int data) {
        if(isFull()) {
            /*System.out.println("顺序表是满的！");
            return;*/
            //throw new RuntimeException("顺序表是满的");//异常

            //扩容
            grow();
        }
        checkPos(pos);
        //开始挪数据
        for (int i = this.usedSize-1; i >= pos; i--) {
            this.elem[i+1] = this.elem[i];
        }
        this.elem[pos] = data;
        this.usedSize++;
    }

    public boolean isFull() {
        if(this.usedSize == this.elem.length) {
            return true;
        }
        return false;
    }

    // 判定是否包含某个元素
    public boolean contains(int toFind) {
        if(isEmpty()) {
            /*System.out.println("空！");
            return false;*/
            throw new RuntimeException("顺序表为空！");
        }
        for (int i = 0; i < this.usedSize; i++) {
            if(this.elem[i] == toFind) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        if(this.usedSize == 0) {
            return true;
        }
        return false;
    }

    // 查找某个元素对应的位置
    public int search(int toFind) {
        if(isEmpty()) {
            throw new RuntimeException("顺序表为空！");
        }
        for (int i = 0; i < this.usedSize; i++) {
            if(this.elem[i] == toFind) {
                return i;
            }
        }
        return -1;
    }
    //这个方法  只是提供给我内部使用
    private void checkPos(int pos) {
        if(pos < 0 || pos > this.usedSize) {
            throw new ArrayIndexOutOfBoundsException("pos位置不合法");
        }
    }
    // 获取 pos 位置的元素
    public int getPos(int pos) {
        if(isEmpty()) {
            throw new RuntimeException("顺序表为空！");
        }
        checkPos(pos);
        return this.elem[pos];
    }

    //删除第一次出现的关键字key
    public void remove(int key) {
        //1、调用search方法  看返回值  -1  没有这个key  那么删除就不要了
        int index = search(key);
        if(index == -1) {
            return;
        }
        //2、index   i = index   [i] = [i+1]   i < size-1
        for (int i = index; i < this.usedSize-1 ; i++) {
            this.elem[i] = this.elem[i+1];
        }
        //3、size = size-1
        this.usedSize--;
    }

    // 获取顺序表长度
    public int size() {
        return this.usedSize;
    }

    // 清空顺序表
    public void clear() {
        this.usedSize = 0;
    }

}
