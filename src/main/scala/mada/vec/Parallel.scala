

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

private[mada] class ParallelVector[A](override val underlying: Vector[A], override val grainSize: Int) extends Adapter.ParallelAlgorithm[A] {
    Assert(!underlying.isParallel)
    ThrowIf.nonpositive(grainSize, "grain size")
    import vec.parallel._

    // value semantics
    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean) = EqualsWith(underlying, that, p, grainSize)
    // filter
    override def filter(p: A => Boolean) = Filter(underlying, p, grainSize)
    // map
    override def map[B](f: A => B): Vector[B] = Map(underlying, f, grainSize)
    // search
    override def seek(p: A => Boolean) = Seek(underlying, p, grainSize)
    override def count(p: A => Boolean) = Count(underlying, p, grainSize)
    // foreach
    override def pareach(f: A => Unit) = Pareach(underlying, f, grainSize)
    // copy
    override def copyTo[B >: A](that: Vector[B]) = CopyTo(underlying, that, grainSize)
    // parallel folding
    override def fold(z: A)(op: (A, A) => A) = Fold(underlying, z, op, grainSize)
    override def folder(z: A)(op: (A, A) => A) = Folder(underlying, z, op, grainSize)
    override def reduce(op: (A, A) => A): A = Reduce(underlying, op, grainSize)
    override def reducer(op: (A, A) => A): Vector[A] = Reducer(underlying, op, grainSize)
    override def sortWith(lt: (A, A) => Boolean) = SortWith(underlying, lt, grainSize)
}
