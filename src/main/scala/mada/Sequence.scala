

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import sequence._


/**
 * Yet another Iterable
 */
trait Sequence[+A] {


// begin

    /**
     * Returns a starting iterator.
     */
    def begin: Iterator[A]


// as value

    /**
     * Equals <code>that</code> iif they has the same length and <code>p</code> meets.
     */
    def equalsIf[B](that: Sequence[B])(p: (A, B) => Boolean): Boolean = {
        val it = begin
        val j = that.begin
        while (it && !j.isEnd) {
            if (!p(~it, ~j)) {
                return false
            }
            it.++; j.++
        }
        !it && !j
    }

    /**
     * Compares the specified object with this sequence for equality.
     * Returns true if and only if the specified object is also a sequence,
     * both sequences have the same size, and all corresponding pairs of
     * elements in the two sequences are equal.
     * For <code>Forwarder</code> to correctly work, you shall not override
     * this in a purpose except optimization.
     */
    override def equals(that: Any) = that match {
        case that: Sequence[_] => equalsIf(that)(function.equal)
        case _ => false
    }

    override def hashCode = {
        var r = 1
        val it = begin
        while (it) {
            r = 31 * r + (~it).hashCode
            it.++
        }
        r
    }

    override def toString = {
        val sb = new StringBuilder
        sb.append('[')

        val it = begin
        if (it) {
            sb.append(~it)
            it.++
        }
        while (it) {
            sb.append(", ")
            sb.append(~it)
            it.++
        }

        sb.append(']')
        sb.toString
    }


// scala sequence

    /**
     * Is <code>this</code> empty?
     */
    def isEmpty: Boolean = begin.isEnd

    /**
     * Returns the length.
     */
    def length: Int = {
        var i = 0
        val it = begin
        while (it) {
            i += 1
            it.++
        }
        i
    }

    @aliasOf("length")
    final def size = length

    /**
     * Appends <code>that</code>.
     */
    def append[B >: A](that: Sequence[B]): Sequence[B] = Append[B](this, that)

    @aliasOf("append")
    final def ++[B >: A](that: Sequence[B]) = append(that)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): Sequence[B] = Map(this, f)

    /**
     * @return  <code>map(f).flatten</code>.
     */
    def flatMap[B](f: A => Sequence[B]): Sequence[B] = _flatten(map(f))

    /**
     * Filters elements using <code>f</code>.
     */
    def filter(p: A => Boolean): Sequence[A] = Filter(this, p)

    /**
     * @return  <code>(filter(p), filter(function.not(p)))</code>.
     */
    def partition(p: A => Boolean): (Sequence[A], Sequence[A]) = (filter(p), filter(function.not(p)))

    /**
     * What?
     */
    def groupBy[K](f: A => K): scala.collection.Map[K, Sequence[A]] = throw new Error

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach[B](f: A => B): Unit = {
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
    def folderLeft[B](z: B)(op: (B, A) => B): Sequence[B] = FolderLeft(this, z, op)

    /**
     * Prefix sum reducing left to right.
     */
    def reducerLeft[B >: A](op: (B, A) => B): Sequence[B] = ReducerLeft(this, op)

    /**
     * Returns the first element.
     */
    def head: A = {
        val it = begin
        if (!it) {
            throw new NoSuchElementException("head on empty sequence")
        }
        ~it
    }

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
     * Returns all the elements without the first one.
     */
    def tail: Sequence[A] = {
        val it = begin
        if (!it) {
            throw new NoSuchElementException("tail on empty sequence")
        }
        bind(it)
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
        var e = NoneOf[A]
        val it = begin
        while (it) {
            e = Some(~it)
        }
        e
    }

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): Sequence[A] = {
        throwIfNegative(n, "take")
        Take(this, n)
    }

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): Sequence[A] = {
        throwIfNegative(n, "drop")
        Drop(this, n)
    }

    /**
     * @return  <code>drop(n).take(n - m)</code>.
     */
    def slice(from: Int, until: Int): Sequence[A] = Slice(this, from, until)

    /**
     * Takes elements while <code>p</code> meets.
     */
    def takeWhile(p: A => Boolean): Sequence[A] = TakeWhile(this, p)

    /**
     * Drops elements while <code>p</code> meets.
     */
    def dropWhile(p: A => Boolean): Sequence[A] = DropWhile(this, p)

    /**
     * @return  <code>(takeWhile(p), dropWhile(p))</code>.
     */
    def span(p: A => Boolean): (Sequence[A], Sequence[A]) = (takeWhile(p), dropWhile(p))

    /**
     * @return  <code>(take(n), drop(n))</code>.
     */
    def splitAt(n: Int): (Sequence[A], Sequence[A]) = {
        throwIfNegative(n, "splitAt")
        (take(n), drop(n))
    }

    @conversion
    def toSIterable: Iterable[A] = ToSIterable(this)


// misc

    /**
     * Returns the <code>n</code>-th element.
     */
    def at(n: Int): A = {
        throwIfNegative(n, "at")
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
    def cycle: Sequence[A] = Cycle(this)

    /**
     * Repeats <code>n</code> times
     */
    def times(n: Int): Sequence[A] = Times(this, n)

    /**
     * Cuts projection.
     */
    def force: Sequence[A] = Force(this)

    /**
     * Turns a sequence of sequences into flat sequence.
     */
    @methodized
    def _flatten[B](_this: Sequence[Sequence[B]]): Sequence[B] = Flatten(_this)

    /**
     * Runtime mixin.
     */
    def mix(x: Mixin): Sequence[A] = Mix(this, x)

    /**
     * Disables overrides.
     */
    final def seal: Sequence[A] = Seal(this)

    /**
     * Disables retraversing.
     */
    final def singlePass: Sequence[A] = SinglePass(this)

    /**
     * Steps by the specified stride.
     */
    def step(n: Int): Sequence[A] = Step(this, n)

    /**
     * Removes duplicates using <code>==</code>.
     */
    def unique: Sequence[A] = Unique(this)

    /**
     * Removes duplicates using the predicate.
     */
    def uniqueBy(p: (A, A) => Boolean): Sequence[A] = UniqueBy(this, p)

    @methodized @conversion
    def _stringize(_this: Sequence[Char]): String = {
        val sb = new StringBuilder
        val it = begin
        while (it) {
            sb.append(~it)
            it.++
        }
        sb.toString
    }

    @methodized @conversion
    def _toSHashMap[K, V](_this: Sequence[(K, V)]): scala.collection.Map[K, V] = {
        val r = new scala.collection.mutable.HashMap[K, V]
        val it = _this.begin
        while (it) {
            r += ~it
            it.++
        }
        r
    }

    @methodized @conversion
    def _toSHashSet[B](_this: Sequence[B]): scala.collection.Set[B] = {
        val r = new scala.collection.mutable.HashSet[B]
        val it = _this.begin
        while (it) {
            r += ~it
            it.++
        }
        r
    }

    @methodized @conversion
    def _toJIterable[B](_this: Sequence[B]): java.lang.Iterable[B] = ToJIterable(_this)

    @methodized @conversion
    def _toVector[B](_this: Sequence[B]): Vector[B] = ToVector(_this)

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: Sequence[B]): Sequence[(A, B)] = Zip(this, that)

    @returnThis
    final def asSequence: Sequence[A] = this


// sorted

    /**
     * @return  <code>mergeBy(that)(c)</code>.
     */
    def merge[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = Merge[B](this, that, c)

    /**
     * Combines the elements in the sorted sequences, into a new sequence with its elements sorted.
     */
    def mergeBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = MergeBy[B](this, that, lt)

    /**
     * @return  <code>unionBy(that)(c)</code>.
     */
    def union[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = Union[B](this, that, c)

    /**
     * Combines the elements in the sorted sequences, into a new sequence with its elements sorted.
     */
    def unionBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = UnionBy[B](this, that, lt)

    /**
     * @return  <code>intersectionBy(that)(c)</code>.
     */
    def intersection[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = Intersection[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set intersection of the two sorted iterables.
     */
    def intersectionBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = IntersectionBy[B](this, that, lt)

    /**
     * @return  <code>differenceBy(that)(c)</code>.
     */
    def difference[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = Difference[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set difference of the two sorted iterables.
     */
    def differenceBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = DifferenceBy[B](this, that, lt)

    /**
     * @return  <code>symmetricDifferenceBy(that)(c)</code>.
     */
    def symmetricDifference[B >: A](that: Sequence[B])(implicit c: Compare[B]): Sequence[B] = SymmetricDifference[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set symmetric difference of the two sorted iterables.
     */
    def symmetricDifferenceBy[B >: A](that: Sequence[B])(lt: compare.Func[B]): Sequence[B] = SymmetricDifferenceBy[B](this, that, lt)

}


object Sequence extends sequence.Compatibles {

    sealed class OfInvariant[A](_this: Sequence[A]) {
        final def toSHashSet: scala.collection.Set[A] = _this._toSHashSet(_this)
        final def toJIterable: java.lang.Iterable[A] = _this._toJIterable(_this)
        final def toVector: Vector[A] = _this._toVector(_this)
    }
    implicit def ofInvariant[A](_this: Sequence[A]): OfInvariant[A] = new OfInvariant(_this)

    sealed class OfSequence[A](_this: Sequence[Sequence[A]]) {
        final def flatten: Sequence[A] = _this._flatten(_this)
    }
    implicit def ofSequence[A](_this: Sequence[Sequence[A]]): OfSequence[A] = new OfSequence(_this)

    sealed class OfTuple2[T1, T2](_this: Sequence[(T1, T2)]) {
        final def toSHashMap: scala.collection.Map[T1, T2] = _this._toSHashMap(_this)
    }
    implicit def ofTuple2[T1, T2](_this: Sequence[(T1, T2)]): OfTuple2[T1, T2] = new OfTuple2(_this)

    sealed class OfChar(_this: Sequence[Char]) {
        final def stringize: String = _this._stringize(_this)
    }
    implicit def ofChar(_this: Sequence[Char]): OfChar = new OfChar(_this)

}
