

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


/**
 * Trivial mixin for zero-width peg.
 */
trait ZeroWidth[-A] extends Peg[A] {
    @annotation.equivalentTo("0")
    override def width = 0
}


private
object IsZeroWidth {
    def apply[A](p: Peg[A]): Boolean = p.isInstanceOf[ZeroWidth[_]]
}
