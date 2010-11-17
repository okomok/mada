

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


trait Forwarder[+A] extends Iterative[A] with Sequence.Forwarder[A] {
    override protected def delegate: Iterative[A]

    protected def around[B](that: => Iterative[B]): Iterative[B] = that
    protected def around2[B, C](that: => (Iterative[B], Iterative[C])): (Iterative[B], Iterative[C]) = {
        val (l, r) = that
        (around(l), around(r))
    }

    override def begin: Iterator[A] = delegate.begin

    override def equalsIf[B](that: Iterative[B])(p: (A, B) => Boolean): Boolean = delegate.equalsIf(that)(p)
    override def isEmpty: Boolean = delegate.isEmpty
    override def size: Int = delegate.size
    override def append[B >: A](that: Iterative[B]): Iterative[B] = around(delegate.append(that))
    override def map[B](f: A => B): Iterative[B] = around(delegate.map(f))
    override def flatMap[B](f: A => Iterative[B]): Iterative[B] = around(delegate.flatMap(f))
    override def filter(p: A => Boolean): Iterative[A] = around(delegate.filter(p))
    override def remove(p: A => Boolean): Iterative[A] = around(delegate.remove(p))
    override def partition(p: A => Boolean): (Iterative[A], Iterative[A]) = around2(delegate.partition(p))
    override def groupBy[B, K](f: A => K)(implicit pre: Iterative[A] <:< Iterative[B]): scala.collection.Map[K, Vector[B]] = delegate.groupBy(f)
    override def foreach(f: A => Unit): Unit = delegate.foreach(f)
    override def forall(p: A => Boolean): Boolean = delegate.forall(p)
    override def exists(p: A => Boolean): Boolean = delegate.exists(p)
    override def count(p: A => Boolean): Int = delegate.count(p)
    override def find(p: A => Boolean): Option[A] = delegate.find(p)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = delegate.foldLeft(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = delegate.reduceLeft(op)
    override def scanLeft[B](z: B)(op: (B, A) => B): Iterative[B] = around(delegate.scanLeft(z)(op))
    override def scanLeft1[B >: A](op: (B, A) => B): Iterative[B] = around(delegate.scanLeft1(op))
    override def head: A = delegate.head
    override def tail: Iterative[A] = around(delegate.tail)
    override def last: A = delegate.last
    override def take(n: Int): Iterative[A] = around(delegate.take(n))
    override def drop(n: Int): Iterative[A] = around(delegate.drop(n))
    override def slice(from: Int, until: Int): Iterative[A] = around(delegate.slice(from, until))
    override def takeWhile(p: A => Boolean): Iterative[A] = around(delegate.takeWhile(p))
    override def dropWhile(p: A => Boolean): Iterative[A] = around(delegate.dropWhile(p))
    override def span(p: A => Boolean): (Iterative[A], Iterative[A]) = around2(delegate.span(p))
    override def splitAt(n: Int): (Iterative[A], Iterative[A]) = around2(delegate.splitAt(n))

    override def contains(e: Any): Boolean = delegate.contains(e)
    override def cycle: Iterative[A] = around(delegate.cycle)
    override def times(n: Int): Iterative[A] = delegate.times(n)
    override def force: Iterative[A] = around(delegate.force)
    override def strict: Iterative[A] = around(delegate.strict)
    override def flatten[B](implicit pre: Iterative[A] <:< Iterative[Sequence[B]]): Iterative[B] = around(delegate.flatten)
    override def memoize: Iterative[A] = around(delegate.memoize)
    override def nth(n: Int): A = delegate.nth(n)
    override def mix(x: Mixin): Iterative[A] = around(delegate.mix(x))
    override def singlePass: Iterative[A] = around(delegate.singlePass)
    override def step(n: Int): Iterative[A] = delegate.step(n)
    override def unique: Iterative[A] = around(delegate.unique)
    override def uniqueBy(p: (A, A) => Boolean): Iterative[A] = around(delegate.uniqueBy(p))
    override def unsplit[B](sep: Iterative[B])(implicit pre: Iterative[A] <:< Iterative[Sequence[B]]): Iterative[B] = around(delegate.unsplit(sep))
    override def zip[B](that: Iterative[B]): Iterative[(A, B)] = around(delegate.zip(that))
    override def unzip[B, C](implicit pre: Iterative[A] <:< Iterative[(B, C)]): (Iterative[B], Iterative[C]) = around2(delegate.unzip)
    override def adjacent(n: Int): Iterative[Vector[A]] = around(delegate.adjacent(n))
    override def stringize(implicit pre: Iterative[A] <:< Iterative[Char]): String = delegate.stringize
    override def lexical(implicit pre: Iterative[A] <:< Iterative[Char]): Lexical = delegate.lexical
    override def toList: List[A] = delegate.toList
    override def toVector: Vector[A] = delegate.toVector
    override def toSeq: Seq[A] = delegate.toSeq
    override def toJIterable[B](implicit pre: Iterative[A] <:< Iterative[B]): java.lang.Iterable[B] = delegate.toJIterable
    override def breakOut[To](implicit bf: scala.collection.generic.CanBuildFrom[Nothing, A, To]) = delegate.breakOut

    override def merge[B >: A](that: Iterative[B])(implicit c: Ordering[B]): Iterative[B] = around(delegate.merge(that)(c))
    override def union[B >: A](that: Iterative[B])(implicit c: Ordering[B]): Iterative[B] = around(delegate.union(that)(c))
    override def intersection[B >: A](that: Iterative[B])(implicit c: Ordering[B]): Iterative[B] = around(delegate.intersection(that)(c))
    override def difference[B >: A](that: Iterative[B])(implicit c: Ordering[B]): Iterative[B] = around(delegate.difference(that)(c))
    override def symmetricDifference[B >: A](that: Iterative[B])(implicit c: Ordering[B]): Iterative[B] = around(delegate.symmetricDifference(that)(c))
}
