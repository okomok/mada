

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector.stl


private[mada] object Count {
    def apply[A](v: Vector[A], __first: Int, __last: Int, e: Any): Int = {
        CountIf(v, __first, __last, (_: A) == e)
    }
}

private[mada] object CountIf {
    def apply[A](v: Vector[A], __first: Int, __last: Int, __pred: A => Boolean): Int = {
        var __n = 0
        ForEach(v, __first, __last, { (e: A) => if (__pred(e)) __n += 1 })
        __n
    }
}