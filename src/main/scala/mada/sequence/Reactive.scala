

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
    def foreach(f: A => Unit): Unit = subscribe(reactor.make(util.theUnit, f))

    def fork(k: Reactor[A]): Reactive[A] = Fork(this, k)

    def forkBy(f: A => Unit): Reactive[A] = ForkBy(this, f)

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
    @methodized
    def _flatten[B](_this: Reactive[Reactive[B]]): Reactive[B] = Flatten(_this)

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
    @methodized
    def _unsplit[B](_this: Reactive[Reactive[B]], sep: Reactive[B]): Reactive[B] = Unsplit(_this, sep)

    /**
     * Returns synchronized one.
     */
    def synchronize: Reactive[A] = Synchronize(this)

    /**
     * Combines the elements unorderly.
     */
    def merge[B >: A](that: Reactive[B]): Reactive[B] = Merge[B](this, that)

}



object Reactive {








}
