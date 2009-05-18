

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object ByLazy {
    def apply[A](p: => Peg[A]): Peg[A] = new ByLazyPeg(p)
}

private[mada] class ByLazyPeg[A](p: => Peg[A]) extends Forwarder[A] {
    override lazy val delegate = p
}
