

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Cut {
    def apply[A](v: Vector[A]): Vector[A] = new CutVector(v)
}

private[mada] class CutVector[A](override val underlying: Vector[A]) extends Adapter.Transform[A] {
    // cut-cut fusion too should be "cut".
}
