

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Init {
    def apply[A](v: Vector[A]): Vector[A] = {
        Assert(!v.isEmpty)
        v.window(0, v.size - 1)
    }
}
