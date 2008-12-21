

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
        if (v1.size != v2.size) {
            false
        } else {
            v1.stlEqual(0, v1.size, v2, 0, p)
        }
    }
}
