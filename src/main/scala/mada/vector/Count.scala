

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Count {
    def apply[A](v: Vector[A], p: A => Boolean): Int = {
        stl.CountIf(v, v.start, v.end, p)
    }
}
