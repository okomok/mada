

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Parallel {
    def apply[A](v: Vector[A], grainSize: Int): Vector[A] = {
        Assert(!v.isParallel)
        ThrowIf.nonpositive(grainSize, "grain size")
        new ParallelVector(v, grainSize)
    }
}

private[mada] class ParallelVector[A](override val underlying: Vector[A], override val grainSize: Int) extends Adapter.Transform[A] {
  // value semantics
    override def equalsWith[B](that: Vector[B])(p: Functions.Predicate2[A, B]) = para.EqualsWith(unparallel, that, p, grainSize)
    override def hashCode: Int = unparallel.hashCode
  // regions
    override def region(_start: Int, _end: Int): Vector[A] = affectParallel(unparallel.region(_start, _end))
  // division
    override def divide(n: Int): Vector[Vector[A]] = unparallel.divide(n).map{ v => affectParallel(v) }
    override def splitAt(i: Int): (Vector[A], Vector[A]) = affectParallel2(unparallel.splitAt(i))
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = affectParallel2(unparallel.span(p))
    override def break(p: A => Boolean): (Vector[A], Vector[A]) = affectParallel2(unparallel.break(p))
  // filter
    override def filter(p: A => Boolean) = para.Filter(unparallel, p, grainSize)
    override def mutatingFilter(p: A => Boolean): Vector[A] = para.MutatingFilter(unparallel, p, grainSize)
  // map
    override def map[B](f: A => B): Vector[B] = para.Map(unparallel, f, grainSize)
    override def flatMap[B](f: A => Vector[B]): Vector[B] = affectParallel(super.flatMap(f))
    override def asVectorOf[B]: Vector[B] = affectParallel(unparallel.asVectorOf[B])
  // loop
    override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = unparallel.loop(i, j, f)
    override def each(f: A => Unit) = para.Each(unparallel, f, grainSize)
  // search
    override def seek(p: A => Boolean) = para.Seek(unparallel, p, grainSize)
    override def count(p: A => Boolean) = para.Count(unparallel, p, grainSize)
  // folding
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = affectParallel(unparallel.folderLeft(z)(op))
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = affectParallel(unparallel.folderRight(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = affectParallel(unparallel.reducerLeft(op))
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = affectParallel(unparallel.reducerRight(op))
  // sort
    override def sortWith(lt: Compare.Type[A]) = para.SortWith(unparallel, lt, grainSize)
  // concatenation
    override def append(that: Vector[A]): Vector[A] = affectParallel(unparallel.append(that.unparallel))
  // permutation
    override def permutation(f: Int => Int): Vector[A] = affectParallel(unparallel.permutation(f))
    override def cycle(n: Int): Vector[A] = affectParallel(unparallel.cycle(n))
    override def nth: Vector[A] = affectParallel(unparallel.nth)
    override def reverse: Vector[A] = affectParallel(unparallel.reverse)
    override def step(n: Int): Vector[A] = affectParallel(unparallel.step(n))
    override def rotate(i: Int): Vector[A] = affectParallel(unparallel.rotate(i))
  // zip
    override def zip[B](that: Vector[B]): Vector[(A, B)] = affectParallel(unparallel.zip(that.unparallel))
  // attributes
    override def force: Vector[A] = affectParallel(unparallel.force)
    override def lazyValues: Vector[A] = affectParallel(unparallel.lazyValues)
    override def bounds: Vector[A] = affectParallel(unparallel.bounds)
    override def readOnly: Vector[A] = affectParallel(unparallel.readOnly)
    override def identity: Vector[A] = affectParallel(unparallel.identity)
  // copy
    override def copyTo[B >: A](that: Vector[B]): Vector[A] = para.CopyTo(unparallel, that, grainSize)
    override def clone: Vector[A] = affectParallel(super.clone)
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = { // parallel-parallel fusion
      if (_grainSize == grainSize) this else unparallel.parallel(_grainSize)
    }
    override def unparallel: Vector[A] = underlying
    override def defaultGrainSize: Int = unparallel.defaultGrainSize
    override def isParallel: Boolean = true
    override def join: Unit = unparallel.foreach{ e => () }
  // associative folding
    override def fold(z: A)(op: (A, A) => A) = para.Fold(unparallel, z, op, grainSize)
    override def folder(z: A)(op: (A, A) => A) = para.Folder(unparallel, z, op, grainSize)
    override def reduce(op: (A, A) => A): A = para.Reduce(unparallel, op, grainSize)
    override def reducer(op: (A, A) => A): Vector[A] = para.Reducer(unparallel, op, grainSize)

    private def affectParallel[B](that: Vector[B]): Vector[B] = { Assert(!that.isParallel); that.parallel(grainSize) }
    private def affectParallel2[B](that: (Vector[B], Vector[B])): (Vector[B], Vector[B]) = (affectParallel(that._1), affectParallel(that._2))
}
