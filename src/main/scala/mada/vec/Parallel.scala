

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Parallel {
    def defaultGrainSize[A](v: Vector[A]): Long = 1
    def apply[A](v: Vector[A]) = new Parallel(v, defaultGrainSize(v))
    def apply[A](v: Vector[A], grainSize: Long) = new Parallel(v, grainSize)
}

class Parallel[A](v: Vector[A], grainSize: Long) {
    import parallel._

    override def equals(that: Any): Boolean = Equals(v, that, grainSize)
    def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsWith(v, that, p, grainSize)
    def cloneVector: Vector[A] = Clone(v, grainSize)
    def foreach(f: A => Unit): Unit = Foreach(v, f, grainSize)
    def fold(z: A)(op: (A, A) => A): A = Fold(v, z, op, grainSize)
    def reduce(op: (A, A) => A): A = Reduce(v, op, grainSize)
}
