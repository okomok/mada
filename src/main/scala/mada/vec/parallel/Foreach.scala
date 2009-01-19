

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Foreach {
    def apply[A](v: Vector[A], f: A => Unit, grainSize: Long): Unit = {
        val (v1, v2) = v.splitAt(grainSize)
        if (v2.isEmpty) {
            v1.foreach(f)
        } else {
            val u = scala.actors.Futures.future {
                apply(v2, f, grainSize)
            }
            v1.foreach(f)
            u()
        }
    }
}
