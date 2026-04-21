import java.util.Random;

public class GridGenerator {

    static Random rand = new Random();

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

        return new int[]{r, c}; // return start coordinates
    }

    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
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
}