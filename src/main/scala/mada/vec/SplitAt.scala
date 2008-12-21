

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object SplitAt {
    def apply[A](v: Vector[A], i: Long): (Vector[A], Vector[A]) = (v.window(0, i), v.window(i, v.size))
}
