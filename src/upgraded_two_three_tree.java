public class upgraded_two_three_tree<K extends Comparable<K>,V> extends two_three_tree<K,V> {

    private Node<K, V> right_sentinel;

    public upgraded_two_three_tree(K max, K min) {
        super(max, min);
    }

    @Override
    public void two_three_init() {
        super.two_three_init();
        this.right_sentinel = this.getRoot().getMiddle();
    }

    private void set_leaves_predecessors(Node<K, V> l, Node<K, V> m, Node<K, V> r) {
        if (m.isLeaf()) {
            m.setPredecessor(null);
            if (r != null) {
                r.setPredecessor(m);
//                if (r.getKey().compareTo(max) < 0) {
//                    Node<K, V> r_successor = two_three_successor(r);
//                    if (r_successor != null) {
//                        r_successor.setPredecessor(r);
//                    }
//                }
            } else {
//                if (m.getKey().compareTo(max) < 0) {
//                    Node<K, V> m_successor = two_three_successor(m);
//                    if (m_successor != null) {
//                        m_successor.setPredecessor(m);
//                    }
//                }
            }
            m.setPredecessor(l);
//            if (l.getKey().compareTo(min) > 0) {
//                Node<K, V> l_predecessor = two_three_predecessor(l);
//                if (l_predecessor != null) {
//                    l.setPredecessor(l_predecessor);
//                }
//            }
        }
    }

    @Override
    protected void set_children(Node<K, V> x, Node<K, V> l, Node<K, V> m, Node<K, V> r) {
        super.set_children(x, l, m, r);
        set_leaves_predecessors(l, m, r);
    }

    public Node<K, V> Max() {
        Node<K, V> p = right_sentinel.getP();
        if (p.getRight() != null) return p.getMiddle();
        return p.getLeft();
    }

    protected void insert(Node<K, V> z) {
        K key = z.getKey();
        super.insert(z);
        z = two_three_search(this.getRoot(), key);
        Node<K, V> z_successor = two_three_successor(z);
        if (z_successor != null) z_successor.setPredecessor(z);
        Node<K, V> z_predecessor = two_three_predecessor(z);
        if (z_predecessor != null) z.setPredecessor(z_predecessor);
    }
}
