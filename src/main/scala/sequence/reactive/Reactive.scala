

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


object Reactive extends Common with Compatibles


/**
 * Yet another Responder with asynchronous foreach.
 */
trait Reactive[+A] extends Sequence[A] {

    @returnThis
    final def of[B >: A]: Reactive[B] = this

    override def asReactive: Reactive[A] = this

    /**
     * `f` is applied to each element, but it is unspecified when|where `f` is called.
     */
    def foreach(f: A => Unit): Unit

    def merge[B >: A](that: Reactive[B]): Reactive[B] = Merge[B](this, that)

    def map[B](f: A => B): Reactive[B] = Map(this, f)

    def flatMap[B](f: A => Reactive[B]): Reactive[B] = FlatMap(this, f)

    def filter(p: A => Boolean): Reactive[A] = Filter(this, p)

    final def withFilter(p: A => Boolean): Reactive[A] = filter(p)

    def remove(p: A => Boolean): Reactive[A] = Remove(this, p)

    def partition(p: A => Boolean): (Reactive[A], Reactive[A]) = (filter(p), remove(p))

    def scanLeft[B](z: B)(op: (B, A) => B): Reactive[B] = ScanLeft(this, z, op)

    def tail: Reactive[A] = Tail(this)

    def init: Reactive[A] = Init(this)

    def take(n: Int): Reactive[A] = Take(this, n)

    def drop(n: Int): Reactive[A] = Drop(this, n)

    def slice(n: Int, m: Int): Reactive[A] = Slice(this, n, m)

    def takeWhile(p: A => Boolean): Reactive[A] = TakeWhile(this, p)

    def dropWhile(p: A => Boolean): Reactive[A] = DropWhile(this, p)

    def span(p: A => Boolean): (Reactive[A], Reactive[A]) = (takeWhile(p), dropWhile(p))

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

    def zip[B](that: Reactive[B]): Reactive[(A, B)] = Zip(this, that)

    def unzip[B, C](implicit pre: Reactive[A] <:< Reactive[(B, C)]): (Reactive[B], Reactive[C]) = (pre(this).map{ bc => bc._1 }, pre(this).map{ bc => bc._2 })


// conversion

    @conversion @visibleForTesting
    def toIterative: Iterative[A] = ToIterative(this)

    @compatibleConversion
    def toResponder: Responder[A] = ToResponder(this)

    @conversion
    def toActor(implicit pre: Reactive[A] <:< Reactive[Unit]): scala.actors.Actor = scala.actors.Actor.reactor(pre(this).toResponder)


// misc

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
     * Takes elements until `that` starts.
     */
    def takeUntil(that: Reactive[_]): Reactive[A] = TakeUntil(this, that)

    /**
     * Calls `f` on the end of subsequence.
     */
    def then(f: => Unit): Reactive[A] = throw new UnsupportedOperationException("Reactive.then")

}
