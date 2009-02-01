

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Equals {
    def apply[A1](v1: Vector[A1], v2: Any): Boolean = v2 match {
        case v2: Vector[_] => v1.equalsTo(v2)
        case _ => false
    }
}

private[mada] object EqualsWith {
    def apply[A1, A2](v1: Vector[A1], v2: Vector[A2], p: (A1, A2) => Boolean): Boolean = {
        if (v1.size != v2.size) {
            false
        } else {
            stl.Equal(v1, v1.start, v1.end, v2, v2.start, p)
        }
    }
}
