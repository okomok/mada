

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Trivial mixin for zero-width peg.
 */
trait ZeroWidth[A] extends Peg[A] {
    /**
     * @return  <code>0</code>.
     */
    override def width = 0
}


private[mada] object IsZeroWidth {
    def apply[A](p: Peg[A]): Boolean = p.isInstanceOf[ZeroWidth[_]]
}
