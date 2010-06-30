

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence


import annotation.tailrec
import list._


// See: http://www.haskell.org/onlinereport/standard-prelude.html


// Nil

/**
 * The nil list
 */
case object Nil extends List[Nothing] {
    override def isNil = true
    override def head = throw new NoSuchElementException("head on empty list")
    override def tail = throw new UnsupportedOperationException("tail on empty list")

    def ::[A](x: A): List[A] = new Cons[A](x, util.byLazy(this))
}


// Cons

/**
 * The cons list
 */
final class Cons[+A](val _1: A, val _2: util.ByLazy[List[A]]) extends List[A] {
    override def isNil = false
    override def head = _1
    override def tail = _2()
}


// List

/**
 * Lazy list
 * <ul>
 * <li/>Backtrackable to any subsequence, while <code>Iterative</code> is backtrackable only to "begin".
 * <li/>No iterators, while number of iterator objects may be exponential growth in recursive <code>Iterative</code>.
 * <li/>A projection method usually need an entire copy. It can be lazy, though.
 * </ul>
 */
sealed abstract class List[+A] extends iterative.Sequence[A] {


    @returnThis
    final def of[B >: A]: List[B] = this


    override def asIterative: Iterative[A] = AsIterative(this) // logical super

    @optimize
    override def equals(that: Any): Boolean = that match {
        case that: List[_] => equalsIf(that.asInstanceOf[List[A]])(function.equal)
        case _ => super.equals(that)
    }

    @optimize
    override def hashCode = {
        var r = 1
        var it = this
        while (!it.isEmpty) {
            r = 31 * r + it.head.hashCode
            it = it.tail
        }
        r
    }


// kernel

    /**
     * Is <code>this</code> Nil?
     */
    def isNil: Boolean

    /**
     * The first element
     */
    def head: A

    /**
     * The remaining elements after the first one.
     */
    def tail: List[A]


// strict cons

    @equivalentTo("x :: this")
    def #::[B >: A](x: B): List[B] = x :: of[B]


// iterative

    /**
     * Returns true if and only if both sequences have the same size,
     * and all corresponding pairs of elements in the two sequences
     * satisfy the predicate <code>p</code>.
     */
    def equalsIf[B](that: List[B])(p: (A, B) => Boolean): Boolean = {
        var it = this
        var jt = that
        while (!it.isNil && !jt.isNil) {
            if (!p(it.head, jt.head)) {
                return false
            }
            it = it.tail; jt = jt.tail
        }
        it.isNil && jt.isNil
    }

    @aliasOf("isNil")
    final def isEmpty: Boolean = isNil

    /**
     * Returns the size.
     */
    def size: Int = {
        var r = 0
        var it = this
        while (!it.isNil) {
            r += 1
            it = it.tail
        }
        r
    }

    def append[B >: A](that: => List[B]): List[B] = this match {
        case Nil => that
        case x :: xs => x :: (xs() append that)
    }

    @deprecated("use ::: instead")
    final def ++[B >: A](that: => List[B]): List[B] = append(that)

    /**
     * @return  efficient <code>reverse.append(that)</code>.
     */
    @tailrec
    final def reverseAppend[B >: A](that: => List[B]): List[B] = this match {
        case Nil => that
        case x :: xs => xs().reverseAppend(x :: that)
    }

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): List[B] = this match {
        case Nil => Nil
        case x :: xs => f(x) :: xs().map(f)
    }

    @equivalentTo("map(f).flatten")
    def flatMap[B](f: A => List[B]): List[B] = map(f).flatten

    /**
     * Filters elements using <code>p</code>.
     */
    def filter(p: A => Boolean): List[A] = this match {
        case Nil => Nil
        case x :: xs => {
            if (p(x)) {
                x :: xs().filter(p)
            } else {
                xs().filter(p)
            }
        }
    }

    @aliasOf("filter")
    def withFilter(p: A => Boolean): List[A] = filter(p)

    /**
     * Filters elements using <code>funtion.not(p)</code>.
     */
    def remove(p: A => Boolean): List[A] = filter(function.not(p))

    @equivalentTo("(filter(p), remove(p))")
    def partition(p: A => Boolean): (List[A], List[A]) = (filter(p), remove(p))

    /**
     * Applies <code>f</code> to each element.
     */
    @tailrec
    final def foreach(f: A => Unit): Unit = this match {
        case Nil => ()
        case x :: xs => { f(x); xs().foreach(f) }
    }

    /**
     * Determines if all the elements satisfy the predicate.
     */
    def forall(p: A => Boolean): Boolean = map(p).and // find(function.not(p)).isEmpty

    /**
     * Determines if any element satisfies the predicate.
     */
    def exists(p: A => Boolean): Boolean = map(p).or // !find(p).isEmpty

    /**
     * Counts elements satisfying <code>p</code>.
     */
    def count(p: A => Boolean): Int = {
        var i = 0
        var it = this
        while (!it.isNil) {
            if (p(it.head)) {
                i += 1
            }
            it = it.tail
        }
        i
    }

    /**
     * Finds an element satisfying <code>p</code>.
     */
    @tailrec
    final def find(p: A => Boolean): Option[A] = this match {
        case Nil => None
        case x :: xs => if (p(x)) Some(x) else xs().find(p)
    }

    /**
     * Folds left-associative. (a.k.a. foldl)
     */
    @tailrec
    final def foldLeft[B](z: B)(f: (B, A) => B): B = this match {
        case Nil => z
        case x :: xs => xs().foldLeft(f(z, x))(f)
    }

    @aliasOf("foldLeft")
    final def /:[B](z: B)(f: (B, A) => B): B = foldLeft(z)(f)

    /**
     * Folds right-associative. (a.k.a. foldr)
     */
    def foldRight[B](z: B)(f: (A, util.ByLazy[B]) => B): B = this match {
        case Nil => z
        case x :: xs => f(x, util.byLazy(xs().foldRight(z)(f)))
    }

    @aliasOf("foldRight")
    final def :\[B](z: B)(f: (A, util.ByLazy[B]) => B): B = foldRight(z)(f)

    /**
     * Reduces left-associative. (a.k.a. foldl1)
     */
    def reduceLeft[B >: A](f: (B, A) => B): B = this match {
        case x :: xs => xs().foldLeft[B](x)(f)
        case Nil => throw new UnsupportedOperationException("reduceLeft on empty list")
    }

    /**
     * Reduces right-associative. (a.k.a. foldr1)
     */
    def reduceRight[B >: A](f: (A, util.ByLazy[B]) => B): B = this match {
        case x #:: Nil => x
        case x :: xs => f(x, util.byLazy(xs().reduceRight(f)))
        case Nil => throw new UnsupportedOperationException("reduceRight on empty list")
    }

    /**
     * Prefix sum folding left-associative. (a.k.a. scanl)
     */
    def folderLeft[B](q: => B)(f: (B, A) => B): List[B] = {
        q :: (this match {
            case Nil => Nil
            case x :: xs => xs().folderLeft(f(q, x))(f)
        })
    }

    /**
     * Prefix sum folding right-associative. (a.k.a. scanr)
     */
    def folderRight[B](q0: B)(f: (A, util.ByLazy[B]) => B): List[B] = this match {
        case Nil => q0 :: Nil
        case x :: xs => {
            lazy val qs = xs().folderRight(q0)(f)
            f(x, util.byLazy(qs.head)) :: qs
        }
    }

    /**
     * Prefix sum reducing left-associative. (a.k.a. scanl1)
     */
    def reducerLeft[B >: A](f: (B, A) => B): List[B] = this match {
        case x :: xs => xs().folderLeft[B](x)(f)
        case Nil => Nil
    }

    /**
     * Reduces right-associative. (a.k.a. scanr1)
     */
    def reducerRight[B >: A](f: (A, util.ByLazy[B]) => B): List[B] = this match {
        case Nil => Nil
        case x #:: Nil => this
        case x :: xs => {
            lazy val qs = xs().reducerRight(f)
            f(x, util.byLazy(qs.head)) :: qs
        }
    }

    /**
     * Optionally returns the first element.
     */
    def headOption: Option[A] = this match {
        case x :: _ => Some(x)
        case Nil => None
    }

    /**
     * Removes the last element.
     */
    def init: List[A] = this match {
        case x #:: Nil => Nil
        case x :: xs => x :: xs().init
        case Nil => throw new UnsupportedOperationException("init on empty list")
    }

    /**
     * Returns the last element.
     */
    @tailrec
    final def last: A = this match {
        case x #:: Nil => x
        case _ :: xs => xs().last
        case Nil => throw new UnsupportedOperationException("last on empty list")
    }

    /**
     * Optionally returns the last element.
     */
    @tailrec
    final def lastOption: Option[A] = this match {
        case x #:: Nil => Some(x)
        case _ :: xs => xs().lastOption
        case Nil => None
    }

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): List[A] = (n, this) match {
        case (n, _) if n <= 0 => Nil
        case (_, Nil) => Nil
        case (n, x :: xs) => x :: xs().take(n - 1)
    }

    /**
     * Drops at most <code>n</code> elements.
     */
    @tailrec
    final def drop(n: Int): List[A] = (n, this) match {
        case (n, xs) if n <= 0 => xs
        case (_, Nil) => Nil
        case (n, _ :: xs) => xs().drop(n - 1)
    }

    @equivalentTo("take(m).drop(n)")
    def slice(n: Int, m: Int): List[A] = take(m).drop(n)

    /**
     * Returns the longest prefix that satisfies the predicate.
     */
    def takeWhile(p: A => Boolean): List[A] = this match {
        case Nil => Nil
        case x :: xs => {
            if (p(x)) {
                x :: xs().takeWhile(p)
            } else {
                Nil
            }
        }
    }

    /**
     * Returns the remaining suffix of <code>takeWhile</code>.
     */
    @tailrec
    final def dropWhile(p: A => Boolean): List[A] = this match {
        case Nil => Nil
        case x :: xs => {
            if (p(x)) {
                xs().dropWhile(p)
            } else {
                this
            }
        }
    }

    @equivalentTo("(takeWhile(p), dropWhile(p))")
    def span(p: A => Boolean): (List[A], List[A]) = {
        var it = this
        var _1, _2 = Nil.of[A]

        while (!it.isNil) {
            val x = it.head
            if (p(x)) {
                _1 = x #:: _1
            } else {
                _2 = it
                return (_1, _2)
            }
            it = it.tail
        }

        (_1, _2)
    }

    @equivalentTo("(take(n), drop(n))")
    def splitAt(n: Int): (List[A], List[A]) = (take(n), drop(n))


// misc

    /**
     * Does this contain the element?
     */
    def contains(x: Any): Boolean = exists(function.equalTo(x))

    /**
     * Repeats infinitely.
     */
    def cycle: List[A] = this match {
        case Nil => throw new UnsupportedOperationException("cycle on empty list")
        case xs => {
            lazy val _xs: List[A] = xs ::: _xs
            _xs
        }
    }

    /**
     * Repeats <code>n</code> times.
     */
    def times(n: Int): List[A] = repeat(()).take(n).flatMap{ _ => this }

    /**
     * Forces evaluation of the whole list.
     */
    def force: List[A] = { foreach{ _ => () }; this }

    @returnThis
    def memoize: List[A] = this

    /**
     * Returns the <code>n</code>-th element.
     */
    @tailrec
    final def nth(n: Int): A = (this, n) match {
        case (_, n) if n < 0 => throw new IllegalArgumentException("negative index")
        case (Nil, n) => throw new NoSuchElementException("index too large")
        case (x :: _, 0) => x
        case (_ :: xs, n) => xs().nth(n - 1)
    }

    /**
     * Reverses.
     */
    def reverse: List[A] = foldLeft(Nil.of[A]){ (xs, x) => x :: xs }

    /**
     * Steps by the specified stride.
     */
    def step(n: Int): List[A] = if (n <= 0) step0 else step1(n)

    private def step0: List[A] = this match {
        case Nil => Nil
        case x :: _ => repeat(x)
    }

    private def step1(n: Int): List[A] = this match {
        case Nil => Nil
        case x :: xs => x :: xs().drop(n - 1).step1(n)
    }

    @equivalentTo("uniqueBy(function.equal)")
    def unique: List[A] = uniqueBy(function.equal)

    /**
     * Removes adjacent duplicates by the predicate.
     */
    def uniqueBy(p: (A, A) => Boolean): List[A] = this match {
        case Nil => Nil
        case x :: xs => x :: xs().dropWhile(p(x, _)).uniqueBy(p)
    }

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: List[B]): List[(A, B)] = zipBy(that){ (a, b) => (a, b) }

    /**
     * Zips <code>this</code> and <code>that</code> applying <code>f</code>.
     */
    def zipBy[B, C](that: List[B])(f: (A, B) => C): List[C] = (this, that) match {
        case (a :: as, b :: bs) => f(a, b) :: as().zipBy(bs())(f)
        case _ => Nil
    }

}


object List {


// logical hierarchy

    implicit def _asIterative[A](from: List[A]): Iterative[A] = from.asIterative


// methodization

    sealed class _OfName[A](_this: => List[A]) {
        def ::(x: A): List[A] = new Cons(x, util.byLazy(_this))
    // right-associative
        def :::(prefix: List[A]): List[A] = prefix.append(_this)
        def reverse_:::(prefix: List[A]): List[A] = prefix.reverseAppend(_this)
    }
    implicit def _ofName[A](_this: => List[A]): _OfName[A] = new _OfName(_this)

    sealed class _OfList[A](_this: List[List[A]]) {
        def flatten: List[A] = _this.foldRight(Nil.of[A])(_ ::: _())
    }
    implicit def _ofList[A](_this: List[List[A]]): _OfList[A] = new _OfList(_this)

    sealed class _OfBoolean(_this: List[Boolean]) {
        def and: Boolean = _this.foldRight(true)(_ && _())
        def or: Boolean = _this.foldRight(false)(_ || _())
    }
    implicit def _ofBoolean(_this: List[Boolean]): _OfBoolean = new _OfBoolean(_this)

    sealed class _OfPair[A, B](_this: List[(A, B)]) {
        def unzip: (List[A], List[B]) = _this.foldRight((Nil.of[A], Nil.of[B])){ (ab, abs) => (ab._1 :: abs()._1, ab._2 :: abs()._2) }
    }
    implicit def _ofPair[A, B](_this: List[(A, B)]): _OfPair[A, B] = new _OfPair(_this)


// compatibles

    implicit def _fromOption[A](from: Option[A]): List[A] = fromOption(from)


// pattern matching

    @aliasOf("Of.apply")
    def apply[A](from: A*): List[A] = Of.apply(from: _*)

    @aliasOf("Of.unapplySeq")
    def unapplySeq[A](from: List[A]): Option[Seq[A]] = Of.unapplySeq(from)

}


/**
 * The matcher for cons list
 */
object :: {

    def unapply[A](xs: List[A]): Option[(A, util.ByLazy[List[A]])] = {
        if (xs.isNil) {
            None
        } else {
            Some(xs.head, util.byLazy(xs.tail))
        }
    }

}

/**
 * The strict matcher for cons list
 */
object #:: {

    def unapply[A](xs: List[A]): Option[(A, List[A])] = xs match {
        case Nil => None
        case x :: xs => Some(x, xs())
    }

}
