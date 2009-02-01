

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains utility types and methods operating on type <code>Adapter</code>.
 */
object Adapter {

    /**
     * Implements a proxy for vectors.
     * It forwards all calls to a different vector object
     */
    trait Algorithm[A] extends Adapter[A, A] {
        override def copyTo[B >: A](that: Vector[B]): Vector[A] = underlying.copyTo(that)
        override def count(p: A => Boolean): Int = underlying.count(p)
        override def cycle(n: Int): Vector[A] = underlying.cycle(n)
        override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = underlying.equalsWith(that)(p)
        override def filter(p: A => Boolean): Vector[A] = underlying.filter(p)
        override def flatMap[B](f: A => Vector[B]): Vector[B] = underlying.flatMap(f)
        override def mutatingFilter(p: A => Boolean): Vector[A] = underlying.mutatingFilter(p)
        override def force: Vector[A] = underlying.force
        override def identity: Vector[A] = underlying.identity
        override def lazyValues : Vector[A] = underlying.lazyValues
        override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = underlying.loop(i, j, f)
        override def map[B](f: A => B): Vector[B] = underlying.map(f)
        override def nth: Vector[A] = underlying.nth
        override def pareach(f: A => Unit): Unit = underlying.pareach(f)
        override def permutation(f: Int => Int): Vector[A] = underlying.permutation(f)
        override def readOnly: Vector[A] = underlying.readOnly
        override def region(_start: Int, _end: Int): Vector[A] = underlying.region(_start, _end)
        override def reverse: Vector[A] = underlying.reverse
        override def seek(p: A => Boolean): Option[A] = underlying.seek(p)
        override def sortWith(lt: (A, A) => Boolean): Vector[A] = underlying.sortWith(lt)
        override def step(n: Int): Vector[A] = underlying.step(n)
        override def zip[B](that: Vector[B]): Vector[(A, B)] = underlying.zip(that)

        override def fold(z: A)(op: (A, A) => A): A = underlying.fold(z)(op)
        override def folder(z: A)(op: (A, A) => A): Vector[A] = underlying.folder(z)(op)
        override def reduce(op: (A, A) => A): A = underlying.reduce(op)
        override def reducer(op: (A, A) => A): Vector[A] = underlying.reducer(op)

        override def isParallel: Boolean = underlying.isParallel
        override def unparallel: Vector[A] = underlying.unparallel
        override def grainSize: Int = underlying.grainSize
        override def defaultGrainSize: Int = underlying.defaultGrainSize
    }


    /**
     * Implements a proxy for vectors.
     * It forwards all calls to a different vector object.
     */
    trait Proxy[A] extends Algorithm[A] with scala.Proxy {
        override def underlying = self

        override def self: Vector[A]
        final override def equals(that: Any): Boolean = Equals(this, that) // works around Proxy.equals.

        // TODO:
    }


    /**
     * Installs hook into vector-to-vector methods.
     */
    abstract class Projector[A](override val self: Vector[A]) extends Proxy[A] {
        /**
         * Called just before vector-to-vector methods return.
         */
        def project[C](that: Vector[C]): Vector[C]

        override def append(that: Vector[A]): Vector[A] = project(self.append(that))
        override def bounds: Vector[A] = project(self.bounds)
        override def copyTo[B >: A](that: Vector[B]): Vector[A] = project(self.copyTo(that))
        override def cycle(n: Int): Vector[A] = project(self.cycle(n))
        override def filter(p: A => Boolean): Vector[A] = project(self.filter(p))
        override def flatMap[B](f: A => Vector[B]): Vector[B] = project(self.flatMap(f))
        override def mutatingFilter(p: A => Boolean): Vector[A] = project(self.mutatingFilter(p))
        override def force: Vector[A] = project(self.force)
        override def identity: Vector[A] = project(self.identity)
        override def lazyValues : Vector[A] = project(self.lazyValues)
        override def map[B](f: A => B): Vector[B] = project(self.map(f))
        override def nth: Vector[A] = project(self.nth)
        override def permutation(f: Int => Int): Vector[A] = project(self.permutation(f))
        override def readOnly: Vector[A] = project(self.readOnly)
        override def region(_start: Int, _end: Int): Vector[A] = project(self.region(_start, _end))
        override def reverse: Vector[A] = project(self.reverse)
        override def sortWith(lt: (A, A) => Boolean): Vector[A] = project(self.sortWith(lt))
        override def step(n: Int): Vector[A] = project(self.step(n))
        override def zip[B](that: Vector[B]): Vector[(A, B)] = project(self.zip(that))

        override def folder(z: A)(op: (A, A) => A): Vector[A] = project(self.folder(z)(op))
        override def reducer(op: (A, A) => A): Vector[A] = project(self.reducer(op))
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
