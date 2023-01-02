public class Node<E> {
    private E value;
    private Node<E> left;
    private Node<E> middle;
    private Node<E> right;
    private Node<E> p;
    private E key;

    public Node(E value, Node<E> left, Node<E> middle, Node<E> right, Node<E> p, E key) {
        this.value = value;
        this.left = left;
        this.middle = middle;
        this.right = right;
        this.p = p;
        this.key = key;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getMiddle() {
        return middle;
    }

    public void setMiddle(Node<E> middle) {
        this.middle = middle;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public Node<E> getP() {
        return p;
    }

    public void setP(Node<E> p) {
        this.p = p;
    }

    public E getKey() {
        return key;
    }

    public void setKey(E key) {
        this.key = key;
    }

    public boolean isLeaf() {
        return this.right == null && this.middle == null && this.left == null;
    }
}