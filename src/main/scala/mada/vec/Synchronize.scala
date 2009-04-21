

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Synchronize {
    def apply[A](v: Vector[A]): Vector[A] = new SynchronizeVector(v)
}

private[mada] class SynchronizeVector[A](override val self: Vector[A]) extends VectorProxy[A] {
  // value semantics
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = synchronized { self.equalsIf(that)(p) }
    override def equals(that: Any): Boolean = synchronized { Equals(this, that) } // works around scala.Proxy.equals.
    override def hashCode: Int = synchronized { self.hashCode }
  // toString
    override def toString: String = synchronized { self.toString }
  // regions
    override def region(_start: Int, _end: Int): Vector[A] = synchronized { self.region(_start, _end) }
    override def regionBase: Vector[A] = synchronized { self.regionBase }
    override def init: Vector[A] = synchronized { self.init }
    override def clear: Vector[A] = synchronized { self.clear }
    override def window(n: Int, m: Int): Vector[A] = synchronized { self.window(n, m) }
    override def offset(i: Int, j: Int): Vector[A] = synchronized { self.offset(i, j) }
    override def slice(n: Int): Vector[A] = synchronized { self.slice(n) }
    override def slice(n: Int, m: Int): Vector[A] = synchronized { self.slice(n, m) }
    override def drop(n: Int): Vector[A] = synchronized { self.drop(n) }
    override def take(n: Int): Vector[A] = synchronized { self.take(n) }
    override def dropWhile(p: A => Boolean): Vector[A] = synchronized { self.dropWhile(p) }
    override def takeWhile(p: A => Boolean): Vector[A] = synchronized { self.takeWhile(p) }
  // division
    override def divide(n: Int): Vector[Vector[A]] = synchronized { self.divide(n) }
    override def splitAt(i: Int): (Vector[A], Vector[A]) = synchronized { self.splitAt(i) }
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = synchronized { self.span(p) }
    override def break(p: A => Boolean): (Vector[A], Vector[A]) = synchronized { self.break(p) }
  // as list
    override def tail: Vector[A] = synchronized { self.tail }
  // filter
    override def filter(p: A => Boolean): Vector[A] = synchronized { self.filter(p) }
    override def mutatingFilter(p: A => Boolean): Vector[A] = synchronized { self.mutatingFilter(p) }
    override def remove(p: A => Boolean): Vector[A] = synchronized { self.remove(p) }
    override def mutatingRemove(p: A => Boolean): Vector[A] = synchronized { self.mutatingRemove(p) }
    override def partition(p: A => Boolean): (Vector[A], Vector[A]) = synchronized { self.partition(p) }
  // map
    override def map[B](f: A => B): Vector[B] = synchronized { self.map(f) }
    override def flatMap[B](f: A => Vector[B]): Vector[B] = synchronized { self.flatMap(f) }
    override def asVectorOf[B]: Vector[B] = synchronized { self.asVectorOf[B] }
  // loop
    override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = synchronized { self.loop(i, j, f) }
    override def each(f: A => Unit): Unit = synchronized { self.each(f) }
  // search
    override def seek(p: A => Boolean): Option[A] = synchronized { self.seek(p) }
    override def count(p: A => Boolean): Int = synchronized { self.count(p) }
  // folding
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = synchronized { self.folderLeft(z)(op) }
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = synchronized { self.folderRight(z)(op) }
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = synchronized { self.reducerLeft(op) }
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = synchronized { self.reducerRight(op) }
  // sort
    override def sortBy(lt: Compare.Func[A]): Vector[A] = synchronized { self.sortBy(lt) }
    override def stableSortBy(lt: Compare.Func[A]): Vector[A] = synchronized { self.stableSortBy(lt) }
  // concatenation
    override def append(that: Vector[A]): Vector[A] = synchronized { self.append(that) }
  // permutation
    override def permutation(f: Int => Int): Vector[A] = synchronized { self.permutation(f) }
    override def cycle(n: Int): Vector[A] = synchronized { self.cycle(n) }
    override def nth: Vector[A] = synchronized { self.nth }
    override def reverse: Vector[A] = synchronized { self.reverse }
    override def step(n: Int): Vector[A] = synchronized { self.step(n) }
    override def rotate(i: Int): Vector[A] = synchronized { self.rotate(i) }
  // zip
    override def zip[B](that: Vector[B]): Vector[(A, B)] = synchronized { self.zip(that) }
  // attributes
    override def force: Vector[A] = synchronized { self.force }
    override def memoize : Vector[A] = synchronized { self.memoize }
    override def bounds: Vector[A] = synchronized { self.bounds }
    override def readOnly: Vector[A] = synchronized { self.readOnly }
    override def identity: Vector[A] = synchronized { self.identity }
  // mixin
    override def mixin(mx: Mixin): Vector[A] = synchronized { self.mixin(mx) }
    override def unmixin: Vector[A] = synchronized { self.unmixin }
  // copy
    override def copyTo[B >: A](that: Vector[B]): Vector[A] = synchronized { self.copyTo(that) }
    override def clone: Vector[A] = synchronized { self.clone }
  // parallel support
    override def parallel(_grainSize: Int): Vector[A] = synchronized { self.parallel(_grainSize) }
    override def grainSize: Int = synchronized { self.grainSize }
    override def defaultGrainSize: Int = synchronized { self.defaultGrainSize }
  // associative folding
    override def fold(z: A)(op: (A, A) => A): A = synchronized { self.fold(z)(op) }
    override def folder(z: A)(op: (A, A) => A): Vector[A] = synchronized { self.folder(z)(op) }
    override def reduce(op: (A, A) => A): A = synchronized { self.reduce(op) }
    override def reducer(op: (A, A) => A): Vector[A] = synchronized { self.reducer(op) }
}
