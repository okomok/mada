

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Head {
    def apply[A](v: Vector[A]): A = {
        Assert(!v.isNil)
        v(0)
    }
}

object Tail {
    def apply[A](v: Vector[A]): Vector[A] = {
        Assert(!v.isNil)
        v.window(1, v.size)
    }
}


object IsNil {
    def apply[A](v: Vector[A]): Boolean = v.isEmpty
}
