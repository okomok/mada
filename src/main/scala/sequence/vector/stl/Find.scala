

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector; package stl


private
object Find {
    def apply[A](v: Vector[A], __first: Int, __last: Int, __val: Any): Int = {
        FindIf(v, __first, __last, (_: A) == __val)
    }
}

private
object FindIf {
    def apply[A](v: Vector[A], __first: Int, __last: Int, __pred: A => Boolean): Int = {
        var __i = __first
        v.loop(__first, __last, { (e: A) => if (__pred(e)) { false } else { __i += 1; true } })
        __i
    }
}
