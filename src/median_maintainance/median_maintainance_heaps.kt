package median_maintainance

import java.util.*
import kotlin.math.ceil

fun medianMaintenanceByHeaps(numbers: Array<Double>): Double {
    var median = 0.0
    val lowersH: PriorityQueue<Double> = PriorityQueue { first, second -> ceil(second - first).toInt() }
    val highersH: PriorityQueue<Double> = PriorityQueue()
    numbers.forEach {
        addNumberToHeap(it, lowersH, highersH)
        balanceHeaps(lowersH, highersH)
        median = computeMedian(lowersH, highersH)
    }


    return median
}

fun computeMedian(lowersH: PriorityQueue<Double>, highersH: PriorityQueue<Double>): Double {
    val biggerHeap = if (lowersH.size > highersH.size) lowersH else highersH
    return if (lowersH.size == highersH.size) {
        (lowersH.peek() + highersH.peek()) / 2
    } else {
        biggerHeap.peek()
    }
}

fun balanceHeaps(lowersH: PriorityQueue<Double>, highersH: PriorityQueue<Double>) {
    val biggerHeap = if (lowersH.size > highersH.size) lowersH else highersH
    val smallerHeap = if (lowersH.size < highersH.size) lowersH else highersH
    if (biggerHeap.size > (smallerHeap.size + 1)) {
        lowersH.add(biggerHeap.poll())
    }
}

fun addNumberToHeap(it: Double, lowersH: PriorityQueue<Double>, highersH: PriorityQueue<Double>) {
    if (lowersH.isEmpty() || it < lowersH.peek())
        lowersH.add(it)
    else
        highersH.add(it)
}
