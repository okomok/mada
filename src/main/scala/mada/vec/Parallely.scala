

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Parallely {
    def apply[A](v: Vector[A]): Vector[A] = apply(v, Parallel.defaultGrainSize(v))
    def apply[A](v: Vector[A], g: Int): Vector[A] = new Parallely(v, g)
}

class Parallely[A](v: Vector[A], grainSize: Int) extends VectorProxy[A] {
    override val self = v.parallel(grainSize)
    override def foreach(f: A => Unit) = self.pareach(f)
}
