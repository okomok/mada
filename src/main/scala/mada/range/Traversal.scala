
package mada.range


trait Traversal {
    def precedence: Int
    def conformsTo(that: Traversal): Boolean = that.precedence <= precedence
    def min(that: Traversal): Traversal = if (precedence < that.precedence) this else that
}


class SinglePassTraversal extends Traversal {
    override def precedence = 0
}
object SinglePassTraversal extends SinglePassTraversal

class ForwardTraversal extends SinglePassTraversal {
    override def precedence = 1
}
object ForwardTraversal extends ForwardTraversal

class BidirectionalTraversal extends ForwardTraversal {
    override def precedence = 2
}
object BidirectionalTraversal extends BidirectionalTraversal

class RandomAccessTraversal extends BidirectionalTraversal {
    override def precedence = 3
}
object RandomAccessTraversal extends RandomAccessTraversal
