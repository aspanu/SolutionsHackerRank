package mit6006courseProblems

// https://ocw.mit.edu/courses/6-006-introduction-to-algorithms-spring-2020/resources/mit6_006s20_ps1-questions/
// Problem 1-2
//
// a)
// for (a = 0, a < k, a++)
//      D.insert_at(i, (D.delete_at(i + k))
// b)
// for (a = 0, a < k, a++)
//      D.insert_at(j, D.delete_at(i)

// Problem 1-4
// Doubly linked List

data class Node(var prev: Node?, var next: Node?, var value: Int)

data class DoublyLinkedList(var head: Node?, var tail: Node?) {
    fun insertFirst(x: Int) {
        val newHead = Node(null, head, x)
        head?.prev = newHead
        head = newHead
    }

    fun insertLast(x: Int) {
        val newTail = Node(tail, null, x)
        tail?.next = newTail
        tail = newTail
    }

    fun deleteFirst() {
        val newHead = head?.next
        head = newHead
        head?.prev = null
    }
    fun deleteLast() {
        val newTail = tail?.prev
        tail = newTail
        tail?.next = null
    }

    private fun remove(node: Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }
}

fun removeSubList(x1: Node, x2: Node): DoublyLinkedList {
    val subList = DoublyLinkedList(null, null)
    val prev = x1.prev
    val next = x2.next
    subList.head = x1
    subList.head?.prev = null
    subList.tail = x2
    subList.tail?.next = null
    prev?.next = next
    next?.prev = prev
    return subList
}

fun splice(x: Node, L2: DoublyLinkedList){
    L2.tail?.next = x.next
    x.next = L2.head
}