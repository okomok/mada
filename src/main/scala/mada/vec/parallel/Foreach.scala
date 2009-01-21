

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Foreach {
    def apply[A](v: Vector[A], f: A => Unit, grainSize: Int): Unit = {
        touch(v.divide(grainSize).parallel(1).map({ w => w.foreach(f) }))
    }

    // parallel.touch needed?
    private def touch[A](v: Vector[A]): Vector[A] = {
        for (e <- v) {
            touchElem(e)
        }
        v
    }
    private def touchElem[A](e: A): A = e
}
