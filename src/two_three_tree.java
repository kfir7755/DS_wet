public class two_three_tree<K extends Comparable,V> {
    protected Node<K,V> Root;
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

    //after watching the rest of pseudocode, there's no need for minimum (or maximum)
    //but I already wrote it, so I'll leave it just in case
    public Node two_three_minimum(){
        Node x=this.getRoot();
        while (!x.isLeaf()){
            x=x.getLeft();
        }
        x = x.getP().getMiddle();
        if (this.getKeyType()=="INTEGER"){
            if (((Integer) x.getKey()).compareTo(Integer.MIN_VALUE) > 0){
                return x;
            }
            else return null;
        }
        else{
            if (((Pair) x.getKey()).compareTo(Pair.min()) > 0){
                return x;
            }
            else return null;
        }
    }

    //there should be a successor and predecessor here but there's no need to in pseudocode

    protected void update_key(Node x){
        x.setKey(x.getLeft().getKey());
        if (x.getMiddle() != null){
            x.setKey(x.getMiddle().getKey());
        }
        if (x.getRight() != null){
            x.setKey(x.getRight().getKey());
        }
    }

    protected void set_children(Node x, Node l, Node m, Node r) {
        x.setLeft(l);
        x.setMiddle(m);
        x.setRight(r);
        l.setP(x);
        if (m != null) m.setP(x);
        if (r != null) r.setP(x);
        update_key(x);
    }

    protected Node insert_and_split(Node x, Node z){
        Node l = x.getLeft();
        Node m = x.getMiddle();
        Node r = x.getRight();
        if (r == null){
            if (((K)z.getKey()).compareTo(l.getKey())<0){
                set_children(x, z, l, m);
            } else if (((K)z.getKey()).compareTo(m.getKey())<0) {
                set_children(x, l, z, m);
            } else{
                set_children(x, l, m, z);
            }
            return null;
        }
        Node y = new Node(null);
        if (((K)z.getKey()).compareTo(l.getKey())<0) {
            set_children(x, z, l, null);
            set_children(y,m,r,null);
        } else if (((K)z.getKey()).compareTo(m.getKey())<0) {
            set_children(x, l, z, null);
            set_children(y,m,r,null);
        } else if (((K)z.getKey()).compareTo(r.getKey())<0) {
            set_children(x, l, m, null);
            set_children(y,z,r,null);
        } else {
            set_children(x, l, m, null);
            set_children(y,r,z,null);
        }
        return y;
    }
}

