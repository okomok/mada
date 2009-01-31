

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Parallel {
    import vec.parallel._

    def apply[A](v: Vector[A]): Vector[A] = apply(v, v.defaultGrainSize)

    def apply[A](v: Vector[A], g: Int): Vector[A] = {
        if (v.isParallel && v.grainSize == g) {
            v
        } else {
            new ParallelVector(v.unparallel, g)
        }
    }
}

private[mada] class ParallelVector[A](override val self: Vector[A], override val grainSize: Int) extends VectorProxy[A] {
    Assert(!self.isParallel)
    ThrowIf.nonpositive(grainSize, "grain size")
    import vec.parallel._

    override def isParallel = true
    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean) = EqualsWith(self, that, p, grainSize)
    override def copyTo[B >: A](that: Vector[B]) = CopyTo(self, that, grainSize) // clone, toArray
    override def count(p: A => Boolean) = Count(self, p, grainSize)
    override def filter(p: A => Boolean) = Filter(self, p, grainSize) // remove
    override def map[B](f: A => B): Vector[B] = Map(self, f, grainSize)
    override def pareach(f: A => Unit) = Pareach(self, f, grainSize)
    override def fold(z: A)(op: (A, A) => A) = Fold(self, z, op, grainSize)
    override def folder(z: A)(op: (A, A) => A) = Folder(self, z, op, grainSize)
    override def reduce(op: (A, A) => A): A = Reduce(self, op, grainSize)
    override def reducer(op: (A, A) => A): Vector[A] = Reducer(self, op, grainSize)
    override def seek(p: A => Boolean) = Seek(self, p, grainSize) // forall, exists, contains
    override def sortWith(lt: (A, A) => Boolean) = SortWith(self, lt, grainSize)

    override def append(that: Vector[A]) = {
        val x = self append that.unparallel
        if (that.isParallel) {
            x.parallel(Math.min(grainSize, that.grainSize))
        } else {
            x
        }
    }
    override def cycle(n: Int) = affect(self.cycle(n))
    override def identity = affect(self.identity)
    override def flatMap[B](f: A => Vector[B]) = affect(super.flatMap(f))
    override def force = affect(self.force)
    override def nth = affect(self.nth)
    override def permutation(f: Int => Int) = affect(self.permutation(f))
    override def readOnly = affect(self.readOnly)
    override def region(_start: Int, _end: Int) = affect(self.region(_start, _end))
    override def reverse = affect(self.reverse)
    override def step(n: Int) = affect(self.step(n))
    override def zip[B](that: Vector[B]) = {
        val x = self zip that.unparallel
        if (that.isParallel) {
            x.parallel(Math.min(grainSize, that.grainSize))
        } else {
            x
        }
    }

    private def affect[B](that: Vector[B]): Vector[B] = that.parallel(grainSize)
}
