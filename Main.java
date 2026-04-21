
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main{

    // static Pattern connectionPattern = Pattern.compile("[a-z]+\\-[a-z]+:\\d+");
    // static Matcher matcher = connectionPattern.matcher("");
    public static void main(String[] args){
        System.out.println("Running Algorithm");
        /*
            faster way to create connections between node string parse 
        */
        // String connections = """
        //     s-a:1
        //     s-g:10
        //     a-c:1
        //     a-b:2
        //     b-d:5
        //     c-d:3
        //     c-g:4
        //     d-g:2
        // """;
        // HashMap<Node,ArrayList<Edge>>  adjMap = createFromTemplate(connections);
        // // aStarFindPath();
        // // System.out.println(adjMap);
        // adjMap.forEach((k,v) -> {
        //     if (v.size() > 0){
        //         System.out.println(k + v.toString());
        //     }
        // });

        /*
            
        */
    }

    // public static HashMap<Node,ArrayList<Edge>> createFromTemplate(String connectionTemplate){
        
    //     HashMap<String,Node> labelMap = new HashMap<>();
    //     HashMap<Node,ArrayList<Edge>> adjMap = new HashMap<>(); 

    //     // String[] lines = connectionTemplate.split("\n");
    //     matcher.reset(connectionTemplate);
    //     while (matcher.find()){
    //         String next = matcher.group();

    //         String[] connectWeight = next.split(":");
    //         String[] ab = connectWeight[0].split("-");

    //         int weight = Integer.parseInt(connectWeight[1]);
            
    //         String labelA = ab[0].trim();
    //         String labelB = ab[1].trim();

    //         Node a = labelMap.get(labelA);
    //         Node b = labelMap.get(labelB);

    //         if (a == null){
    //             a = new Node(labelA);
    //             labelMap.put(labelA,a);
    //             adjMap.put(a,new ArrayList<Edge>());
    //         }

    //         if (b == null){
    //             b = new Node(labelB);
    //             labelMap.put(labelB,b);
    //             adjMap.put(b,new ArrayList<Edge>());
    //         }
            
    //         Edge edge = new Edge(a,b,weight);
    //         adjMap.get(a).add(edge);
    //         // }
    //     }

    //     return adjMap;
    // }

    //do what exactly? 
    //find the shortest path from start to goal node
    public static void aStarFindPath(int[][] grid,int[] start,int[] target){
        //frontier
        //came_from
        //cost_to
        //hfunction
        //
    }
}