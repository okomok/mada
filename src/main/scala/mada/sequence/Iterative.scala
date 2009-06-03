

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


import iterative._


/**
 * Yet another Iterable
 */
trait Iterative[+A] extends Sequence[A] {


    override def toIterative = this


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
    def ++[B >: A](that: Iterative[B]): Iterative[B] = Append[B](this, that)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): Iterative[B] = Map(this, f)

    /**
     * @return  <code>map(f).flatten</code>.
     */
    def flatMap[B](f: A => Iterative[B]): Iterative[B] = _flatten(map(f))

    /**
     * Filters elements using <code>p</code>.
     */
    def filter(p: A => Boolean): Iterative[A] = Filter(this, p)

    /**
     * Filters elements using <code>funtion.not(p)</code>.
     */
    def remove(p: A => Boolean): Iterative[A] = Remove(this, p)

    /**
     * @return  <code>(filter(p), remove(p))</code>.
     */
    def partition(p: A => Boolean): (Iterative[A], Iterative[A]) = (filter(p), remove(p))

    /**
     * What?
     */
    def groupBy[K](f: A => K): scala.collection.Map[K, Iterative[A]] = throw new Error

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
    def folderLeft[B](z: B)(op: (B, A) => B): Iterative[B] = FolderLeft(this, z, op)

    /**
     * Prefix sum reducing left to right.
     */
    def reducerLeft[B >: A](op: (B, A) => B): Iterative[B] = ReducerLeft(this, op)

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
    def tail: Iterative[A] = Tail(this)

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
    def take(n: Int): Iterative[A] = Take(this, n)

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): Iterative[A] = Drop(this, n)

    /**
     * @return  <code>drop(n).take(n - m)</code>.
     */
    def slice(from: Int, until: Int): Iterative[A] = Slice(this, from, until)

    /**
     * Takes elements while <code>p</code> meets.
     */
    def takeWhile(p: A => Boolean): Iterative[A] = TakeWhile(this, p)

    /**
     * Drops elements while <code>p</code> meets.
     */
    def dropWhile(p: A => Boolean): Iterative[A] = DropWhile(this, p)

    /**
     * @return  <code>(takeWhile(p), dropWhile(p))</code>.
     */
    def span(p: A => Boolean): (Iterative[A], Iterative[A]) = (takeWhile(p), dropWhile(p))

    /**
     * @return  <code>(take(n), drop(n))</code>.
     */
    def splitAt(n: Int): (Iterative[A], Iterative[A]) = {
        throwIfNegative(n, "splitAt")
        (take(n), drop(n))
    }

    @compatibleConversion
    def toSSequence: scala.collection.Sequence[A] = ToSSequence(this)


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
    def cycle: Iterative[A] = Cycle(this)

    /**
     * Repeats <code>n</code> times
     */
    def times(n: Int): Iterative[A] = Times(this, n)

    /**
     * Cuts projection. (A result sequence is always readOnly.)
     */
    def force: Iterative[A] = Force(this)

    /**
     * Turns a sequence of sequences into flat sequence.
     */
    @methodized
    def _flatten[B](_this: Iterative[Sequence[B]]): Iterative[B] = Flatten(_this)

    /**
     * Makes every element access be lazy.
     */
    def memoize: Iterative[A] = Memoize(this)

    /**
     * Transforms sequence-to-sequence expression `seq.f.g.h` to `seq.x.f.x.g.x.h`.
     */
    def mix(x: Mixin): Iterative[A] = Mix(this, x)

    /**
     * Disables overrides.
     */
    def seal: Iterative[A] = Seal(this)

    /**
     * Disables retraversing.
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

    @compatibleConversion
    def toSome: ToSome[A] = new ToSome(this)

    @methodized @conversion
    def _stringize(_this: Iterative[Char]): String = {
        val sb = new StringBuilder
        val it = begin
        while (it) {
            sb.append(~it)
            it.++
        }
        sb.toString
    }

    @methodized @conversion
    def _toSHashMap[K, V](_this: Iterative[(K, V)]): scala.collection.Map[K, V] = {
        val r = new scala.collection.mutable.HashMap[K, V]
        val it = _this.begin
        while (it) {
            r += ~it
            it.++
        }
        r
    }

    @methodized @conversion
    def _toSHashSet[B](_this: Iterative[B]): scala.collection.Set[B] = {
        val r = new scala.collection.mutable.HashSet[B]
        val it = _this.begin
        while (it) {
            r += ~it
            it.++
        }
        r
    }

    @methodized @compatibleConversion
    def _toJIterable[B](_this: Iterative[B]): java.lang.Iterable[B] = ToJIterable(_this)

    @methodized @conversion
    def _toVector[B](_this: Iterative[B]): Vector[B] = ToVector(_this)

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: Iterative[B]): Iterative[(A, B)] = Zip(this, that)

    /**
     * Zips <code>this</code> and <code>that</code> applying <code>f</code>.
     */
    def zipBy[B, C](that: Iterative[B])(f: (A, B) => C): Iterative[C] = ZipBy(this, that, f)

    @returnThis
    final def asIterative: Iterative[A] = this


// sorted

    /**
     * @return  <code>mergeBy(that)(c)</code>.
     */
    def merge[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = Merge[B](this, that, c)

    /**
     * Combines the elements in the sorted sequences, into a new sequence with its elements sorted.
     */
    def mergeBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = MergeBy[B](this, that, lt)

    /**
     * @return  <code>unionBy(that)(c)</code>.
     */
    def union[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = Union[B](this, that, c)

    /**
     * Combines the elements in the sorted sequences, into a new sequence with its elements sorted.
     */
    def unionBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = UnionBy[B](this, that, lt)

    /**
     * @return  <code>intersectionBy(that)(c)</code>.
     */
    def intersection[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = Intersection[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set intersection of the two sorted iterables.
     */
    def intersectionBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = IntersectionBy[B](this, that, lt)

    /**
     * @return  <code>differenceBy(that)(c)</code>.
     */
    def difference[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = Difference[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set difference of the two sorted iterables.
     */
    def differenceBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = DifferenceBy[B](this, that, lt)

    /**
     * @return  <code>symmetricDifferenceBy(that)(c)</code>.
     */
    def symmetricDifference[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = SymmetricDifference[B](this, that, c)

    /**
     * Constructs a sorted iterable with the set symmetric difference of the two sorted iterables.
     */
    def symmetricDifferenceBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = SymmetricDifferenceBy[B](this, that, lt)

}


object Iterative {


// methodization

    sealed class OfInvariant[A](_this: Iterative[A]) {
        def toSHashSet: scala.collection.Set[A] = _this._toSHashSet(_this)
        def toJIterable: java.lang.Iterable[A] = _this._toJIterable(_this)
        def toVector: Vector[A] = _this._toVector(_this)
    }
    implicit def ofInvariant[A](_this: Iterative[A]): OfInvariant[A] = new OfInvariant(_this)

    sealed class OfSequence[A](_this: Iterative[Sequence[A]]) {
        def flatten: Iterative[A] = _this._flatten(_this)
    }
    implicit def ofSequence[A](_this: Iterative[Sequence[A]]): OfSequence[A] = new OfSequence(_this)

    sealed class OfTuple2[T1, T2](_this: Iterative[(T1, T2)]) {
        def toSHashMap: scala.collection.Map[T1, T2] = _this._toSHashMap(_this)
    }
    implicit def ofTuple2[T1, T2](_this: Iterative[(T1, T2)]): OfTuple2[T1, T2] = new OfTuple2(_this)

    sealed class OfChar(_this: Iterative[Char]) {
        def stringize: String = _this._stringize(_this)
    }
    implicit def ofChar(_this: Iterative[Char]): OfChar = new OfChar(_this)


// compatibles

    implicit def madaIterativeFromVector[A](from: Vector[A]): Iterative[A] = fromVector(from)
    implicit def madaIterativeFromSIterable[A](from: Iterable[A]): Iterative[A] = fromSIterable(from)
    implicit def madaIterativeFromJIterable[A](from: java.lang.Iterable[A]): Iterative[A] = fromJIterable(from)
    implicit def madaIterativeUnstringize(from: String): Iterative[Char] = Unstringize(from)
    implicit def madaIterativeFromJObjectInput(from: java.io.ObjectInput): Iterative[AnyRef] = fromJObjectInput(from)
    implicit def madaIterativeFromJReader(from: java.io.Reader): Iterative[Char] = fromJReader(from)


// pattern matching

    @aliasOf("Of.apply")
    def apply[A](from: A*): Iterative[A] = Of.apply(from: _*)

    @aliasOf("Of.unapplySeq")
    def unapplySeq[A](from: Iterative[A]): Option[Seq[A]] = Of.unapplySeq(from)

}
