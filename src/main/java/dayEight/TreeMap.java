package dayEight;

public class TreeMap {
    private int[][] treeMap;
    private int width;
    private int height;

    public TreeMap(int[][] treeMap, int width, int height) {
        this.treeMap = treeMap;
        this.width = width;
        this.height = height;
    }

    public int[][] getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(int[][] treeMap) {
        this.treeMap = treeMap;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
