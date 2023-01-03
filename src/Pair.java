public class Pair implements Comparable<Pair>{
    private int mainKey;
    private int secondaryKey;

    public Pair(int mainKey, int secondaryKey) {
        this.mainKey = mainKey;
        this.secondaryKey = secondaryKey;
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

    public static Pair max(){
        return new Pair(Integer.MAX_VALUE,Integer.MIN_VALUE);
    }

    public static Pair min(){
        return new Pair(Integer.MIN_VALUE,Integer.MAX_VALUE);
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


