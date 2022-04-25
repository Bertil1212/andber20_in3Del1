public class DataBuffer<T> /*implements Iterable<T>*/ {

    private int front = 0;
    private int back = -1;
    private int bufferSize = 10;
    private T[] buffer;


    public DataBuffer(int size) {
        buffer = (T[]) new Object[size];
        bufferSize = size;
    }
    

    public void enqueue(T t){
        
    }

    public static void main(String[] args) {
        System.out.println("Hej");
    }
}