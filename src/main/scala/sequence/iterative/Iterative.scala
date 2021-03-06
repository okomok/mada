

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


object Iterative extends Common with Compatibles


/**
 * Yet another Iterable
 */
trait Iterative[+A] extends Sequence[A] {


    @annotation.returnThis
    final def of[B >: A]: Iterative[B] = this


    override def asIterative: Iterative[A] = this


// begin

    /**
     * Returns a beginning iterator.
     */
    def begin: Iterator[A]


// as value

    /**
     * Returns true if and only if both sequences have the same size,
     * and all corresponding pairs of elements in the two sequences
     * satisfy the predicate <code>p</code>.
     */
    def equalsIf[B](that: Iterative[B])(p: (A, B) => Boolean): Boolean = {
        val it = begin
        val jt = that.begin
        while (it && jt) {
            if (!p(~it, ~jt)) {
                return false
            }
            it.++; jt.++
        }
        !it && !jt
    }


// scala sequence

    /**
     * Is <code>this</code> empty?
     */
    def isEmpty: Boolean = begin.isEnd

    /**
     * Returns the size.
     */
    def size: Int = {
        var r = 0
        val it = begin
        while (it) {
            r += 1
            it.++
        }
        r
    }

    /**
     * Appends <code>that</code>.
     */
    def append[B >: A](that: Iterative[B]): Iterative[B] = Append[B](this, that)

    @annotation.aliasOf("append")
    final def ++[B >: A](that: Iterative[B]): Iterative[B] = append(that)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): Iterative[B] = Map(this, f)

    @annotation.equivalentTo("map(f).flatten")
    def flatMap[B](f: A => Iterative[B]): Iterative[B] = FlatMap(this, f)

    /**
     * Filters elements using <code>p</code>.
     */
    def filter(p: A => Boolean): Iterative[A] = Filter(this, p)

    @annotation.aliasOf("filter")
    final def withFilter(p: A => Boolean): Iterative[A] = filter(p)

    /**
     * Filters elements using <code>funtion.not(p)</code>.
     */
    def remove(p: A => Boolean): Iterative[A] = Remove(this, p)

    @annotation.equivalentTo("(filter(p), remove(p))")
    def partition(p: A => Boolean): (Iterative[A], Iterative[A]) = (filter(p), remove(p))

    /**
     * Creates a map of vector according to some discriminator function.
     */
    def groupBy[B, K](f: A => K)(implicit pre: Iterative[A] <:< Iterative[B]): scala.collection.Map[K, Vector[B]] = {
        val m = new scala.collection.mutable.HashMap[K, Vector[B]]
        pre(this).foreach { e =>
            val k = f(e.asInstanceOf[A])
            assoc.lazyGet(m)(k)(Vector.from(new java.util.ArrayList[B])).
                asInstanceOf[vector.FromJList[B]]._1.add(e)
        }
        m
    }

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach(f: A => Unit) {
        val it = begin
        while (it) {
            f(~it)
            it.++
        }
    }

    /**
     * Determines if all the elements satisfy the predicate.
     */
    def forall(p: A => Boolean): Boolean = find(function.not(p)).isEmpty

    /**
     * Determines if any element satisfies the predicate.
     */
    def exists(p: A => Boolean): Boolean = !find(p).isEmpty

    /**
     * Counts elements which satisfy the predicate.
     */
    def count(p: A => Boolean): Int = {
        var i = 0
        val it = begin
        while (it) {
            if (p(~it)) {
                i += 1
            }
            it.++
        }
        i
    }

    /**
     * Finds an element which satisfies the predicate.
     */
    def find(p: A => Boolean): Option[A] = {
        val it = begin
        while (it) {
            val e = ~it
            if (p(e)) {
                return Some(e)
            }
            it.++
        }
        None
    }

    /**
     * Folds left-associative.
     */
    def foldLeft[B](z: B)(op: (B, A) => B): B = {
        var acc = z
        val it = begin
        while (it) {
            acc = op(acc, ~it)
            it.++
        }
        acc
    }

    @annotation.aliasOf("foldLeft")
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    /**
     * Reduces left-associative.
     */
    def reduceLeft[B >: A](op: (B, A) => B): B = {
        val it = begin
        if (!it) {
            throw new UnsupportedOperationException("reduceLeft on empty sequence")
        }
        val e = ~it
        it.++
        bind(it).foldLeft[B](e)(op)
    }

    /**
     * Prefix sum folding left-associative.
     */
    def scanLeft[B](z: B)(op: (B, A) => B): Iterative[B] = ScanLeft(this, z, op)

    /**
     * Prefix sum reducing left-associative.
     */
    def scanLeft1[B >: A](op: (B, A) => B): Iterative[B] = ScanLeft1(this, op)

    /**
     * Returns the first element.
     */
    def head: A = {
        val it = begin
        if (!it) {
            throw new NoSuchElementException("head on empty sequence")
        } else {
            ~it
        }
    }

    /**
     * Returns all the elements without the first one.
     */
    def tail: Iterative[A] = Tail(this)

    /**
     * Returns the last element.
     */
    def last: A = {
        var e = util.NoneOf[A]
        val it = begin
        while (it) {
            e = Some(~it)
            it.++
        }
        e.getOrElse{throw new NoSuchElementException("last on empty sequence")}
    }

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): Iterative[A] = Take(this, n)

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): Iterative[A] = Drop(this, n)

    @annotation.equivalentTo("take(m).drop(n)")
    def slice(n: Int, m: Int): Iterative[A] = Slice(this, n, m)

    /**
     * Returns the longest prefix that satisfies the predicate.
     */
    def takeWhile(p: A => Boolean): Iterative[A] = TakeWhile(this, p)

    /**
     * Returns the remaining suffix of <code>takeWhile</code>.
     */
    def dropWhile(p: A => Boolean): Iterative[A] = DropWhile(this, p)

    @annotation.equivalentTo("(takeWhile(p), dropWhile(p))")
    def span(p: A => Boolean): (Iterative[A], Iterative[A]) = (takeWhile(p), dropWhile(p))

    @annotation.equivalentTo("(take(n), drop(n))")
    def splitAt(n: Int): (Iterative[A], Iterative[A]) = {
        Precondition.nonnegative(n, "splitAt")
        (take(n), drop(n))
    }


// misc

    /**
     * Does this contain the element?
     */
    def contains(e: Any): Boolean = exists(function.equalTo(e))

    /**
     * Repeats infinitely.
     */
    def cycle: Iterative[A] = Cycle(this)

    /**
     * Repeats <code>n</code> times
     */
    def times(n: Int): Iterative[A] = Times(this, n)

    /**
     * Cuts projection. (A result sequence is always readOnly.)
     */
    def force: Iterative[A] = Force(this)

    @annotation.equivalentTo("mix(Mixin.force)")
    def strict: Iterative[A] = Strict(this)

    /**
     * Turns a sequence of sequences into flat sequence.
     */
    def flatten[B](implicit pre: Iterative[A] <:< Iterative[Sequence[B]]): Iterative[B] = Flatten(pre(this))

    /**
     * Makes every element access be lazy.
     */
    def memoize: Iterative[A] = Memoize(this)

    /**
     * Returns the <code>n</code>-th element.
     */
    def nth(n: Int): A = {
        Precondition.nonnegative(n, "nth")
        var i = n
        val it = begin
        while (it) {
            if (i == 0) {
                return ~it
            }
            i -= 1
            it.++
        }
        throw new NoSuchElementException("nth" + Tuple1(n))
    }

    /**
     * Transforms sequence-to-sequence expression `seq.f.g.h` to `seq.x.f.x.g.x.h`.
     */
    def mix(x: Mixin): Iterative[A] = Mix(this, x)

    /**
     * Disables overrides.
     */
    final def seal: Iterative[A] = Seal(this)

    /**
     * Disables reiteration.
     */
    def singlePass: Iterative[A] = SinglePass(this)

    /**
     * Steps by the specified stride.
     */
    def step(n: Int): Iterative[A] = Step(this, n)

    /**
     * Removes duplicates using <code>==</code>.
     */
    def unique: Iterative[A] = Unique(this)

    /**
     * Removes duplicates using the predicate.
     */
    def uniqueBy(p: (A, A) => Boolean): Iterative[A] = UniqueBy(this, p)

    /**
     * Flattens <code>vs</code>, each vector appending <code>sep</code> except the last one.
     */
    def unsplit[B](sep: Iterative[B])(implicit pre: Iterative[A] <:< Iterative[Sequence[B]]): Iterative[B] = Unsplit(pre(this), sep)

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: Iterative[B]): Iterative[(A, B)] = Zip(this, that)

    /**
     * Reverts <code>zip</code>.
     */
    def unzip[B, C](implicit pre: Iterative[A] <:< Iterative[(B, C)]): (Iterative[B], Iterative[C]) = (pre(this).map{ bc => bc._1 }, pre(this).map{ bc => bc._2 })

    /**
     * Constructs adjacent pairs.
     */
    def adjacent(n: Int): Iterative[Vector[A]] = Adjacent(this, n)


// conversion

    @annotation.conversion
    def stringize(implicit pre: Iterative[A] <:< Iterative[Char]): String = {
        val sb = new StringBuilder
        val it = pre(this).begin
        while (it) {
            sb.append(~it)
            it.++
        }
        sb.toString
    }

    def lexical(implicit pre: Iterative[A] <:< Iterative[Char]): Lexical = Lexical(pre(this))

    @annotation.conversion
    def toList: List[A] = {
        val it = begin
        if (it) {
            val e = ~it
            it.++
            e :: bind(it).toList
        } else {
            Nil
        }
    }

    @annotation.conversion
    def toVector: Vector[A] = ToVector(this)

    @annotation.conversion
    def toSeq: Seq[A] = ToSeq(this)

    @annotation.conversion
    def toSIterable: scala.collection.Iterable[A] = ToSIterable(this)

    @annotation.conversion
    def toJIterable[B](implicit pre: Iterative[A] <:< Iterative[B]): java.lang.Iterable[B] = ToJIterable(pre(this))

    @annotation.conversion
    def breakOut[To](implicit bf: scala.collection.generic.CanBuildFrom[Nothing, A, To]): To = {
        val b = bf()
        b ++= toSIterable
        b.result
    }


// sorted

    /**
     * Combines the elements in the sorted sequences, into a new sequence with its elements sorted.
     */
    def merge[B >: A](that: Iterative[B])(implicit c: Ordering[B]): Iterative[B] = Merge[B](this, that, c)

    /**
     * Combines the elements in the sorted sequences, into a new sequence with its elements sorted.
     */
    def union[B >: A](that: Iterative[B])(implicit c: Ordering[B]): Iterative[B] = Union[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set intersection of the two sorted iterables.
     */
    def intersection[B >: A](that: Iterative[B])(implicit c: Ordering[B]): Iterative[B] = Intersection[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set difference of the two sorted iterables.
     */
    def difference[B >: A](that: Iterative[B])(implicit c: Ordering[B]): Iterative[B] = Difference[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set symmetric difference of the two sorted iterables.
     */
    def symmetricDifference[B >: A](that: Iterative[B])(implicit c: Ordering[B]): Iterative[B] = SymmetricDifference[B](this, that, c)

}
