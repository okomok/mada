

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector

/*
private[mada] object Synchronize {
    def apply[A](v: Vector[A]): Vector[A] = new SynchronizeVector(v)
}

private[mada] class SynchronizeVector[A](override val delegate: Vector[A]) extends Forwarder[A] {
  // value semantics
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = synchronized { delegate.equalsIf(that)(p) }
    override def equals(that: Any): Boolean = synchronized { Equals(this, that) } // works around scala.Proxy.equals.
    override def hashCode: Int = synchronized { delegate.hashCode }
  // toString
    override def toString: String = synchronized { delegate.toString }
  // regions
    override def region(_start: Int, _end: Int): Vector[A] = synchronized { delegate.region(_start, _end) }
    override def regionBase: Vector[A] = synchronized { delegate.regionBase }
    override def init: Vector[A] = synchronized { delegate.init }
    override def clear: Vector[A] = synchronized { delegate.clear }
    override def window(n: Int, m: Int): Vector[A] = synchronized { delegate.window(n, m) }
    override def offset(i: Int, j: Int): Vector[A] = synchronized { delegate.offset(i, j) }
    override def slice(n: Int): Vector[A] = synchronized { delegate.slice(n) }
    override def slice(n: Int, m: Int): Vector[A] = synchronized { delegate.slice(n, m) }
    override def drop(n: Int): Vector[A] = synchronized { delegate.drop(n) }
    override def take(n: Int): Vector[A] = synchronized { delegate.take(n) }
    override def dropWhile(p: A => Boolean): Vector[A] = synchronized { delegate.dropWhile(p) }
    override def takeWhile(p: A => Boolean): Vector[A] = synchronized { delegate.takeWhile(p) }
  // division
    override def divide(n: Int): Vector[Vector[A]] = synchronized { delegate.divide(n) }
    override def splitAt(i: Int): (Vector[A], Vector[A]) = synchronized { delegate.splitAt(i) }
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = synchronized { delegate.span(p) }
    override def break(p: A => Boolean): (Vector[A], Vector[A]) = synchronized { delegate.break(p) }
  // as list
    override def tail: Vector[A] = synchronized { delegate.tail }
  // filter
    override def filter(p: A => Boolean): Vector[A] = synchronized { delegate.filter(p) }
    override def mutatingFilter(p: A => Boolean): Vector[A] = synchronized { delegate.mutatingFilter(p) }
    override def remove(p: A => Boolean): Vector[A] = synchronized { delegate.remove(p) }
    override def mutatingRemove(p: A => Boolean): Vector[A] = synchronized { delegate.mutatingRemove(p) }
    override def partition(p: A => Boolean): (Vector[A], Vector[A]) = synchronized { delegate.partition(p) }
  // map
    override def map[B](f: A => B): Vector[B] = synchronized { delegate.map(f) }
    override def flatMap[B](f: A => Vector[B]): Vector[B] = synchronized { delegate.flatMap(f) }
    override def asVectorOf[B]: Vector[B] = synchronized { delegate.asVectorOf[B] }
  // loop
    override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = synchronized { delegate.loop(i, j, f) }
    override def each(f: A => Unit): Unit = synchronized { delegate.each(f) }
  // search
    override def seek(p: A => Boolean): Option[A] = synchronized { delegate.seek(p) }
    override def count(p: A => Boolean): Int = synchronized { delegate.count(p) }
  // folding
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = synchronized { delegate.folderLeft(z)(op) }
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = synchronized { delegate.folderRight(z)(op) }
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = synchronized { delegate.reducerLeft(op) }
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = synchronized { delegate.reducerRight(op) }
  // sort
    override def sortBy(lt: compare.Func[A]): Vector[A] = synchronized { delegate.sortBy(lt) }
    override def stableSortBy(lt: compare.Func[A]): Vector[A] = synchronized { delegate.stableSortBy(lt) }
  // concatenation
    override def append(that: Vector[A]): Vector[A] = synchronized { delegate.append(that) }
  // permutation
    override def permutation(f: Int => Int): Vector[A] = synchronized { delegate.permutation(f) }
    override def cycle(n: Int): Vector[A] = synchronized { delegate.cycle(n) }
    override def nth: Vector[A] = synchronized { delegate.nth }
    override def reverse: Vector[A] = synchronized { delegate.reverse }
    override def step(n: Int): Vector[A] = synchronized { delegate.step(n) }
    override def rotate(i: Int): Vector[A] = synchronized { delegate.rotate(i) }
  // zip
    override def zip[B](that: Vector[B]): Vector[(A, B)] = synchronized { delegate.zip(that) }
  // attributes
    override def force: Vector[A] = synchronized { delegate.force }
    override def memoize : Vector[A] = synchronized { delegate.memoize }
    override def bounds: Vector[A] = synchronized { delegate.bounds }
    override def readOnly: Vector[A] = synchronized { delegate.readOnly }
    override def identity: Vector[A] = synchronized { delegate.identity }
  // mixin
    override def mixin(mx: Mixin): Vector[A] = synchronized { delegate.mixin(mx) }
    override def unmixin: Vector[A] = synchronized { delegate.unmixin }
  // copy
    override def copyTo[B >: A](that: Vector[B]): Vector[A] = synchronized { delegate.copyTo(that) }
    override def clone: Vector[A] = synchronized { delegate.clone }
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = synchronized { delegate.parallel(_grainSize) }
    override def grainSize: Int = synchronized { delegate.grainSize }
    override def defaultGrainSize: Int = synchronized { delegate.defaultGrainSize }
  // associative folding
    override def fold(z: A)(op: (A, A) => A): A = synchronized { delegate.fold(z)(op) }
    override def folder(z: A)(op: (A, A) => A): Vector[A] = synchronized { delegate.folder(z)(op) }
    override def reduce(op: (A, A) => A): A = synchronized { delegate.reduce(op) }
    override def reducer(op: (A, A) => A): Vector[A] = synchronized { delegate.reducer(op) }
}
*/
