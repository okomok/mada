

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Implements a proxy for vectors.
 * It forwards all calls to a different vector object.
 */
trait VectorProxy[A] extends Vector[A] with Proxy {
    override def self: Vector[A]
    final override def equals(that: Any): Boolean = Equals(this, that) // works around Proxy.equals.

    final override def start: Int = self.start
    final override def end: Int = self.end

    override def apply(i: Int): A = self.apply(i)
    override def update(i: Int, e: A): Unit = self.update(i, e)
    override def isDefinedAt(i: Int): Boolean = self.isDefinedAt(i)

    override def bounds: Vector[A] = self.bounds
    override def copyTo[B >: A](that: Vector[B]): Vector[A] = self.copyTo(that)
    override def count(p: A => Boolean): Int = self.count(p)
    override def cycle(n: Int): Vector[A] = self.cycle(n)
    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = self.equalsWith(that)(p)
    override def filter(p: A => Boolean): Vector[A] = self.filter(p)
    override def mutatingFilter(p: A => Boolean): Vector[A] = self.mutatingFilter(p)
    override def firstOption: Option[A] = self.firstOption
    override def lastOption: Option[A] = self.lastOption
    override def force: Vector[A] = self.force
    override def lazyValues : Vector[A] = self.lazyValues
    override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = self.loop(i, j, f)
    override def map[B](f: A => B): Vector[B] = self.map(f)
    override def nth: Vector[A] = self.nth
    override def pareach(f: A => Unit): Unit = self.pareach(f)
    override def randomAccessSeq: RandomAccessSeq.Mutable[A] = self.randomAccessSeq
    override def readOnly: Vector[A] = self.readOnly
    override def reduce(op: (A, A) => A): A = self.reduce(op)
    override def reducer(op: (A, A) => A): Vector[A] = self.reducer(op)
    override def region(_start: Int, _end: Int): Vector[A] = self.region(_start, _end)
    override def reverse: Vector[A] = self.reverse
    override def seek(p: A => Boolean): Option[A] = self.seek(p)
    override def sortWith(lt: (A, A) => Boolean): Vector[A] = self.sortWith(lt)
    override def step(n: Int): Vector[A] = self.step(n)

    override def parallel: Vector[A] = self.parallel
    override def parallel(g: Int): Vector[A] = self.parallel(g)
    override def unparallel: Vector[A] = self.unparallel
    override def isParallel: Boolean = self.isParallel
    override def defaultGrainSize: Int = self.defaultGrainSize
}
