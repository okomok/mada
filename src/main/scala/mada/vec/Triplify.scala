

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Triplify {
    def apply[A, B](f: Vector[A] => B): ((Vector[A], Long, Long) => B) = {
        { (v: Vector[A], i: Long, j: Long) => f(v.window(i, j)) }
    }
}

object Untriplify {
    def apply[A, B](f: (Vector[A], Long, Long) => B): (Vector[A] => B) = {
        { (v: Vector[A]) => f(v, 0, v.size) }
    }
}
