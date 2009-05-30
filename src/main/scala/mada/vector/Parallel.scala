

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Parallel {
    def apply[A](v: Vector[A], grainSize: Int): Vector[A] = new ParallelVector(v, grainSize)
}

private[mada] class ParallelVector[A](override val delegate: Vector[A], override val grainSize: Int) extends Forwarder[A] {
    util.assert(!IsParallel(delegate))
    ThrowIf.nonpositive(grainSize, "grain size")
  // value semantics
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean) = para.EqualsIf(delegate, that, p, grainSize)
  // filter
    override def filter(p: A => Boolean) = para.Filter(delegate, p, grainSize)
    override def mutatingFilter(p: A => Boolean): Vector[A] = para.MutatingFilter(delegate, p, grainSize)
  // map
    override def map[B](f: A => B): Vector[B] = para.Map(delegate, f, grainSize)
    override def flatMap[B](f: A => sequence.Iterative[B]): Vector[B] = vector.flatten(map(f))
  // loop
    override def each(f: A => Unit) = para.Each(delegate, f, grainSize)
  // search
    override def seek(p: A => Boolean) = para.Seek(delegate, p, grainSize)
    override def count(p: A => Boolean) = para.Count(delegate, p, grainSize)
  // sort
    override def sortBy(lt: compare.Func[A]) = para.Sort(delegate, lt, grainSize)
  // copy
    override def copyTo[B >: A](that: Vector[B]): Vector[A] = para.CopyTo(delegate, that, grainSize)
    override def copy: Vector[A] = vector.fromArray(ToArray(this))
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = { // parallel-parallel fusion
      if (_grainSize == grainSize) this else delegate.parallel(_grainSize)
    }
  // associative folding
    override def fold(z: A)(op: (A, A) => A) = para.Fold(delegate, z, op, grainSize)
    override def folder(z: A)(op: (A, A) => A) = para.Folder(delegate, z, op, grainSize)
    override def reduce(op: (A, A) => A): A = para.Reduce(delegate, op, grainSize)
    override def reducer(op: (A, A) => A): Vector[A] = para.Reducer(delegate, op, grainSize)
}


private[mada] object IsParallel {
    def apply[A](v: Vector[A]): Boolean = v.isInstanceOf[ParallelVector[_]]
}
