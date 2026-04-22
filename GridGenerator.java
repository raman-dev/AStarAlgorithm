import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridGenerator {

    static Random rand = new Random();

    public static int[][] testGrid() {
        return new int[][] {
                { 0, 0, 0, 0, 0, 1 },
                { 1, 0, 1, 0, 0, 0 },
                { 1, 0, 1, 0, 2, 0 },
                { 1, 0, 1, 1, 0, 1 },
                { 0, 3, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };
    }

    public static int[] testStart() {
        return new int[] { 4, 1 };
    }

    public static int[][] generateGrid(int m, int n) {
        int[][] grid = new int[m][n];

        // fill grid with obstacles or open cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = rand.nextDouble() < 0.3 ? 1 : 0;
            }
        }

        placeTarget(grid);

        return grid;
    }

    public static void placeTarget(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int r, c;

        // ensure target not on obstacle
        do {
            r = rand.nextInt(m);
            c = rand.nextInt(n);
        } while (grid[r][c] == 1);

        grid[r][c] = 2;
    }

    public static int[] placeStart(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int r, c;

        // find random open cell
        do {
            r = rand.nextInt(m);
            c = rand.nextInt(n);
        } while (grid[r][c] != 0);

        grid[r][c] = 3;

        return new int[] { r, c }; // return start coordinates
    }

    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void printGridWithPath(int[][] grid, ArrayList<int[]> path) {
        char[][] digits = new char[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    digits[i][j] = ' ';
                } else if (grid[i][j] == 1) {
                    digits[i][j] = 'O';
                } else {
                    digits[i][j] = (char) (grid[i][j] + '0');
                }
            }
        }
        int[] start = path.get(0);
        int[] end = path.get(path.size() - 1);

        digits[start[0]][start[1]] = 'S';
        digits[end[0]][end[1]] = 'T';
        for (int i = 1; i < path.size() - 1; i++) {
            int[] p = path.get(i);
            digits[p[0]][p[1]] = 'x';
        }

        for (char[] row : digits) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] grid = generateGrid(6, 8);

        int[] start = placeStart(grid);

        printGrid(grid);

        System.out.println("Start position: (" + start[0] + "," + start[1] + ")");
    }

    public static int[][] loadGrid(String filename) throws IOException {
        List<int[]> rows = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            int[] row = new int[parts.length];

            for (int i = 0; i < parts.length; i++) {
                row[i] = Integer.parseInt(parts[i]);
            }

            rows.add(row);
        }

        br.close();

        int[][] grid = new int[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            grid[i] = rows.get(i);
        }

        return grid;
    }

}