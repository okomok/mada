

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Tokens {
    def apply[A](p: Peg[A], v: Vector[A]): Iterator[Vector[A]] = {
        for ((v, i, j) <- p.tokenize(v))
            yield v(i, j)
    }
}
