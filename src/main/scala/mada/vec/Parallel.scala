

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

private[mada] class ParallelVector[A](override val underlying: Vector[A], override val grainSize: Int) extends Adapter.Algorithm[A] {
    Assert(!underlying.isParallel)
    ThrowIf.nonpositive(grainSize, "grain size")
    import vec.parallel._

    override def isParallel = true
    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean) = EqualsWith(underlying, that, p, grainSize)
    override def copyTo[B >: A](that: Vector[B]) = CopyTo(underlying, that, grainSize) // clone, toArray
    override def count(p: A => Boolean) = Count(underlying, p, grainSize)
    override def filter(p: A => Boolean) = Filter(underlying, p, grainSize) // remove
    override def map[B](f: A => B): Vector[B] = Map(underlying, f, grainSize)
    override def pareach(f: A => Unit) = Pareach(underlying, f, grainSize)
    override def fold(z: A)(op: (A, A) => A) = Fold(underlying, z, op, grainSize)
    override def folder(z: A)(op: (A, A) => A) = Folder(underlying, z, op, grainSize)
    override def reduce(op: (A, A) => A): A = Reduce(underlying, op, grainSize)
    override def reducer(op: (A, A) => A): Vector[A] = Reducer(underlying, op, grainSize)
    override def seek(p: A => Boolean) = Seek(underlying, p, grainSize) // forall, exists, contains
    override def sortWith(lt: (A, A) => Boolean) = SortWith(underlying, lt, grainSize)

    override def append(that: Vector[A]) = {
        val x = underlying append that.unparallel
        if (that.isParallel) {
            x.parallel(Math.min(grainSize, that.grainSize))
        } else {
            x
        }
    }
    override def cycle(n: Int) = affect(underlying.cycle(n))
    override def identity = affect(underlying.identity)
    override def flatMap[B](f: A => Vector[B]) = affect(super.flatMap(f))
    override def force = affect(underlying.force)
    override def nth = affect(underlying.nth)
    override def permutation(f: Int => Int) = affect(underlying.permutation(f))
    override def readOnly = affect(underlying.readOnly)
    override def region(_start: Int, _end: Int) = affect(underlying.region(_start, _end))
    override def reverse = affect(underlying.reverse)
    override def step(n: Int) = affect(underlying.step(n))
    override def zip[B](that: Vector[B]) = {
        val x = underlying zip that.unparallel
        if (that.isParallel) {
            x.parallel(Math.min(grainSize, that.grainSize))
        } else {
            x
        }
    }

    private def affect[B](that: Vector[B]): Vector[B] = that.parallel(grainSize)
}
