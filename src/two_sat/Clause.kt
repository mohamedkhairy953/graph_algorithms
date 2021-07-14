package two_sat

data class Clause(val first: String, val second: String)

fun applyDoubleNegations(literal: String): String {
    return literal.replace(neg + neg, "")
}

const val neg = "~"