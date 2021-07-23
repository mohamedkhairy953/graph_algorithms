package bst_operations

class BSTNode(
    private val value: Int,
    var leftChild: BSTNode?,
    var rightChild: BSTNode?,
    var parent: BSTNode?,
    var leftSize: Int=1
) {

    fun insert(value: Int) {
        if (value > this.value) {
            addToRightChild(value)
        } else {
            addToLeftChild(value)
        }
    }

    fun find(value: Int): BSTNode? {
        if (value == this.value) return this

        return if (value > this.value) {
            this.rightChild?.find(value)
        } else {
            this.leftChild?.find(value)
        }
    }

    fun delete(bstNode: BSTNode, value: Int): Boolean {
        if (value < bstNode.value) {
            if (bstNode.leftChild != null)
                delete(bstNode.leftChild!!, value)
            else
                return false
        } else if (value > bstNode.value) {
            if (bstNode.rightChild != null)
                delete(bstNode.rightChild!!, value)
            else
                return false
        } else if (value == bstNode.value) {
            if (bstNode.rightChild == null && bstNode.leftChild == null && bstNode.parent != null) {
                return removeLeafChild(bstNode)
            }
            if (bstNode.rightChild != null && bstNode.leftChild == null && bstNode.parent != null) {
                bstNode.rightChild!!.parent = bstNode.parent
                return true
            }
            if (bstNode.rightChild == null && bstNode.leftChild != null && bstNode.parent != null) {
                bstNode.leftChild!!.parent = bstNode.parent
                return true
            }
            val rsNode = findRightSmallest(bstNode)
            rsNode.parent = bstNode.parent
            return true
        }

        return false
    }

    fun findSuccessor(value: Int): BSTNode? {
        val node = find(value) ?: return null
        return if (node.rightChild != null)
            findRightSmallest(node)
        else {
            getFirstBiggerParent(node, value)
        }

    }

    fun findKthElement(k:Int): BSTNode? {
        val count: Int = this.leftSize
        if (count == k) return this

        return if (count > k) this.leftChild?.findKthElement(k) else this.rightChild?.findKthElement(k - count)
    }

    //Helpers

    private fun getFirstBiggerParent(node: BSTNode, value: Int): BSTNode? {
        if (node.value > value) return node
        if (node.parent == null) return null
        return getFirstBiggerParent(node.parent!!, value)
    }

    private fun findRightSmallest(bstNode: BSTNode): BSTNode {
        if (bstNode.leftChild == null) {
            return bstNode
        }
        return findRightSmallest(bstNode.leftChild!!)
    }

    private fun removeLeafChild(bstNode: BSTNode) = if (bstNode.parent!!.value > bstNode.value) {
        bstNode.parent?.leftChild = null
        true
    } else {
        bstNode.parent?.rightChild = null
        true
    }

    private fun addToRightChild(value: Int) {
        if (this.rightChild != null) this.rightChild!!.insert(value)
        else this.rightChild = BSTNode(value, null, null, this,1)
    }

    private fun addToLeftChild(value: Int) {
        if (this.leftChild != null) {
            this.leftChild!!.insert(value)
            this.leftSize+=1
        }
        else this.leftChild = BSTNode(value, null, null, this,1)
    }

}