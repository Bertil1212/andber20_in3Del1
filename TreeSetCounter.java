import java.util.Iterator;

public class TreeSetCounter<T extends Comparable<T>> implements Iterable<T>{
    int size;
    node<T> topnode;

    public TreeSetCounter(){
        size = 0;

    }

    public void add(T t){
        if(size == 0){
            topnode = new node<T>(t);
        }else{
            node<T> curnode = topnode;
            while(true){
                
                if(t.compareTo(curnode.value) == 1){
                    
                    if(curnode.rightchild == null){
                        curnode.rightchild = new node<T>(t);
                        break;
                    }else{
                        curnode = curnode.rightchild;
                    }

                }else if(t.compareTo(curnode.value) == -1){
                    
                    if(curnode.leftchild == null){
                        curnode.leftchild = new node<T>(t);
                        break;
                    }else{
                        curnode = curnode.leftchild;
                    }

                }else{
                    //same value as parent
                    curnode.addToCount();
                    break;
                }
            }
            
        }

        size++;

        

    }


    public void find(T t){
        int layer = 1;
        for(int i = 0; i < size; i++){
            
        }
    }



    public Iterator<T> iterator(){
        return new Iterator<T>() {
            node<T> currentNode = topnode;

            public boolean hasNext(){
                
                if(currentNode != null){
                    node<T> nodeToSend = currentNode;
                    return true;
                }
                return false;

            }

            public T next(){
                
                return null;

            }
        };
    }

    public static void main(String[] args) {
        TreeSetCounter<Integer> tree = new TreeSetCounter<>();
        tree.add(5);
        node<Integer> nod = new node<Integer>(6);
        
        
    }


}