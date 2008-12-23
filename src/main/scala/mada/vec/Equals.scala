

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Equals {
    def apply[A1](v1: Vector[A1], v2: Any): Boolean = v2 match {
        case v2: Vector[_] => v1.equalsWith(v2)(stl.EqualTo)
        case _ => false
    }
}

object EqualsWith {
    def apply[A1, A2](v1: Vector[A1], v2: Vector[A2], p: (A1, A2) => Boolean): Boolean = {
        val (first1, last1) = v1.toPair
        val (first2, last2) = v2.toPair
        if (last1 - first1 != last2 - first2) {
            false
        } else {
            stl.Equal(v1, first1, last1, v2, first2, p)
        }
    }
}
