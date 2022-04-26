import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T>{

    private T[] mylist;
    private int size;
    
    public MyArrayList(){
        mylist = (T[]) new Object[10];
        size = 0;
    }

    public boolean add(T t){
        
        if(size == mylist.length){
            T[] temp;
            temp = (T[]) new Object[mylist.length*2];
            for(int i = 0; i < mylist.length; i++){
                temp[i] = mylist[i];
            }

            mylist = (T[]) new Object[mylist.length*2];
            
            for(int i = 0; i < mylist.length; i++){
                mylist[i] = temp[i];
            }
        }
        
        mylist[size] = t;
        size++;
        return true;
    }

    public void add(int index, T t){
        mylist[index] = t;
    }

    public boolean contains(T t){
        for(int i = 0; i < size; i++){
            if(mylist[i] == t)
                return true;
            
        }
        return false;
    }


    public T get(int index){
        return mylist[index];
    }

    public int indexOf(T t){
        for(int i = 0; i < size; i++){
            if(mylist[i] == t){
                return i;
            }
        }
        return -1;
    }

    public T Remove(int index){
        T temp = mylist[index];
        for(int i = index+1; i < size; i++){
            mylist[i-1] = mylist[i]; 
        }
        size--;

        return temp;
    }

    public boolean remove(T t){
        boolean temp = false;
        for(int i = 0; i < size; i++){
            if(mylist[i] == t){
                temp = true;
                Remove(i);
            }
            
        }
        return temp;
    }

    public int removeAll(T t){
        int count = 0;
        for(int i = 0; i < size; i++){
            if(remove(t))
                count++;
        }
        return count;
    }

    public T set(int index, T t){
        T temp = mylist[index];
        mylist[index] = t;
        return temp;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        return false;
    }

    public int size(){
        return size;
    }

    public Iterator<T> iterator(){
        return new Iterator<T>() {
            int index = 0;
            public boolean hasNext(){
                return index < size;
            }
            public T next(){
                return mylist[index++];
            }
        };
    } 

    public void clear(){
        size = 0;
        mylist = (T[]) new Object[10];
    }

    public String toString(){
        

        String temp = "[";
        for (int i = 0; i < size; i++) {
            
            temp = temp + mylist[i];
            if(i+1 < size)
                temp = temp + ", ";
            
        }
        
        temp = temp + "]";
        return temp;
    }

    public static void main(String[] args) {
        MyArrayList<Integer> l = new MyArrayList<>();
        Iterator it = l.iterator();
        
    }
   

}