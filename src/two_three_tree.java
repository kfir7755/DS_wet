public class two_three_tree<K extends Comparable<K>,V> {
    protected Node<K,V> Root;
    protected K max;
    protected K min;

    public two_three_tree(Node<K,V> root, K max, K min) {
        Root = root;
        this.max = max;
        this.min = min;
    }

    public Node<K,V> getRoot() {
        return Root;
    }

    public void setRoot(Node<K,V> root) {
        Root = root;
    }


    public void two_three_init(){
        Node<K,V> x,l,m;
        x = new Node<>(max);
        l = new Node<>(min);
        m = new Node<>(max);
        l.setP(x);
        m.setP(x);
        x.setLeft(l);
        x.setMiddle(m);
        this.setRoot(x);
    }

    public Node<K,V> two_three_search(Node<K,V> x, K k){
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
    public Node<K,V> two_three_minimum(){
        Node<K,V> x=this.getRoot();
        while (!x.isLeaf()){
            x=x.getLeft();
        }
        x = x.getP().getMiddle();
        if ((x.getKey()).compareTo(min) > 0){
            return x;
        } else {
            return null;
        }
    }

    //there should be a successor and predecessor here but there's no need to in pseudocode

    protected void update_key(Node<K,V> x){
        x.setKey(x.getLeft().getKey());
        if (x.getMiddle() != null){
            x.setKey(x.getMiddle().getKey());
        }
        if (x.getRight() != null){
            x.setKey(x.getRight().getKey());
        }
    }

    protected void set_children(Node<K,V> x, Node<K,V> l, Node<K,V> m, Node<K,V> r) {
        x.setLeft(l);
        x.setMiddle(m);
        x.setRight(r);
        l.setP(x);
        if (m != null) m.setP(x);
        if (r != null) r.setP(x);
        update_key(x);
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


    protected void insert(Node<K,V> z){

        Node<K,V> y = this.getRoot();
        while(!z.isLeaf()) {

            if (z.getKey().compareTo(y.getLeft().getKey()) < 0) {
                y = y.getLeft();
            } else if (z.getKey().compareTo(y.getMiddle().getKey()) < 0) {
                y = y.getMiddle();
            } else {
                y = y.getRight();
            }
        }

        Node<K,V> x = y.getP();
        z = insert_and_split(x, z);

        while (x != this.getRoot()) {
            x = x.getP();
            if (z != null) {
                z = insert_and_split(x, z);
            } else {
                update_key(x);
            }
        }

        if (z != null) {
            Node<K, V> w = new Node<>(z.getKey());
            set_children(w, x, z, null);
            this.setRoot(w);
        }

    }


    protected Node<K, V> borrowOrMerge(Node<K,V> y) {

        Node<K,V> z = y.getP();
        Node<K,V> x;

        if (y == z.getLeft()) {

            x = z.getMiddle();

            if(x.getLeft() != null) {
                set_children(y, y.getLeft(), x.getLeft(), null);
                set_children(x, x.getMiddle(), x.getRight(), null);
            } else {
                set_children(x, y.getLeft(), x.getLeft(), x.getMiddle());
                //delete y
                set_children(z, x, z.getRight(), null);
            }

            return z;

        }

        if (y == z.getMiddle()) {

            x = z.getLeft();

            if(x.getRight() != null) {
                set_children(y, x.getRight(), y.getLeft(), null);
                set_children(x, x.getLeft(), x.getMiddle(), null);
            } else {
                set_children(x, x.getLeft(), x.getMiddle(), y.getLeft());
                //delete y
                set_children(z, x, z.getRight(), null);
            }

            return z;

        }

        x = z.getMiddle();

        if(x.getRight() != null) {
            set_children(y, x.getRight(), y.getLeft(), null);
            set_children(x, x.getLeft(), x.getMiddle(), null);
        } else {
            set_children(x, x.getLeft(), x.getMiddle(), y.getLeft());
            //delete y
            set_children(z, x, z.getLeft(), null);
        }

        return z;

    }


    protected void delete(Node<K,V> x) {

        Node<K,V> y = x.getP();

        if (x == y) {
            set_children(y, y.getMiddle(), y.getRight(), null);
        } else if (x == y.getMiddle()) {
            set_children(y, y.getLeft(), y.getRight(), null);
        } else {
            set_children(y, y.getLeft(), y.getMiddle(), null);
        }
        //delete x
        while (y != null) {

            if (y.getMiddle() == null) {

                if(y != this.getRoot()) {
                    y = borrowOrMerge(y);
                } else {
                    this.setRoot(y.getLeft());
                    y.getLeft().setP(null);
                    //delete y
                    return;
                }

            } else {
                update_key(y);
                y = y.getP();
            }

        }

    }



}

