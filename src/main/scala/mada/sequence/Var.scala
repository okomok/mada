

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


/**
 * The mutable forwarder for sequence.
 */
class Var[A](@volatile private var seq: Sequence[A]) extends Forwarder[A] {
    def this() = this(null)
    override protected def delegate = seq
    final def isNull: Boolean = seq eq null
    final def assign(that: Sequence[A]): Unit = { seq = that }

    @aliasOf("assign")
    final def :=(that: Sequence[A]): Unit = assign(that)
}
