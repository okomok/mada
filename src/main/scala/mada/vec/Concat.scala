

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Concat {
    def apply[A](vs: Vector[A]*): Vector[A] = {
        vs.foldLeft(Vector.empty[A])({ (r: Vector[A], v: Vector[A]) => r.append(v) })
    }
}
