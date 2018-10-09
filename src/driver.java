public class driver {
    public static void main(String[] args) {
        BinarySearchTree<Integer, String> b= new BinarySearchTree<>();
        b.put(5,"h");
        System.out.println(b.size());
    }
    public class isBST<Key extends Comparable<Key>, Value>{

        public boolean iBST(Node n){
            boolean b = true;
            if(n.getLeft().getKey().compareTo(n.getRight().getKey())<0){
                return false;
            }
        }
    }
}
