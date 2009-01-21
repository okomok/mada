

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Parallel {
    def defaultGrainSize[A](v: Vector[A]): Int = 1
    def isDefaultGrainSize[A](v: Vector[A], g: Int): Boolean = g == defaultGrainSize(v)

    def apply[A](v: Vector[A]): Vector[A] = new ParallelVector(v, defaultGrainSize(v))
    def apply[A](v: Vector[A], g: Int): Vector[A] = new ParallelVector(v, g)
}

class ParallelVector[A](override val self: Vector[A], grainSize: Int) extends VectorProxy[A]  {
    Assert(!IsParallelVector(self))
    if (grainSize <= 0) {
        throw new java.lang.IllegalArgumentException("nonpositive grain size: " + grainSize)
    }

    import vec.parallel._

    override def equals(that: Any) = Equals(self, that, grainSize)
    override def equalsTo[B](that: Vector[B]) = EqualsTo(self, that, grainSize)
    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean) = EqualsWith(self, that, p, grainSize)
    override def copyTo[B >: A](that: Vector[B]) = CopyTo(self, that, grainSize) // clone, toArray
    override def count(p: A => Boolean) = Count(self, p, grainSize)
    override def find(p: A => Boolean) = Find(self, p, grainSize) // forall, exists, contains
    override def fold(z: A)(op: (A, A) => A): A = Fold(self, z, op, grainSize)
    override def foreach(f: A => Unit) = Foreach(self, f, grainSize)
    override def map[B](f: A => B): Vector[B] = Map(self, f, grainSize)
    override def reduce(op: (A, A) => A): A = Reduce(self, op, grainSize)

    override def parallel = if (Parallel.isDefaultGrainSize(self, grainSize)) this else self.parallel // parallel-parallel fusion
    override def parallel(g: Int) = if (grainSize == g) this else self.parallel(g)
}

object IsParallelVector {
    def apply[A](v: Vector[A]): Boolean = v match {
        case v: ParallelVector[_] => true
        case _ => false
    }
}
