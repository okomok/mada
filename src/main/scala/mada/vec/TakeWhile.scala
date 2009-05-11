

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object TakeWhile {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = {
        v(v.start, stl.FindIf(v, v.start, v.end, function.not(p)))
    }
}
