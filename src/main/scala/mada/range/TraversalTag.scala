
package mada.range


trait TraversalTag {
    def precedence: Int
    def min(that: TraversalTag): TraversalTag = if (precedence < that.precedence) this else that
}


case class SinglePassTraversalTag extends TraversalTag {
    override def precedence = 0
}

case class ForwardTraversalTag extends SinglePassTraversalTag {
    override def precedence = 1
}

case class BidirectionalTraversalTag extends ForwardTraversalTag {
    override def precedence = 2
}

case class RandomAccessTraversalTag extends BidirectionalTraversalTag {
    override def precedence = 3
}
