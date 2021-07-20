package dijkstra

import DirectedGraph

fun dijkstraImp(g: DirectedGraph, startNode: String): MutableMap<String, Double> {
    val visitedNodes = mutableSetOf<String>()
    val distanceMap = mutableMapOf<String, Double>()
    distanceMap[startNode] = 0.0
    g.nodes.forEach {
        if (it != startNode)
            distanceMap[it] = Double.MAX_VALUE
    }
    while (visitedNodes.size < g.nodes.size) {
        val edges = dijkstra_heap.getNotVisitedNeighborsOfVisitedNodes(visitedNodes, g)
        val shortestEdge = dijkstra_heap.getShortestEdge(g, edges)
        if (shortestEdge == null)
            return distanceMap
        else {
            val value = shortestEdge.value
            val key = shortestEdge.key
            visitedNodes.add(value)
            if ((dijkstra_heap.distanceBetween(g, key, value) + distanceMap[key]!!) < distanceMap[value]!!) {
                distanceMap[value] = (dijkstra_heap.distanceBetween(g, key, value) + distanceMap[key]!!)
            }
        }
    }
    return distanceMap
}

fun getShortestEdge(g: DirectedGraph,edges: MutableMap<String, String>): MutableMap.MutableEntry<String, String>? {
    var shortestDist = Double.MAX_VALUE
    var shortestEdge: MutableMap.MutableEntry<String, String>? = null
    for (n in edges) {
        val d = dijkstra_heap.distanceBetween(g, n.key, n.value)
        if (d < shortestDist) {
            shortestDist = d
            shortestEdge = n
        }
    }
    return shortestEdge
}

fun getNotVisitedNeighborsOfVisitedNodes(visited: MutableSet<String>, g: DirectedGraph): MutableMap<String, String> {
    val allNotVisitedNeighbors = mutableMapOf<String, String>()
    visited.forEach {
        g.graph[it]!!.forEach { n ->
            if (n !in visited)
                allNotVisitedNeighbors[it] = n
        }
    }
    return allNotVisitedNeighbors
}

fun distanceBetween(g: DirectedGraph, v: String?, n: String): Double {
    return 0.0; //TODO return actual distance
}
