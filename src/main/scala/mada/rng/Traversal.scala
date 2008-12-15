

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Traversal {
    object SinglePass extends SinglePass
    sealed abstract class SinglePass extends Traversal {
        override val bound = 0
        override def toString = "Traversal.SinglePass"
    }

    object Forward extends Forward
    sealed abstract class Forward extends SinglePass {
        override val bound = -1
        override def toString = "Traversal.Forward"
    }

    object Bidirectional extends Bidirectional
    sealed abstract class Bidirectional extends Forward {
        override val bound = -2
        override def toString = "Traversal.Bidirectional"
    }

    object RandomAccess extends RandomAccess
    sealed abstract class RandomAccess extends Bidirectional {
        override val bound = -3
        override def toString = "Traversal.RandomAccess"
    }
}

trait Traversal {
    protected def bound: Int
    final def <:<(that: Traversal): Boolean = bound <= that.bound
    final def >:>(that: Traversal): Boolean = bound >= that.bound
    final def lower(that: Traversal): Traversal = if (this <:< that) this else that
    final def upper(that: Traversal): Traversal = if (this >:> that) this else that
}


trait TraversalModeller {
    protected def _traversal: Traversal

    final def traversal = _traversal
    final def models(t: Traversal) = traversal <:< t
    final def notModels(t: Traversal) = !models(t)
    final def assertModels(t: Traversal) = AssertModels(this, t)

    final protected def SinglePass = Traversal.SinglePass
    final protected def Forward = Traversal.Forward
    final protected def Bidirectional = Traversal.Bidirectional
    final protected def RandomAccess = Traversal.RandomAccess
}
