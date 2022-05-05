
import java.util.Iterator;

public class TreeSetCounter<T extends Comparable<T>> implements Iterable<T>{
    int size;
    node<T> topnode;
    int maxcount = 0;

    public TreeSetCounter(){
        size = 0;

    }

    public void add(T t){
        if(size == 0){
            topnode = new node<T>(t);
            maxcount = 1;
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
                    
                    if(curnode.count > maxcount)
                        maxcount = curnode.count;

                    break;
                }
            }
            
        }

        size++;

        

    }

    public void clear(){
        topnode = null;
    }
    public int getMaxFrequency(){
        return maxcount;
    }

    public node<T> getnode(T t){
        node<T> curNode = topnode;
        while(true){
            if(curNode.value == t)
                return curNode;
            

            if(t.compareTo(curNode.value) == 1){
                if(curNode.rightchild != null){
                    curNode = curNode.rightchild;

                }else{
                    return null;
                }
            }else{
                if(curNode.leftchild != null){
                    curNode = curNode.leftchild;
                }else{
                    return null;
                }
            }
        }
    }

    public boolean contains(T t){
        return getnode(t) != null;
    }
    
    public int size(){
        return size;
    }

    public int counter(T t){
        node<T> node = getnode(t);
        if(node != null){
            return node.count;
        }else{
            return 0;
        }

    }



    public Iterator<T> iterator(){
        return new Iterator<T>() {
            node<T> currentNode = topnode;
            String curpath = "start";
            int count = 0;
            boolean done = false;
            
            //1 = right    0 = left 
            //To get to a node follow a string as such 1000110
            //Every node gets a value dependent on the path string. 1 being equal to 1, 0 being equal to -1.
            //The most significant digit has a weight of 1 the second one a weight of 0.5  then 0.25 and so on.
            //Multiply every 1 by its weight and for every 0 multyply -1 by its weight. Then sum up all the resulting values
            //The higher the path value is, the higher the value of t in the corresponding node 
            

            public boolean hasNext(){
                if(topnode == null)
                    return false;

                return !done;
            }

            public T next(){
                if(curpath == "start"){
                   
                    curpath = "";
                    if(topnode == null){
                        return null;
                    }                                                
                    while(currentNode.rightchild != null){
                        currentNode = currentNode.rightchild;
                        curpath = curpath + "1";
                    }   
                    count = currentNode.count;
                    
                        
                }else{
                    //dont update path if items left
                    if(count == 0){
                        //Update path
                        if(currentNode.leftchild != null){
                            curpath = curpath + "0";
    
                            currentNode = topnode;
                            for(int i = 0; i < curpath.length(); i++){
                                if(curpath.charAt(i) == '1')
                                    currentNode = currentNode.rightchild;
                                else if(curpath.charAt(i) == '0')
                                    currentNode = currentNode.leftchild;
                            }
    
                            while(currentNode.rightchild != null){
                                curpath = curpath + "1";
                                currentNode = currentNode.rightchild;
                            }
                        } else if(curpath.length() > 0 && curpath.charAt(curpath.length()-1) == '1'){
                            curpath = curpath.substring(0, curpath.length()-1);
                        }else{
                            

                            

                            

                            while(curpath.length() > 0 && curpath.charAt(curpath.length()-1) == '0'){
                                curpath = curpath.substring(0, curpath.length()-1);
                            }

                            
                            
                            /*if(curpath.length() == 0){
                                
                                return null;
                            }*/
                            curpath = curpath.substring(0, curpath.length()-1);
    
                        }
                    }else{
                        count --;
                        return currentNode.value;
                    }
                    
                }
                    
                    





                //follow new path
                if(!done){

                    
                    currentNode = topnode;
                    
                    for(int i = 0; i < curpath.length(); i++){
                        if(curpath.charAt(i) == '1')
                        currentNode = currentNode.rightchild;
                        
                        else if(curpath.charAt(i) == '0')
                        currentNode = currentNode.leftchild;
                    }
                    done = true;
                            for(int i = 0; i < curpath.length(); i++){
                                if(curpath.charAt(i) == '1')
                                    done = false;
                            }

                            if(currentNode.leftchild != null)
                                done = false;
                    
                    
                    count = currentNode.count - 1;
                    return currentNode.value;
                }else{
                    currentNode = null;
                    return null;
                }
            }
        };
    }

    public static void main(String[] args) {
        TreeSetCounter<Integer> tree = new TreeSetCounter<>();
        
        tree.add(6);
        tree.add(7);
        tree.add(8);
        tree.add(20);
        tree.add(15);
        tree.add(13);
        tree.add(1);
        tree.add(4);
        tree.add(18);
        tree.add(7);
        tree.add(13);
        tree.add(3);
        tree.add(10);
        tree.add(12);
        tree.add(13);
        tree.add(17);
        tree.add(5);
        tree.add(7);
        tree.add(7);
        
        tree.add(11);
        
    
        Iterator<Integer> it = tree.iterator();

        while(it.hasNext()){

            System.out.println("  "+it.next()+"  ");
        }

        
        System.out.println(tree.counter(13));

        /*
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
            System.out.println("   "+ it.next() + "    ");
        */       

        
        
    }


}