

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Parallel {
    def defaultGrainSize[A](v: Vector[A]): Long = 1
    def apply[A](v: Vector[A]) = new Parallel(v, defaultGrainSize(v))
    def apply[A](v: Vector[A], grainSize: Long) = new Parallel(v, grainSize)
}

class Parallel[A](override val self: Vector[A], grainSize: Long) extends VectorProxy[A]  {
    import vec.parallel._

    override def equals(that: Any) = Equals(self, that, grainSize)
    override def equalsTo[B](that: Vector[B]) = EqualsTo(self, that, grainSize)
    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean) = EqualsWith(self, that, p, grainSize)
    override def copyTo[B >: A](that: Vector[B]) = CopyTo(self, that, grainSize)
    // clone, toArray
    override def find(p: A => Boolean) = Find(this, p, grainSize)
    // forall, exists, contains
    override def foreach(f: A => Unit) = Foreach(self, f, grainSize)

    def fold(z: A)(op: (A, A) => A): A = Fold(self, z, op, grainSize)
    def reduce(op: (A, A) => A): A = Reduce(self, op, grainSize)
}
