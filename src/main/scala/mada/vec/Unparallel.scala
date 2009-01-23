

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Unparallel {
    def apply[A](v: Vector[A]): Vector[A] = v
}
/*
class UnparallelVector[A](override val self: Vector[A]) extends VectorProxy[A]  {
    Assert(self.isParallel)

    override def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean) = EqualsWith(self, that, p)
    override def copyTo[B >: A](that: Vector[B]) = CopyTo(self, that) // clone, toArray
    override def count(p: A => Boolean) = Count(self, p)
    override def filter(p: A => Boolean) = Filter(self, p) // remove
    override def find(p: A => Boolean) = Find(self, p) // forall, exists, contains
    override def fold(z: A)(op: (A, A) => A): A = Fold(self, z, op)
    override def foreach(f: A => Unit) = Foreach(self, f)
    override def map[B](f: A => B): Vector[B] = Map(self, f)
    override def reduce(op: (A, A) => A): A = Reduce(self, op)
}*/

