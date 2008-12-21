

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object ForEach {
    def apply[A, F <: (A => Any)](v: Vector[A], __first: Long, __last: Long, __f: F): F = {
        v.loop(__first, __last, { (e: A) => __f(e); true })
        __f
    }
}
