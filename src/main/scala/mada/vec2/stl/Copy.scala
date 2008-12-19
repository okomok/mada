

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2.stl


object CopyIf {
    def apply[A, F <: (A => Any)](v: Vector[A], f: F, p: A => Boolean): F = {
        v.stlForEach({ (e: A) => if (p(e)) f(e) })
        f
    }
}
