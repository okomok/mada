

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector; package stl


private
object Sort {
    def apply[A](v: Vector[A], __first: Int, __last: Int, __comp: Ordering[A]) {
        IntroSort(v, __first, __last, __comp)
    }
}
