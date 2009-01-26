

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Identity {
    def apply[A](p: Peg[A]): Peg[A] = new IdentityPeg(p)
}

private[mada] class IdentityPeg[A](override val self: Peg[A]) extends PegProxy[A]
