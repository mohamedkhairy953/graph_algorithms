package smallest_bottleneck

import DirectedGraph
import java.util.*
import kotlin.math.min

fun computeSmallestBottleneck(g: DirectedGraph, startNode: String): MutableMap<String, Double> {
    val keys = mutableMapOf<String, Double>()
    val largestBottleneckMap = mutableMapOf<String, Double>()
    keys[startNode] = Double.MAX_VALUE
    g.nodes.forEach {
        if (it != startNode)
            keys[it] = Double.MIN_VALUE
    }
    val priorityQueue: PriorityQueue<String> = PriorityQueue { s1, s2 -> (keys[s1]!! - keys[s2]!!).toInt() }
    g.nodes.forEach {
        priorityQueue.add(it)
    }

    while (priorityQueue.isNotEmpty()) {
        val selectedNode = priorityQueue.remove()
        largestBottleneckMap[selectedNode] = keys[selectedNode]!!
        //update the heap
        getNeighborsOf(selectedNode, g).forEach {
            priorityQueue.remove(it)
            val min = min(keys[selectedNode]!!, distanceBetween(g, selectedNode, it))
            keys[it] = min(keys[it]!!, min)
            priorityQueue.add(it)
        }

    }
    return largestBottleneckMap
}

fun getNeighborsOf(selectedNode: String, g: DirectedGraph): List<String> {
    val allNeighbors = mutableListOf<String>()

    g.graph[selectedNode]!!.forEach { n ->
        allNeighbors.add(n)
    }

    return allNeighbors
}

fun distanceBetween(g: DirectedGraph, v: String?, n: String): Double {
    return 0.0; //TODO return actual distance
}
