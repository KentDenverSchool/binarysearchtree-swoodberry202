
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    public Node<Key, Value> root;

    public BinarySearchTree() {
         root=null;
    }


    public int size() {
        return size(root);
    }



    private int size(Node x) {
       if(x == null){
           return 0;
       }
       else{
           if (x.getLeft()!= null && x.getRight()!= null){
               return 1+ size(x.getLeft()) + size(x.getRight());
           }
           else if (x.getLeft()== null && x.getRight()!= null){
               return 1+ size(x.getRight());
           }
           else if (x.getLeft()!= null && x.getRight()== null){
               return 1+ size(x.getLeft());
           }
           else {
               return 1;
           }
        }
    }

    public boolean isEmpty() {
    if((root.getLeft().equals(null))&&(root.getRight().equals(null))){return true;}
    return false;
    }

    //recursive put wrapper
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    //recursive put
    //sets left/right or creates a new node appropriately, returns the
    //modified node n
    private Node<Key,Value> put(Node<Key, Value> n, Key key, Value val) {
        if(root.equals(null)){
            Node q= new Node(key,val,0);
            root=q;
            return root;
        }
        if (n.getRight().equals(null)&&((n.getKey()).compareTo(key)<0)){
           Node q= new Node(key,val,0);
           n.setRight(q);
           return n;
       }
        if (n.getLeft().equals(null)&&((n.getKey()).compareTo(key)>0)){
            Node q= new Node(key,val,0);
            n.setLeft(q);
            return n;
        }
        if (n.getKey().compareTo(key)>0){
            n.setLeft(put(n.getLeft(),key,val));
        }
        if (n.getKey().compareTo(key)<0){
            n.setRight(put(n.getRight(),key,val));
        }

        return null;
    }

    //recursive get wrapper
    public Value get(Key key) {
        return get(root, key);
    }

    //recursive get
    //returns null if the key does not exist
    private Value get(Node<Key, Value> n, Key key) {
        if (contains(key)==false){return null;}
        if(n.getKey().compareTo(key)==0){
            return n.getValue();
        }
        else if(n.getKey().compareTo(key)<0){
            get(n.getLeft(),key);
        }
        else if(n.getKey().compareTo(key)>0){
            get(n.getRight(), key);
        }

        return null;


    }

    public boolean contains(Key key) {
        boolean b=true;
        Node n=root;
        while (b){
            if (n.getKey().equals(key)){return true;}
            else if (n.getKey().compareTo(key)>0){
                if(n.getRight().equals(null)){return false;}
                n=n.getRight();
            }
            else if(n.getKey().compareTo(key)<0){
                if(n.getLeft().equals(null)){return false;}
                n=n.getLeft();
            }
        }
        return false;
    }

    public Value remove(Key key) {
        Value v = get(key);
        root = remove(root, key);
        return v;
    }

    private Node remove(Node<Key, Value> n, Key key) {
        if(n == null) return null;
        int i = key.compareTo(n.getKey());
        if( i < 0) {
            n.setLeft(remove(n.getLeft(), key));
        } else if(i > 0) {
            n.setRight(remove(n.getRight(), key));
        }else {
            if(n.getRight() == null) return n.getLeft();
            if(n.getLeft() == null) return n.getRight();
            Node min = min(n.getRight());
            min.setLeft(n.getLeft());
            n = n.getRight();
        }
        n.setSize(size(n.getRight()) + size(n.getLeft()) + 1);
        return n;
    }

    public Key min() {
        return min(root).getKey();
    }

    //returns the node at the left most left branch of n
    private Node<Key, Value> min(Node<Key, Value> n) {
        boolean b=true;
        Node q=n;
        while (b){
            if ((n.getLeft()).equals(null)){
                b=false;
                return n;}
            else{n=n.getLeft();}
        }
        return null;
    }

    public Key max() {
        return max(root).getKey();
    }

    //returns the node at the right most right branch of n
    private Node<Key, Value> max(Node<Key, Value> n) {
        boolean b=true;
        Node q=n;
        while (b){
            if ((n.getRight()).equals(null)){
                b=false;
                return n;}
            else{n=n.getRight();}
        }
        return null;
    }

    public String toString() {
        String temp = toString(root);
        temp = temp.substring(0, temp.length()-2);
        return "{" + temp + "}";
    }

    private String toString(Node<Key, Value> n) {
        if(n == null) return "";
        return toString(n.getLeft()) +
                n.getKey() + "=" + n.getValue()  + ", " +
                toString(n.getRight());

    }
    public boolean iBST(Node n){
        boolean b = true;
        if(n.equals(null)){return true;}
        if((n.getLeft().getKey().compareTo(n.getRight().getKey())<0)
                ||((n.getLeft().getKey().compareTo(n)>0)&&(n.getRight().getKey().compareTo(n)<0))){
            return false;
        }
        else {
            return iBST(n.getRight())&&iBST(n.getLeft());
        }
    }
}