

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Find {
    def apply[A](v: Vector[A], __first: Int, __last: Int, __val: Any): Int = {
        FindIf(v, __first, __last, (_: A) == __val)
    }
}

object FindIf {
    def apply[A](v: Vector[A], __first: Int, __last: Int, __pred: A => Boolean): Int = {
        var __i = 0
        v.loop(__first, __last, { (e: A) => if (__pred(e)) { false } else { __i += 1; true } })
        __i
    }
}
