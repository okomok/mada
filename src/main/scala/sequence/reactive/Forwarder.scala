

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


trait Forwarder[+A] extends Reactive[A] with Sequence.Forwarder[A] {
    override protected def delegate: Reactive[A]

    protected def around[B](that: => Reactive[B]): Reactive[B] = that
    protected def around2[B, C](that: => (Reactive[B], Reactive[C])): (Reactive[B], Reactive[C]) = {
        val (l, r) = that
        (around(l), around(r))
    }

    override def close: Unit = delegate.close
    override def foreach(f: A => Unit): Unit = delegate.foreach(f)
    override def merge[B >: A](that: Reactive[B]): Reactive[B] = around(delegate.merge(that))
    override def map[B](f: A => B): Reactive[B] = around(delegate.map(f))
    override def flatMap[B](f: A => Reactive[B]): Reactive[B] = around(delegate.flatMap(f))
    override def filter(p: A => Boolean): Reactive[A] = around(delegate.filter(p))
    override def collect[B](f: PartialFunction[A, B]): Reactive[B] = around(delegate.collect(f))
    override def remove(p: A => Boolean): Reactive[A] = around(delegate.remove(p))
    override def partition(p: A => Boolean): (Reactive[A], Reactive[A]) = around2(delegate.partition(p))
    override def scanLeft[B](z: B)(op: (B, A) => B): Reactive[B] = around(delegate.scanLeft(z)(op))
    override def scanLeft1[B >: A](op: (B, A) => B): Reactive[B] = around(delegate.scanLeft1(op))
    override def head: A = delegate.head
    override def tail: Reactive[A] = around(delegate.tail)
    override def init: Reactive[A] = around(delegate.init)
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
    override def zip[B](that: Reactive[B]): Reactive[(A, B)] = around(delegate.zip(that))
    override def zipBy[B, C](that: Reactive[B])(f: (A, B) => C): Reactive[C] = around(delegate.zipBy(that)(f))
    override def unzip[B, C](implicit pre: Reactive[A] <:< Reactive[(B, C)]): (Reactive[B], Reactive[C]) = around2(delegate.unzip)
    override def toIterative: Iterative[A] = delegate.toIterative
    override def toResponder: Responder[A] = delegate.toResponder
    override def actor: scala.actors.Actor = delegate.actor
    override def react(f: A => Unit): Reactive[A] = delegate.react(f)
    override def start: Unit = delegate.start
    override def fork(f: Reactive[A] => Unit): Reactive[A] = around(delegate.fork(f))
    override def break: Reactive[A] = around(delegate.break)
    override def takeUntil(that: Reactive[_]): Reactive[A] = around(delegate.takeUntil(that))
    override def then(f: => Unit): Reactive[A] = around(delegate.then(f))
    override def catching(f: Throwable => Unit): Reactive[A] = around(delegate.catching(f))
    override def using(c: java.io.Closeable): Reactive[A] = around(delegate.using(c))
    override def header[B >: A](it: Iterative[B]): Reactive[B] = around(delegate.header(it))
    override def protect: Reactive[A] = around(delegate.protect)
}
