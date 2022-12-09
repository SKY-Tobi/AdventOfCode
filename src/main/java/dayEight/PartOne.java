package dayEight;

import java.util.List;

public class PartOne {
    public static void execute(List<String> lines) {
        TreeMap treeMap = initializeTreeMap(lines);
        int[][] map = treeMap.getTreeMap();
        int amountOfVisibleTrees = 0;
        for (int y = 0; y < treeMap.getHeight(); y++) {
            for (int x = 0; x < treeMap.getWidth(); x++) {
                // if tree is at the border, count amountOfVisibleTrees up as no check is needed
                if (x == 0 || y == 0 || x == treeMap.getWidth() - 1 || y == treeMap.getHeight() - 1) {
                    amountOfVisibleTrees++;
                } else {
                    if (isTreeIsVisible(map, x, y, map[y][x])) {
                        amountOfVisibleTrees++;
                    }
                }
            }
        }
        System.out.println(amountOfVisibleTrees + " trees are visible");
    }

    private static TreeMap initializeTreeMap(List<String> lines) {
        int width = lines.get(0).split("").length;
        int height = lines.size();
        int[][] treeMap = new int[width][height];
        for (int y = 0; y < height; y++) {
            String[] line = lines.get(y).split("");
            for (int x = 0; x < width; x++) {
                treeMap[y][x] = Integer.parseInt(line[x]);
            }
        }
        return new TreeMap(treeMap, width, height);
    }

    private static boolean isTreeIsVisible(int[][] map, int x, int y, int treeSize) {
        boolean isTreeVisibleToTop = true;
        // top check
        for (int yy = y - 1; yy >= 0; yy--) {
            if (map[yy][x] >= treeSize) {
                isTreeVisibleToTop = false;
                break;
            }
        }

        boolean isTreeVisibleToRight = true;
        // right check
        for (int xx = x + 1; xx < map[y].length; xx++) {
            if (map[y][xx] >= treeSize) {
                isTreeVisibleToRight = false;
                break;
            }
        }

        boolean isTreeVisibleToBottom = true;
        // bottom check
        for (int yy = y + 1; yy < map.length; yy++) {
            if (map[yy][x] >= treeSize) {
                isTreeVisibleToBottom = false;
                break;
            }
        }

        boolean isTreeVisibleToLeft = true;
        // left check
        for (int xx = x - 1; xx >= 0; xx--) {
            if (map[y][xx] >= treeSize) {
                isTreeVisibleToLeft = false;
                break;
            }
        }
        return isTreeVisibleToTop || isTreeVisibleToRight || isTreeVisibleToBottom || isTreeVisibleToLeft;
    }
}
