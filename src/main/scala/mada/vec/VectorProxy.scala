

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


trait VectorProxy[A] extends Vector[A] with Proxy {
    override def self: Vector[A]
    final override def equals(that: Any): Boolean = Equals(this, that) // works around Proxy.equals.

    override def size: Int = self.size
    override def apply(i: Int): A = self.apply(i)
    override def update(i: Int, e: A): Unit = self.update(i, e)

    override def bounds: Vector[A] = self.bounds
    override def copyTo[B >: A](that: Vector[B]): Vector[A] = self.copyTo(that)
    override def count(p: A => Boolean): Int = self.count(p)
    override def cycle(n: Int): Vector[A] = self.cycle(n)
    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = self.equalsWith(that)(p)
    override def filter(p: A => Boolean): Vector[A] = self.filter(p)
    override def firstOption: Option[A] = self.firstOption
    override def fold(z: A)(op: (A, A) => A): A = self.fold(z)(op)
    override def force: Vector[A] = self.force
    override def foreach(f: A => Unit): Unit = self.foreach(f)
    override def lastOption: Option[A] = self.lastOption
    override def lazyValues : Vector[A] = self.lazyValues
    override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = self.loop(i, j, f)
    override def map[B](f: A => B): Vector[B] = self.map(f)
    override def pareach(f: A => Unit): Unit = self.pareach(f)
    override def randomAccessSeq: RandomAccessSeq.Mutable[A] = self.randomAccessSeq
    override def readOnly: Vector[A] = self.readOnly
    override def reduce(op: (A, A) => A): A = self.reduce(op)
    override def reverse: Vector[A] = self.reverse
    override def seek(p: A => Boolean): Option[A] = self.seek(p)
    override def step(n: Int): Vector[A] = self.step(n)
    override def sort(lt: (A, A) => Boolean): Vector[A] = self.sort(lt)
    override def triple: Vector.Triple[A] = self.triple
    override def window(n: Int, m: Int): Vector[A] = self.window(n, m)

    override def parallel: Vector[A] = self.parallel
    override def parallel(g: Int): Vector[A] = self.parallel(g)
}
