

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Parallel {
    def defaultGrainSize[A](v: Vector[A]): Long = 1
    def apply[A](v: Vector[A]) = new ParallelVector(v, defaultGrainSize(v))
    def apply[A](v: Vector[A], grainSize: Long) = new ParallelVector(v, grainSize)
}

class ParallelVector[A](override val self: Vector[A], grainSize: Long) extends VectorProxy[A]  {
    Assert(grainSize > 0)
    import vec.parallel._

    override def equals(that: Any) = Equals(self, that, grainSize)
    override def equalsTo[B](that: Vector[B]) = EqualsTo(self, that, grainSize)
    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean) = EqualsWith(self, that, p, grainSize)
    override def copyTo[B >: A](that: Vector[B]) = CopyTo(self, that, grainSize) // clone, toArray
    override def count(p: A => Boolean) = Count(self, p, grainSize)
    override def find(p: A => Boolean) = Find(self, p, grainSize) // forall, exists, contains
    override def foreach(f: A => Unit) = Foreach(self, f, grainSize)
//    override def map[B](f: A => B): Vector[B] = Map(self, f, grainSize)

    override def parallel = throw new UnsupportedOperationException("ParallelVector parallel")
    override def parallel(grainSize: Long) = throw new UnsupportedOperationException("ParallelVector parallel")

    def fold(z: A)(op: (A, A) => A): A = Fold(self, z, op, grainSize)
    def reduce(op: (A, A) => A): A = Reduce(self, op, grainSize)
}
