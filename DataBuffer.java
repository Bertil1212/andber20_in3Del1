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
    

    public void enqueue(T t){
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

    public T dequeue(){
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

    public void changeBufferSize(int newBufferSize){
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

    public void printbuffer(){ //Print not working 
        String temp = "[";
        for(int i= 0; i < size; i++){
            if(i+front > size-1)
            temp = temp + buffer[i];
            if(i != back)
                temp = temp + ", ";
        }
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
                if(temp > size)
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
        System.out.println(db.front + " " + db.back);
        db.printbuffer();
        db.dequeue();
        db.dequeue();
        db.dequeue();
        System.out.println(db.front + " " + db.back);
        db.printbuffer();
        db.enqueue(2);
        db.enqueue(2);
        db.enqueue(3);
        System.out.println(db.front + " " + db.back);

        db.printbuffer();

       /* Iterator it = db.iterator();
        while(true){
            if(it.hasNext() == false)
                break;
            
            System.out.println(it.next());
        }*/
    }
}