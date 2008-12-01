
package mada.rng


trait Traversal {
    protected def bound: Int
    final def <:<(that: Traversal): Boolean = bound <= that.bound
    final def >:>(that: Traversal): Boolean = bound >= that.bound
    final def lower(that: Traversal): Traversal = if (this <:< that) this else that
    final def upper(that: Traversal): Traversal = if (this >:> that) this else that
}


object SinglePassTraversal extends SinglePassTraversal
sealed abstract class SinglePassTraversal extends Traversal {
    override val bound = 0
    override def toString = "SinglePassTraversal"
}

object ForwardTraversal extends ForwardTraversal
sealed abstract class ForwardTraversal extends SinglePassTraversal {
    override val bound = -1
    override def toString = "ForwardTraversal"
}

object BidirectionalTraversal extends BidirectionalTraversal
sealed abstract class BidirectionalTraversal extends ForwardTraversal {
    override val bound = -2
    override def toString = "BidirectionalTraversal"
}

object RandomAccessTraversal extends RandomAccessTraversal
sealed abstract class RandomAccessTraversal extends BidirectionalTraversal {
    override val bound = -3
    override def toString = "RandomAccessTraversal"
}
