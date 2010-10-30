

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


/**
 * Boxed non-dual type
 */
final case class Box[A](override val undual: A) extends Any {
    Predef.assert(!undual.isInstanceOf[Box[_]])
    type self = Box[A]
    override type undual = A
}

object Box {
    implicit def _autounboxing[A](from: Box[A]): A = from.undual
}
