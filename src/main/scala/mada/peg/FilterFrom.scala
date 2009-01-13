

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object FilterFrom {
    def apply[A](p: Peg[A], v: Vector[A]): Iterator[A] = {
        IteratorFlatten(p.tokens(v).map(_.iterator))
    }
}
