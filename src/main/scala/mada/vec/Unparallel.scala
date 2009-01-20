

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Unparallel {
    def apply[A](v: Vector[A]): Vector[A] = v match {
        case v: ParallelVector[_] => v.self // .unparallel
        case _ => v
    }
}
