record Node(String label){
    @Override
    public final String toString() {
        return "node_" + label.toUpperCase();
    }
}