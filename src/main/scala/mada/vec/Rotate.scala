

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Rotate {
    def apply[A](v: Vector[A], i: Int): Vector[A] = v.window(i, v.size) ++ v.window(0, i)
}
