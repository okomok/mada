

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


/**
 * The mutable forwarder for traversable.
 */
class Var[A](@volatile private var tr: Traversable[A]) extends Forwarder[A] {
    def this() = this(null)
    override protected def delegate = tr

    final def isNull: Boolean = tr eq null
    final def assign(that: Traversable[A]): Unit = { tr = that }

    @aliasOf("assign")
    final def :=(that: Traversable[A]): Unit = assign(that)
}
