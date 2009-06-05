

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


trait Forwarder[A] extends TransformAdapter[A] with iterative.SequenceForwarder[A] {
    override protected def delegate: Vector[A]
    protected def afterForward[B](that: Vector[B]): Vector[B] = that
    private def afterForward2[B, C](that: (Vector[B], Vector[C])): (Vector[B], Vector[C]) = (afterForward(that._1), afterForward(that._2))
    final override def underlying = delegate

// iterative
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = delegate.equalsIf(that)(p)
    override def ++(that: Vector[A]): Vector[A] = afterForward(delegate.++(that))
    override def map[B](f: A => B): Vector[B] = afterForward(delegate.map(f))
    override def flatMap[B](f: A => Vector[B]): Vector[B] = afterForward(delegate.flatMap(f))
    override def filter(p: A => Boolean): Vector[A] = afterForward(delegate.filter(p))
    override def remove(p: A => Boolean): Vector[A] = afterForward(delegate.remove(p))
    override def partition(p: A => Boolean): (Vector[A], Vector[A]) = afterForward2(delegate.partition(p))
    override def foreach(f: A => Unit): Unit = delegate.foreach(f)
    override def forall(p: A => Boolean): Boolean = delegate.forall(p)
    override def exists(p: A => Boolean): Boolean = delegate.exists(p)
    override def count(p: A => Boolean): Int = delegate.count(p)
    override def find(p: A => Boolean): Option[A] = delegate.find(p)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = delegate.foldLeft(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = delegate.reduceLeft(op)
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = afterForward(delegate.folderLeft(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = afterForward(delegate.reducerLeft(op))
    override def head: A = delegate.head
    override def headOption: Option[A] = delegate.headOption
    override def tail: Vector[A] = afterForward(delegate.tail)
    override def last: A = delegate.last
    override def lastOption: Option[A] = delegate.lastOption
    override def take(n: Int): Vector[A] = afterForward(delegate.take(n))
    override def drop(n: Int): Vector[A] = afterForward(delegate.drop(n))
    override def slice(n: Int, m: Int): Vector[A] = afterForward(delegate.slice(n, m))
    override def takeWhile(p: A => Boolean): Vector[A] = afterForward(delegate.takeWhile(p))
    override def dropWhile(p: A => Boolean): Vector[A] = afterForward(delegate.dropWhile(p))
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = afterForward2(delegate.span(p))
    override def splitAt(i: Int): (Vector[A], Vector[A]) = afterForward2(delegate.splitAt(i))
    override def at(n: Int): A = delegate.at(n)
    override def contains(e: Any): Boolean = delegate.contains(e)
    override def times(n: Int): Vector[A] = afterForward(delegate.times(n))
    override def force: Vector[A] = afterForward(delegate.force)
    override def memoize : Vector[A] = afterForward(delegate.memoize)
    override def mix(x: Mixin): Vector[A] = afterForward(delegate.mix(x))
    override def step(n: Int): Vector[A] = afterForward(delegate.step(n))
    override def zip[B](that: Vector[B]): Vector[(A, B)] = afterForward(delegate.zip(that))
    override def _unzip[B, C](_this: Vector[(B, C)]): (Vector[B], Vector[C]) = afterForward2(delegate.asInstanceOf[Vector[(B, C)]].unzip)
    override def zipBy[B, C](that: Vector[B])(f: (A, B) => C): Vector[C] = afterForward(delegate.zipBy(that)(f))

// regions
    override def region(_start: Int, _end: Int): Vector[A] = afterForward(delegate.region(_start, _end))
    override def regionBase: Vector[A] = afterForward(delegate.regionBase)
    override def init: Vector[A] = afterForward(delegate.init)
    override def clear: Vector[A] = afterForward(delegate.clear)
    override def window(n: Int, m: Int): Vector[A] = afterForward(delegate.window(n, m))
    override def offset(i: Int, j: Int): Vector[A] = afterForward(delegate.offset(i, j))
    override def shallowEquals[B](that: Vector[B]): Boolean = delegate.shallowEquals(that)
// division
    override def divide(n: Int): Vector[Vector[A]] = afterForward(delegate.divide(n))
    override def _undivide[B](_this: Vector[Vector[B]]): Vector[B] = afterForward(delegate.asInstanceOf[Vector[Vector[B]]].undivide)
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
    override def bounds: Vector[A] = afterForward(delegate.bounds)
    override def readOnly: Vector[A] = afterForward(delegate.readOnly)
// copy
    override def copy: Vector[A] = afterForward(delegate.copy)
    override def copyTo[B >: A](that: Vector[B]): Vector[B] = delegate.copyTo(that)
// parallel support
    override def parallel(_grainSize: Int): Vector[A] = afterForward(delegate.parallel(_grainSize))
    override def grainSize: Int = delegate.grainSize
    override def defaultGrainSize: Int = delegate.defaultGrainSize
// associative folding
    override def folder(z: A)(op: (A, A) => A): Vector[A] = afterForward(delegate.folder(z)(op))
    override def reducer(op: (A, A) => A): Vector[A] = afterForward(delegate.reducer(op))
// string
    override def _stringize(_this: Vector[Char]): String = delegate.asInstanceOf[Vector[Char]].stringize
    override def _lowerCase(_this: Vector[Char]): Vector[Char] = delegate.asInstanceOf[Vector[Char]].lowerCase
    override def _upperCase(_this: Vector[Char]): Vector[Char] = delegate.asInstanceOf[Vector[Char]].upperCase
}
