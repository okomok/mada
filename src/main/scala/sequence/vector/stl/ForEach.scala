

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector; package stl


private
object ForEach {
    def apply[A, F <: (A => Any)](v: Vector[A], __first: Int, __last: Int, __f: F): F = {
        v.loop(__first, __last, { (e: A) => __f(e); true })
        __f
    }
}
