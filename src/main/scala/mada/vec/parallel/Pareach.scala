

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Pareach {
    def apply[A](v: Vector[A], f: A => Unit, grainSize: Int): Unit = {
        Assert(!v.isParallel)
        v.parallel(grainSize).map(f).force
    }
}
