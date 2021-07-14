class DirectedGraph {
     val graph: MutableMap<String, MutableList<String>> = mutableMapOf()
     val nodes: MutableSet<String> = mutableSetOf()

    fun addEdge(u: String, v: String) {
        graph[u]?.add(v) ?: graph.put(u, mutableListOf(v))
        nodes.add(u)
        nodes.add(v)
    }
}