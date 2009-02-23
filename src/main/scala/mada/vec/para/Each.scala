

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


private[mada] object Each {
    def apply[A](v: Vector[A], f: A => Unit, grainSize: Int): Unit = {
        Assert(!v.isParallel)
        v.parallel(grainSize).map(f).join
    }
}
