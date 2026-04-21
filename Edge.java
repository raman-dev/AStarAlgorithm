record Edge(Node a,Node b,int weight){
    
    @Override
    public final String toString() {
        return a.label() +" -> "+b.label() + ":" + weight + " ";
    }
}