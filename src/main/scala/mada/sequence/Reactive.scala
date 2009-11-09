

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence


// See: scala.Responder


import reactive._


trait Reactive[+A] extends Sequence[A] with Runnable {


    @returnThis
    final def of[B >: A]: Reactive[B] = this


    override def asReactive: Reactive[A] = this


    /**
     * Registers a reactor.
     */
    def subscribe(k: Reactor[A]): Unit

    @equivalentTo("foreach{ e => () }")
    override def run: Unit = foreach{ e => () }

    /**
     * Appends <code>that</code>.
     */
    def append[B >: A](that: Reactive[B]): Reactive[B] = Append[B](this, that) // maybe useless.

    @aliasOf("append")
    final def ++[B >: A](that: Reactive[B]): Reactive[B] = append(that)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): Reactive[B] = Map(this, f)

    def flatMap[B](f: A => Reactive[B]): Reactive[B] = FlatMap(this, f)

    /**
     * Filters elements using <code>p</code>.
     */
    def filter(p: A => Boolean): Reactive[A] = Filter(this, p)

    @aliasOf("filter")
    final def withFilter(p: A => Boolean): Reactive[A] = filter(p)

    /**
     * Filters elements using <code>funtion.not(p)</code>.
     */
    def remove(p: A => Boolean): Reactive[A] = Remove(this, p)

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach(f: A => Unit): Unit = subscribe(reactor.make(util.theUnit, f))

    def fork(k: Reactor[A]): Reactive[A] = Fork(this, k)

    def forkBy(f: A => Unit): Reactive[A] = ForkBy(this, f)

    /**
     * Returns all the elements without the first one.
     */
    def tail: Reactive[A] = Tail(this)

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): Reactive[A] = Take(this, n)

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): Reactive[A] = Drop(this, n)

    @equivalentTo("drop(n).take(n - m)")
    def slice(n: Int, m: Int): Reactive[A] = Slice(this, n, m)

    /**
     * Returns the longest prefix that satisfies the predicate.
     */
    def takeWhile(p: A => Boolean): Reactive[A] = TakeWhile(this, p)

    /**
     * Returns the remaining suffix of <code>takeWhile</code>.
     */
    def dropWhile(p: A => Boolean): Reactive[A] = DropWhile(this, p)

}



object Reactive {








}
