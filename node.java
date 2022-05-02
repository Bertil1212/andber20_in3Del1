public class node<T extends Comparable<T>> {
    
    T value;
    node<T> leftchild;
    node<T> rightchild;
    int count = 1;

    public node(T value, node<T> leftchild, node<T> rightchild){
        this.value = value;
        this.leftchild = leftchild;
        this.rightchild = rightchild;
    }
    public node(T value){
        this.value = value;
    }

    public void addToCount(){
        count ++;
    }
    public void subFromCount(){
        count --;
    }

    public int returnCount(){
        return count;
    }

    public int compareTo(node<T> cmpnode){
        return this.value.compareTo(cmpnode.value);
    }




}
