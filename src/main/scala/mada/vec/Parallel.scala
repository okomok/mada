

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Parallel {
    def defaultGrainSize[A](v: Vector[A]): Long = 1
    def apply[A](v: Vector[A]) = new Parallel(v, defaultGrainSize(v))
    def apply[A](v: Vector[A], grainSize: Long) = new Parallel(v, grainSize)
}

// This is NOT a Vector but a syntax sugar.
class Parallel[A](override val self: Vector[A], grainSize: Long) extends VectorProxy[A]  {
    import vec.parallel._

    override def equals(that: Any): Boolean = Equals(self, that, grainSize)
    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsWith(self, that, p, grainSize)
    override def clone: Vector[A] = Clone(self, grainSize)
    override def foreach(f: A => Unit): Unit = Foreach(self, f, grainSize)
    def fold(z: A)(op: (A, A) => A): A = Fold(self, z, op, grainSize)
    def reduce(op: (A, A) => A): A = Reduce(self, op, grainSize)
}
