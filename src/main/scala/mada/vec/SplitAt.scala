

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object SplitAt {
    def apply[A](v: Vector[A], n: Long): (Vector[A], Vector[A]) = {
        val (first, last) = v.pair
        val middle = Math.min(n, last)
        (v.window(first, middle), v.window(middle, last))
    }
}
