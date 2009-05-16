

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


/**
 * The mutable forwarder for traversable.
 */
case class Var[A] extends Forwarder[A] {
    @volatile private var tr: Traversable[A] = null
    override protected def delegate = tr
    final def :=(that: Traversable[A]): Unit = { tr = that }
}
