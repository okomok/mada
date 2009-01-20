

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


import scala.actors.Futures.future


object Reduce {
    def apply[A](v: Vector[A], op: (A, A) => A, grainSize: Long): A = {
        v.divide(grainSize).future({ w => w.reduceLeft(op) }).reduceLeft(op)
    }
}
