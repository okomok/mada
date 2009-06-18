

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Parallel[A](_1: Vector[A], _2: Int) extends Forwarder[A] {
    util.assert(!IsParallel(_1))
    Precondition.positive(_2, "grain size")

    override protected val delegate = _1

// parallel support
    override def parallel(g: Int): Vector[A] = if (g == grainSize) this else delegate.parallel(g) // parallel-parallel fusion
    override def grainSize: Int = _2
// value semantics
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = ParallelEqualsIf(delegate, that, p, grainSize)
// filter
    override def mutatingFilter(p: A => Boolean): Vector[A] = ParallelMutatingFilter(delegate, p, grainSize)
// map
    override def map[B](f: A => B): Vector[B] = ParallelMap(delegate, f, grainSize)
// loop
    override def each(f: A => Unit): Unit = ParallelEach(delegate, f, grainSize)
// search
    override def seek(p: A => Boolean): Option[A] = ParallelSeek(delegate, p, grainSize)
    override def count(p: A => Boolean): Int = ParallelCount(delegate, p, grainSize)
// sort
    override def sort(implicit c: Compare[A]): Vector[A] = ParallelSort(delegate, c, grainSize)
    override def sortBy(lt: compare.Func[A]): Vector[A] = ParallelSort(delegate, lt, grainSize)
// copy
    override def copy: Vector[A] = ParallelCopy(delegate, grainSize)
    override def copyTo[B >: A](that: Vector[B]): Vector[B] = ParallelCopyTo(delegate, that, grainSize)
// associative folding
    override def fold(z: A)(op: (A, A) => A): A = ParallelFold(delegate, z, op, grainSize)
    override def reduce(op: (A, A) => A): A = ParallelReduce(delegate, op, grainSize)
    override def folder(z: A)(op: (A, A) => A): Vector[A] = ParallelFolder(delegate, z, op, grainSize)
    override def reducer(op: (A, A) => A): Vector[A] = ParallelReducer(delegate, op, grainSize)
// conversion
    override def toArray: Array[A] = {
        val r = new Array[A](size)
        ParallelCopyTo(delegate, fromArray(r), grainSize)
        r
    }
}


private object IsParallel {
    def apply[A](v: Vector[A]): Boolean = v.isInstanceOf[Parallel[_]]
}
