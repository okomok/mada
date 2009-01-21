

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Equals {
    def apply[A1](v1: Vector[A1], v2: Any): Boolean = v2 match {
        case v2: Vector[_] => v1.equalsTo(v2)
        case _ => false
    }
}

object EqualsTo {
    def apply[A1, A2](v1: Vector[A1], v2: Vector[A2]): Boolean = {
        v1.equalsWith(v2)(stl.EqualTo)
    }
}

object EqualsWith {
    def apply[A1, A2](v1: Vector[A1], v2: Vector[A2], p: (A1, A2) => Boolean): Boolean = {
        val (x1, first1, last1) = v1.triple
        val (x2, first2, last2) = v2.triple
        if (last1 - first1 != last2 - first2) {
            false
        } else {
            stl.Equal(x1, first1, last1, x2, first2, p)
        }
    }
}
