public class upgraded_two_three_tree<K extends Comparable<K>,V> extends two_three_tree<K,V>{

    private Node<K,V> right_sentinel;

    public upgraded_two_three_tree(Node root, K max, K min) {
        super(root, max, min);
    }

    @Override
    public void two_three_init(){
        super.two_three_init();
        this.right_sentinel = this.getRoot().getMiddle();
    }

    protected Node<K,V> insert_and_split(Node<K,V> x, Node<K,V> z){
        Node<K,V> l = x.getLeft();
        Node<K,V> m = x.getMiddle();
        Node<K,V> r = x.getRight();
        if (r == null){
            if ((z.getKey()).compareTo(l.getKey())<0){
                set_children(x, z, l, m);
            } else if ((z.getKey()).compareTo(m.getKey())<0) {
                set_children(x, l, z, m);
            } else{
                set_children(x, l, m, z);
            }
            return null;
        }
        Node<K,V> y = new Node<>(null);
        if ((z.getKey()).compareTo(l.getKey())<0) {
            set_children(x, z, l, null);
            set_children(y,m,r,null);
        } else if ((z.getKey()).compareTo(m.getKey())<0) {
            set_children(x, l, z, null);
            set_children(y,m,r,null);
        } else if ((z.getKey()).compareTo(r.getKey())<0) {
            set_children(x, l, m, null);
            set_children(y,z,r,null);
        } else {
            set_children(x, l, m, null);
            set_children(y,r,z,null);
        }
        return y;
    }
}
