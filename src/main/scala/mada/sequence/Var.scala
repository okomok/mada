

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


/**
 * The mutable forwarder for sequence.
 */
class Var[A](@volatile private var seq: Sequence[A]) extends Forwarder[A] with util.Var[Sequence[A]] {
    def this() = this(null)
    override protected def delegate = seq
    override def assign(that: Sequence[A]): Unit = { seq = that }
}
