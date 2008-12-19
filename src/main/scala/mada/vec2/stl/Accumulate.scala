

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2.stl


object Accumulate {
    def apply[A, B](v: Vector[A], z: B, op: (B, A) => B): B = {
        var acc = z
        v.foreach({ (e: A) => acc = op(acc, e) })
        acc
    }
}
