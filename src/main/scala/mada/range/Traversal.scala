
package mada.range


trait Traversal {
    def precedence: Int
    def conformsTo(that: Traversal): Boolean = that.precedence <= precedence
    def min(that: Traversal): Traversal = if (precedence < that.precedence) this else that
}


case class SinglePassTraversal extends Traversal {
    override def precedence = 0
}

case class ForwardTraversal extends SinglePassTraversal {
    override def precedence = 1
}

case class BidirectionalTraversal extends ForwardTraversal {
    override def precedence = 2
}

case class RandomAccessTraversal extends BidirectionalTraversal {
    override def precedence = 3
}
