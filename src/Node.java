public class Node<K,V> {
    private K key;
    private Node left;
    private Node middle;
    private Node right;
    private Node p;
    private V value;

    public Node(K key, Node left, Node middle, Node right, Node p, V value) {
        this.key = key;
        this.left = left;
        this.middle = middle;
        this.right = right;
        this.p = p;
        this.value = value;
    }

    public Node(K key, V value) {
        this(key,null,null,null,null,value);
    }
    public Node(K key) {
        this(key,null,null,null,null,null);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getMiddle() {
        return middle;
    }

    public void setMiddle(Node middle) {
        this.middle = middle;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getP() {
        return p;
    }

    public void setP(Node p) {
        this.p = p;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean isLeaf(){
        return this.getLeft()==null && this.getMiddle()==null && this.getRight()==null;
    }
}
