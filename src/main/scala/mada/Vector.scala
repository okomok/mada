

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import vec._


/**
 * Contains utility types and methods operating on <code>Vector</code>.
 */
object Vector extends Conversions with Compatibles with Eligibles with Operators {


// constants

    /**
     * @return  <code>Math.MIN_INT</code>, which is the reserved index by <code>mada.Vector</code>.
     */
    final val SINGULAR = 0x80000000


// exceptions

    /**
     * Thrown if vector is not readable.
     */
    class NotReadableException[A](val vector: Vector[A]) extends RuntimeException

    /**
     * Thrown if vector is not writable.
     */
    class NotWritableException[A](val vector: Vector[A]) extends RuntimeException


// pattern matching

    /**
     * Alias of <code>fromValues</code>
     */
    def apply[A](from: A*): Vector[A] = fromValues(from: _*)

    /**
     * Supports pattern matching.
     */
    def unapplySeq[A](from: Vector[A]): Option[Seq[A]] = Some(toRandomAccessSeq(from))


// constructors

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Vector[A]): Vector[A] = to

    /**
     * Concatenate all argument sequences into a single vector.
     *
     * @param   vs  the given argument sequences
     * @return  the projection vector created from the concatenated arguments
     */
    def concat[A](vs: Vector[A]*): Vector[A] = Concat(vs: _*)

    /**
     * @return  an empty vector.
     */
    def empty[A]: Vector[A] = Empty.apply[A]

    /**
     * Create a vector that is the concantenation of all vectors
     * returned by a given vector of vectors.
     *
     * @param   vs  The iterator which returns on each call to next
     *              a new vector whose elements are to be concatenated to the result.
     * @return  the newly created writable vector (not a projection into <code>vs</code>).
     */
    def flatten[A](vs: Iterable[Vector[A]]): Vector[A] = Flatten(vs)

    /**
     * @return  <code>v.filter(_.isLeft).map(_.left.get)</code>.
     */
    def lefts[A, B](v: Vector[Either[A, B]]): Vector[A] = v.filter(_.isLeft).map(_.left.get)

    /**
     * @return  <code>v.filter(_.isRight).map(_.right.get)</code>.
     */
    def rights[A, B](v: Vector[Either[A, B]]): Vector[B] = v.filter(_.isRight).map(_.right.get)

    /**
     * Creates a vector containing of successive integers.
     *
     * @param   i   the value of the first element of the vector
     * @param   j   the value of the last element of the vector plus 1
     * @return  the sorted vector of all integers in range [i, j).
     */
    def range(i: Int, j: Int): Vector[Int] = Range(i, j)

    /**
     * @return  <code>range(i, Math.MAX_INT)</code>.
     */
    def range(i: Int, u: Unit): Vector[Int] = Range(i, Math.MAX_INT)

    /**
     * @param   e   the element
     * @return  the writable vector with one single element.
     */
    def single[A](e: A): Vector[A] = Single(e)

    /**
     * Reverts <code>divide</code>.
     *
     * @pre     each vector is the same size except for the last one.
     */
    def undivide[A](vv: Vector[Vector[A]]): Vector[A] = Undivide(vv)

    /**
     * Flattens <code>vs</code>, each vector appending <code>sep</code> except the last one.
     */
    def unsplit[A](vs: Iterable[Vector[A]])(sep: Vector[A]): Vector[A] = Unsplit(vs, sep)

    /**
     * Reverts <code>zip</code>.
     */
    def unzip[A, B](v: Vector[(A, B)]): (Vector[A], Vector[B]) = Unzip(v)

    /**
     * Creates a <code>lazy</code> vector.
     */
    def `lazy`[A](v: => Vector[A]) = Lazy(v)


// triplify

    /**
     * Converts a <code>Func</code> to <code>Func3</code>.
     */
    def triplify[A, B](f: Func[A, B]): Func3[A, B] = Triplify(f)

    /**
     * Converts a <code>Func3</code> to <code>Func</code>.
     */
    def untriplify[A, B](f: Func3[A, B]): Func[A, B] = Untriplify(f)


// Char vector

    /**
     * Alias of <code>vec.Lexical</code>
     */
    val lexical = vec.Lexical

    /**
     * Converts to lower case letters.
     */
    def lowerCase(v: Vector[Char]): Vector[Char] = v.map(java.lang.Character.toLowerCase(_))

    /**
     * Converts to upper case letters.
     */
    def upperCase(v: Vector[Char]): Vector[Char] = v.map(java.lang.Character.toUpperCase(_))


// aliases

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: vec.Compatibles = this

    /**
     * @return  <code>this</code>.
     */
    val Operators: vec.Operators = this

    /**
     * Alias of <code>Vector[A] => B</code>
     */
    type Func[A, B] = Vector[A] => B

    /**
     * Alias of <code>(Vector[A], Int, Int) => B</code>
     */
    type Func3[A, B] = (Vector[A], Int, Int) => B

    /**
     * Alias of <code>Func[A, Boolean]</code>
     */
    type Pred[A] = Func[A, Boolean]

    /**
     * Alias of <code>Func3[A, Boolean]</code>
     */
    type Pred3[A] = Func3[A, Boolean]

    /**
     * Alias of <code>vec.Adapter</code>
     */
    val Adapter = vec.Adapter

    /**
     * Alias of <code>vec.Adapter</code>
     */
    type Adapter[Z, A] = vec.Adapter[Z, A]

    /**
     * Alias of <code>vec.VectorProxy</code>
     */
    type Forwarder[A] = vec.VectorProxy[A]

    /**
     * Alias of <code>vec.VectorProxy</code>
     */
    type VectorProxy[A] = vec.VectorProxy[A]

    /**
     * Alias of <code>vec.Mixin</code>
     */
    val Mixin = vec.Mixin

    /**
     * Alias of <code>vec.Mixin</code>
     */
    type Mixin = vec.Mixin

    /**
     * Alias of <code>vec.Region</code>
     */
    val Region = vec.Region

    /**
     * Alias of <code>vec.Region</code>
     */
    type Region[A] = vec.Region[A]

    /**
     * Alias of <code>vec.IntFileVector</code>
     */
    type IntFileVector = vec.IntFileVector

    /**
     * Alias of <code>vec.LongFileVector</code>
     */
    type LongFileVector = vec.LongFileVector

    /**
     * Alias of <code>vec.Writer</code>
     */
    type Writer[A] = vec.Writer[A]

    /**
     * Alias of <code>Vector</code>
     */
    type Type[A] = Vector[A]
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
trait Vector[A] extends PartialFunction[Int, A] {


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
     * @param   i   index of element to return.
     * @pre     this vector is readable.
     * @pre     <code>isDefinedAt(i)</code>
     * @return  the element at the specified position in this vector.
     * @throws  Vector.NotReadableException if not overridden.
     */
    override def apply(i: Int): A = throw new Vector.NotReadableException(this)

    /**
     * Replaces the element at the specified position in this vector with
     * the specified element.
     *
     * @param   i   index of element to replace.
     * @param   e   element to be stored at the specified position.
     * @pre     this vector is writable.
     * @pre     <code>isDefinedAt(i)</code>
     * @throws  Vector.NotWritableException if not overridden.
     */
    def update(i: Int, e: A): Unit = throw new Vector.NotWritableException(this)

    /**
     * @return  <code>(start <= i) && (i < end)</code>, possibly overridden in subclasses.
     */
    override def isDefinedAt(i: Int): Boolean = (start <= i) && (i < end)


// value semantics

    /**
     * Compares each element using predicate <code>p</code>.
     * Returns <code>false</code> if <code>size != that.size</code>.
     */
    def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsIf(this, that, p)

    /**
     * Vector has value semantics <code>==</code>.
     *
     * @return  <code>equalsIf(that)(Functions.equal)</code>.
     */
    override def equals(that: Any): Boolean = Equals(this, that)

    /**
     * @return  <code>equalsIf(that)(Functions.equal)</code>.
     */
    final def equalsTo[B](that: Vector[B]): Boolean = equalsIf(that)(Functions.equal)

    /**
     * Generates hash code as value semantics.
     */
    override def hashCode: Int = HashCode_(this)


// regions

    /**
     * @return  <code>Region(this, _start, _end)</code>.
     * @see     apply as alias.
     */
    def region(_start: Int, _end: Int): Vector[A] = Region(this, _start, _end)

    /**
     * @return  <code>this</code>.
     */
    def regionBase: Vector[A] = this

    /**
     * @return  <code>(regionBase eq that.regionBase) && (start == that.start) && (end == that.end)</code>.
     */
    final def shallowEquals[B](that: Vector[B]): Boolean = (regionBase eq that.regionBase) && (start == that.start) && (end == that.end)

    /**
     * @pre     <code>!isEmpty</code>
     * @return  <code>this(start, end - 1)</code>.
     */
    def init: Vector[A] = { throwIfEmpty("init"); this(start, end - 1) }

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
     * @param   p  the test predicate.
     * @return  the longest prefix of this vector whose elements satisfy
     *          the predicate <code>p</code>.
     */
    def takeWhile(p: A => Boolean): Vector[A] = TakeWhile(this, p)


// division

    /**
     * Divides this vector into vector of Regions.
     * Each vector size is <code>n</code> except for the last one.
     *
     * @param   n   divisor
     * @return  <code>[this(start, n), this(start + n, 2*n), this(start + 2*n, 3*n),...]</code>.
     * @see     Vector.undivide
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
     * @param   p   the test predicate
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
     * @param   p   the predicate used to filter the vector.
     * @return  the non-writable elements of this vector satisfying <code>p</code>.
     */
    def filter(p: A => Boolean): Vector[A] = Filter(this, p)

    /**
     * Returns all the elements of this vector that satisfy the
     * predicate <code>p</code>. The order of the elements is preserved.
     * Unlike <code>filter</code>, this requires no intermediate buffer,
     * but the state of this vector is unpredictable after calling this.
     *
     * @param   p   the predicate used to filter the vector.
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
     *
     * @pre <code>f</code> has no side effects.
     */
    def map[B](f: A => B): Vector[B] = Map(this, f)

    /**
     * @return  <code>Vector.flatten(map(f))</code>.
     */
    def flatMap[B](f: A => Vector[B]): Vector[B] = Vector.flatten(map(f).toIterable)

    /**
     * Casts element to type <code>B</code>.
     */
    def asVectorOf[B]: Vector[B] = AsVectorOf[A, B](this)


// loop

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
     * @param   f   a function that is applied to every element.
     */
    def each(f: A => Unit): Unit = foreach(f)


// search

    /**
     * Alias of <code>elements.find</code>
     */
    final def find(p: A => Boolean): Option[A] = Find(this, p)

    /**
     * Find and return the element of this vector satisfying a
     * predicate, if any. No guarantee the element is the first one.
     *
     * @param   p   the predicate
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
     * Returns the prefix sum of this vector. (a.k.a. scanl)
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
     * @return  <code>sortBy(c)</code>.
     */
    final def sort(implicit c: Compare[A]): Vector[A] = sortBy(c)

    /**
     * Sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt  strict weak ordering
     * @return  this vector sorted according to <code>lt</code>.
     */
    def sortBy(lt: Compare.Func[A]): Vector[A] = Sort(this, lt)

    /**
     * @return  <code>stableSortBy(c)</code>.
     */
    final def stableSort(implicit c: Compare[A]): Vector[A] = stableSortBy(c)

    /**
     * Stable sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt  strict weak ordering
     * @return  this vector sorted according to <code>lt</code>.
     */
    def stableSortBy(lt: Compare.Func[A]): Vector[A] = StableSort(this, lt)


// concatenation

    /**
     * @return  <code>Vector.fromRandomAccessSeq(randomAccessSeq.projection ++ that)</code>
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
    final def seal: Vector[A] = Seal(this)

    /**
     * Returns synchronized one.
     */
    final def synchronize: Vector[A] = Synchronize(this)


// mixin

    /**
     * Runtime mixin; <code>mx</code> is applied to every vector-to-vector method.
     */
    def mixin(mx: Mixin): Vector[A] = NewMixin(this, mx)

    /**
     * Reverts <code>mixin</code>.
     */
    def unmixin: Vector[A] = this


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
    override def clone: Vector[A] = Vector.fromArray(toArray)

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
    def defaultGrainSize: Int = DefaultGrainSize(this)

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

    override def toString: String = ToString(this)

    /**
     * @return  <code>Vector.toArray(this)</code>.
     */
    final def toArray: Array[A] = Vector.toArray(this)

    /**
     * @return  <code>Vector.toIterable(this)</code>.
     */
    final def toIterable: Iterable[A] = Vector.toIterable(this)

    /**
     * @return  <code>Vector.toJclArrayList(this)</code>.
     */
    final def toJclArrayList: java.util.ArrayList[A] = Vector.toJclArrayList(this)

    /**
     * @return  <code>Vector.toIterator</code>.
     */
    final def toLinearAccessSeq: Seq[A] = Vector.toLinearAccessSeq(this)

    /**
     * @return  <code>Vector.toList(this)</code>.
     */
    final def toList: List[A] = Vector.toList(this)

    /**
     * @return  <code>Vector.toRandomAccessSeq(this)</code>.
     */
    final def toRandomAccessSeq: RandomAccessSeq.Mutable[A] = Vector.toRandomAccessSeq(this)

    /**
     * @return  <code>Vector.toStream(this)</code>.
     */
    final def toStream: Stream[A] = Vector.toStream(this)


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
    final def sideEffect(f: Vector[A] => Unit): Vector[A] = { f(this); this }

    /**
     * @return  <code>Vector.range(start, end)</code>.
     */
    final def indices: Vector[Int] = Vector.range(start, end)

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
     * Alias of <code>toIterable.elements</code>
     */
    final def elements: Iterator[A] = toIterable.elements

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

    /**
     * Alias of <code>Vector</code>
     */
    final def companion = Vector


// implementation helpers

    private def throwIfEmpty(method: String) = ThrowIf.empty(this, method)
}
