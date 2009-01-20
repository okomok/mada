

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Map {
    def apply[Z, A](v: Vector[Z], f: Z => A, grainSize: Long): Vector[A] = {
        if (grainSize == 1) {
            v.map({ e => scala.actors.Futures.future(f(e)) }).force.map({ u => u() })
        } else {
            Vector.undivide(
                v.divide(grainSize).map({ w => scala.actors.Futures.future(w.map(f)) }).force.map({ u => u() })
            )
        }
        // how to terminate unneeded values?
    }
}
