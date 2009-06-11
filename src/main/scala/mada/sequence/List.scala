

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


import list._


// See: http://www.haskell.org/onlinereport/standard-prelude.html


// Nil

/**
 * The nil list
 */
case object Nil extends List[Nothing] {
    override def isNil = true
    override def head = throw new NoSuchElementException("head on Nil")
    override def tail =  throw new UnsupportedOperationException("tail on Nil")
}


// Cons

/**
 * The cons list
 */
final class Cons[+A](val _1: util.ByLazy[A], val _2: util.ByLazy[List[A]]) extends List[A] {
    override def isNil = false
    override def head = _1()
    override def tail = _2()
}

/**
 * The matcher for cons list
 */
object Cons {

    def apply[A](x: => A, xs: => List[A]): List[A] = new Cons(util.byLazy(x), util.byLazy(xs))

    def unapply[A](xs: List[A]): Option[(util.ByLazy[A], util.ByLazy[List[A]])] = {
        if (xs.isNil) {
            None
        } else {
            Some(util.byLazy(xs.head), util.byLazy(xs.tail))
        }
    }

}

/**
 * The strict matcher for cons list
 */
object :: {

    def unapply[A](xs: List[A]): Option[(A, List[A])] = xs match {
        case Nil => None
        case Cons(x, xs) => Some(x(), xs())
    }

}


// List

/**
 * Lazy list
 * <ul>
 * <li/>Backtrackable to any subsequence, while <code>Iterative</code> is backtrackable only to "begin".
 * <li/>No intermediate objects, while number of iterator objects may be exponential growth in recursive <code>Iterative</code>.
 * <li/>A projection method usually need an entire copy. It can be lazy, though.
 * </ul>
 */
sealed trait List[+A] extends Sequence[A] {


    override def asList: List[A] = this


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

    /**
     * @return  <code>Cons(x, this)</code>.
     */
    def ::[B >: A](x: B): List[B] = Cons(x, this)


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

    def append[B >: A](ys: => List[B]): List[B] = this match {
        case Nil => ys
        case Cons(x, xs) => Cons(x(), xs() append ys)
    }

    @aliasOf("append")
    final def ++[B >: A](ys: => List[B]): List[B] = append(ys)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): List[B] = this match {
        case Nil => Nil
        case Cons(x, xs) => Cons(f(x()), xs().map(f))
    }

    /**
     * @return  <code>map(f).flatten</code>.
     */
    def flatMap[B](f: A => List[B]): List[B] = map(f).flatten

    /**
     * Filters elements using <code>p</code>.
     */
    def filter(p: A => Boolean): List[A] = this match {
        case Nil => Nil
        case Cons(x, xs) => {
            if (p(x())) {
                Cons(x(), xs().filter(p))
            } else {
                xs().filter(p)
            }
        }
    }

    /**
     * Filters elements using <code>funtion.not(p)</code>.
     */
    def remove(p: A => Boolean): List[A] = filter(function.not(p))

    /**
     * @return  <code>(filter(p), remove(p))</code>.
     */
    def partition(p: A => Boolean): (List[A], List[A]) = (filter(p), remove(p))

    /**
     * What?
     */
    def groupBy[K](f: A => K): scala.collection.Map[K, List[A]] = throw new Error

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach(f: A => Unit): Unit = {
        var it = this
        while (!it.isNil) {
            f(it.head)
            it = it.tail
        }
    }

    /**
     * Does <code>p</code> meet for any element?
     */
    def forall(p: A => Boolean): Boolean = find(function.not(p)).isEmpty

    /**
     * Does an element exists which <code>p</code> meets?
     */
    def exists(p: A => Boolean): Boolean = !find(p).isEmpty

    /**
     * Counts elements <code>p</code> meets.
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
     * Finds an element <code>p</code> meets.
     */
    def find(p: A => Boolean): Option[A] = {
        var it = this
        while (!it.isNil) {
            val e = it.head
            if (p(e)) {
                return Some(e)
            }
            it = it.tail
        }
        None
    }

    /**
     * Folds left to right. (a.k.a. foldl)
     */
    def foldLeft[B](z: B)(f: (B, A) => B): B = {
        var acc = z
        var it = this
        while (!it.isNil) {
            acc = f(acc, it.head)
            it = it.tail
        }
        acc
    }

    @aliasOf("foldLeft")
    final def /:[B](z: B)(f: (B, A) => B): B = foldLeft(z)(f)

    /**
     * Folds right to left. (a.k.a. foldr)
     */
    def foldRight[B](z: B)(f: (util.ByLazy[A], util.ByLazy[B]) => B): B = this match {
        case Nil => z
        case Cons(x, xs) => f(x, util.byLazy(xs().foldRight(z)(f)))
    }

    /**
     * Reduces left to right. (a.k.a. foldl1)
     */
    def reduceLeft[B >: A](f: (B, A) => B): B = this match {
        case Cons(x, xs) => xs().foldLeft[B](x())(f)
        case Nil => throw new UnsupportedOperationException("reduceLeft on empty list")
    }

    /**
     * Reduces right to left. (a.k.a. foldr1)
     */
    def reduceRight[B >: A](f: (util.ByLazy[A], util.ByLazy[B]) => B): B = this match {
        case Single(x) => x()
        case Cons(x, xs) => f(x, util.byLazy(xs().reduceRight(f)))
        case Nil => throw new UnsupportedOperationException("reduceRight on empty list")
    }

    /**
     * Prefix sum folding left to right. (a.k.a. scanl)
     */
    def folderLeft[B](q: => B)(f: (B, A) => B): List[B] = {
        Cons(q, this match {
            case Nil => Nil
            case Cons(x, xs) => xs().folderLeft(f(q, x()))(f)
        })
    }

    /**
     * Prefix sum folding right to left. (a.k.a. scanr)
     */
    def folderRight[B](q0: B)(f: (util.ByLazy[A], util.ByLazy[B]) => B): List[B] = this match {
        case Nil => Single(q0)
        case Cons(x, xs) => {
            lazy val qs = xs().folderRight(q0)(f)
            Cons(f(x, util.byLazy(qs.head)), qs)
        }
    }

    /**
     * Prefix sum reducing left to right. (a.k.a. scanl1)
     */
    def reducerLeft[B >: A](f: (B, A) => B): List[B] = this match {
        case Cons(x, xs) => xs().folderLeft[B](x())(f)
        case Nil => Nil
    }

    /**
     * Reduces right to left. (a.k.a. scanr1)
     */
    def reducerRight[B >: A](f: (util.ByLazy[A], util.ByLazy[B]) => B): List[B] = this match {
        case Nil => Nil
        case Single(x) => Single(x())
        case Cons(x, xs) => {
            lazy val qs = xs().reducerRight(f)
            Cons(f(x, util.byLazy(qs.head)), qs)
        }
    }

    /**
     * Optionally returns the first element.
     */
    def headOption: Option[A] = if (isNil) None else Some(head)

    /**
     * Returns the last element.
     */
    def last: A = {
        Precondition.notEmpty(this, "last")
        lastOption.get
    }

    /**
     * Optionally returns the last element.
     */
    def lastOption: Option[A] = {
        var e = option.NoneOf[A]
        var it = this
        while (!it.isNil) {
            e = Some(it.head)
            it = it.tail
        }
        e
    }

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): List[A] = {
        if (n <= 0) {
            Nil
        } else {
            this match {
                case Nil => Nil
                case Cons(x, xs) => Cons(x(), xs().take(n - 1))
            }
        }
    }

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): List[A] = {
        if (n <= 0) {
            this
        } else {
            this match {
                case Nil => Nil
                case Cons(_, xs) => xs().drop(n - 1)
            }
        }
    }

    /**
     * @return  <code>drop(n).take(n - m)</code>.
     */
    def slice(n: Int, m: Int): List[A] = drop(n).take(n - m)

    /**
     * Takes elements while <code>p</code> meets.
     */
    def takeWhile(p: A => Boolean): List[A] = this match {
        case Nil => Nil
        case Cons(x, xs) => {
            if (p(x())) {
                Cons(x(), xs().takeWhile(p))
            } else {
                Nil
            }
        }
    }

    /**
     * Drops elements while <code>p</code> meets.
     */
    def dropWhile(p: A => Boolean): List[A] = this match {
        case Nil => Nil
        case Cons(x, xs) => {
            if (p(x())) {
                xs().dropWhile(p)
            } else {
                this
            }
        }
    }

    /**
     * @return  <code>(takeWhile(p), dropWhile(p))</code>.
     */
    def span(p: A => Boolean): (List[A], List[A]) = (takeWhile(p), dropWhile(p))

    /**
     * @return  <code>(take(n), drop(n))</code>.
     */
    def splitAt(n: Int): (List[A], List[A]) = (take(n), drop(n))


// misc

    /**
     * Returns the <code>n</code>-th element.
     */
    def at(n: Int): A = {
        Precondition.nonnegative(n, "at")
        var i = n
        var it = this
        while (!it.isNil) {
            if (i == 0) {
                return it.head
            }
            i -= 1
            it = it.tail
        }
        throw new NoSuchElementException("at" + Tuple1(n))
    }

    /**
     * Does this contain the element?
     */
    def contains(e: Any): Boolean = exists(function.equalTo(e))

    /**
     * Repeats infinitely.
     */
    def cycle: List[A] = this match {
        case Nil => throw new UnsupportedOperationException("cycle on empty list")
        case xs => {
            lazy val _xs: List[A] = xs ++ _xs
            _xs
        }
    }

    /**
     * Repeats <code>n</code> times.
     */
    def times(n: Int): List[A] = throw new Error

    /**
     * Cuts projection. (A result sequence is always readOnly.)
     */
    def force: List[A] = this

/*
    /**
     * Transforms sequence-to-sequence expression `seq.f.g.h` to `seq.x.f.x.g.x.h`.
     */
    def mix(x: Mixin): List[A] = Mix(this, x)
*/
    /**
     * Reverses.
     */
    def reverse: List[A] = foldLeft(NilOf[A]){ (xs, x) => Cons(x, xs) }
/*
    /**
     * Disables overrides.
     */
    final def seal: List[A] = Seal(this)

    /**
     * Steps by the specified stride.
     */
    def step(n: Int): List[A] = Step(this, n)

    /**
     * Removes duplicates using <code>==</code>.
     */
    def unique: List[A] = Unique(this)

    /**
     * Removes duplicates using the predicate.
     */
    def uniqueBy(p: (A, A) => Boolean): List[A] = UniqueBy(this, p)
*/
    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](ys: List[B]): List[(A, B)] = zipBy(ys){ (a, b) => (a, b) }

    /**
     * Zips <code>this</code> and <code>that</code> applying <code>f</code>.
     */
    def zipBy[B, C](ys: List[B])(f: (A, B) => C): List[C] = this match {
        case Nil => Nil
        case Cons(a, as) => ys match {
            case Nil => Nil
            case Cons(b, bs) => Cons(f(a(), b()), as().zipBy(bs())(f))
        }
    }
}
