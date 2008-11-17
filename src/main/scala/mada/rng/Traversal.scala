
package mada.rng


trait Traversal {
    protected def bound: Int
    final def <:<(that: Traversal): Boolean = bound <= that.bound
    final def >:>(that: Traversal): Boolean = bound >= that.bound
    final def lower(that: Traversal): Traversal = if (this <:< this) this else that
    final def upper(that: Traversal): Traversal = if (this >:> that) this else that
}


sealed abstract class SinglePassTraversal extends Traversal {
    override def bound = 0
    override def toString = "SinglePassTraversal"
}
object SinglePassTraversal extends SinglePassTraversal

sealed abstract class ForwardTraversal extends SinglePassTraversal {
    override def bound = -1
    override def toString = "ForwardTraversal"
}
object ForwardTraversal extends ForwardTraversal

sealed abstract class BidirectionalTraversal extends ForwardTraversal {
    override def bound = -2
    override def toString = "BidirectionalTraversal"
}
object BidirectionalTraversal extends BidirectionalTraversal

sealed abstract class RandomAccessTraversal extends BidirectionalTraversal {
    override def bound = -3
    override def toString = "RandomAccessTraversal"
}
object RandomAccessTraversal extends RandomAccessTraversal
