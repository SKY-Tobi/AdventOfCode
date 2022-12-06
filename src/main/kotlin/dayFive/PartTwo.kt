package dayFive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class PartTwo {
    private static final int SHIP_HEIGHT = 40;
    private static final String EMPTY_CONTAINER = "    ";

    public static void execute(List<String> lines) {
        Ship ship = analyseShipSize(lines);
        String[][] cargo = ship.getCargo();
        AtomicBoolean hasFieldBeenInitialized = new AtomicBoolean(false);
        IntStream.range(0, lines.size()).forEach(index -> {
            String line = lines.get(index);
            if (!hasFieldBeenInitialized.get()) {
                try {
                    // check if line only contains numbers
                    Integer.parseInt(line.replaceAll("\\s", ""));
                    hasFieldBeenInitialized.set(true);
                } catch (Exception ignored) {
                    Pattern p = Pattern.compile("\\s{4}|(\\[[A-Z]\\])");
                    Matcher m = p.matcher(line);
                    int i = 0;
                    while (m.find()) {
                        if (!m.group().trim().isEmpty())
                            cargo[index + SHIP_HEIGHT - ship.getInitializeHighestPoint()][i] = m.group() + " ";
                        i++;
                    }
                }
            } else {
                //get input (amount to move, fromColumn and toColumn) (3 Input parameters else skip)
                List<String> splitInput = new ArrayList<>();
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(line);
                while (m.find()) {
                    splitInput.add(m.group());
                }

                if (splitInput.size() == 3) {
                    int amount = Integer.parseInt(splitInput.get(0));
                    int fromX = Integer.parseInt(splitInput.get(1)) - 1;
                    int toX = Integer.parseInt(splitInput.get(2)) - 1;
                    List<String> tempCrane = new ArrayList<>();
                    int amountLeftToMove = amount;
                    for (int y = 0; amountLeftToMove != 0 && y < SHIP_HEIGHT; y++) {
                        if (!cargo[y][fromX].trim().isEmpty()) {
                            tempCrane.add(cargo[y][fromX]);
                            cargo[y][fromX] = EMPTY_CONTAINER;
                            amountLeftToMove--;
                        }
                    }
                    //reverse list since the get placed in the same order again
                    Collections.reverse(tempCrane);

                    //find Index where container starts (when column is empty, then SHIP_HEIGHT + 1)
                    int highestContainerIndex = SHIP_HEIGHT;
                    for (int y = 0; highestContainerIndex == SHIP_HEIGHT && y < SHIP_HEIGHT; y++) {
                        if (!cargo[y][toX].trim().isEmpty()) {
                            highestContainerIndex = y;
                        }
                    }
                    try {
                        //start filling column to the top till no more container are left to move
                        for (int y = highestContainerIndex - 1; y > highestContainerIndex - 1 - tempCrane.size(); y--) {
                            cargo[y][toX] = tempCrane.get(highestContainerIndex - 1 - y);
                        }
                    } catch (Exception e) {
                        System.err.println("amount: " + amount + ", fromX: " + fromX + ", toX " + toX);
                        System.exit(4);
                    }
                } else {
                    System.out.println("line skipped: " + index);
                }
            }
        });
        System.out.println("End State of Cargos: ");
        for (String[] row : cargo) System.out.println(Arrays.toString(row));
        System.out.println();
        //Get Highest Container Letter
        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 0; x < ship.getCargoColumns(); x++) {
            for (int y = 0; y < SHIP_HEIGHT; y++) {
                if (!cargo[y][x].trim().isEmpty()) {
                    Pattern p = Pattern.compile("[A-Z]");
                    Matcher m = p.matcher(cargo[y][x]);
                    if (m.find()) {
                        stringBuilder.append(m.group());
                        break;
                    }
                }
            }
        }
        System.out.println("All letters of highest Container of a column combined: " + stringBuilder);
    }

    private static Ship analyseShipSize(List<String> lines) {
        Ship ship = new Ship();
        // analyse amount of columns by finding the row with column labeling (1 2 3 ...) which only contains numbers
        int x = 0;
        int y = 0;
        for (String line : lines) {
            try {
                // check if line only contains numbers
                Integer.parseInt(line.replaceAll("\\s", ""));
                // pulls all numbers out and saves the last one which should be the highest
                x = Integer.parseInt(line.replaceAll("\\s", "").substring(line.replaceAll("\\s", "").length() - 1));
                ship.setCargoColumns(x);
                ship.setInitializeHighestPoint(y);
                break;
            } catch (Exception ignored) {
                y++;
            }
        }
        ship.setCargo(new String[SHIP_HEIGHT][x]);
        for (String[] array : ship.getCargo()) Arrays.fill(array, "    ");
        return ship;
    }
}
