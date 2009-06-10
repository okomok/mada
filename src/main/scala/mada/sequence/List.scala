

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence



import list._


/**
 * Yet another Stream:
 * <ul>
 * <li/>Backtrackable to any subsequence, while <code>Iterative</code> is backtrackable only to "begin".
 * <li/>No intermediate objects, while number of iterator objects may be exponential growth in recursive <code>Iterative</code>.
 * <li/>A projection method usually need an entire copy. It can be lazy, though.
 * </ul>
 */
trait List[+A] extends Sequence[A] {


    override def asList: List[A] = this


// kernel

    /**
     * Is <code>this</code> nil?
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

    /**
     * The precondition of <code>head</code>
     */
    protected def preHead: Unit = if (isNil) throw new NoSuchElementException("head on empty list")

    /**
     * The precondition of <code>tail</code>
     */
    protected def preTail: Unit = if (isNil) throw new UnsupportedOperationException("tail on empty list")


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

    /**
     * Appends <code>that</code>.
     */
    def append[B >: A](that: List[B]): List[B] = Append[B](this, that)

    @aliasOf("append")
    final def ++[B >: A](that: List[B]): List[B] = append(that)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): List[B] = Map(this, f)

    /**
     * @return  <code>map(f).flatten</code>.
     */
    def flatMap[B](f: A => List[B]): List[B] = FlatMap(this, f)

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
     * Folds left to right.
     */
    def foldLeft[B](z: B)(op: (B, A) => B): B = {
        var acc = z
        var it = this
        while (!it.isNil) {
            acc = op(acc, it.head)
            it = it.tail
        }
        acc
    }

    @aliasOf("foldLeft")
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    /**
     * Reduces left to right.
     */
    def reduceLeft[B >: A](op: (B, A) => B): B = {
        Precondition.notEmpty(this, "reduceLeft")
        tail.foldLeft[B](head)(op)
    }
    /**
     * Prefix sum folding left to right.
     */
    def folderLeft[B](z: => B)(op: (B, A) => B): List[B] = FolderLeft(this, z, op)

    /**
     * Prefix sum reducing left to right.
     */
    def reducerLeft[B >: A](op: (B, A) => B): List[B] = ReducerLeft(this, op)

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
/*
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
    def zip[B](that: List[B]): List[(A, B)] = Zip(this, that)

    /**
     * Zips <code>this</code> and <code>that</code> applying <code>f</code>.
     */
    def zipBy[B, C](that: List[B])(f: (A, B) => C): List[C] = ZipBy(this, that, f)

}
