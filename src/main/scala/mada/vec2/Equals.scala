

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


object Equals {
    def apply[A1, A2](v1: Vector[A1], v2: Vector[A2]): Boolean = {
        apply(v1, v2, (_: A1) == (_: A2))
    }

    def apply[A1, A2](v1: Vector[A1], v2: Vector[A2], __binary_pred: (A1, A2) => Boolean): Boolean = {
        var (__first1, __last1) = v1.toPair
        var (__first2, __last2) = v2.toPair

        if (__last1 - __first1 != __last2 - __first2) {
            false
        } else {
            stl.Equal(v1, __first1, __last1, v2, __first2, __binary_pred)
        }
    }
}
