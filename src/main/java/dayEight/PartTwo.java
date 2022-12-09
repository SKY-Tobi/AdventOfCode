package dayEight;

import java.util.List;

public class PartTwo {
    public static void execute(List<String> lines) {
        TreeMap treeMap = initializeTreeMap(lines);
        int[][] map = treeMap.getTreeMap();
        int highestScenicScore = 0;
        for (int y = 0; y < treeMap.getHeight(); y++) {
            for (int x = 0; x < treeMap.getWidth(); x++) {
                int scenicScore = calculateScenicScore(map, x, y, map[y][x]);
                if (scenicScore > highestScenicScore) {
                    highestScenicScore = scenicScore;
                }
            }
        }
        System.out.println(highestScenicScore + " is the highest possible scenic score");
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

    private static int calculateScenicScore(int[][] map, int x, int y, int treeSize) {
        int scoreToTop = 0;
        // top check
        for (int yy = y - 1; yy >= 0; yy--) {
            if (map[yy][x] >= treeSize) {
                scoreToTop++;
                break;
            }
            scoreToTop++;
        }

        int scoreToRight = 0;
        // right check
        for (int xx = x + 1; xx < map[y].length; xx++) {
            if (map[y][xx] >= treeSize) {
                scoreToRight++;
                break;
            }
            scoreToRight++;
        }

        int scoreToBottom = 0;
        // bottom check
        for (int yy = y + 1; yy < map.length; yy++) {
            if (map[yy][x] >= treeSize) {
                scoreToBottom++;
                break;
            }
            scoreToBottom++;
        }

        int scoreToLeft = 0;
        // left check
        for (int xx = x - 1; xx >= 0; xx--) {
            if (map[y][xx] >= treeSize) {
                scoreToLeft++;
                break;
            }
            scoreToLeft++;
        }
        return scoreToTop * scoreToRight * scoreToBottom * scoreToLeft;
    }
}
