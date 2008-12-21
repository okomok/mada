

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Slice {
    def apply[A](v: Vector[A], n: Long): Vector[A] = v.slice(n, v.size)
    def apply[A](v: Vector[A], n: Long, m: Long): Vector[A] = v.drop(n).take(m - n)
}
