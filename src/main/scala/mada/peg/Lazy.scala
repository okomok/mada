

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Lazy {
    def apply[A](p: => Peg[A]): Peg[A] = new LazyPeg(p)
}

private[mada] class LazyPeg[A](p: => Peg[A]) extends PegProxy[A] {
    override lazy val self = p
}
