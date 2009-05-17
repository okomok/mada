

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import traversable._


/**
 * Yet another Iterable
 */
trait Traversable[+A] {


// begin

    /**
     * Returns a starting traverser.
     */
    def begin: Traverser[A]


// as value

    override def equals(that: Any) = that match {
        case that: Traversable[_] => equalsIf(that)(function.equal)
        case _ => false
    }

    /**
     * Equals <code>that</code> iif they has the same length and <code>p</code> meets.
     */
    def equalsIf[B](that: Traversable[B])(p: (A, B) => Boolean): Boolean = {
        val t = begin
        val u = that.begin
        while (t && !u.isEnd) {
            if (!p(~t, ~u)) {
                return false
            }
            t.++; u.++
        }
        !t && !u
    }

    override def toString = {
        val sb = new StringBuilder
        sb.append('[')

        val t = begin
        if (t) {
            sb.append(~t)
            t.++
        }
        while (t) {
            sb.append(", ")
            sb.append(~t)
            t.++
        }

        sb.append(']')
        sb.toString
    }

    override def hashCode = {
        var code = 1
        val t = begin
        while (t) {
            code = 31 * code + (~t).hashCode
            t.++
        }
        code
    }


// scala traversable

    /**
     * Is <code>this</code> empty?
     */
    def isEmpty: Boolean = begin.isEnd

    /**
     * Returns the length.
     */
    def length: Int = {
        var i = 0
        val t = begin
        while (t) {
            i += 1
            t.++
        }
        i
    }

    @aliasOf("length")
    final def size = length

    /**
     * Appends <code>that</code>.
     */
    def append[B >: A](that: Traversable[B]): Traversable[B] = Append[B](this, that)

    @aliasOf("append")
    final def ++[B >: A](that: Traversable[B]) = append(that)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): Traversable[B] = Map(this, f)

    /**
     * @return  <code>map(f).flatten</code>.
     */
    def flatMap[B](f: A => Traversable[B]): Traversable[B] = _flatten(map(f))

    /**
     * Filters elements using <code>f</code>.
     */
    def filter(p: A => Boolean): Traversable[A] = Filter(this, p)

    /**
     * @return  <code>(filter(p), filter(function.not(p)))</code>.
     */
    def partition(p: A => Boolean): (Traversable[A], Traversable[A]) = (filter(p), filter(function.not(p)))

    /**
     * What?
     */
    def groupBy[K](f: A => K): scala.collection.Map[K, Traversable[A]] = throw new Error

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach[B](f: A => B): Unit = {
        val t = begin
        while (t) {
            f(~t)
            t.++
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
        val t = begin
        while (t) {
            if (p(~t)) {
                i += 1
            }
            t.++
        }
        i
    }

    /**
     * Finds an element <code>p</code> meets.
     */
    def find(p: A => Boolean): Option[A] = {
        val t = begin
        while (t) {
            val e = ~t
            if (p(e)) {
                return Some(e)
            }
            t.++
        }
        None
    }

    /**
     * Folds left to right.
     */
    def foldLeft[B](z: B)(op: (B, A) => B): B = {
        var acc = z
        val t = begin
        while (t) {
            acc = op(acc, ~t)
            t.++
        }
        acc
    }

    @aliasOf("foldLeft")
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    /**
     * Reduces left to right.
     */
    def reduceLeft[B >: A](op: (B, A) => B): B = {
        val t = begin
        if (!t) {
            throw new UnsupportedOperationException("reduceLeft on empty traversable")
        }
        val e = ~t
        t.++
        bind(t).foldLeft[B](e)(op)
    }

    /**
     * Prefix sum folding left to right.
     */
    def folderLeft[B](z: B)(op: (B, A) => B): Traversable[B] = FolderLeft(this, z, op)

    /**
     * Prefix sum reducing left to right.
     */
    def reducerLeft[B >: A](op: (B, A) => B): Traversable[B] = ReducerLeft(this, op)

    /**
     * Returns the first element.
     */
    def head: A = {
        val t = begin
        if (!t) {
            throw new NoSuchElementException("head on empty traversable")
        }
        ~t
    }

    /**
     * Optionally returns the first element.
     */
    def headOption: Option[A] = {
        val t = begin
        if (!t) {
            None
        } else {
            Some(~t)
        }
    }

    /**
     * Returns all the elements without the first one.
     */
    def tail: Traversable[A] = {
        val t = begin
        if (!t) {
            throw new NoSuchElementException("tail on empty traversable")
        }
        bind(t)
    }

    /**
     * Returns the last element.
     */
    def last: A = {
        val t = begin
        if (!t) {
            throw new NoSuchElementException("last on empty traversable")
        }
        var e = ~t
        t.++
        while (t) {
            e = ~t
        }
        e
    }

    /**
     * Optionally returns the last element.
     */
    def lastOption: Option[A] = {
        var e = NoneOf[A]
        val t = begin
        while (t) {
            e = Some(~t)
        }
        e
    }

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): Traversable[A] = {
        throwIfNegative(n, "take")
        Take(this, n)
    }

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): Traversable[A] = {
        throwIfNegative(n, "drop")
        Drop(this, n)
    }

    /**
     * @return  <code>drop(n).take(n - m)</code>.
     */
    def slice(from: Int, until: Int): Traversable[A] = Slice(this, from, until)

    /**
     * Takes elements while <code>p</code> meets.
     */
    def takeWhile(p: A => Boolean): Traversable[A] = TakeWhile(this, p)

    /**
     * Drops elements while <code>p</code> meets.
     */
    def dropWhile(p: A => Boolean): Traversable[A] = DropWhile(this, p)

    /**
     * @return  <code>(takeWhile(p), dropWhile(p))</code>.
     */
    def span(p: A => Boolean): (Traversable[A], Traversable[A]) = (takeWhile(p), dropWhile(p))

    /**
     * @return  <code>(take(n), drop(n))</code>.
     */
    def splitAt(n: Int): (Traversable[A], Traversable[A]) = {
        throwIfNegative(n, "splitAt")
        (take(n), drop(n))
    }

    @conversion
    def toIterable: Iterable[A] = ToIterable(this)


// misc

    /**
     * Returns the <code>n</code>-th element.
     */
    def at(n: Int): A = {
        throwIfNegative(n, "at")
        var i = n
        val t = begin
        while (t) {
            if (i == 0) {
                return ~t
            }
            i -= 1
            t.++
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
    def cycle: Traversable[A] = Cycle(this)

    /**
     * Repeats <code>n</code> times
     */
    def times(n: Int): Traversable[A] = Times(this, n)

    /**
     * Turns a traversable of traversables into flat traversable.
     */
    @methodized
    def _flatten[B](_this: Traversable[Traversable[B]]): Traversable[B] = Flatten(_this)

    /**
     * Disables overrides.
     */
    final def seal: Traversable[A] = Seal(this)

    /**
     * Disables retraversing.
     */
    final def singlePass: Traversable[A] = SinglePass(this)

    /**
     * Steps by the specified stride.
     */
    def step(n: Int): Traversable[A] = Step(this, n)

    /**
     * Removes duplicates using <code>==</code>.
     */
    def unique: Traversable[A] = Unique(this)

    /**
     * Removes duplicates using the predicate.
     */
    def uniqueBy(p: (A, A) => Boolean): Traversable[A] = UniqueBy(this, p)

    @methodized @conversion
    def _stringize(_this: Traversable[Char]): String = {
        val sb = new StringBuilder
        val t = begin
        while (t) {
            sb.append(~t)
            t.++
        }
        sb.toString
    }

    @methodized @conversion
    def _toHashMap[K, V](_this: Traversable[(K, V)]): scala.collection.Map[K, V] = {
        val r = new scala.collection.mutable.HashMap[K, V]
        val t = _this.begin
        while (t) {
            r += ~t
            t.++
        }
        r
    }

    @methodized @conversion
    def _toHashSet[B](_this: Traversable[B]): scala.collection.Set[B] = {
        val r = new scala.collection.mutable.HashSet[B]
        val t = _this.begin
        while (t) {
            r += ~t
            t.++
        }
        r
    }

    @methodized @conversion
    def _toVector[B](_this: Traversable[B]): Vector[B] = ToVector(_this)

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: Traversable[B]): Traversable[(A, B)] = Zip(this, that)


// sorted

    /**
     * @return  <code>mergeBy(that)(c)</code>.
     */
    def merge[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = Merge[B](this, that, c)

    /**
     * Combines the elements tr the sorted traversables, into a new traversable with its elements sorted.
     */
    def mergeBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = MergeBy[B](this, that, lt)

    /**
     * @return  <code>unionBy(that)(c)</code>.
     */
    def union[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = Union[B](this, that, c)

    /**
     * Combines the elements tr the sorted traversables, into a new traversable with its elements sorted.
     */
    def unionBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = UnionBy[B](this, that, lt)

    /**
     * @return  <code>intersectionBy(that)(c)</code>.
     */
    def intersection[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = Intersection[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set intersection of the two sorted iterables.
     */
    def intersectionBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = IntersectionBy[B](this, that, lt)

    /**
     * @return  <code>differenceBy(that)(c)</code>.
     */
    def difference[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = Difference[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set difference of the two sorted iterables.
     */
    def differenceBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = DifferenceBy[B](this, that, lt)

    /**
     * @return  <code>symmetricDifferenceBy(that)(c)</code>.
     */
    def symmetricDifference[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = SymmetricDifference[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set symmetric difference of the two sorted iterables.
     */
    def symmetricDifferenceBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = SymmetricDifferenceBy[B](this, that, lt)

}


object Traversable extends Compatibles {

    sealed class OfInvariant[A](_this: Traversable[A]) {
        final def toHashSet: scala.collection.Set[A] = _this._toHashSet(_this)
        final def toVector: Vector[A] = _this._toVector(_this)
    }
    implicit def ofInvariant[A](_this: Traversable[A]): OfInvariant[A] = new OfInvariant(_this)

    sealed class OfTraversable[A](_this: Traversable[Traversable[A]]) {
        final def flatten: Traversable[A] = _this._flatten(_this)
    }
    implicit def ofTraversable[A](_this: Traversable[Traversable[A]]): OfTraversable[A] = new OfTraversable(_this)

    sealed class OfTuple2[T1, T2](_this: Traversable[(T1, T2)]) {
        final def toHashMap: scala.collection.Map[T1, T2] = _this._toHashMap(_this)
    }
    implicit def ofTuple2[T1, T2](_this: Traversable[(T1, T2)]): OfTuple2[T1, T2] = new OfTuple2(_this)

    sealed class OfChar(_this: Traversable[Char]) {
        final def stringize: String = _this._stringize(_this)
    }
    implicit def ofChar(_this: Traversable[Char]): OfChar = new OfChar(_this)

}
