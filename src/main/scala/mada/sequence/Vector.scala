

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


import vector._


/**
 * Sequences that guarantees O(1) element access and O(1) length computation.
 * A vector is optionally writable but structurally-unmodifiable so that synchronization is unneeded.<p/>
 *
 * In vector, an index works like a "key" or "pointer"; it is not guaranteed to start from <code>0</code>.
 * You have to use the <code>nth</code> method if you need "0-to-size" indexing, or extract an index by using
 * the <code>start</code> method.<p/>
 *
 * Unless otherwise specified, these methods return projections to keep readability and writability.
 */
trait Vector[A] extends PartialFunction[Int, A] with Sequence[A] {


// kernel

    /**
     * @return  start index of this vector, which is NOT guaranteed to be <code>0</code>.
     */
    def start: Int

    /**
     * @return  end index of this vector, which is NOT guaranteed to be <code>size</code>.
     */
    def end: Int

    /**
     * @param   i   index of element to return.
     * @pre     this vector is readable.
     * @pre     <code>isDefinedAt(i)</code>
     * @return  the element at the specified position in this vector.
     * @throws  vector.NotReadableException if not overridden.
     */
    override def apply(i: Int): A = throw new NotReadableException(this)

    /**
     * Replaces the element at the specified position in this vector with
     * the specified element.
     *
     * @param   i   index of element to replace.
     * @param   e   element to be stored at the specified position.
     * @pre     this vector is writable.
     * @pre     <code>isDefinedAt(i)</code>
     * @throws  vector.NotWritableException if not overridden.
     */
    def update(i: Int, e: A): Unit = throw new NotWritableException(this)

    /**
     * @return  <code>(start <= i) && (i < end)</code>, possibly overridden in subclasses.
     */
    override def isDefinedAt(i: Int): Boolean = (start <= i) && (i < end)


// iterative

    override def toIterative: Iterative[A] = ToIterative(this)

    /**
     * Returns true if and only if both sequences have the same size,
     * and all corresponding pairs of elements in the two sequences
     * satisfy the predicate <code>p</code>.
     */
    def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = {
        if (size != that.size) {
            false
        } else {
            stl.Equal(this, start, end, that, that.start, p)
        }
    }

    @optimize
    override def equals(that: Any): Boolean = that match {
        case that: Vector[_] => equalsIf(that)(function.equal)
        case _ => super.equals(that)
    }

    @optimize
    override def hashCode: Int = {
        var r = 1
        var i = start; val j = end
        while (i != j) {
            r = 31 * r + this(i).hashCode
            i += 1
        }
        r
    }

    @optimize
    override def toString = toJclArrayList.toString

    /**
     * @return  <code>start == end</code>.
     */
    final def isEmpty: Boolean = start == end

    /**
     * Returns the size.
     */
    final def size: Int = end - start

    /**
     * Appends <code>that</code>.
     */
    def ++(that: Vector[A]): Vector[A] = Append(this, that)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): Vector[B] = Map(this, f)

    /**
     * @return  <code>map(f).flatten</code>.
     */
    def flatMap[B](f: A => Iterative[B]): Vector[B] = vector.flatten(map(f).toIterative)

    /**
     * Filters elements using <code>p</code>.
     */
    def filter(p: A => Boolean): Vector[A] = Filter(this, p)

    /**
     * Filters elements using <code>funtion.not(p)</code>.
     */
    def remove(p: A => Boolean): Vector[A] = Remove(this, p)

    /**
     * @return  <code>(filter(p), remove(p))</code>.
     */
    def partition(p: A => Boolean): (Vector[A], Vector[A]) = (filter(p), remove(p))

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach(f: A => Unit): Unit = stl.ForEach(this, start, end, f)

    /**
     * Does <code>p</code> meet for any element?
     */
    def forall(p: A => Boolean): Boolean = seek(function.not(p)).isEmpty

    /**
     * Does an element exists which <code>p</code> meets?
     */
    def exists(p: A => Boolean): Boolean = !seek(p).isEmpty

    /**
     * Counts elements <code>p</code> meets.
     */
    def count(p: A => Boolean): Int = stl.CountIf(this, start, end, p)

    /**
     * Finds an element <code>p</code> meets.
     */
    def find(p: A => Boolean): Option[A] = {
        val i = stl.FindIf(this, start, end, p)
        if (i == end) {
            None
        } else {
            Some(this(i))
        }
    }

    /**
     * Folds left to right.
     */
    def foldLeft[B](z: B)(op: (B, A) => B): B = stl.Accumulate(this, start, end, z, op)

    @aliasOf("foldLeft")
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    /**
     * Reduces left to right.
     */
    def reduceLeft[B >: A](op: (B, A) => B): B = tail.foldLeft[B](head)(op)

    /**
     * Prefix sum folding left to right.
     */
    def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = FolderLeft(this, z, op)

    /**
     * Prefix sum reducing left to right.
     */
    def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = tail.folderLeft[B](head)(op)

    /**
     * Returns the first element.
     */
    def head: A = { throwIfEmpty("head"); this(start) }

    /**
     * Optionally returns the first element.
     */
    def headOption: Option[A] = FirstOption(this)

    /**
     * Returns all the elements without the first one.
     */
    def tail: Vector[A] = { throwIfEmpty("tail"); this(start + 1, end) }

    /**
     * Returns the last element.
     */
    def last: A = { throwIfEmpty("last"); this(end - 1) }

    /**
     * Optionally returns the last element.
     */
    def lastOption: Option[A] = LastOption(this)

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): Vector[A] = this(start, Math.min(start + n, end))

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): Vector[A] = this(Math.min(start + n, end), end)

    /**
     * @return  <code>drop(n).take(n - m)</code>.
     */
    def slice(n: Int, m: Int): Vector[A] = drop(n).take(m - n)

    /**
     * Takes elements while <code>p</code> meets.
     */
    def takeWhile(p: A => Boolean): Vector[A] = TakeWhile(this, p)

    /**
     * Drops elements while <code>p</code> meets.
     */
    def dropWhile(p: A => Boolean): Vector[A] = DropWhile(this, p)

    /**
     * @return  <code>(takeWhile(p), dropWhile(p))</code>.
     */
    def span(p: A => Boolean): (Vector[A], Vector[A]) = {
        val middle = stl.FindIf(this, start, end, function.not(p))
        (this(start, middle), this(middle, end))
    }

    /**
     * @return  <code>(take(n), drop(n))</code>.
     */
    def splitAt(i: Int): (Vector[A], Vector[A]) = {
        val middle = Math.min(start + i, end)
        (this(start, middle), this(middle, end))
    }

    /**
     * Returns the <code>n</code>-th element.
     */
    def at(n: Int): A = nth(n)

    /**
     * Does this contain the element?
     */
    def contains(e: Any): Boolean = exists(function.equalTo(e))

    /**
     * Repeats <code>n</code> times
     */
    def times(n: Int): Vector[A] = Times(this, n)

    /**
     * Cuts projection. (A result sequence is always readOnly.)
     */
    def force: Vector[A] = Force(this)

    /**
     * Returns a vector whose elements are lazy.
     */
    def memoize: Vector[A] = Memoize(this)

    /**
     * Transforms sequence-to-sequence expression `seq.f.g.h` to `seq.x.f.x.g.x.h`.
     */
    def mix(x: Mixin): Vector[A] = Mix(this, x)

    /**
     * Disables overrides.
     */
    def seal: Vector[A] = Seal(this)

    /**
     * Steps by the specified stride.
     */
    def step(n: Int): Vector[A] = Step(this, n)

//    @optimize override def _toVector[B](_this: Iterative[B]): Vector[B] = this.asInstanceOf[Vector[B]].readOnly // writable guarantee.

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: Vector[B]): Vector[(A, B)] = Zip(this, that)


// regions

    /**
     * Replaces <code>start</code> and <code>end</code> of this vector.
     * Note that a larger vector than <code>this</code> is ALLOWED as far as <code>isDefinedAt</code> says ok.
     *
     * @pre <code>start <= end</code>
     */
    def region(_start: Int, _end: Int): Vector[A] = Region(this, _start, _end)

    @returnThis
    def regionBase: Vector[A] = this

    @aliasOf("region")
    final def apply(_start: Int, _end: Int): Vector[A] = region(_start, _end)

    /**
     * @pre     <code>!isEmpty</code>
     * @return  <code>this(start, end - 1)</code>.
     */
    def init: Vector[A] = Init(this)

    /**
     * @return  <code>this(start, start)</code>
     */
    def clear: Vector[A] = Clear(this)

    /**
     * @return  <code>this(start + n, start + m)</code>.
     */
    def window(n: Int, m: Int): Vector[A] = Window(this, n, m)

    /**
     * @return  <code>this(start + i, end + j)</code>.
     */
    def offset(i: Int, j: Int): Vector[A] = Offset(this, i, j)

    /**
     * @return  <code>(regionBase eq that.regionBase) && (start == that.start) && (end == that.end)</code>.
     */
    def shallowEquals[B](that: Vector[B]): Boolean = (regionBase eq that.regionBase) && (start == that.start) && (end == that.end)


// division

    /**
     * Divides this vector into vector of Regions.
     * Each vector size is <code>n</code> except for the last one.
     *
     * @param   n   divisor
     * @return  <code>[this(start, n), this(start + n, 2*n), this(start + 2*n, 3*n),...]</code>.
     * @see     vector.undivide
     */
    def divide(n: Int): Vector[Vector[A]] = Divide(this, n)

    /**
     * @return  <code>span(function.not(p))</code>.
     */
    def break(p: A => Boolean): (Vector[A], Vector[A]) = span(function.not(p))


// filter

    def mutatingFilter(p: A => Boolean): Vector[A] = MutatingFilter(this, p)

    /**
     * @return  <code>mutatingFilter(function.not(p))</code>.
     */
    def mutatingRemove(p: A => Boolean): Vector[A] = mutatingFilter(function.not(p))


// map

    /**
     * Casts element to type <code>B</code>.
     */
    def asVectorOf[B]: Vector[B] = AsVectorOf[A, B](this)


// loop

    /**
     * Similar to <code>foreach</code>, but loop is breakable by <code>f</code> returning <code>false</code>.
     */
    def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = {
        var __first = i

        while (__first != j && f(this(__first))) {
            __first += 1
        }
        f
    }

    /**
     * Apply a function <code>f</code> to all elements of this vector.
     * <code>f</code> may be called in any order, and
     * there is no guarantee <code>f</code> calls are single-threaded.
     *
     * @param   f   a function that is applied to every element.
     */
    def each(f: A => Unit): Unit = foreach(f)


// search

    /**
     * Find and return the element of this vector satisfying a
     * predicate, if any. No guarantee the element is the first one.
     *
     * @param   p   the predicate
     * @return  the element in the iterable object satisfying
     *          <code>p</code>, or <code>None</code> if none exists.
     */
    def seek(p: A => Boolean): Option[A] = find(p)


// folding


    @aliasOf("elements.foldRight")
    final def foldRight[B](z: B)(op: (A, B) => B): B = reverse.foldLeft(z)(function.flip(op))

    /**
     * @return  <code>reverse.reduceLeft(function.flip(op))</code>.
     */
    final def reduceRight[B >: A](op: (A, B) => B): B = reverse.reduceLeft(function.flip(op))

    /**
     * @return  <code>reverse.folderLeft(z)(function.flip(op))</code>.
     */
    def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = reverse.folderLeft(z)(function.flip(op))

    /**
     * @return  <code>reverse.reducerLeft(function.flip(op))</code>.
     */
    def reducerRight[B >: A](op: (A, B) => B): Vector[B] = reverse.reducerLeft(function.flip(op))


// sort

    /**
     * @return  <code>sortBy(c)</code>.
     */
    final def sort(implicit c: Compare[A]): Vector[A] = Sort(this, c)

    /**
     * Sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt  strict weak ordering
     * @return  this vector sorted according to <code>lt</code>.
     */
    def sortBy(lt: compare.Func[A]): Vector[A] = SortBy(this, lt)

    /**
     * @return  <code>stableSortBy(c)</code>.
     */
    final def stableSort(implicit c: Compare[A]): Vector[A] = StableSort(this, c)

    /**
     * Stable sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt  strict weak ordering
     * @return  this vector sorted according to <code>lt</code>.
     */
    def stableSortBy(lt: compare.Func[A]): Vector[A] = StableSortBy(this, lt)


// permutation

    /**
     * Reorders using "0-to-size" index mapping <code>f</code>.
     */
    def permutation(f: Int => Int): Vector[A] = Permutation(this, f)

    /**
     * Turns this vector into "0-to-size" indexing vector.
     */
    def nth: Vector[A] = _nth
    private lazy val _nth: Vector[A] = Nth(this)

    /**
     * Reverses order of elements.
     */
    def reverse: Vector[A] = Reverse(this)

    /**
     * @return  <code>this(i, end) ++ this(start, i)</code>.
     */
    def rotate(i: Int): Vector[A] = Rotate(this, i)


// attributes

    /**
     * Creates a vector whose <code>isDefinedAt(i)</code> returns true
     * iif <code>start <= i && i < end</code>.
     */
    def bounds: Vector[A] = Bounds(this)

    /**
     * @return  a read only alias of this vector.
     */
    def readOnly: Vector[A] = ReadOnly(this)

    /**
     * Returns synchronized one.
     */
    def synchronize: Vector[A] = throw new Error("sync not yet")


// mixin

    /**
     * Reverts <code>mixin</code>.
     */
    def unmix: Vector[A] = this


// copy

    /**
     * Copies all the elements into another.
     *
     * @pre     <code>size == that.size</code>.
     * @pre     <code>that</code> is writable.
     * @return  this vector.
     */
    def copyTo[B >: A](that: Vector[B]): Vector[A] = CopyTo(this, that)

    /**
     * Returns a shallow copy of this vector. (The elements themselves are not copied.)
     *
     * @return  a writable clone of this vector.
     */
    def copy: Vector[A] = vector.fromArray(toArray)

    /**
     * @return  <code>writer(start)</code>
     */
    final def writer: Writer[A] = Writer(this)

    /**
     * @return  <code>new Writer(this, i)</code>
     */
    final def writer(i: Int): Writer[A] = Writer(this, i)


// parallel support

    /**
     * @return  <code>parallel(defaultGrainSize)</code>
     */
    final def parallel: Vector[A] = parallel(defaultGrainSize)

    /**
     * Requests a vector to perform parallel methods.
     */
    def parallel(grainSize: Int): Vector[A] = Parallel(this, grainSize)

    /**
     * Specifies the grain size, which is used to divide this vector in parallel methods.
     */
    def grainSize: Int = size

    /**
     * Specifies the default grain size.
     */
    def defaultGrainSize: Int = Math.max(1, size / function.parallels.POOL_SIZE)

    /**
     * Waits for parallel element calculations over.
     */
    final def join: Unit = foreach{ e => () }

    /**
     * @return  <code>divide(grainSize).parallel(1)</code>.
     */
    final def parallelRegions(grainSize: Int): Vector[Vector[A]] = divide(grainSize).parallel(1)


// associative folding

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>foldLeft(z)(op)</code>
     */
    def fold(z: A)(op: (A, A) => A): A = foldLeft(z)(op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>folderLeft(z)(op)</code>
     */
    def folder(z: A)(op: (A, A) => A): Vector[A] = folderLeft(z)(op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>reduceLeft(op)</code>
     */
    def reduce(op: (A, A) => A): A = reduceLeft(op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>reducerLeft(op)</code>
     */
    def reducer(op: (A, A) => A): Vector[A] = reducerLeft(op)


// conversions

    /**
     * @return  <code>vector.toArray(this)</code>.
     */
    final def toArray: Array[A] = vector.toArray(this)

    /**
     * @return  <code>vector.toJclArrayList(this)</code>.
     */
    final def toJclArrayList: java.util.ArrayList[A] = vector.toJclArrayList(this)

    /**
     * @return  <code>vector.toIterator</code>.
     */
    final def toLinearAccessSeq: Seq[A] = vector.toLinearAccessSeq(this)

    /**
     * @return  <code>vector.toList(this)</code>.
     */
    final def toList: scala.List[A] = vector.toList(this)

    /**
     * @return  <code>vector.toRandomAccessSeq(this)</code>.
     */
    final def toRandomAccessSeq: scala.collection.mutable.Vector[A] = vector.toRandomAccessSeq(this)


// trivials

    @returnThis
    final def asVector: Vector[A] = this

    /**
     * @return  <code>f(this); this</code>.
     */
    final def sideEffect(f: Vector[A] => Unit): Vector[A] = { f(this); this }

    /**
     * @return  <code>vector.range(start, end)</code>.
     */
    final def indices: Vector[Int] = vector.range(start, end)

    /**
     * Returns a set entry as pair, which is useful for <code>peg.switch</code>.
     *
     * @return  <code>(this, p)</code>
     */
    final def -->(p: Peg[A]): (Vector[A], Peg[A]) = (this, p)


// implementation helpers

    private def throwIfEmpty(method: String) = ThrowIf.empty(this, method)
}


object Vector extends vector.Compatibles {


// pattern matching

    @aliasOf("of")
    def apply[A](from: A*): Vector[A] = of(from: _*)

    def unapplySeq[A](from: Vector[A]): Option[Seq[A]] = Some(toRandomAccessSeq(from))


// operators

    sealed class MadaVectorEither[A, B](_1: Vector[Either[A, B]]) {
        def lefts = vector.lefts(_1)
        def rights = vector.rights(_1)
    }
    implicit def madaVectorEither[A, B](_1: Vector[Either[A, B]]): MadaVectorEither[A, B] = new MadaVectorEither(_1)

    sealed class MadaVectorVector[A](_1: Vector[Vector[A]]) {
        def undivide = vector.undivide(_1)
    }
    implicit def madaVectorVector[A](_1: Vector[Vector[A]]): MadaVectorVector[A] = new MadaVectorVector(_1)

    sealed class MadaVectorPair[A, B](_1: Vector[(A, B)]) {
        def unzip = vector.unzip(_1)
    }
    implicit def madaVectorPair[A, B](_1: Vector[(A, B)]): MadaVectorPair[A, B] = new MadaVectorPair(_1)

    // remove me.
    sealed class MadaVectorByName[A](_1: => Vector[A]) {
        def byLazy = vector.byLazy(_1)
    }
    implicit def madaVectorByName[A](_1: => Vector[A]): MadaVectorByName[A] = new MadaVectorByName(_1)

    sealed class MadaVectorChar(_1: Vector[Char]) {
        def lowerCase = vector.lowerCase(_1)
        def upperCase = vector.upperCase(_1)
        def stringize = vector.stringize(_1)
    }
    implicit def madaVectorChar(_1: Vector[Char]): MadaVectorChar = new MadaVectorChar(_1)


// eligibles

    // Hmm, Ordering should have taken [-A]?
    implicit def forOrdering[A](implicit c: Ordering[A]): Ordering[Vector[A]] = {
        forCompare(compare.fromOrdering(c)).toOrdering
    }

    /*implicit*/ def forOrdering_[A](implicit c: compare.GetOrdered[A]): Ordering[Vector[A]] = {
        forCompare(compare.fromGetOrdered(c)).toOrdering
    }

    // For unambiguous overload resolution, `implicit` is facing the alternative
    // of `madaVectorToOrdered` or ...
    /*implicit*/ def forCompare[A](implicit c: Compare[A]): Compare[Vector[A]] = new Compare[Vector[A]] {
        override def apply(v: Vector[A], w: Vector[A]) = {
            stl.LexicographicalCompare(v, v.start, v.end, w, w.start, w.end, c)
        }
        override def threeWay(v: Vector[A], w: Vector[A]) = {
            stl.LexicographicalCompare3way(v, v.start, v.end, w, w.start, w.end, c)
        }
    }

}
