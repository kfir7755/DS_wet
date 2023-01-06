public class Node<K,V> {
    private K key;
    private Node<K,V> left;
    private Node<K,V> middle;
    private Node<K,V> right;
    private Node<K,V> p;
    private V value;

    public Node(K key, Node<K,V> left, Node<K,V> middle, Node<K,V> right, Node<K,V> p, V value) {
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

    public Node<K,V> getLeft() {
        return left;
    }

    public void setLeft(Node<K,V> left) {
        this.left = left;
    }

    public Node<K,V> getMiddle() {
        return middle;
    }

    public void setMiddle(Node<K,V> middle) {
        this.middle = middle;
    }

    public Node<K,V> getRight() {
        return right;
    }

    public void setRight(Node<K,V> right) {
        this.right = right;
    }

    public Node<K,V> getP() {
        return p;
    }

    public void setP(Node<K,V> p) {
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
