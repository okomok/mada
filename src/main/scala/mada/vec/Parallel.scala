

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Parallel {
    def apply[A](v: Vector[A], grainSize: Int): Vector[A] = new ParallelVector(v, grainSize)
}

private[mada] class ParallelVector[A](override val self: Vector[A], override val grainSize: Int) extends VectorProxy[A] {
    Assert(!IsParallel(self))
    ThrowIf.nonpositive(grainSize, "grain size")
  // value semantics
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean) = para.EqualsIf(self, that, p, grainSize)
  // filter
    override def filter(p: A => Boolean) = para.Filter(self, p, grainSize)
    override def mutatingFilter(p: A => Boolean): Vector[A] = para.MutatingFilter(self, p, grainSize)
  // map
    override def map[B](f: A => B): Vector[B] = para.Map(self, f, grainSize)
    override def flatMap[B](f: A => Vector[B]): Vector[B] = Vector.flatten(map(f).toIterable)
  // loop
    override def each(f: A => Unit) = para.Each(self, f, grainSize)
  // search
    override def seek(p: A => Boolean) = para.Seek(self, p, grainSize)
    override def count(p: A => Boolean) = para.Count(self, p, grainSize)
  // sort
    override def sortBy(lt: Compare.Func[A]) = para.SortBy(self, lt, grainSize)
  // copy
    override def copyTo[B >: A](that: Vector[B]): Vector[A] = para.CopyTo(self, that, grainSize)
    override def clone: Vector[A] = Vector.fromArray(ToArray(this))
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = { // parallel-parallel fusion
      if (_grainSize == grainSize) this else self.parallel(_grainSize)
    }
  // associative folding
    override def fold(z: A)(op: (A, A) => A) = para.Fold(self, z, op, grainSize)
    override def folder(z: A)(op: (A, A) => A) = para.Folder(self, z, op, grainSize)
    override def reduce(op: (A, A) => A): A = para.Reduce(self, op, grainSize)
    override def reducer(op: (A, A) => A): Vector[A] = para.Reducer(self, op, grainSize)
}


private[mada] object IsParallel {
    def apply[A](v: Vector[A]): Boolean = v.isInstanceOf[ParallelVector[_]]
}
