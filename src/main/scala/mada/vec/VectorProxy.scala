

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


trait VectorProxy[A] extends Vector[A] with Proxy {
    override def self: Vector[A]

    override def equals(that: Any): Boolean = that match {
        case that: Proxy => self equals that.self
        case _ => self equals that
    }

    override def size: Long = self.size
    override def apply(i: Long): A = self(i)
    override def update(i: Long, e: A): Unit = self(i) = e

    override def bounds: Vector[A] = self.bounds
    override def cycle(n: Long): Vector[A] = self.cycle(n)
    override def force: Vector[A] = self.force
    override def lazy_ : Vector[A] = self.lazy_
    override def loop[F <: (A => Boolean)](i: Long, j: Long, f: F): F = self.loop(i, j, f)
    override def map[B](f: A => B): Vector[B] = self.map( f)
    override def randomAccessSeq: RandomAccessSeq.Mutable[A] = self.randomAccessSeq
    override def readOnly: Vector[A] = self.readOnly
    override def reverse: Vector[A] = self.reverse
    override def step(n: Long, m: Long): Vector[A] = self.step(n, m)
    override def sort(lt: (A, A) => Boolean): Vector[A] = self.sort(lt)
    override def toCell: Cell[A] = self.toCell
    override def toOption: Option[A] = self.toOption
    override def window(n: Long, m: Long): Vector[A] = self.window(n, m)
}
