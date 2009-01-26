

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Head {
    def apply[A](v: Vector[A]): A = {
        ThrowIf.empty(v, "head")
        v(0)
    }
}

private[mada] object Tail {
    def apply[A](v: Vector[A]): Vector[A] = {
        ThrowIf.empty(v, "tail")
        v.window(1, v.size)
    }
}


private[mada] object IsNil {
    def apply[A](v: Vector[A]): Boolean = v.isEmpty
}
