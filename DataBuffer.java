import java.util.Iterator;


public class DataBuffer<T> implements Iterable<T> {

    private int front = 0;
    private int back = -1;
    private int bufferSize = 10;
    private T[] buffer;
    private int size = 0;


    public DataBuffer(int size) {
        buffer = (T[]) new Object[size];
        bufferSize = size;
    }
    

    public synchronized void enqueue(T t){
        if(size == bufferSize)
            System.out.println("Buffer Full");
        else{
            size++;
            back++;
            if(back == bufferSize)
                back = 0;
            buffer[back] = t;
        }
    }

    public synchronized T dequeue(){
        T temp = null;
        if(size != 0){
            size--;
            temp = buffer[front];
            front++;

            if(front == bufferSize)
                front = 0;
        }
        return temp;
        

    }

    public synchronized void changeBufferSize(int newBufferSize){
        T[] temp = (T[]) new Object[newBufferSize];
        int steps;
        int count = 0;
        if(newBufferSize > bufferSize)
            steps = bufferSize; 
        else
            steps = newBufferSize;
        
        for(int i = 0; i < steps; i ++){
            temp[i] = buffer[front+i];
        }
        front = 0;
        back = steps-1;
        buffer = (T[]) new Object[newBufferSize];

        for(int i = 0; i < steps; i++){
            buffer[i] = temp[i];
        }
        bufferSize = newBufferSize;
    }

    public void printbuffer(){ 
        String temp = "[";
        for(int i= front; i < front+size; i++){
            if(i > size-1)
                temp = temp + buffer[i-size];
            else
                temp = temp + buffer[i];
            temp = temp + ", ";
        }
        temp = temp.substring(0, temp.length()-2);
        temp = temp + "]";
        System.out.println(temp);
    }

    public boolean isFull(){
        return size == bufferSize;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int bufferSize(){
        return bufferSize;
    }

    public Iterator<T> iterator(){
        return new Iterator<T>() {
            int index = 0;

            public boolean hasNext(){
                if(index > size-1)
                    return false;
                else
                    return true;
            }

            public T next(){
                
                int temp = front+index;
                if(temp > size-1)
                    temp = temp - (size);
                
                index++; 
                return buffer[temp];

            }
        };
    }

    public static void main(String[] args) {
        DataBuffer<Integer> db= new DataBuffer<>(10);
        
        
        

        db.enqueue(1);
        db.enqueue(2);
        db.enqueue(3);
        db.enqueue(4);
        db.enqueue(5);
        db.enqueue(6);
        db.enqueue(7);
        db.enqueue(8);
        db.enqueue(9);
        db.enqueue(10); 
    }
}