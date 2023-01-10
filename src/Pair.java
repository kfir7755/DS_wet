public class Pair<T> implements Comparable<Pair<T>>{
    private int mainKey;
    private int secondaryKey;
    private T type;

    public Pair(int mainKey, int secondaryKey,T type) {
        this.mainKey = mainKey;
        this.secondaryKey = secondaryKey;
        this.type = type;
    }

    public int getMainKey() {
        return mainKey;
    }

    public void setMainKey(int mainKey) {
        this.mainKey = mainKey;
    }

    public int getSecondaryKey() {
        return secondaryKey;
    }

    public void setSecondaryKey(int secondaryKey) {
        this.secondaryKey = secondaryKey;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    public static Pair max(){
        return new Pair(Integer.MAX_VALUE,Integer.MIN_VALUE,null);
    }

    public static Pair min(){
        return new Pair(Integer.MIN_VALUE,Integer.MAX_VALUE,null);
    }

    @Override
    public int compareTo(Pair p) {
        if (this.getMainKey() < p.getMainKey()){
            return -1;
        }
        if (this.getMainKey() > p.getMainKey()){
            return 1;
        }
        if (this.getSecondaryKey() < p.getSecondaryKey()){
            return 1;
        }
        if (this.getSecondaryKey() > p.getSecondaryKey()){
            return -1;
        }
        return 0;
    }

    public boolean equals(Pair p){
        return this.getMainKey()==p.getMainKey() && this.getSecondaryKey()==p.getSecondaryKey();
    }
}


