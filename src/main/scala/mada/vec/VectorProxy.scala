

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


trait VectorProxy[A] extends Vector[A] with Proxy {
    override def self: Vector[A]

    override def equals(that: Any): Boolean = that match {
        case that: Proxy => self equals that.self
        case _ => self equals that
    }
    override def equalsTo[B](that: Vector[B]): Boolean = self.equalsTo(that)
    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = self.equalsWith(that)(p)

    override def size: Int = self.size
    override def apply(i: Int): A = self.apply(i)
    override def update(i: Int, e: A): Unit = self.update(i, e)

    override def bounds: Vector[A] = self.bounds
    override def copyTo[B >: A](that: Vector[B]): Vector[A] = self.copyTo(that)
    override def count(p: A => Boolean): Int = self.count(p)
    override def cycle(n: Int): Vector[A] = self.cycle(n)
    override def find(p: A => Boolean): Option[A] = self.find(p)
    override def firstOption: Option[A] = self.firstOption
    override def fold(z: A)(op: (A, A) => A): A = self.fold(z)(op)
    override def foreach(f: A => Unit): Unit = self.foreach(f)
    override def lastOption: Option[A] = self.lastOption
    override def force: Vector[A] = self.force
    override def lazyValues : Vector[A] = self.lazyValues
    override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = self.loop(i, j, f)
    override def map[B](f: A => B): Vector[B] = self.map( f)
    override def parallel: Vector[A] = self.parallel
    override def parallel(g: Int): Vector[A] = self.parallel(g)
    override def randomAccessSeq: RandomAccessSeq.Mutable[A] = self.randomAccessSeq
    override def readOnly: Vector[A] = self.readOnly
    override def reduce(op: (A, A) => A): A = self.reduce(op)
    override def reverse: Vector[A] = self.reverse
    override def step(n: Int): Vector[A] = self.step(n)
    override def sort(lt: (A, A) => Boolean): Vector[A] = self.sort(lt)
    override def triple: Vector.Triple[A] = self.triple
    override def window(n: Int, m: Int): Vector[A] = self.window(n, m)
}
