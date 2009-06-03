

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


trait Forwarder[A] extends iterative.Forwarder[A] with Adapter.Transform[A]  {
    override protected def delegate: Vector[A]
    protected def afterForward[B](that: Vector[B]): Vector[B] = that
    private def afterForward2[B](that: (Vector[B], Vector[B])): (Vector[B], Vector[B]) = (afterForward(that._1), afterForward(that._2))
    final override def afterForward[B](that: Iterative[B]): Iterative[B] = throw new Error
    final override def underlying = delegate

// sequence
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = delegate.equalsIf(that)(p)
    override def ++(that: Vector[A]): Vector[A] = afterForward(delegate.++(that))
    override def map[B](f: A => B): Vector[B] = afterForward(delegate.map(f))
    override def flatMap[B](f: A => Iterative[B]): Vector[B] = afterForward(delegate.flatMap(f))
    override def filter(p: A => Boolean): Vector[A] = afterForward(delegate.filter(p))
    override def filterNot(p: A => Boolean): Vector[A] = afterForward(delegate.filterNot(p))
    override def partition(p: A => Boolean): (Vector[A], Vector[A]) = afterForward2(delegate.partition(p))
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = afterForward(delegate.folderLeft(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = afterForward(delegate.reducerLeft(op))
    override def tail: Vector[A] = afterForward(delegate.tail)
    override def drop(n: Int): Vector[A] = afterForward(delegate.drop(n))
    override def take(n: Int): Vector[A] = afterForward(delegate.take(n))
    override def slice(n: Int, m: Int): Vector[A] = afterForward(delegate.slice(n, m))
    override def dropWhile(p: A => Boolean): Vector[A] = afterForward(delegate.dropWhile(p))
    override def takeWhile(p: A => Boolean): Vector[A] = afterForward(delegate.takeWhile(p))
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = afterForward2(delegate.span(p))
    override def splitAt(i: Int): (Vector[A], Vector[A]) = afterForward2(delegate.splitAt(i))
    override def at(n: Int): A = delegate.at(n)
    override def seal: Vector[A] = delegate.seal
    override def times(n: Int): Vector[A] = afterForward(delegate.times(n))
    override def force: Vector[A] = afterForward(delegate.force)
    override def mix(x: Mixin): Vector[A] = afterForward(delegate.mix(x))
    override def step(n: Int): Vector[A] = afterForward(delegate.step(n))
    override def zip[B](that: Vector[B]): Vector[(A, B)] = afterForward(delegate.zip(that))

  // regions
    override def region(_start: Int, _end: Int): Vector[A] = afterForward(delegate.region(_start, _end))
    override def regionBase: Vector[A] = afterForward(delegate.regionBase)
    override def shallowEquals[B](that: Vector[B]): Boolean = delegate.shallowEquals(that)
    override def init: Vector[A] = afterForward(delegate.init)
    override def clear: Vector[A] = afterForward(delegate.clear)
    override def window(n: Int, m: Int): Vector[A] = afterForward(delegate.window(n, m))
    override def offset(i: Int, j: Int): Vector[A] = afterForward(delegate.offset(i, j))
    override def slice(n: Int): Vector[A] = afterForward(delegate.slice(n))
  // division
    override def divide(n: Int): Vector[Vector[A]] = afterForward(delegate.divide(n))
    override def break(p: A => Boolean): (Vector[A], Vector[A]) = afterForward2(delegate.break(p))
  // filter
    override def mutatingFilter(p: A => Boolean): Vector[A] = afterForward(delegate.mutatingFilter(p))
    override def mutatingRemove(p: A => Boolean): Vector[A] = afterForward(delegate.mutatingRemove(p))
  // map
   override def asVectorOf[B]: Vector[B] = afterForward(delegate.asVectorOf[B])
  // loop
    override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = delegate.loop(i, j, f)
    override def each(f: A => Unit): Unit = delegate.each(f)
  // search
    override def seek(p: A => Boolean): Option[A] = delegate.seek(p)
  // folding
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = afterForward(delegate.folderRight(z)(op))
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = afterForward(delegate.reducerRight(op))
  // sort
    override def sortBy(lt: compare.Func[A]): Vector[A] = afterForward(delegate.sortBy(lt))
    override def stableSortBy(lt: compare.Func[A]): Vector[A] = afterForward(delegate.stableSortBy(lt))
  // permutation
    override def permutation(f: Int => Int): Vector[A] = afterForward(delegate.permutation(f))
    override def nth: Vector[A] = afterForward(delegate.nth)
    override def reverse: Vector[A] = afterForward(delegate.reverse)
    override def rotate(i: Int): Vector[A] = afterForward(delegate.rotate(i))
  // attributes
    override def memoize : Vector[A] = afterForward(delegate.memoize)
    override def bounds: Vector[A] = afterForward(delegate.bounds)
    override def readOnly: Vector[A] = afterForward(delegate.readOnly)
    override def identity: Vector[A] = afterForward(delegate.identity)
  // copy
    override def copyTo[B >: A](that: Vector[B]): Vector[A] = afterForward(delegate.copyTo(that))
    override def copy: Vector[A] = afterForward(delegate.copy)
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = afterForward(delegate.parallel(_grainSize))
    override def grainSize: Int = delegate.grainSize
    override def defaultGrainSize: Int = delegate.defaultGrainSize
  // associative folding
    override def folder(z: A)(op: (A, A) => A): Vector[A] = afterForward(delegate.folder(z)(op))
    override def reducer(op: (A, A) => A): Vector[A] = afterForward(delegate.reducer(op))
}
