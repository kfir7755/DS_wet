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

    private void set_leaves_predecessors(Node<K,V> l,Node<K,V> m,Node<K,V> r){
        if (m.isLeaf()) {
            if (r != null) {
                Node<K, V> r_successor = two_three_successor(r);
                r_successor.setPredecessor(r);
                r.setPredecessor(m);
                m.setPredecessor(l);
                l.setPredecessor(two_three_predecessor(l));
            } else{
                Node<K, V> m_successor = two_three_successor(m);
                m_successor.setPredecessor(m);
                m.setPredecessor(l);
                l.setPredecessor(two_three_predecessor(l));
            }
        }
    }

    @Override
    protected void set_children(Node<K, V> x, Node<K, V> l, Node<K, V> m, Node<K, V> r) {
        super.set_children(x, l, m, r);
        set_leaves_predecessors(l,m,r);
    }
}
