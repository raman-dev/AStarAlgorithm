import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

class Main{

    // static Pattern connectionPattern = Pattern.compile("[a-z]+\\-[a-z]+:\\d+");
    // static Matcher matcher = connectionPattern.matcher("");
    static int[][] dirs = new int[][]{
        {1,0},
        {0,1},
       {-1,0},
        {0,-1}
    };

    public static void main(String[] args){
        System.out.println("Running Algorithm");
        System.out.println(Arrays.toString(args));
        // int[][] grid = GridGenerator.testGrid();
        int[][] grid = null;
        int[] start = null;
        if (args.length > 0){
            try {
             System.out.println("Loading From file: "+args[0]);
             grid = GridGenerator.loadGrid(args[0]);   
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
             grid = GridGenerator.generateGrid(12,12);
             start = GridGenerator.placeStart(grid);
        }
        // int[] start = GridGenerator.testStart();
        int[] target = new int[]{0,0};

        //find targt
        for (int i = 0;i < grid.length;i++){
            for (int j = 0;j < grid[0].length;j++){
                if (grid[i][j] == 2){
                    target[0] = i;
                    target[1] = j;
                }else if (grid[i][j] == 3 && start == null){
                    start = new int[]{i,j};
                }
            }
        }

        aStarFindPath(grid, start, target);
    }

    public static int heuristic(int sr,int sc,int tr,int tc){
        return Math.abs(sr - tr) + Math.abs(sc - tc);//manhattan distance
    }

    
    
    
    public static ArrayList<int[]> reconstructPath(HashMap<Integer,Integer> came_from,Integer startIndex,int rows,int cols){
        ArrayList<int[]> result = new ArrayList<>();
        
        Integer next = startIndex;
        int runs = 0;
        while (came_from.containsKey(next)){
            result.add(new int[]{next / cols,next % cols});
            next = came_from.get(next);
            
            runs ++;
            
        }
        Collections.reverse(result);
        return result;
    }

    public static boolean isBlocked(int r,int c,int[][] grid){
        return grid[r][c] == 1;
    }
    //find the shortest path from start to goal node
    public static void aStarFindPath(int[][] grid,int[] start,int[] target){
        //frontier
        //came_from
        //cost_to
        //hfunction
        GridGenerator.printGrid(grid);

        int rows = grid.length;
        int cols = grid[0].length;

        PriorityQueue<int[]> frontier = new PriorityQueue<>((a,b)->{
            return a[a.length - 1] - b[b.length - 1];
        });

        frontier.add(new int[]{start[0],start[1],0});

        HashMap<Integer,Integer> cost_to = new HashMap<>();
        HashMap<Integer,Integer> came_from = new HashMap<>();

        cost_to.put(start[0] * cols + start[1],0);
        came_from.put(start[0] * cols + start[1],null);
        int targetIndex = target[0] * cols + target[1];

        while (!frontier.isEmpty()){
            int[] current = frontier.poll();
            int r = current[0];
            int c = current[1];
            int cost = current[2];
            int currIndex = r * cols + c;

            if (currIndex == targetIndex){
                break;
            }

            for (int[] d : dirs){
                int nr = r + d[0];
                int nc = c + d[1];
                
                //valid index
                if (nr < rows && nr >=0 && nc < cols && nc >= 0 && !isBlocked(nr,nc,grid)){
                    int index = nr * cols + nc;

                    if (!cost_to.containsKey(index) || cost_to.get(index) > cost + 1){
                        int priority = cost + 1 + heuristic(nr,nc,target[0],target[1]);
                        cost_to.put(index,cost + 1);
                        came_from.put(index,currIndex);
                        frontier.add(new int[]{nr,nc,priority});
                    }
                }
            }
        }
        
        System.out.println("complete!");
        ArrayList<int[]> path = reconstructPath(came_from, targetIndex, rows, cols);

        if (path.size() == 0){
            System.out.println("Unreachable Target!");
            return;
        }
        // for (int[] p : path){
        //     System.out.println(Arrays.toString(p));
        // }
        GridGenerator.printGridWithPath(grid, path);
    }
}