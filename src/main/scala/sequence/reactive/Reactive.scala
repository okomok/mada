

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


// See: scala.Responder


object Reactive extends Common


trait Reactive[+A] extends Sequence[A] {


    @returnThis
    final def of[B >: A]: Reactive[B] = this


    override def asReactive: Reactive[A] = this


    /**
     * Activates the reactor.
     */
    def activate(k: Reactor[A]): Unit


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

    @equivalentTo("map(f).flatten")
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
    def foreach(f: A => Unit): Unit = activate(reactor.make(_ => (), f))

    @equivalentTo("foreach{ e => () }")
    def start: Unit = foreach{ e => () }

    /**
     * Forks.
     */
    def fork(f: Reactive[A] => Unit): Reactive[A] = Fork(this, f)

    /**
     * Forks to a reactor.
     */
    def forkTo(k: Reactor[A]): Reactive[A] = ForkTo(this, k)

    /**
     * Loops with evaluating <code>f</code>.
     */
    def forLoop(f: A => Unit): Reactive[A] = ForLoop(this, f)

    /**
     * Ends with evaluating <code>z</code>.
     */
    def endWith(z: => Unit): Reactive[A] = EndWith(this, util.byName(z))

    /**
     * Prefix sum folding left-associative.
     */
    def folderLeft[B](z: B)(op: (B, A) => B): Reactive[B] = FolderLeft(this, z, op)

    /**
     * Prefix sum reducing left-associative.
     */
    def reducerLeft[B >: A](op: (B, A) => B): Reactive[B] = ReducerLeft(this, op)

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

    @equivalentTo("take(m).drop(n)")
    def slice(n: Int, m: Int): Reactive[A] = Slice(this, n, m)

    /**
     * Returns the longest prefix that satisfies the predicate.
     */
    def takeWhile(p: A => Boolean): Reactive[A] = TakeWhile(this, p)

    /**
     * Returns the remaining suffix of <code>takeWhile</code>.
     */
    def dropWhile(p: A => Boolean): Reactive[A] = DropWhile(this, p)

    @equivalentTo("(takeWhile(p), dropWhile(p))")
    def span(p: A => Boolean): (Reactive[A], Reactive[A]) = (takeWhile(p), dropWhile(p))

    @equivalentTo("(take(n), drop(n))")
    def splitAt(n: Int): (Reactive[A], Reactive[A]) = {
        Precondition.nonnegative(n, "splitAt")
        (take(n), drop(n))
    }

    /**
     * Turns a sequence of sequences into flat sequence.
     */
    def flatten[B](implicit pre: Reactive[A] => Reactive[Sequence[B]]): Reactive[B] = Flatten(pre(this))

    /**
     * Steps by the specified stride.
     */
    def step(n: Int): Reactive[A] = Step(this, n)

    /**
     * Removes duplicates using <code>==</code>.
     */
    def unique: Reactive[A] = Unique(this)

    /**
     * Removes duplicates using the predicate.
     */
    def uniqueBy(p: (A, A) => Boolean): Reactive[A] = UniqueBy(this, p)

    /**
     * Flattens <code>vs</code>, each reactive appending <code>sep</code> except the last one.
     */
    def unsplit[B](sep: Reactive[B])(implicit pre: Reactive[A] => Reactive[Sequence[B]]): Reactive[B] = Unsplit(pre(this), sep)

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: Reactive[B]): Reactive[(A, B)] = Zip(this, that)

    /**
     * Reverts <code>zip</code>.
     */
    def unzip[B, C](implicit pre: Reactive[A] => Reactive[(B, C)]): (Reactive[B], Reactive[C]) = (pre(this).map{ bc => bc._1 }, pre(this).map{ bc => bc._2 })

    /**
     * Zips <code>this</code> and <code>that</code> applying <code>f</code>.
     */
    def zipBy[B, C](that: Reactive[B])(f: (A, B) => C): Reactive[C] = ZipBy(this, that, f)


// conversion

    @conversion
    def toIterative: Iterative[A] = ToIterative(this)


// misc

    /**
     * Returns synchronized one.
     */
    def synchronize: Reactive[A] = Synchronize(this)

    /**
     * Combines the elements unorderly.
     */
    def merge[B >: A](that: Reactive[B]): Reactive[B] = Merge[B](this, that)

    /**
     * Manages resources.
     */
    def using(a: => Auto[Any]): Reactive[A] = Using(this, util.byLazy(a))

    /**
     * Catches exceptions.
     */
    def catching(f: Throwable => Unit): Reactive[A] = Catch(this, f)

    /**
     * Breaks the flow.
     */
    def break: Reactive[A] = Break(this)

    /**
     * Ends if <code>that</code> emits a reaction.
     */
    def before(that: Reactive[Any]): Reactive[A] = Before(this, that)

    /**
     * Changes lane to <code>that</code>.
     */
    def connect[B >: A](that: Reactive[B]): Reactive[B] = Connect[B](this, that)

}
