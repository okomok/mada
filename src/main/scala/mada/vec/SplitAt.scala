

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object SplitAt {
    def apply[A](v: Vector[A], n: Int): (Vector[A], Vector[A]) = {
        val (x, first, last) = v.triple
        val middle = Math.min(first + n, last)
        (x.window(first, middle), x.window(middle, last))
    }
}
