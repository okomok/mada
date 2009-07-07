

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


    override def asVector: Vector[A] = this


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
    override def apply(i: Int): A = throw NotReadableException(this)

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
    def update(i: Int, e: A): Unit = throw NotWritableException(this)

    /**
     * @return  <code>(start <= i) && (i < end)</code>, possibly overridden in subclasses.
     */
    override def isDefinedAt(i: Int): Boolean = (start <= i) && (i < end)


// iterative

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

    @equivalentTo("start == end")
    def isEmpty: Boolean = start == end

    /**
     * Returns the size.
     */
    def size: Int = end - start

    /**
     * Appends <code>that</code>.
     */
    def append(that: Vector[A]): Vector[A] = Append(this, that)

    @aliasOf("append")
    final def ++(that: Vector[A]): Vector[A] = append(that)

    /**
     * Maps elements using <code>f</code>.
     */
    def map[B](f: A => B): Vector[B] = Map(this, f)

    /**
     * Applies <code>f</code> to each element.
     */
    def foreach(f: A => Unit): Unit = stl.ForEach(this, start, end, f)

    /**
     * Determines if all the elements satisfy the predicate.
     */
    def forall(p: A => Boolean): Boolean = seek(function.not(p)).isEmpty

    /**
     * Determines if any element satisfies the predicate.
     */
    def exists(p: A => Boolean): Boolean = !seek(p).isEmpty

    /**
     * Counts elements which satisfy the predicate.
     */
    def count(p: A => Boolean): Int = stl.CountIf(this, start, end, p)

    /**
     * Finds an element which satisfies the predicate.
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
     * Folds left-associative.
     */
    def foldLeft[B](z: B)(op: (B, A) => B): B = stl.Accumulate(this, start, end, z, op)

    @aliasOf("foldLeft")
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    /**
     * Reduces left-associative.
     */
    def reduceLeft[B >: A](op: (B, A) => B): B = tail.foldLeft[B](head)(op)

    /**
     * Returns the first element.
     */
    def head: A = {
        Precondition.notEmpty(this, "head")
        this(start)
    }

    /**
     * Optionally returns the first element.
     */
    def headOption: Option[A] = if (isEmpty) None else Some(this(start))

    /**
     * Returns all the elements without the first one.
     */
    def tail: Vector[A] = {
        Precondition.notEmpty(this, "tail")
        this(start + 1, end)
    }

    /**
     * Returns the last element.
     */
    def last: A = {
        Precondition.notEmpty(this, "last")
        this(end - 1)
    }

    /**
     * Optionally returns the last element.
     */
    def lastOption: Option[A] = if (isEmpty) None else Some(this(end - 1))

    /**
     * Takes at most <code>n</code> elements.
     */
    def take(n: Int): Vector[A] = Take(this, n)

    /**
     * Drops at most <code>n</code> elements.
     */
    def drop(n: Int): Vector[A] = Drop(this, n)

    @equivalentTo("drop(n).take(n - m)")
    def slice(n: Int, m: Int): Vector[A] = Slice(this, n, m)

    /**
     * Returns the longest prefix that satisfies the predicate.
     */
    def takeWhile(p: A => Boolean): Vector[A] = TakeWhile(this, p)

    /**
     * Returns the remaining suffix of <code>takeWhile</code>.
     */
    def dropWhile(p: A => Boolean): Vector[A] = DropWhile(this, p)

    @equivalentTo("(takeWhile(p), dropWhile(p))")
    def span(p: A => Boolean): (Vector[A], Vector[A]) = {
        val middle = stl.FindIf(this, start, end, function.not(p))
        (this(start, middle), this(middle, end))
    }

    @equivalentTo("(take(n), drop(n))")
    def splitAt(i: Int): (Vector[A], Vector[A]) = {
        val middle = Math.min(start + i, end)
        (this(start, middle), this(middle, end))
    }

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
    final def seal: Vector[A] = Seal(this)

    /**
     * Steps by the specified stride.
     */
    def step(n: Int): Vector[A] = Step(this, n)

    /**
     * Zips <code>this</code> and <code>that</code>.
     */
    def zip[B](that: Vector[B]): Vector[(A, B)] = Zip(this, that)

    /**
     * Reverts <code>zip</code>.
     */
    def _unzip[B, C](_this: Vector[(B, C)]): (Vector[B], Vector[C]) = (_this.map{ bc => bc._1 }, _this.map{ bc => bc._2 })

    /**
     * Zips <code>this</code> and <code>that</code> applying <code>f</code>.
     */
    def zipBy[B, C](that: Vector[B])(f: (A, B) => C): Vector[C] = ZipBy(this, that, f)

    /**
     * Installs auto relation.
     */
    final def using(as: Auto[_]*): Auto[Vector[A]] = auto.usedWith(as, this)


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

    @equivalentTo("this(start, start)")
    def clear: Vector[A] = Clear(this)

    @equivalentTo("this(start + n, start + m)")
    def window(n: Int, m: Int): Vector[A] = Window(this, n, m)

    @equivalentTo("this(start + i, end + j)")
    def offset(i: Int, j: Int): Vector[A] = Offset(this, i, j)

    @equivalentTo("(regionBase eq that.regionBase) && (start == that.start) && (end == that.end)")
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
     * Reverts <code>divide</code>.
     *
     * @pre     each vector is the same size except for the last one.
     */
    @methodized
    def _undivide[B](_this: Vector[Vector[B]]): Vector[B] = Undivide(_this)

    @equivalentTo("span(function.not(p))")
    def break(p: A => Boolean): (Vector[A], Vector[A]) = span(function.not(p))


// filter

    def mutatingFilter(p: A => Boolean): Vector[A] = this(start, stl.RemoveIf(this, start, end, function.not(p)))

    @equivalentTo("mutatingFilter(function.not(p))")
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


    /**
     * Folds right-associative.
     */
    def foldRight[B](z: B)(op: (A, B) => B): B = reverse.foldLeft(z)(function.flip(op))

    /**
     * Reduces right-associative.
     */
    def reduceRight[B >: A](op: (A, B) => B): B = reverse.reduceLeft(function.flip(op))


// sort

    @equivalentTo("sortBy(c)")
    def sort(implicit c: Compare[A]): Vector[A] = sortBy(c)

    /**
     * Sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt  strict weak ordering
     * @return  this vector sorted according to <code>lt</code>.
     */
    def sortBy(lt: compare.Func[A]): Vector[A] = { stl.Sort(this, start, end, lt); this }

    @equivalentTo("stableSortBy(c)")
    def stableSort(implicit c: Compare[A]): Vector[A] = stableSortBy(c)

    /**
     * Stable sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt  strict weak ordering
     * @return  this vector sorted according to <code>lt</code>.
     */
    def stableSortBy(lt: compare.Func[A]): Vector[A] = { stl.StableSort(this, start, end, lt); this }


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

    @equivalentTo("this(i, end) ++ this(start, i)")
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


// copy

    /**
     * Returns a shallow copy of this vector. (The elements themselves are not copied.)
     *
     * @return  a writable copy of this vector.
     */
    def copy: Vector[A] = Copy(this)

    /**
     * Copies all the elements into another.
     *
     * @pre     <code>size <= that.size</code>.
     * @pre     <code>that</code> is writable.
     */
    def copyTo[B >: A](that: Vector[B]): Vector[B] = {
        Precondition.range(this.size, that.size, "copyTo")
        that(that.start, stl.Copy(this, start, end, that, that.start))
    }


// parallel support

    @equivalentTo("parallel(defaultGrainSize)")
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
    def defaultGrainSize: Int = Math.max(1, size / util.Parallels.poolSize)

    @equivalentTo("mix(Mixin.parallel)")
    final def parallelize: Vector[A] = mix(Mixin.parallel)

    /**
     * Makes parallel map computation join.
     */
    final def join: Vector[A] = { foreach{ _ => () }; this }


// associative folding

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>foldLeft(z)(op)</code>.
     */
    def fold(z: A)(op: (A, A) => A): A = foldLeft(z)(op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>reduceLeft(op)</code>.
     */
    def reduce(op: (A, A) => A): A = reduceLeft(op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>asIterative.folderLeft(z)(op).toVector</code>.
     */
    def folder(z: A)(op: (A, A) => A): Vector[A] = Folder(this, z, op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>asIterative.reducerLeft(op).toVector</code>.
     */
    def reducer(op: (A, A) => A): Vector[A] = Reducer(this, op)


// conversion

    @conversion
    def toArray: Array[A] = {
        val r = new Array[A](size)
        copyTo(fromArray(r))
        r
    }

    @conversion
    def toProduct: Product = new ToProduct(this)

    @conversion
    def toOrdered(implicit c: compare.GetOrdered[A]): Ordered[Vector[A]] = ToOrdered(this, c)

    @conversion
    def toSVector: scala.collection.mutable.Vector[A] = ToSVector(this)

    @conversion
    def toJList: java.util.List[A] = ToJList(this)


// string

    @methodized @conversion
    def _stringize(_this: Vector[Char]): String = {
        val sb = new StringBuilder(size)
        foreach{ e => sb.append(e) }
        sb.toString
    }

    @methodized @conversion
    def _lowerCase(_this: Vector[Char]): Vector[Char] = LowerCase(_this)

    @methodized @conversion
    def _upperCase(_this: Vector[Char]): Vector[Char] = UpperCase(_this)

    @methodized @conversion
    def _toJCharSequence(_this: Vector[Char]): java.lang.CharSequence = ToJCharSequence(_this)


// trivials

    @equivalentTo("f(this); this")
    final def sideEffect(f: Vector[A] => Unit): Vector[A] = { f(this); this }

    @equivalentTo("range(start, end)")
    final def indices: Vector[Int] = range(start, end)

}


object Vector {


// compatibles

    implicit def _ustringize(from: String): Vector[Char] = unstringize(from)
    implicit def _fromArray[A](from: Array[A]): Vector[A] = fromArray(from)
    implicit def _fromCell[A](from: Cell[A]): Vector[A] = fromCell(from)
    implicit def _fromOption[A](from: Option[A]): Vector[A] = fromOption(from)
    implicit def _fromProduct(from: Product): Vector[Any] = fromProduct(from)
    implicit def _fromSVector[A](from: scala.collection.Vector[A]): Vector[A] = fromSVector(from)
    implicit def _fromJCharSequence(from: java.lang.CharSequence): Vector[Char] = fromJCharSequence(from)
    implicit def _fromJList[A](from: java.util.List[A]): Vector[A] = fromJList(from)

    implicit def _fromJByteBuffer(from: java.nio.ByteBuffer): Vector[Byte] = fromJByteBuffer(from)
    implicit def _fromJCharBuffer(from: java.nio.CharBuffer): Vector[Char] = fromJCharBuffer(from)
    implicit def _fromJDoubleBuffer(from: java.nio.DoubleBuffer): Vector[Double] = fromJDoubleBuffer(from)
    implicit def _fromJFloatBuffer(from: java.nio.FloatBuffer): Vector[Float] = fromJFloatBuffer(from)
    implicit def _fromJIntBuffer(from: java.nio.IntBuffer): Vector[Int] = fromJIntBuffer(from)
    implicit def _fromJLongBuffer(from: java.nio.LongBuffer): Vector[Long] = fromJLongBuffer(from)
    implicit def _fromJShortBuffer(from: java.nio.ShortBuffer): Vector[Short] = fromJShortBuffer(from)


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


// pattern matching

    @aliasOf("Of.apply")
    def apply[A](from: A*): Vector[A] = Of.apply(from: _*)

    @aliasOf("Of.unapplySeq")
    def unapplySeq[A](from: Vector[A]): Option[Seq[A]] = Of.unapplySeq(from)

}
