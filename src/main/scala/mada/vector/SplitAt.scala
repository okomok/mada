

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object SplitAt {
    def apply[A](v: Vector[A], n: Int): (Vector[A], Vector[A]) = {
        val middle = Math.min(v.start + n, v.end)
        (v(v.start, middle), v(middle, v.end))
    }
}
