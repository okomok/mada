

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Foreach {
    def apply[A](v: Vector[A], f: A => Unit, grainSize: Int): Unit = {
        v.parallel(grainSize).map(f).force
    }
}
