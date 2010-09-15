

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


object Reactive extends Common


/**
 * Yet another Traversable with Scala 2.7 style
 */
trait Reactive[+A] extends Sequence[A] {

    @returnThis
    final def of[B >: A]: Reactive[B] = this

    override def asReactive: Reactive[A] = this

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach(f: A => Unit): Unit

    def append[B >: A](that: Reactive[B]): Reactive[B] = Append[B](this, that)

    @aliasOf("append")
    final def ++[B >: A](that: Reactive[B]): Reactive[B] = append(that)

    def map[B](f: A => B): Reactive[B] = Map(this, f)

    @equivalentTo("map(f).flatten")
    def flatMap[B](f: A => Reactive[B]): Reactive[B] = FlatMap(this, f)

    def filter(p: A => Boolean): Reactive[A] = Filter(this, p)

    @aliasOf("filter")
    final def withFilter(p: A => Boolean): Reactive[A] = filter(p)

    def remove(p: A => Boolean): Reactive[A] = Remove(this, p)

    @equivalentTo("(filter(p), remove(p))")
    def partition(p: A => Boolean): (Reactive[A], Reactive[A]) = (filter(p), remove(p))

    def scanLeft[B](z: B)(op: (B, A) => B): Reactive[B] = ScanLeft(this, z, op)

    def tail: Reactive[A] = Tail(this)

    def init: Reactive[A] = Init(this)

    def take(n: Int): Reactive[A] = Take(this, n)

    def drop(n: Int): Reactive[A] = Drop(this, n)

    @equivalentTo("take(m).drop(n)")
    def slice(n: Int, m: Int): Reactive[A] = Slice(this, n, m)

    def takeWhile(p: A => Boolean): Reactive[A] = TakeWhile(this, p)

    def dropWhile(p: A => Boolean): Reactive[A] = DropWhile(this, p)

    @equivalentTo("(takeWhile(p), dropWhile(p))")
    def span(p: A => Boolean): (Reactive[A], Reactive[A]) = (takeWhile(p), dropWhile(p))

    @equivalentTo("(take(n), drop(n))")
    def splitAt(n: Int): (Reactive[A], Reactive[A]) = {
        Precondition.nonnegative(n, "splitAt")
        (take(n), drop(n))
    }

    def flatten[B](implicit pre: Reactive[A] <:< Reactive[Sequence[B]]): Reactive[B] = Flatten(pre(this))

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
    def unsplit[B](sep: Reactive[B])(implicit pre: Reactive[A] <:< Reactive[Sequence[B]]): Reactive[B] = Unsplit(pre(this), sep)

    def unzip[B, C](implicit pre: Reactive[A] <:< Reactive[(B, C)]): (Reactive[B], Reactive[C]) = (pre(this).map{ bc => bc._1 }, pre(this).map{ bc => bc._2 })


// conversion

    @conversion
    def toIterative: Iterative[A] = ToIterative(this)


// algorithm

    def isEmpty: Boolean = {
        for (x <- this) {
            return false
        }
        true
    }

    def forall(p: A => Boolean): Boolean = find(function.not(p)).isEmpty

    def exists(p: A => Boolean): Boolean = !find(p).isEmpty

    def find(p: A => Boolean): Option[A] = {
        for (x <- this) {
            if (p(x)) {
                return Some(x)
            }
        }
        None
    }

    def head: A = {
        for (x <- this) {
            return x
        }
        throw new NoSuchElementException
    }

    def last: A = {
        var lst = head
        for (x <- this)
            lst = x
        lst
    }


// misc

    def force: Reactive[A] = Force(this)

    /**
     * Loops with evaluating `f`.
     */
    def doing(f: A => Unit): Reactive[A] = Doing(this, f)

    @equivalentTo("foreach(_ => ())")
    def start: Unit = foreach(_ => ())

    /**
     * Forks.
     */
    def fork(f: Reactive[A] => Unit): Reactive[A] = Fork(this, f)

    /**
     * Throws if multiple foreach calls happen.
     */
    def singlePass: Reactive[A] = SinglePass(this)

    /**
     * Skips trailing forks.
     */
    def break: Reactive[A] = Break(this)

    /**
     * Calls `f` on the beginning.
     */
    def onBegin(f: => Unit): Reactive[A] = OnBegin(this, util.byLazy(f))

    /**
     * Calls `f` on the ending.
     */
    def onEnd(f: => Unit): Reactive[A] = OnEnd(this, util.byName(f))

    /**
     *
     */
    def until(that: Reactive[_]): Reactive[A] = Until(this, that)

}
