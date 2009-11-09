

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence


// See: scala.Responder


import reactive._


trait Reactive[+A] extends Runnable {


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
    def append[B >: A](that: Reactive[B]): Reactive[B] = Append[B](this, that)

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
     * Applies <code>f</code> to each element.
     */
    def foreach(f: A => Unit): Unit = subscribe(reactor.by(f))

    def fork(k: Reactor[A]): Reactive[A] = Fork(this, k)

    def forkBy(f: A => Unit): Reactive[A] = ForkBy(this, f)



    /**
     * Returns the first element.
     */
    def head: A = {
        val e = headOption
        if (e.isEmpty){
            throw new NoSuchElementException("head on empty sequence")
        }
        e.get
    }

    /**
     * Optionally returns the first element.
     */
    def headOption: Option[A] = {
        var x = option.NoneOf[A]
        val j = new Reactor[A] {
            override def onEnd = ()
            override def react(e: A) = if (x.isEmpty) x = Some(e)
        }
        subscribe(j)
        x
    }

    /**
     * Returns all the elements without the first one.
     */
   // def tail: Iterative[A] = Tail(this)

    /**
     * Returns the last element.
     */
    def last: A = {
        val e = lastOption
        if (e.isEmpty) {
            throw new NoSuchElementException("last on empty sequence")
        }
        e.get
    }

    /**
     * Optionally returns the last element.
     */
    def lastOption: Option[A] = {
        var x = option.NoneOf[A]
        val j = new Reactor[A] {
            override def onEnd = ()
            override def react(e: A) = x = Some(e)
        }
        subscribe(j)
        x
    }

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
