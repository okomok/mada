

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains implicit conversions around <code>Vector</code>.
 */
object Vector {
    /**
     * @return  <code>Vectors.fromArray(from)</code>.
     */
    implicit def array2madaVector[A](from: Array[A]): Vector[A] = Vectors.fromArray(from)

    /**
     * @return  <code>Vectors.fromCell(from)</code>.
     */
    implicit def cell2madaVector[A](from: Cell[A]): Vector[A] = Vectors.fromCell(from)

    /**
     * @return  <code>Vectors.fromJclCharSequence(from)</code>.
     */
    implicit def JclCharSequence2madaVector(from: java.lang.CharSequence): Vector[Char] = Vectors.fromJclCharSequence(from)

    /**
     * @return  <code>Vectors.fromJclList(from)</code>.
     */
    implicit def jclList2madaVector[A](from: java.util.List[A]): Vector[A] = Vectors.fromJclList(from)

    /**
     * @return  <code>Vectors.fromOption(from)</code>.
     */
    implicit def option2madaVector[A](from: Option[A]): Vector[A] = Vectors.fromOption(from)

    /**
     * @return  <code>Vectors.fromProduct(from)</code>.
     */
    implicit def product2madaVector(from: Product): Vector[Any] = Vectors.fromProduct(from)

    /**
     * @return  <code>Vectors.fromRandomAccessSeq(from)</code>.
     */
    implicit def randomAccessSeq2madaVector[A](from: RandomAccessSeq[A]): Vector[A] = Vectors.fromRandomAccessSeq(from)

    /**
     * @return  <code>Vectors.fromString(from)</code>.
     */
    implicit def string2madaVector(from: String): Vector[Char] = Vectors.fromString(from)

    /**
     * @return  <code>Vectors.toIterator(from)</code>.
     */
    implicit def madaVector2Iterator[A](from: Vector[A]): Iterator[A] = Vectors.toIterator(from)

    /**
     * @return  <code>Vectors.toJclCharSequence(from)</code>.
     */
    implicit def madaVector2JclCharSequence(from: Vector[Char]): java.lang.CharSequence = Vectors.toJclCharSequence(from)

    /**
     * @return  <code>Vectors.toRandomAccessSeq(from)</code>.
     */
    implicit def madaVector2RandomAccessSeq[A](from: Vector[A]): RandomAccessSeq.Mutable[A] = Vectors.toRandomAccessSeq(from)
}


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
trait Vector[A] extends PartialFunction[Int, A] with HashCode.OfRef {
    import vec._


// kernel interface

    /**
     * @return  start index of this vector, which is NOT guaranteed to be <code>0</code>.
     */
    def start: Int

    /**
     * @return  end index of this vector, which is NOT guaranteed to be <code>size</code>.
     */
    def end: Int

    /**
     * @param   i index of element to return.
     * @pre     this vector is readable.
     * @pre     <code>isDefinedAt(i)</code>
     * @return  the element at the specified position in this vector.
     * @throws  Vectors.NotReadableException if not overridden.
     */
    override def apply(i: Int): A = throw new Vectors.NotReadableException(this)

    /**
     * Replaces the element at the specified position in this vector with
     * the specified element.
     *
     * @param   i index of element to replace.
     * @param   e element to be stored at the specified position.
     * @pre     this vector is writable.
     * @pre     <code>isDefinedAt(i)</code>
     * @throws  Vectors.NotWritableException if not overridden.
     */
    def update(i: Int, e: A): Unit = throw new Vectors.NotWritableException(this)

    /**
     * @return  <code>(start <= i) && (i < end)</code>, possibly overridden in subclasses.
     */
    override def isDefinedAt(i: Int): Boolean = (start <= i) && (i < end)


// value semantics

    /**
     * Compares each element using predicate <code>p</code>.
     * Returns <code>false</code> if <code>size != that.size</code>.
     */
    def equalsWith[B](that: Vector[B])(p: Functions.Predicate2[A, B]): Boolean = EqualsWith(this, that, p)

    /**
     * Vector has value semantics <code>==</code>.
     *
     * @return  <code>equalsWith(that)(Functions.equal)</code>.
     */
    override def equals(that: Any): Boolean = Equals(this, that)

    /**
     * @return  <code>equalsWith(that)(Functions.equal)</code>.
     */
    final def equalsTo[B](that: Vector[B]): Boolean = equalsWith(that)(Functions.equal)

    /**
     * Generates hash code as value semantics.
     */
    override def hashCode: Int = HashCode_(this)

    final override def hashCodeOfRef: Int = super.hashCode


// regions

    /**
     * @return  <code>Region(this, _start, _end)</code>.
     * @see     apply as alias.
     */
    def region(_start: Int, _end: Int): Vector[A] = Region(this, _start, _end)

    /**
     * @pre     <code>!isEmpty</code>
     * @return  <code>this(start, end - 1)</code>.
     */
    def init: Vector[A] = this(start, end - 1)

    /**
     * @return  <code>this(start, start)</code>
     */
    def clear: Vector[A] = this(start, start)

    /**
     * @return  <code>this(start + n, start + m)</code>.
     */
    def window(n: Int, m: Int): Vector[A] = this(start + n, start + m)

    /**
     * @return  <code>this(start + i, end + j)</code>.
     */
    def offset(i: Int, j: Int): Vector[A] = this(start + i, end + j)

    /**
     * @return  <code>slice(n, end)</code>.
     */
    def slice(n: Int): Vector[A] = slice(n, end)

    /**
     * @return <code>drop(n).take(m - n)</code>.
     */
    def slice(n: Int, m: Int): Vector[A] = drop(n).take(m - n)

    /**
     * @return <code>this(Math.min(start + n, end), end)</code>.
     */
    def drop(n: Int): Vector[A] = this(Math.min(start + n, end), end)

    /**
     * @return <code>this(start, Math.min(start + n, end))</code>.
     */
    def take(n: Int): Vector[A] = this(start, Math.min(start + n, end))

    /**
     * Returns the longest suffix of this vector whose first element
     * does not satisfy the predicate <code>p</code>.
     *
     * @param   p the test predicate.
     * @return  the longest suffix of the vector whose first element
     *          does not satisfy the predicate <code>p</code>.
     */
    def dropWhile(p: A => Boolean): Vector[A] = DropWhile(this, p)

    /**
     * Returns the longest prefix of this vector whose elements satisfy
     * the predicate <code>p</code>.
     *
     * @param   p the test predicate.
     * @return  the longest prefix of this vector whose elements satisfy
     *          the predicate <code>p</code>.
     */
    def takeWhile(p: A => Boolean): Vector[A] = TakeWhile(this, p)


// division

    /**
     * Divides this vector into vector of Regions.
     * Each vector size is <code>n</code> except for the last one.
     *
     * @param   n divisor
     * @return  <code>[this(start, n), this(start + n, 2*n), this(start + 2*n, 3*n),...]</code>.
     * @see     Vectors.undivide
     */
    def divide(n: Int): Vector[Vector[A]] = Divide(this, n)

    /**
     * @return  <code>(this(start, m), this(m, end))</code>, where <code>val m = Math.min(i, end)</code>.
     */
    def splitAt(i: Int): (Vector[A], Vector[A]) = SplitAt(this, i)

    /**
     * Returns the longest prefix of the vector whose elements all satisfy
     * the given predicate, and the rest of the vector.
     *
     * @param   p the test predicate
     * @return  a pair consisting of the longest prefix of the vector whose
     *          elements all satisfy <code>p</code>, and the rest of the vector.
     */
    def span(p: A => Boolean): (Vector[A], Vector[A]) = Span(this, p)

    /**
     * @return  <code>span(Functions.not(p))</code>.
     */
    def break(p: A => Boolean): (Vector[A], Vector[A]) = span(Functions.not(p))


// first and last

    /**
     * @return  <code>this(start)</code>.
     */
    final def first: A = { throwIfEmpty("first"); this(start) }

    /**
     * @return  <code>this(end - 1)</code>.
     */
    final def last: A = { throwIfEmpty("last"); this(end - 1) }

    /**
     * Alias of <code>randomAccessSeq.firstOption</code>
     */
    final def firstOption: Option[A] = FirstOption(this)

    /**
     * Alias of <code>randomAccessSeq.lastOption</code>
     */
    final def lastOption: Option[A] = LastOption(this)


// as list

    /**
     * Alias of <code>first</code>
     */
    final def head: A = { throwIfEmpty("head"); first }

    /**
     * @return  <code>this(start + 1, end)</code>
     */
    def tail: Vector[A] = { throwIfEmpty("tail"); this(start + 1, end) }

    /**
     * Alias of <code>isEmpty</code>
     */
    final def isNil: Boolean = isEmpty


// filter

    /**
     * Returns all the elements of this vector that satisfy the
     * predicate <code>p</code>. The order of the elements is preserved.
     *
     * @param   p the predicate used to filter the vector.
     * @return  the non-writable elements of this vector satisfying <code>p</code>.
     */
    def filter(p: A => Boolean): Vector[A] = Filter(this, p)

    /**
     * Returns all the elements of this vector that satisfy the
     * predicate <code>p</code>. The order of the elements is preserved.
     * Unlike <code>filter</code>, this requires no intermediate buffer,
     * but the state of this vector is unpredictable after calling this.
     *
     * @param   p the predicate used to filter the vector.
     * @return  the elements of this vector satisfying <code>p</code>.
     */
    def mutatingFilter(p: A => Boolean): Vector[A] = MutatingFilter(this, p)

    /**
     * @return  <code>filter(Functions.not(p))</code>.
     */
    def remove(p: A => Boolean): Vector[A] = filter(Functions.not(p))

    /**
     * @return  <code>mutatingFilter(Functions.not(p))</code>.
     */
    def mutatingRemove(p: A => Boolean): Vector[A] = mutatingFilter(Functions.not(p))

    /**
     * @return  <code>(filter(p), remove(p))</code>.
     */
    def partition(p: A => Boolean): (Vector[A], Vector[A]) = (filter(p), remove(p))


// map

    /**
     * Returns the vector resulting from applying the given function <code>f</code>
     * to each element of this vector.
     */
    def map[B](f: A => B): Vector[B] = Map(this, f)

    /**
     * @return  <code>Vectors.flatten(map(f))</code>.
     */
    def flatMap[B](f: A => Vector[B]): Vector[B] = Vectors.flatten(map(f))

    /**
     * Casts element to type <code>B</code>.
     */
    def asVectorOf[B]: Vector[B] = AsVectorOf[A, B](this)


// foreach

    /**
     * Similar to <code>foreach</code>, but loop is breakable by <code>f</code> returning <code>false</code>.
     */
    def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = Loop(this, i, j, f)

    /**
     * Alias of <code>elements.foreach</code>.
     */
    final def foreach(f: A => Unit): Unit = Foreach(this, f)

    /**
     * Apply a function <code>f</code> to all elements of this vector.
     * <code>f</code> may be called in any order, and
     * there is no guarantee <code>f</code> calls are single-threaded.
     *
     * @param   f a function that is applied to every element.
     */
    def pareach(f: A => Unit): Unit = foreach(f)


// search

    /**
     * Alias of <code>elements.find</code>
     */
    final def find(p: A => Boolean): Option[A] = Find(this, p)

    /**
     * Find and return the element of this vector satisfying a
     * predicate, if any. No guarantee the element is the first one.
     *
     * @param   p the predicate
     * @return  the element in the iterable object satisfying
     *          <code>p</code>, or <code>None</code> if none exists.
     */
    def seek(p: A => Boolean): Option[A] = find(p)

    /**
     * Alias of <code>randomAccessSeq.count</code>
     */
    def count(p: A => Boolean): Int = Count(this, p)

    /**
     * @return  <code>exists(Funtions.equalTo(e))</code>.
     */
    final def contains(e: Any): Boolean = exists(Functions.equalTo(e))

    /**
     * @return  <code>seek(Functions.not(p)).isEmpty</code>.
     */
    final def forall(p: A => Boolean): Boolean = seek(Functions.not(p)).isEmpty

    /**
     * @return  <code>!seek(p).isEmpty</code>.
     */
    final def exists(p: A => Boolean): Boolean = !seek(p).isEmpty


// folding

    /**
     * Alias of <code>elements.foldLeft</code>
     */
    final def foldLeft[B](z: B)(op: (B, A) => B): B = FoldLeft(this, z, op)

    /**
     * Alias of <code>elements.foldRight</code>
     */
    final def foldRight[B](z: B)(op: (A, B) => B): B = reverse.foldLeft(z)(Functions.flip(op))

    /**
     * @return  <code>tail.foldLeft[B](head)(op)</code>.
     */
    final def reduceLeft[B >: A](op: (B, A) => B): B = tail.foldLeft[B](head)(op)

    /**
     * @return  <code>reverse.reduceLeft(Functions.flip(op))</code>.
     */
    final def reduceRight[B >: A](op: (A, B) => B): B = reverse.reduceLeft(Functions.flip(op))

    /**
     * Returns the prefix sum of this vector.
     *
     * @return  <code>[z, op(z, this(start)), op(op(z, this(start)), this(start + 1)),...]</code>.
     */
    def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = FolderLeft(this, z, op)

    /**
     * @return  <code>reverse.folderLeft(z)(Functions.flip(op))</code>.
     */
    def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = reverse.folderLeft(z)(Functions.flip(op))

    /**
     * @return  <code>tail.folderLeft(head)</code>.
     */
    def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = tail.folderLeft[B](head)(op)

    /**
     * @return  <code>reverse.reducerLeft(Functions.flip(op))</code>.
     */
    def reducerRight[B >: A](op: (A, B) => B): Vector[B] = reverse.reducerLeft(Functions.flip(op))


// sort

    /**
     * Sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt the "less-than" comparison function
     * @return  this vector sorted according to the comparison function <code>lt</code>.
     */
    def sortWith(lt: Functions.Compare[A]): Vector[A] = SortWith(this, lt)

    /**
     * @return  <code>sortWith(Functions.less(c))</code>.
     */
    def sort(implicit c: A => Ordered[A]): Vector[A] = sortWith(Functions.less(c))


// concatenation

    /**
     * @return  <code>Vectors.fromRandomAccessSeq(randomAccessSeq.projection ++ that)</code>
     */
    def append(that: Vector[A]): Vector[A] = Append(this, that)


// permutation

    /**
     * Reorders using "0-to-size" index mapping <code>f</code>.
     */
    def permutation(f: Int => Int): Vector[A] = Permutation(this, f)

    /**
     * Returns a non-writable circular vector from this vector.
     *
     * @return  <code>[this(start),...,this(end - 1),this(start),...,this(end - 1),...n times...]</code>
     */
    def cycle(n: Int): Vector[A] = Cycle(this, n)

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
     * Returns steps with specified stride <code>n</code>.
     *
     * @return  <code>[this(start), this(start + n), this(start + 2*n), ...]</code>.
     */
    def step(n: Int): Vector[A] = Step(this, n)

    /**
     * @return  <code>this(i, end) ++ this(start, i)</code>.
     */
    def rotate(i: Int): Vector[A] = this(start + i, end) ++ this(start, start + i)


// zip

    /**
     * Returns a vector formed from this vector and the specified vector
     * <code>that</code> by associating each element of the former with
     * the element at the same position in the latter.
     *  If one of the two vectors is longer than the other, its remaining elements are ignored.
     *
     * @return  <code>[(this(start), that(that.start)), (this(start + 1), that(that.start + 1)), (this(start + 2), that(that.start + 2)), ...]</code>.
     */
    def zip[B](that: Vector[B]): Vector[(A, B)] = Zip(this, that)


// attributes

    /**
     * Converts to a strict collection.
     *
     * @return  non-writable vector.
     */
    def force: Vector[A] = Force(this)

    /**
     * Returns a vector whose elements are lazy.
     */
    def lazyValues: Vector[A] = LazyValues(this)

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
     * @return  an alias of this vector.
     */
    def identity: Vector[A] = Identity(this)

    /**
     * @return  an alias of this vector, but any override is turned off.
     */
    final def cut: Vector[A] = Cut(this)


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
    override def clone: Vector[A] = Clone(this)

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
    final def parallel: Vector[A] = Parallel(this)

    /**
     * Requests a vector to perform parallel methods.
     */
    final def parallel(grainSize: Int): Vector[A] = Parallel(this, grainSize)

    /**
     * Reverts <code>parallel</code>.
     */
    def unparallel: Vector[A] = this

    /**
     * Specifies the grain size, which is used to divide this vector in parallel methods.
     */
    def grainSize: Int = size

    /**
     * Specifies the default grain size.
     */
    def defaultGrainSize: Int = DefaultGrainSize(this)

    /**
     * Is this vector methods possibly performing in parallel?
     */
    def isParallel: Boolean = false

    /**
     * Waits for parallel element calculations over.
     */
    def join: Unit = Join(this)


// parallel folding

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

    override def toString: String = ToString(this)

    /**
     * @return  <code>Vectors.toArray(this)</code>.
     */
    final def toArray: Array[A] = Vectors.toArray(this)

    /**
     * Alias of <code>elements</code>
     */
    final def toIterator: Iterator[A] = Vectors.toIterator(this)

    /**
     * @return  <code>Vectors.toJclArrayList(this)</code>.
     */
    final def toJclArrayList: java.util.ArrayList[A] = Vectors.toJclArrayList(this)

    /**
     * @return  <code>Vectors.toIterator</code>.
     */
    final def toLinearAccessSeq: Seq[A] = Vectors.toLinearAccessSeq(this)

    /**
     * @return  <code>Vectors.toList(this)</code>.
     */
    final def toList: List[A] = Vectors.toList(this)

    /**
     * @return  <code>Vectors.toRandomAccessSeq(this)</code>.
     */
    final def toRandomAccessSeq: RandomAccessSeq.Mutable[A] = Vectors.toRandomAccessSeq(this)

    /**
     * @return  <code>Vectors.toStream(this)</code>.
     */
    final def toStream: Stream[A] = Vectors.toStream(this)


// trivials

    /**
     * @return  <code>this</code>.
     */
    final def vector: Vector[A] = this

    /**
     * @return  <code>start == end</code>.
     */
    final def isEmpty: Boolean = start == end

    /**
     * @return  <code>end - start</code>.
     */
    final def size: Int = end - start

    /**
     * @return  <code>that</code>.
     */
    final def always[B](that: Vector[B]): Vector[B] = that

    /**
     * @return  <code>f(this); this</code>.
     */
    final def sideEffect(f: Vector[A] => Any): Vector[A] = { f(this); this }

    /**
     * @return  <code>Vectors.range(start, end)</code>.
     */
    final def indices: Vector[Int] = Vectors.range(start, end)

    /**
     * Returns a set entry as pair, which is useful for <code>Peg.switch</code>.
     *
     * @return  <code>(this, p)</code>
     */
    final def -->(p: Peg[A]): (Vector[A], Peg[A]) = (this, p)


// aliases

    /**
     * Alias of <code>size</code>
     */
    final def length: Int = size

    /**
     * Alias of <code>toIterator</code>
     */
    final def elements: Iterator[A] = toIterator

    /**
     * Alias of <code>region</code>
     */
    final def apply(_start: Int, _end: Int): Vector[A] = region(_start, _end)

    /**
     * Alias of <code>append</code>
     */
    final def ++(that: Vector[A]): Vector[A] = append(that)

    /**
     * Alias of <code>foldLeft</code>
     */
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    /**
     * Alias of <code>foldRight</code>
     */
    final def :\[B](z: B)(op: (A, B) => B): B = foldRight(z)(op)


// implementation helpers

    private def throwIfEmpty(method: String) = ThrowIf.empty(this, method)
}
