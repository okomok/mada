

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Cut {
    def apply[A](v: Vector[A]): Vector[A] = new CutVector(v)
}

class CutVector[A](override val * : Vector[A]) extends VectorAdapter[A, A]
