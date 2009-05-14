

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


@forwarder
trait Forwarder[+A] extends Traversable[A] with any.Forwarder {
    override protected def delegate: Traversable[A]

    override def start: Traverser[A] = delegate.start
    override def isEmpty: Boolean = delegate.isEmpty
    override def length: Int = delegate.length
    override def append[B >: A](that: Traversable[B]): Traversable[B] = delegate.append(that)
    override def map[B](f: A => B): Traversable[B] = delegate.map(f)
    override def filter(p: A => Boolean): Traversable[A] = delegate.filter(p)
    override def foreach(f: A => Unit): Unit = delegate.foreach(f)
    override def contains(e: Any): Boolean = delegate.contains(e)
    override def count(p: A => Boolean): Int = delegate.count(p)
    override def find(p: A => Boolean): Option[A] = delegate.find(p)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = delegate.foldLeft(z)(op)
    override def head: A = delegate.head
    override def headOption: Option[A] = delegate.headOption
    override def tail: Traversable[A] = delegate.tail
    override def last: A = delegate.last
    override def lastOption: Option[A] = delegate.lastOption
    override def take(n: Int): Traversable[A] = delegate.take(n)
    override def drop(n: Int): Traversable[A] = delegate.drop(n)
    override def slice(n: Int, m: Int): Traversable[A] = delegate.slice(n, m)
    override def takeWhile(p: A => Boolean): Traversable[A] = delegate.takeWhile(p)
    override def dropWhile(p: A => Boolean): Traversable[A] = delegate.dropWhile(p)
//    override def span(p: A => Boolean): (Forwarder[A], Forwarder[A]) = delegate.span(p)
//    override def splitAt(n: Int): (Forwarder[A], Forwarder[A]) = delegate.splitAt(n)
}

