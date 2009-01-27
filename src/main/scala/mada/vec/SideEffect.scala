

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object SideEffect {
    def apply[A](v: Vector[A], f: Vector[A] => Any): Vector[A] = {
        f(v)
        v
    }
}
