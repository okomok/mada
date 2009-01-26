

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Parallel {
    import vec.parallel._

    def apply[A](v: Vector[A]): Vector[A] = apply(v, DefaultGrainSize(v))
    def apply[A](v: Vector[A], g: Int): Vector[A] = new ParallelVector(v, g)
}

class ParallelVector[A](override val self: Vector[A], grainSize: Int) extends VectorProxy[A] {
    Assert(!self.isParallel)
    ThrowIf.nonpositive(grainSize, "grain size")
    import vec.parallel._

    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean) = EqualsWith(self, that, p, grainSize)
    override def copyTo[B >: A](that: Vector[B]) = CopyTo(self, that, grainSize) // clone, toArray
    override def count(p: A => Boolean) = Count(self, p, grainSize)
    override def filter(p: A => Boolean) = Filter(self, p, grainSize) // remove
    override def map[B](f: A => B): Vector[B] = Map(self, f, grainSize)
    override def pareach(f: A => Unit) = Pareach(self, f, grainSize)
    override def reduce(op: (A, A) => A): A = Reduce(self, op, grainSize)
    override def seek(p: A => Boolean) = Seek(self, p, grainSize) // forall, exists, contains
    override def sortWith(lt: (A, A) => Boolean) = SortWith(self, lt, grainSize)

    override def parallel = {
        if (grainSize == DefaultGrainSize(self)) {
            this // parallel-parallel fusion
        } else {
            self.parallel
        }
    }
    override def parallel(g: Int) = {
        if (grainSize == g) {
            this // parallel-parallel fusion
        } else {
            self.parallel(g)
        }
    }
    override def isParallel = true
}
