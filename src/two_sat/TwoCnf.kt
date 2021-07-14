package two_sat

class TwoCnf {
    private val clauses: MutableList<Clause> = mutableListOf()
    fun addClause(clause: Clause) {
        clauses.add(clause)
    }
    fun getVariables():Set<String>{
        val vs= mutableSetOf<String>()
        clauses.forEach {
            vs.add(it.first)
            vs.add(it.second)
        }
        return vs
    }
}