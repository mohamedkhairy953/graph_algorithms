package dijkstra

import DirectedGraph
import java.util.*

fun dijkstraImp(g: DirectedGraph, startNode: String) {
    val visitedNodes = mutableSetOf<String>()
    val distanceMap = mutableMapOf<String, Double>()
    val prevNodes = mutableMapOf<String, String>()
    val minPQ: MutableSet<String> = mutableSetOf()
    distanceMap[startNode] = 0.0
    minPQ.add(startNode)
    g.nodes.forEach {
        if (it != startNode)
            distanceMap[it] = Double.MAX_VALUE
    }
    while (minPQ.isNotEmpty()) {
        val selectedNode = minPQ.peek()
        g.graph[selectedNode]!!.forEach { neighbor ->
            if (neighbor !in visitedNodes) {
                val altPath = distanceMap[selectedNode]!!.plus(distanceBetween(selectedNode, neighbor))
                if (altPath < distanceMap[neighbor]!!){
                    distanceMap[neighbor]=altPath
                    prevNodes[neighbor] = selectedNode
                    minPQ.add(neighbor)
                }
            }
        }
    }

}

fun distanceBetween(shortestNode: String?, neighbor: String): Double {

}
