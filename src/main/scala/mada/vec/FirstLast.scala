

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object First {
    def apply[A](v: Vector[A]): A = {
        ThrowIf.empty(v, "first")
        v(v.start)
    }
}

private[mada] object Last {
    def apply[A](v: Vector[A]): A = {
        ThrowIf.empty(v, "last")
        v(v.end - 1)
    }
}

