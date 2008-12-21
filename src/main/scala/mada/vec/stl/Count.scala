

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Count {
    def apply[A](v: Vector[A], __first: Long, __last: Long, e: Any): Long = {
        v.stlCountIf(__first, __last, _ == e)
    }
}

object CountIf {
    def apply[A](v: Vector[A], __first: Long, __last: Long, __pred: A => Boolean): Long = {
        var __n = 0L
        v.stlForEach(__first, __last, { (e: A) => if (__pred(e)) __n += 1 })
        __n
    }
}
