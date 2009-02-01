

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains utility types and methods operating on type <code>Adapter</code>.
 */
object Adapter {

    /**
     * Implements a proxy for vectors.
     * It forwards all calls to a different vector object.
     */
    trait Proxy[A] extends Adapter[A, A] with scala.Proxy {
        final override def underlying = self
        // scala.Proxy
        override def self: Vector[A]
        // value semantics
        override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = underlying.equalsWith(that)(p)
        override def equals(that: Any): Boolean = Equals(this, that) // works around scala.Proxy#equals.
        override def hashCode: Int = underlying.hashCode
        // toString
        override def toString: String = underlying.toString
        // regions
        override def region(_start: Int, _end: Int): Vector[A] = underlying.region(_start, _end)
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
        // foreach
        override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = underlying.loop(i, j, f)
        override def pareach(f: A => Unit): Unit = underlying.pareach(f)
        // search
        override def seek(p: A => Boolean): Option[A] = underlying.seek(p)
        override def count(p: A => Boolean): Int = underlying.count(p)
        // folding
        override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = underlying.folderLeft(z)(op)
        override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = underlying.folderRight(z)(op)
        override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = underlying.reducerLeft(op)
        override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = underlying.reducerRight(op)
        // sort
        override def sortWith(lt: (A, A) => Boolean): Vector[A] = underlying.sortWith(lt)
        override def sort(implicit c: A => Ordered[A]): Vector[A] = underlying.sort(c)
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
        override def lazyValues : Vector[A] = underlying.lazyValues
        override def bounds: Vector[A] = underlying.bounds
        override def readOnly: Vector[A] = underlying.readOnly
        override def identity: Vector[A] = underlying.identity
        // copy
        override def copyTo[B >: A](that: Vector[B]): Vector[A] = underlying.copyTo(that)
        override def clone: Vector[A] = underlying.clone
        // parallel support
        override def unparallel: Vector[A] = underlying.unparallel
        override def grainSize: Int = underlying.grainSize
        override def defaultGrainSize: Int = underlying.defaultGrainSize
        override def isParallel: Boolean = underlying.isParallel
        override def join: Unit = underlying.join
        // parallel folding
        override def fold(z: A)(op: (A, A) => A): A = underlying.fold(z)(op)
        override def folder(z: A)(op: (A, A) => A): Vector[A] = underlying.folder(z)(op)
        override def reduce(op: (A, A) => A): A = underlying.reduce(op)
        override def reducer(op: (A, A) => A): Vector[A] = underlying.reducer(op)
    }


    private[mada] trait ParallelAlgorithm[A] extends Adapter[A, A] {
        // value semantics
        override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean
        override def hashCode: Int = underlying.hashCode
        // regions
        override def region(_start: Int, _end: Int): Vector[A] = affectParallel(underlying.region(_start, _end))
        // division
        override def divide(n: Int): Vector[Vector[A]] = underlying.divide(n)
        override def splitAt(i: Int): (Vector[A], Vector[A]) = underlying.splitAt(i)
        override def span(p: A => Boolean): (Vector[A], Vector[A]) = underlying.span(p)
        override def break(p: A => Boolean): (Vector[A], Vector[A]) = underlying.break(p)
        // filter
        override def filter(p: A => Boolean): Vector[A] = underlying.filter(p)
        override def mutatingFilter(p: A => Boolean): Vector[A] = underlying.mutatingFilter(p)
        // map
        override def map[B](f: A => B): Vector[B]
        override def flatMap[B](f: A => Vector[B]): Vector[B] = affectParallel(underlying.flatMap(f))
        override def asVectorOf[B]: Vector[B] = underlying.asVectorOf[B]
        // foreach
        override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = underlying.loop(i, j, f)
        override def pareach(f: A => Unit): Unit
        // search
        override def seek(p: A => Boolean): Option[A]
        override def count(p: A => Boolean): Int
        // folding
        override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = underlying.folderLeft(z)(op)
        override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = underlying.folderRight(z)(op)
        override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = underlying.reducerLeft(op)
        override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = underlying.reducerRight(op)
        // sort
        override def sortWith(lt: (A, A) => Boolean): Vector[A] = underlying.sortWith(lt)
        // concatenation
        override def append(that: Vector[A]): Vector[A] = {
            val x = underlying.append(that.unparallel)
            if (that.isParallel) {
                x.parallel(Math.min(grainSize, that.grainSize))
            } else {
                x
            }
        }
        // permutation
        override def permutation(f: Int => Int): Vector[A] = affectParallel(underlying.permutation(f))
        override def cycle(n: Int): Vector[A] = affectParallel(underlying.cycle(n))
        override def nth: Vector[A] = affectParallel(underlying.nth)
        override def reverse: Vector[A] = affectParallel(underlying.reverse)
        override def step(n: Int): Vector[A] = affectParallel(underlying.step(n))
        override def rotate(i: Int): Vector[A] = affectParallel(underlying.rotate(i))
        // zip
        override def zip[B](that: Vector[B]): Vector[(A, B)] = {
            val x = underlying.zip(that.unparallel)
            if (that.isParallel) {
                x.parallel(Math.min(grainSize, that.grainSize))
            } else {
                x
            }
        }
        // attributes
        override def force: Vector[A] = affectParallel(underlying.force)
        override def lazyValues : Vector[A] = affectParallel(underlying.lazyValues)
        override def bounds: Vector[A] = affectParallel(underlying.bounds)
        override def readOnly: Vector[A] = affectParallel(underlying.readOnly)
        override def identity: Vector[A] = affectParallel(underlying.identity)
        // copy
        override def copyTo[B >: A](that: Vector[B]): Vector[A]
        // parallel support
        override def unparallel: Vector[A] = underlying.unparallel
        override def grainSize: Int
        override def defaultGrainSize: Int = underlying.defaultGrainSize
        override def isParallel: Boolean = true
        override def join: Unit = underlying.join
        // parallel folding
        override def fold(z: A)(op: (A, A) => A): A
        override def folder(z: A)(op: (A, A) => A): Vector[A]
        override def reduce(op: (A, A) => A): A
        override def reducer(op: (A, A) => A): Vector[A]
        // implementation helpers
        protected def affectParallel[B](that: Vector[B]): Vector[B] = {
            Assert(!underlying.isParallel)
            that.parallel(grainSize)
        }
    }
}


/**
 * Adapts underlying vector.
 */
trait Adapter[Z, A] extends Vector[A] {
    /**
     * Underlying vector, overridden in subclasses.
     */
    def underlying: Vector[Z]

    /**
     * @return  <code>underlying.start</code>.
     */
    override def start = underlying.start

    /**
     * @return  <code>underlying.end</code>.
     */
    override def end = underlying.end

    /**
     * @return  <code>underlying(i).asInstanceOf[A]</code>.
     */
    override def apply(i: Int): A = underlying(i).asInstanceOf[A]

    /**
     * @return  <code>underlying(i) = e.asInstanceOf[Z]</code>.
     */
    override def update(i: Int, e: A): Unit = underlying(i) = e.asInstanceOf[Z]

    /**
     * @return  <code>underlying.isDefinedAt(i)</code>.
     */
    override def isDefinedAt(i: Int): Boolean = underlying.isDefinedAt(i)
}
