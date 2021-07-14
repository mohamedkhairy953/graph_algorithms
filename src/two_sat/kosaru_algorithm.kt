package two_sat

import DirectedGraph
import java.util.*

fun reverseGraph(g: DirectedGraph): DirectedGraph {
    val reversedGraph = DirectedGraph()
    g.nodes.forEach { key ->
        g.graph[key]?.forEach { node ->
            reversedGraph.addEdge(node, key)
        }
    }
    return reversedGraph
}

fun getSCCs(dg: DirectedGraph): MutableList<MutableList<String>> {
    val sccs = mutableListOf<MutableList<String>>()

    val visited = mutableListOf<String>()
    val stack: Stack<String> = Stack()
    val scc = mutableListOf<String>()
    dfs(dg, visited, stack, mutableListOf())
    visited.clear()
    val reversedGraph = reverseGraph(dg)
    while (stack.isNotEmpty()) {
        val node = stack.pop()
        if (node !in visited){
            scc.add(node)
            explore(reversedGraph,node,visited, Stack(),scc)
            sccs.add(scc)
        }
    }
    return sccs
}