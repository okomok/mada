

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


object Reactive extends Common with Compatibles {

// methodization
    sealed class _OfName[A](_this: => Reactive[A]) {
        def byName: Reactive[A] = ByName(_this)
    }
    implicit def _ofName[A](_this: => Reactive[A]): _OfName[A] = new _OfName(_this)

}


/**
 * Reactive sequence, which is built upon asynchronous foreach.
 */
trait Reactive[+A] extends Sequence[A] with java.io.Closeable {


    @annotation.returnThis
    final def of[B >: A]: Reactive[B] = this

    override def asReactive: Reactive[A] = this


// kernel

    /**
     * Should be thread-safe.
     */
    override def close(): Unit = ()

    /**
     * Possibly asynchronous foreach with end reaction.
     * Shall guarantee reactions `f*k?`(in regex) are called in a serialized fashion.
     *
     * @param f reaction for each element
     * @param k reaction for the end of sequence (should not throw)
     */
    def forloop(f: A => Unit, k: Exit => Unit): Unit

    @annotation.equivalentTo("forloop(f, ())")
    final def foreach(f: A => Unit) = forloop(f, _ => ())

    @annotation.equivalentTo("foreach(_ => ())")
    final def start(): Unit = foreach(_ => ())

    private[mada]
    final def _for(f: A => Unit): _ForThen = new _ForThen(f)

    private[mada]
    sealed class _ForThen(f: A => Unit) {
        def _then(k: Exit => Unit): Unit = forloop(f, k)
    }


// combinator

    def append[B >: A](that: Reactive[B]): Reactive[B] = Append[B](this, that)

    @annotation.aliasOf("append")
    final def ++[B >: A](that: Reactive[B]): Reactive[B] = append(that)

    def merge[B >: A](that: Reactive[B]): Reactive[B] = Merge[B](this, that)

    def map[B](f: A => B): Reactive[B] = Map(this, f)

    def flatMap[B](f: A => Reactive[B]): Reactive[B] = FlatMap(this, f)

    def filter(p: A => Boolean): Reactive[A] = Filter(this, p)

    final def withFilter(p: A => Boolean): Reactive[A] = filter(p)

    def collect[B](f: PartialFunction[A, B]): Reactive[B] = Collect(this, f)

    def remove(p: A => Boolean): Reactive[A] = Remove(this, p)

    def partition(p: A => Boolean): (Reactive[A], Reactive[A]) = duplicate match {
        case (xs, ys) => (xs.filter(p), ys.remove(p))
    }

    def scanLeft[B](z: B)(op: (B, A) => B): Reactive[B] = ScanLeft(this, z, op)

    def scanLeft1[B >: A](op: (B, A) => B): Reactive[B] = ScanLeft1(this, op)

    final def scanl[B](z: B): _ScanlBy[B] = new _ScanlBy(z)
    sealed class _ScanlBy[B](z: B) {
        @annotation.equivalentTo("scanLeft(z)(op)")
        def by(op: (B, A) => B): Reactive[B] = scanLeft(z)(op)
    }

//    def head: A = throw new UnsupportedOperationException("Reactive.head")

    def tail: Reactive[A] = Tail(this)

    def init: Reactive[A] = Init(this)

    def take(n: Int): Reactive[A] = Take(this, n)

    def drop(n: Int): Reactive[A] = Drop(this, n)

    def slice(n: Int, m: Int): Reactive[A] = Slice(this, n, m)

    def takeWhile(p: A => Boolean): Reactive[A] = TakeWhile(this, p)

    def dropWhile(p: A => Boolean): Reactive[A] = DropWhile(this, p)

    def span(p: A => Boolean): (Reactive[A], Reactive[A]) = duplicate match {
        case (xs, ys) => (xs.takeWhile(p), ys.dropWhile(p))
    }

    def splitAt(n: Int): (Reactive[A], Reactive[A]) = {
        Precondition.nonnegative(n, "splitAt")
        duplicate match {
            case (xs, ys) => (xs.take(n), ys.drop(n))
        }
    }

    def flatten[B](implicit pre: Reactive[A] <:< Reactive[Sequence[B]]): Reactive[B] = Flatten(pre(this))

    /**
     * Steps by the specified stride.
     */
    def step(n: Int): Reactive[A] = Step(this, n)

    /**
     * Steps by the specified time-span(millisecond).
     */
    def stepTime(i: Long): Reactive[A] = StepTime(this, i)

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
    def zipWithIndex: Reactive[(A, Int)] = ZipWithIndex(this)

    def unzip[B, C](implicit pre: Reactive[A] <:< Reactive[(B, C)]): (Reactive[B], Reactive[C]) = pre(this).duplicate match {
        case (xs, ys) => (xs.map(_._1), ys.map(_._2))
    }


// conversion

    @annotation.conversion @annotation.pre("synchronous")
    def toIterative: Iterative[A] = ToIterative(this)

    @annotation.conversion
    def toResponder: Responder[A] = ToResponder(this)

    def actor: scala.actors.Actor = scala.actors.Actor.actor(start)

    @annotation.conversion
    final def toCps: A @scala.util.continuations.cpsParam[Any, Unit] = ToCps(this)


// misc

    /**
     * Loops with evaluating `f`.
     */
    def react(f: A => Unit): Reactive[A] = React(this, f)

    /**
     * Loops with evaluating `f`.
     */
    def reactMatch(f: PartialFunction[A, Unit]): Reactive[A] = ReactMatch(this, f)

    @annotation.equivalentTo("react(_ => f)")
    final def doing(f: => Unit): Reactive[A] = react(_ => f)

    /**
     * Forks.
     */
    def fork(f: Reactive[A] => Reactive[_]): Reactive[A] = Fork(this, f)

    /**
     * Creates a duplicate.
     */
    def duplicate: (Reactive[A], Reactive[A]) = { val b = Duplicate(this); (b, b) }

    /**
     * Skips trailing forks.
     */
    def break: Reactive[A] = Break(this)

    /**
     * Takes elements until `that` starts. `that` may be closed.
     */
    def takeUntil(that: Reactive[_]): Reactive[A] = TakeUntil(this, that)

    /**
     * Drops elements until `that` starts. `that` may be closed.
     */
    def dropUntil(that: Reactive[_]): Reactive[A] = DropUntil(this, that)

    /**
     * Calls `f` on the head of sequence.
     */
    def onHead(f: A => Unit): Reactive[A] = OnHead(this, f)

    /**
     * Calls `f` on the nth of sequence.
     */
    def onNth(n: Int)(f: A => Unit): Reactive[A] = OnNth(this, n, f)

    /**
     * Calls `f` on the closing of sequence.
     */
    def onClose(f: => Unit): Reactive[A] = OnClose(this, f)

    /**
     * Calls `f` on the exit of sequence.
     */
    def onExit(k: Exit => Unit): Reactive[A] = OnExit(this, k)

    /**
     * Pseudo catch-statement
     */
    def catching(f: PartialFunction[Throwable, Unit]): Reactive[A] = Catching(this, f)

    /**
     * Attach a resource.
     */
    def using(c: java.io.Closeable): Reactive[A] = Using(this, c)

    @annotation.aliasOf("using(this)")
    final def used: Reactive[A] = using(this)

    /**
     * Ignores `close()` call.
     */
    def protect: Reactive[A] = Protect(this)

    /**
     * Tokenized by Peg.
     */
    def tokenize[B >: A](p: Peg[B]): Reactive[Vector[B]] = Tokenize[B](this, p)

    /**
     * Retrieves adjacent sequences.
     */
    def adjacent(n: Int): Reactive[Vector[A]] = Adjacent(this, n)

    /**
     * Replaces elements by those of `it`. The length of this sequence never becomes longer.
     */
    def generate[B](it: Iterative[B]): Reactive[B] = Generate(this, it)

    /**
     * Replaces elements by those of `it`. The length of this sequence never be changed.
     */
    def replace[B >: A](it: Iterative[B]): Reactive[B] = Replace[B](this, it)

    /**
     * Replaces elements by those of `it`. The length of this sequence never be changed.
     */
    def replaceRegion[B >: A](n: Int, m: Int, it: Iterative[B]): Reactive[B] = ReplaceRegion[B](this, n, m, it)

    @annotation.equivalentTo("replace(Stream.from(0))")
    def indices: Reactive[Int] = Indices(this)

    /**
     * Reactions are invoked in somewhere you specify.
     */
    def shift(k: (=> Unit) => Unit): Reactive[A] = Shift(this, k)

    /**
     * Reactions are invoked by somehow you specify.
     */
    def shiftReact[B >: A](g: B => (B => Unit) => Unit): Reactive[B] = ShiftReact[B](this, g)

    /**
     * Elements with a break function.
     */
    def breakable: Reactive[(A, Function0[Unit])] = Breakable(this)

}
