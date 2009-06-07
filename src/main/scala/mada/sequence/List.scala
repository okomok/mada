

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence



import list._


/**
 * Yet another Stream
 */
trait List[+A] extends iterative.Sequence[A] {


// kernel

    /**
     * Is <code>this</code> empty?
     */
    def isEmpty: Boolean

    /**
     * The first element
     */
    def head: A

    /**
     * The remaining elements after the first one.
     */
    def tail: List[A]

    protected def preHead: Unit = if (isEmpty) throw new NoSuchElementException("head on empty list")
    protected def preTail: Unit = if (isEmpty) throw new UnsupportedOperationException("tail on empty list")


// as value

    /**
     * Returns true if and only if both sequences have the same size,
     * and all corresponding pairs of elements in the two sequences
     * satisfy the predicate <code>p</code>.
     */
    def equalsIf[B](that: => List[B])(p: (A, B) => Boolean): Boolean = {
        var it = this
        var jt = that
        while (!it.isEmpty && !jt.isEmpty) {
            if (!p(it.head, jt.head)) {
                return false
            }
            it = it.tail; jt = jt.tail
        }
        it.isEmpty && jt.isEmpty
    }

    /**
     * Compares the specified object with this sequence for equality.
     * Returns true if and only if the specified object is also a sequence,
     * both sequences have the same size, and all corresponding pairs of
     * elements in the two sequences are equal.
     * You shall not override this in a purpose except optimization.
     *
     * @see Effective Java 2nd Edition - Item 8
     */
    @optimize
    override def equals(that: Any) = that match {
        case that: List[_] => equalsIf(that)(function.equal)
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


// iterative

    override def toIterative: Iterative[A] = throw new Error

    /**
     * Returns the size.
     */
    def size: Int = {
        var r = 0
        var it = this
        while (!it.isEmpty) {
            r += 1
            it = it.tail
        }
        r
    }

    /**
     * Appends <code>that</code>.
     */
//    def ++[B >: A](that: => List[B]): List[B] = Append[B](this, that)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): List[B] = Map(this, f)
/*
    /**
     * @return  <code>map(f).flatten</code>.
     */
    def flatMap[B](f: A => List[B]): List[B] = _flatten(map(f))

    /**
     * Filters elements using <code>p</code>.
     */
    def filter(p: A => Boolean): List[A] = Filter(this, p)

    /**
     * Filters elements using <code>funtion.not(p)</code>.
     */
    def remove(p: A => Boolean): List[A] = Remove(this, p)

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
        val it = begin
        while (it) {
            f(~it)
            it.++
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
     * Finds an element <code>p</code> meets.
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
     * Folds left to right.
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

    @aliasOf("foldLeft")
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    /**
     * Reduces left to right.
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
     * Prefix sum folding left to right.
     */
    def folderLeft[B](z: B)(op: (B, A) => B): List[B] = FolderLeft(this, z, op)

    /**
     * Prefix sum reducing left to right.
     */
    def reducerLeft[B >: A](op: (B, A) => B): List[B] = ReducerLeft(this, op)

    /**
     * Optionally returns the first element.
     */
    def headOption: Option[A] = {
        val it = begin
        if (!it) {
            None
        } else {
            Some(~it)
        }
    }

    /**
     * Returns the last element.
     */
    def last: A = {
        val it = begin
        if (!it) {
            throw new NoSuchElementException("last on empty sequence")
        }
        var e = ~it
        it.++
        while (it) {
            e = ~it
        }
        e
    }

    /**
     * Optionally returns the last element.
     */
    def lastOption: Option[A] = {
        var e = option.NoneOf[A]
        val it = begin
        while (it) {
            e = Some(~it)
        }
        e
    }

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): List[A] = Take(this, n)

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): List[A] = Drop(this, n)

    /**
     * @return  <code>drop(n).take(n - m)</code>.
     */
    def slice(from: Int, until: Int): List[A] = Slice(this, from, until)

    /**
     * Takes elements while <code>p</code> meets.
     */
    def takeWhile(p: A => Boolean): List[A] = TakeWhile(this, p)

    /**
     * Drops elements while <code>p</code> meets.
     */
    def dropWhile(p: A => Boolean): List[A] = DropWhile(this, p)

    /**
     * @return  <code>(takeWhile(p), dropWhile(p))</code>.
     */
    def span(p: A => Boolean): (List[A], List[A]) = (takeWhile(p), dropWhile(p))

    /**
     * @return  <code>(take(n), drop(n))</code>.
     */
    def splitAt(n: Int): (List[A], List[A]) = {
        Precondition.nonnegative(n, "splitAt")
        (take(n), drop(n))
    }

    @compatibleConversion
    def toSSequence: scala.collection.Sequence[A] = ToSSequence(this)


// misc

    /**
     * Returns the <code>n</code>-th element.
     */
    def at(n: Int): A = {
        Precondition.nonnegative(n, "at")
        var i = n
        val it = begin
        while (it) {
            if (i == 0) {
                return ~it
            }
            i -= 1
            it.++
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
    def cycle: List[A] = Cycle(this)

    /**
     * Repeats <code>n</code> times
     */
    def times(n: Int): List[A] = Times(this, n)

    /**
     * Cuts projection. (A result sequence is always readOnly.)
     */
    def force: List[A] = Force(this)

    /**
     * Turns a sequence of sequences into flat sequence.
     */
    @methodized
    def _flatten[B](_this: List[List[B]]): List[B] = Flatten(_this)

    /**
     * Transforms sequence-to-sequence expression `seq.f.g.h` to `seq.x.f.x.g.x.h`.
     */
    def mix(x: Mixin): List[A] = Mix(this, x)

    /**
     * Disables overrides.
     */
    def seal: List[A] = Seal(this)

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
    def zip[B](that: => List[B]): List[(A, B)] = Zip(this, function.ofLazy(that))

    /**
     * Zips <code>this</code> and <code>that</code> applying <code>f</code>.
     */
    def zipBy[B, C](that: => List[B])(f: (A, B) => C): List[C] = ZipBy(this, function.ofLazy(that), f)

    @returnThis
    final def asList: List[A] = this

}
