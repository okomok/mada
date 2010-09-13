

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


trait Forwarder[+A] extends Reactive[A] with Sequence.Forwarder[A] {
    override protected def delegate: Reactive[A]

    protected def around[B](that: => Reactive[B]): Reactive[B] = that
    protected def around2[B, C](that: => (Reactive[B], Reactive[C])): (Reactive[B], Reactive[C]) = {
        val (l, r) = that
        (around(l), around(r))
    }

    override def append[B >: A](that: Reactive[B]): Reactive[B] = around(delegate.append(that))
    override def map[B](f: A => B): Reactive[B] = around(delegate.map(f))
    override def flatMap[B](f: A => Reactive[B]): Reactive[B] = around(delegate.flatMap(f))
    override def filter(p: A => Boolean): Reactive[A] = around(delegate.filter(p))
    override def remove(p: A => Boolean): Reactive[A] = around(delegate.remove(p))
    override def foreach(f: A => Unit): Unit = delegate.foreach(f)
    override def scanLeft[B](z: B)(op: (B, A) => B): Reactive[B] = around(delegate.scanLeft(z)(op))
    override def tail: Reactive[A] = around(delegate.tail)
    override def take(n: Int): Reactive[A] = around(delegate.take(n))
    override def drop(n: Int): Reactive[A] = around(delegate.drop(n))
    override def slice(from: Int, until: Int): Reactive[A] = around(delegate.slice(from, until))
    override def takeWhile(p: A => Boolean): Reactive[A] = around(delegate.takeWhile(p))
    override def dropWhile(p: A => Boolean): Reactive[A] = around(delegate.dropWhile(p))
    override def span(p: A => Boolean): (Reactive[A], Reactive[A]) = around2(delegate.span(p))
    override def splitAt(n: Int): (Reactive[A], Reactive[A]) = around2(delegate.splitAt(n))
    override def step(n: Int): Reactive[A] = delegate.step(n)
    override def flatten[B](implicit pre: Reactive[A] <:< Reactive[Sequence[B]]): Reactive[B] = around(delegate.flatten)
    override def unique: Reactive[A] = around(delegate.unique)
    override def uniqueBy(p: (A, A) => Boolean): Reactive[A] = around(delegate.uniqueBy(p))
    override def unsplit[B](sep: Reactive[B])(implicit pre : Reactive[A] <:< Reactive[Sequence[B]]): Reactive[B] = around(delegate.unsplit(sep))
    override def unzip[B, C](implicit pre: Reactive[A] <:< Reactive[(B, C)]): (Reactive[B], Reactive[C]) = around2(delegate.unzip)
    override def toIterative: Iterative[A] = delegate.toIterative
}
