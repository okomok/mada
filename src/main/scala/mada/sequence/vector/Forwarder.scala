

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


trait Forwarder[A] extends TransformAdapter[A] with SequenceForwarder[A] {
    override protected def delegate: Vector[A]
    final override def underlying = delegate

    protected def around[B](that: => Vector[B]): Vector[B] = that
    protected def around2[B, C](that: => (Vector[B], Vector[C])): (Vector[B], Vector[C]) = {
        val _that = that
        (around(_that._1), around(_that._2))
    }

// iterative
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = delegate.equalsIf(that)(p)
    override def ++(that: Vector[A]): Vector[A] = around(delegate.++(that))
    override def map[B](f: A => B): Vector[B] = around(delegate.map(f))
    override def flatMap[B](f: A => Vector[B]): Vector[B] = around(delegate.flatMap(f))
    override def filter(p: A => Boolean): Vector[A] = around(delegate.filter(p))
    override def remove(p: A => Boolean): Vector[A] = around(delegate.remove(p))
    override def partition(p: A => Boolean): (Vector[A], Vector[A]) = around2(delegate.partition(p))
    override def foreach(f: A => Unit): Unit = delegate.foreach(f)
    override def forall(p: A => Boolean): Boolean = delegate.forall(p)
    override def exists(p: A => Boolean): Boolean = delegate.exists(p)
    override def count(p: A => Boolean): Int = delegate.count(p)
    override def find(p: A => Boolean): Option[A] = delegate.find(p)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = delegate.foldLeft(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = delegate.reduceLeft(op)
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = around(delegate.folderLeft(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = around(delegate.reducerLeft(op))
    override def head: A = delegate.head
    override def headOption: Option[A] = delegate.headOption
    override def tail: Vector[A] = around(delegate.tail)
    override def last: A = delegate.last
    override def lastOption: Option[A] = delegate.lastOption
    override def take(n: Int): Vector[A] = around(delegate.take(n))
    override def drop(n: Int): Vector[A] = around(delegate.drop(n))
    override def slice(n: Int, m: Int): Vector[A] = around(delegate.slice(n, m))
    override def takeWhile(p: A => Boolean): Vector[A] = around(delegate.takeWhile(p))
    override def dropWhile(p: A => Boolean): Vector[A] = around(delegate.dropWhile(p))
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = around2(delegate.span(p))
    override def splitAt(i: Int): (Vector[A], Vector[A]) = around2(delegate.splitAt(i))
    override def at(n: Int): A = delegate.at(n)
    override def contains(e: Any): Boolean = delegate.contains(e)
    override def times(n: Int): Vector[A] = around(delegate.times(n))
    override def force: Vector[A] = around(delegate.force)
    override def memoize : Vector[A] = around(delegate.memoize)
    override def mix(x: Mixin): Vector[A] = around(delegate.mix(x))
    override def step(n: Int): Vector[A] = around(delegate.step(n))
    override def zip[B](that: Vector[B]): Vector[(A, B)] = around(delegate.zip(that))
    override def _unzip[B, C](_this: Vector[(B, C)]): (Vector[B], Vector[C]) = around2(delegate.asInstanceOf[Vector[(B, C)]].unzip)
    override def zipBy[B, C](that: Vector[B])(f: (A, B) => C): Vector[C] = around(delegate.zipBy(that)(f))

// regions
    override def region(_start: Int, _end: Int): Vector[A] = around(delegate.region(_start, _end))
    override def regionBase: Vector[A] = around(delegate.regionBase)
    override def init: Vector[A] = around(delegate.init)
    override def clear: Vector[A] = around(delegate.clear)
    override def window(n: Int, m: Int): Vector[A] = around(delegate.window(n, m))
    override def offset(i: Int, j: Int): Vector[A] = around(delegate.offset(i, j))
    override def shallowEquals[B](that: Vector[B]): Boolean = delegate.shallowEquals(that)
// division
    override def divide(n: Int): Vector[Vector[A]] = around(delegate.divide(n))
    override def _undivide[B](_this: Vector[Vector[B]]): Vector[B] = around(delegate.asInstanceOf[Vector[Vector[B]]].undivide)
    override def break(p: A => Boolean): (Vector[A], Vector[A]) = around2(delegate.break(p))
// filter
    override def mutatingFilter(p: A => Boolean): Vector[A] = around(delegate.mutatingFilter(p))
    override def mutatingRemove(p: A => Boolean): Vector[A] = around(delegate.mutatingRemove(p))
// map
   override def asVectorOf[B]: Vector[B] = around(delegate.asVectorOf[B])
// loop
    override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = delegate.loop(i, j, f)
    override def each(f: A => Unit): Unit = delegate.each(f)
// search
    override def seek(p: A => Boolean): Option[A] = delegate.seek(p)
// folding
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = around(delegate.folderRight(z)(op))
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = around(delegate.reducerRight(op))
// sort
    override def sort(implicit c: Compare[A]): Vector[A] = around(delegate.sort(c))
    override def sortBy(lt: compare.Func[A]): Vector[A] = around(delegate.sortBy(lt))
    override def stableSort(implicit c: Compare[A]): Vector[A] = around(delegate.stableSort(c))
    override def stableSortBy(lt: compare.Func[A]): Vector[A] = around(delegate.stableSortBy(lt))
// permutation
    override def permutation(f: Int => Int): Vector[A] = around(delegate.permutation(f))
    override def nth: Vector[A] = around(delegate.nth)
    override def reverse: Vector[A] = around(delegate.reverse)
    override def rotate(i: Int): Vector[A] = around(delegate.rotate(i))
// attributes
    override def bounds: Vector[A] = around(delegate.bounds)
    override def readOnly: Vector[A] = around(delegate.readOnly)
// copy
    override def copy: Vector[A] = around(delegate.copy)
    override def copyTo[B >: A](that: Vector[B]): Vector[B] = delegate.copyTo(that)
// parallel support
    override def parallel(_grainSize: Int): Vector[A] = around(delegate.parallel(_grainSize))
    override def grainSize: Int = delegate.grainSize
    override def defaultGrainSize: Int = delegate.defaultGrainSize
// associative folding
    override def fold(z: A)(op: (A, A) => A): A = delegate.fold(z)(op)
    override def reduce(op: (A, A) => A): A = delegate.reduce(op)
    override def folder(z: A)(op: (A, A) => A): Vector[A] = around(delegate.folder(z)(op))
    override def reducer(op: (A, A) => A): Vector[A] = around(delegate.reducer(op))
// conversion
    override def toArray: Array[A] = delegate.toArray
    override def toProduct: Product = delegate.toProduct
    override def toOrdered(implicit c: compare.GetOrdered[A]): Ordered[Vector[A]] = delegate.toOrdered(c)
    override def toSVector: scala.collection.mutable.Vector[A] = delegate.toSVector
    override def toJArrayList: java.util.ArrayList[A] = delegate.toJArrayList
    override def toJIterable: java.lang.Iterable[A] = delegate.toJIterable
// string
    override def _stringize(_this: Vector[Char]): String = delegate.asInstanceOf[Vector[Char]].stringize
    override def _lowerCase(_this: Vector[Char]): Vector[Char] = delegate.asInstanceOf[Vector[Char]].lowerCase
    override def _upperCase(_this: Vector[Char]): Vector[Char] = delegate.asInstanceOf[Vector[Char]].upperCase
}
