

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Init {
    def apply[A](v: Vector[A]): Vector[A] = {
        ThrowIf.empty(v, "init")
        v(v.start, v.end - 1)
    }
}
