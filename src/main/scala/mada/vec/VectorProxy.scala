

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Implements a proxy for vectors.
 * It forwards all calls to a different vector object.
 */
@aliased
trait VectorProxy[A] extends Adapter.Transform[A] with Proxies.ProxyOf[Vector[A]] {
    final override def underlying = self
  // value semantics
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = underlying.equalsIf(that)(p)
    override def equals(that: Any): Boolean = Equals(this, that) // works around scala.Proxy.equals.
    override def hashCode: Int = underlying.hashCode
  // toString
    override def toString: String = underlying.toString
  // regions
    override def region(_start: Int, _end: Int): Vector[A] = underlying.region(_start, _end)
    override def regionBase: Vector[A] = underlying.regionBase
    override def init: Vector[A] = underlying.init
    override def clear: Vector[A] = underlying.clear
    override def window(n: Int, m: Int): Vector[A] = underlying.window(n, m)
    override def offset(i: Int, j: Int): Vector[A] = underlying.offset(i, j)
    override def slice(n: Int): Vector[A] = underlying.slice(n)
    override def slice(n: Int, m: Int): Vector[A] = underlying.slice(n, m)
    override def drop(n: Int): Vector[A] = underlying.drop(n)
    override def take(n: Int): Vector[A] = underlying.take(n)
    override def dropWhile(p: A => Boolean): Vector[A] = underlying.dropWhile(p)
    override def takeWhile(p: A => Boolean): Vector[A] = underlying.takeWhile(p)
  // division
    override def divide(n: Int): Vector[Vector[A]] = underlying.divide(n)
    override def splitAt(i: Int): (Vector[A], Vector[A]) = underlying.splitAt(i)
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = underlying.span(p)
    override def break(p: A => Boolean): (Vector[A], Vector[A]) = underlying.break(p)
  // as list
    override def tail: Vector[A] = underlying.tail
  // filter
    override def filter(p: A => Boolean): Vector[A] = underlying.filter(p)
    override def mutatingFilter(p: A => Boolean): Vector[A] = underlying.mutatingFilter(p)
    override def remove(p: A => Boolean): Vector[A] = underlying.remove(p)
    override def mutatingRemove(p: A => Boolean): Vector[A] = underlying.mutatingRemove(p)
    override def partition(p: A => Boolean): (Vector[A], Vector[A]) = underlying.partition(p)
  // map
    override def map[B](f: A => B): Vector[B] = underlying.map(f)
    override def flatMap[B](f: A => Vector[B]): Vector[B] = underlying.flatMap(f)
    override def asVectorOf[B]: Vector[B] = underlying.asVectorOf[B]
  // loop
    override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = underlying.loop(i, j, f)
    override def each(f: A => Unit): Unit = underlying.each(f)
  // search
    override def seek(p: A => Boolean): Option[A] = underlying.seek(p)
    override def count(p: A => Boolean): Int = underlying.count(p)
  // folding
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = underlying.folderLeft(z)(op)
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = underlying.folderRight(z)(op)
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = underlying.reducerLeft(op)
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = underlying.reducerRight(op)
  // sort
    override def sortBy(lt: Compare.Func[A]): Vector[A] = underlying.sortBy(lt)
    override def stableSortBy(lt: Compare.Func[A]): Vector[A] = underlying.stableSortBy(lt)
  // concatenation
    override def append(that: Vector[A]): Vector[A] = underlying.append(that)
  // permutation
    override def permutation(f: Int => Int): Vector[A] = underlying.permutation(f)
    override def cycle(n: Int): Vector[A] = underlying.cycle(n)
    override def nth: Vector[A] = underlying.nth
    override def reverse: Vector[A] = underlying.reverse
    override def step(n: Int): Vector[A] = underlying.step(n)
    override def rotate(i: Int): Vector[A] = underlying.rotate(i)
  // zip
    override def zip[B](that: Vector[B]): Vector[(A, B)] = underlying.zip(that)
  // attributes
    override def force: Vector[A] = underlying.force
    override def memoize : Vector[A] = underlying.memoize
    override def bounds: Vector[A] = underlying.bounds
    override def readOnly: Vector[A] = underlying.readOnly
    override def identity: Vector[A] = underlying.identity
  // mixin
    override def mixin(mx: Mixin): Vector[A] = underlying.mixin(mx)
    override def unmixin: Vector[A] = underlying.unmixin
  // copy
    override def copyTo[B >: A](that: Vector[B]): Vector[A] = underlying.copyTo(that)
    override def clone: Vector[A] = underlying.clone
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = underlying.parallel(_grainSize)
    override def grainSize: Int = underlying.grainSize
    override def defaultGrainSize: Int = underlying.defaultGrainSize
  // associative folding
    override def fold(z: A)(op: (A, A) => A): A = underlying.fold(z)(op)
    override def folder(z: A)(op: (A, A) => A): Vector[A] = underlying.folder(z)(op)
    override def reduce(op: (A, A) => A): A = underlying.reduce(op)
    override def reducer(op: (A, A) => A): Vector[A] = underlying.reducer(op)
}
