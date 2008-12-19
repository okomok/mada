

// RemoveCopyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2.stl


object RemoveCopyIf {
    def apply[A, F <: (A => Any)](v: Vector[A], f: F, p: A => Boolean): F = {
        v.stlCopyIf(f, !p(_: A))
        f
    }
}
