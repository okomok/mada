

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object EqualsWith {
    /*
    def apply[A1, A2](v1: Vector[A1], v2: Vector[A2]): Boolean = {
        apply(v1, v2, (_: A1) == (_: A2))
    }
    */

    def apply[A1, A2](v1: Vector[A1], v2: Vector[A2], p: (A1, A2) => Boolean): Boolean = {
        if (v1.size != v2.size) {
            false
        } else {
            v1.stlEqual(v2, 0, p)
        }
    }
}
