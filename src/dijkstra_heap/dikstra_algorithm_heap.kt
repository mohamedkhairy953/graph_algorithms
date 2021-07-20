package dijkstra_heap

import DirectedGraph
import java.util.*

fun dijkstraHeapImp(g: DirectedGraph, startNode: String): MutableMap<String, Double> {
    val visitedNodes = mutableSetOf<String>()
    val keys = mutableMapOf<String, Double>()
    val distanceMap = mutableMapOf<String, Double>()
    keys[startNode] = 0.0
    g.nodes.forEach {
        if (it != startNode)
            keys[it] = Double.MAX_VALUE
    }
    val priorityQueue: PriorityQueue<String> = PriorityQueue { s1, s2 -> (keys[s1]!! - keys[s2]!!).toInt() }
    g.nodes.forEach {
        priorityQueue.add(it)
    }

    while (priorityQueue.isNotEmpty()) {
        val shortestNode = priorityQueue.remove()
        visitedNodes.add(shortestNode)
        distanceMap[shortestNode] = keys[shortestNode]!!
        //update the heap
        getNeighborsOf(shortestNode,g).forEach {
            priorityQueue.remove(it)
            keys[it] = keys[it]!!.coerceAtMost((distanceMap[shortestNode]!! + distanceBetween(g, shortestNode, it)))
           priorityQueue.add(it)
        }

    }
    return distanceMap
}
fun getNeighborsOf(shortestNode: String, g: DirectedGraph): List<String> {
    val allNeighbors = mutableListOf<String>()

        g.graph[shortestNode]!!.forEach { n ->
                allNeighbors.add(n)
        }

    return allNeighbors
}

fun distanceBetween(g: DirectedGraph, v: String?, n: String): Double {
    return 0.0; //TODO return actual distance
}
