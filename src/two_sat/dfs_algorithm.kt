package two_sat

import DirectedGraph
import java.util.*

fun dfs(g: DirectedGraph, visited: MutableList<String>, stack: Stack<String>, scc: MutableList<String>) {
    g.nodes.forEach {
        if (it !in visited) {
            explore(g, it, visited, stack, scc)
        }

    }
}

fun explore(
    g: DirectedGraph,
    node: String,
    visited: MutableList<String>,
    stack: Stack<String>,
    scc: MutableList<String>
): MutableList<String> {
    if (node !in visited) {
        visited.add(node)
        g.graph[node]?.forEach {
            explore(g, it, visited, stack, scc)
        }
        stack.push(node)
        scc.add(node)
    }
    return visited
}

