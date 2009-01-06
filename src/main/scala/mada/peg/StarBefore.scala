

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object StarBefore {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = p.starUntil(q.lookAhead)
}
