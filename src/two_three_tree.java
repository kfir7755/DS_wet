public class two_three_tree<K extends Comparable,V> {
    protected Node Root;
    protected String KeyType;

    public two_three_tree(Node root, String keyType) {
        Root = root;
        KeyType = keyType;
    }

    public Node getRoot() {
        return Root;
    }

    public void setRoot(Node root) {
        Root = root;
    }

    public String getKeyType() {
        return KeyType;
    }

    public void setKeyType(String keyType) {
        KeyType = keyType;
    }

    public void two_three_init(){
        Node x,l,m;
        if (KeyType.equals("INTEGER")){
            x = new Node(Integer.MAX_VALUE);
            l = new Node(Integer.MIN_VALUE);
            m = new Node(Integer.MAX_VALUE);
        }
        else{
            x = new Node(Pair.max());
            l = new Node(Pair.min());
            m = new Node(Pair.max());
        }
        l.setP(x);
        m.setP(x);
        x.setLeft(l);
        x.setMiddle(m);
        this.setRoot(x);
    }

    public Node two_three_search(Node x, K k){
        if (x.isLeaf()){
            if(x.getKey().equals(k)) return x;
            else return null;
        }
        if (k.compareTo(x.getLeft().getKey())<=0){
            return two_three_search(x.getLeft(),k);
        }
        else if (k.compareTo(x.getMiddle().getKey())<=0){
            return two_three_search(x.getMiddle(),k);
        }
        else return two_three_search(x.getRight(),k);

    }

//    public Node
}
