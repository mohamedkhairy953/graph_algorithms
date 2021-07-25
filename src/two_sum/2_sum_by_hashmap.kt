package two_sum

fun twoSum(arr: Array<Int>, sum: Int): Boolean {
    val integerMap = mutableMapOf<Int, Int>()
    arr.forEach {
        integerMap[it] = it
    }
    for (i in 0..arr.size) {
        val y = sum - arr[i]
        if (integerMap.containsKey(y))
            return true
    }
    return false

}// O(n)