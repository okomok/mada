

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada

/**
 * Contains utility types and methods operating on type <code>Vector</code>.
 */
object Vector {
    import vec._

    /**
     * Concatenate all argument sequences into a single vector.
     *
     * @param   vs the given argument sequences
     * @return  the vector created from the concatenated arguments
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
     * @param   vv The vector which returns on each call to next
     *             a new vector whose elements are to be concatenated to the result.
     */
    def flatten[A](vv: Vector[Vector[A]]): Vector[A] = Flatten(vv)

    /**
     * @return  <code>flatten(triplesVector(vv))</code>.
     */
    def flatten3[A](vv: Vector[Vector.Triple[A]]): Vector[A] = Flatten3(vv)

    /**
     * @return  <code>v.filter(_.isLeft).map(_.left.get)</code>.
     */
    def lefts[A, B](v: Vector[Either[A, B]]): Vector[A] = Lefts(v)

    /**
     * @return  <code>v.filter(_.isRight).map(_.right.get)</code>.
     */
    def rights[A, B](v: Vector[Either[A, B]]): Vector[B] = Rights(v)

    /**
     * Converts to lower case letters.
     */
    def lowerCase(v: Vector[Char]): Vector[Char] = LowerCase(v)

    /**
     * Converts to upper case letters.
     */
    def upperCase(v: Vector[Char]): Vector[Char] = UpperCase(v)

    /**
     * Creates a vector containing of successive integers.
     *
     * @param   i the value of the first element of the vector
     * @param   j the value of the last element of the vector plus 1
     * @return  the sorted vector of all integers in range [i, j).
     */
    def range(i: Int, j: Int): Vector[Int] = Range(i, j)

    /**
     * @param   e the element
     * @return  the writable vector with one single element.
     */
    def single[A](e: A): Vector[A] = Single(e)

    /**
     * Converts characters to <code>String</code>.
     */
    def stringize(v: Vector[Char]): String = Stringize(v)

    /**
     * Reverts <code>Vector[A]#divide</code>.
     *
     * @pre     each vector is the same size except for the last one.
     */
    def undivide[A](vv: Vector[Vector[A]]): Vector[A] = Undivide(vv)

    /**
     * Reverts <code>Vector[A]#divide3</code>.
     *
     * @return  <code>undivide(triplesVector(vv))</code>.
     */
    def undivide3[A](vv: Vector[Vector.Triple[A]]): Vector[A] = Undivide3(vv)

    def untokenize[A](vv: Vector[Vector[A]], sep: Vector[A]): Vector[A] = Untokenize(vv, sep)
    def untokenize3[A](vv: Vector[Vector.Triple[A]], sep: Vector[A]): Vector[A] = Untokenize3(vv, sep)

    /**
     * Reverts <code>Vector[A]#zip</code>.
     */
    def unzip[A, B](v: Vector[(A, B)]): (Vector[A], Vector[B]) = Unzip(v)

    /**
     * Creates a <code>lazy</code> vector.
     */
    def `lazy`[A](v: => Vector[A]) = Lazy(v)

    /**
     * Creates a <code>synchronized</code> vector.
     */
    def `synchronized`[A](v: Vector[A]) = Synchronized(v)

    /**
     * Alias of <code>vec.Compatibles</code>
     */
    val Compatibles = vec.Compatibles

    /**
     * Converts an <code>Array</code> to vector.
     */
    def arrayVector[A](from: Array[A]): Vector[A] = ArrayVector(from)

    /**
     * Converts a <code>Cell</code> to vector.
     */
    def cellVector[A](from: Cell[A]): Vector[A] = CellVector(from)

    /**
     * Converts a <code>java.lang.CharSequence</code> to vector.
     */
    def jclCharSequenceVector(from: java.lang.CharSequence): Vector[Char] = jcl.CharSequenceVector(from)

    /**
     * Converts a <code>java.util.List</code> to vector.
     */
    def jclListVector[A](from: java.util.List[A]): Vector[A] = jcl.ListVector(from)

    /**
     * Converts an <code>Option</code> to vector.
     */
    def optionVector[A](from: Option[A]): Vector[A] = OptionVector(from)

    /**
     * Converts a <code>Product</code> to vector.
     */
    def productVector(from: Product): Vector[Any] = ProductVector(from)

    /**
     * Converts a <code>RandomAccessSeq</code> to vector.
     */
    def randomAccessSeqVector[A](from: RandomAccessSeq[A]): Vector[A] = RandomAccessSeqVector(from)

    /**
     * Converts a <code>String</code> to vector.
     */
    def stringVector(from: String): Vector[Char] = StringVector(from)

    /**
     * Converts a <code>Triple</code> to vector.
     */
    def tripleVector[A](from: Vector.Triple[A]): Vector[A] = TripleVector(from)

    /**
     * Converts a vector to <code>java.lang.CharSequence</code>.
     */
    def jclCharSequence(from: Vector[Char]): java.lang.CharSequence = jcl.VectorCharSequence(from)

    /**
     * Converts an <code>Iterator</code> to vector.
     */
    def fromIterator[A](from: Iterator[A]): Vector[A] = FromIterator(from)

    /**
     * Converts a <code>java.util.Iterator</code> to vector.
     */
    def fromJclIterator[A](from: java.util.Iterator[A]): Vector[A] = jcl.FromIterator(from)

    /**
     * Converts values to vector.
     */
    def fromValues[A](from: A*): Vector[A] = FromValues(from: _*)

    /**
     * Converts a <code>Func</code> to <code>Func3</code>.
     */
    def triplify[A, B](f: Func[A, B]): Func3[A, B] = Triplify(f)

    /**
     * Converts a <code>Func3</code> to <code>Func</code>.
     */
    def untriplify[A, B](f: Func3[A, B]): Func[A, B] = Untriplify(f)

    /**
     * Alias of <code>(Vector[A], Int, Int)</code>
     */
    type Triple[A] = (Vector[A], Int, Int)

    /**
     * Alias of <code>Vector[A] => B</code>
     */
    type Func[A, B] = Vector[A] => B

    /**
     * Alias of <code>(Vector[A], Int, Int) => B</code>
     */
    type Func3[A, B] = (Vector[A], Int, Int) => B

    /**
     * Converts <code>Vector[Vector[A]]</code> to <code>Vector[Vector.Triple[A]]</code>.
     */
    def triples[A](from: Vector[Vector[A]]): Vector[Vector.Triple[A]] = VectorTriples(from)

    /**
     * Converts <code>Vector[Vector.Triple[A]]</code> to <code>Vector[Vector[A]]</code>.
     */
    def triplesVector[A](from: Vector[Vector.Triple[A]]): Vector[Vector[A]] = TriplesVector(from)

    /**
     * Alias of <code>vec.NotReadableError</code>
     */
    type NotReadableError[A] = vec.NotReadableError[A]

    /**
     * Alias of <code>vec.NotWritableError</code>
     */
    type NotWritableError[A] = vec.NotWritableError[A]

    /**
     * Alias of <code>vec.VectorAdapter</code>
     */
    type VectorAdapter[Z, A] = vec.VectorAdapter[Z, A]

    /**
     * Alias of <code>vec.VectorProxy</code>
     */
    type VectorProxy[A] = vec.VectorProxy[A]

    /**
     * Alias of <code>vec.NotWritable</code>
     */
    type NotWritable[A] = vec.NotWritable[A]

    /**
     * Alias of <code>vec.IntFileVector</code>
     */
    type IntFileVector = vec.IntFileVector

    /**
     * Alias of <code>vec.LongFileVector</code>
     */
    type LongFileVector = vec.LongFileVector
}

/**
 * Sequences that guarantees O(1) element access and O(1) length computation.
 * A vector is optionally writable but structurally-unmodifiable so that synchronization is unneeded.
 * Note that these methods return projections unless otherwise specified.
 */
trait Vector[A] extends PartialFunction[Int, A] {
    import vec._

    /**
     * @return  the number of elements in this vector.
     */
    def size: Int

    /**
     * @param   i index of element to return.
     * @pre     this vector is readable.
     * @pre     <code>this.isDefinedAt(i)</code>.
     * @return  the element at the specified position in this vector.
     */
    override def apply(i: Int): A = throw new NotReadableError(this)

    /**
     * Replaces the element at the specified position in this vector with
     * the specified element.
     *
     * @param   i index of element to replace.
     * @param   e element to be stored at the specified position.
     * @pre     this vector is writable.
     * @pre     <code>this.isDefinedAt(i)</code>.
     */
    def update(i: Int, e: A): Unit = throw new NotWritableError(this)

    /**
     * @return  <code>0 <= i && i < this.size</code>
     */
    override def isDefinedAt(x: Int): Boolean = IsDefinedAt(this, x)

    /**
     * @return  <code>this.size == 0</code>
     */
    final def isEmpty: Boolean = IsEmpty(this)

    /**
     * @return  this vector.
     */
    final def vector: Vector[A] = this

    /**
     * Compares each element using predicate <code>p</code>.
     * Returns <code>false</code> if <code>this.size != that.size</code>.
     */
    def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsWith(this, that, p)

    /**
     * Type-safe alias of <code>Any.equals</code>
     */
    final def equalsTo[B](that: Vector[B]): Boolean = EqualsTo(this, that)

    /**
     * @return <code>this.equalsWith(that){ (a, b) => a == b }</code>.
     */
    override def equals(that: Any): Boolean = Equals(this, that)

    override def hashCode: Int = HashCode(this)

    /**
     * @return <code>super.hashCode</code>
     */
    final def refHashCode: Int = super.hashCode

    /**
     * Divides this vector into vector of vectors.
     * Each vector size is <code>n</code> except for the last one.
     *
     * @param   n divisor
     * @return  <code>[this(0, n), this(n, 2*n), this(2*n, 3*n),...]</code>.
     * @see     Vector.undivide
     */
    final def divide(n: Int): Vector[Vector[A]] = Divide(this, n)

    /**
     * @return <code>Vector.triples(this.divide(n))</code>
     * @see     Vector.undivide3
     */
    final def divide3(n: Int): Vector[Vector.Triple[A]] = Divide3(this, n)

    /**
     * Returns a subsequence with specified region.
     * <code>n < 0</code> or <code>m > this.size</code> is allowed if its behavior is defined.
     *
     * @pre     <code>n <= m</code>
     * @return  a subsequence with specified region.
     * @see     apply
     */
    def window(n: Int, m: Int): Vector[A] = Window(this, n, m)

    /**
     * @return <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection.drop(n))</code>
     */
    final def drop(n: Int): Vector[A] = Drop(this, n)

    /**
     * @return <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection.take(n))</code>
     */
    final def take(n: Int): Vector[A] = Take(this, n)

    /**
     * Returns the longest suffix of this vector whose first element
     * does not satisfy the predicate <code>p</code>.
     *
     * @param   p the test predicate.
     * @return  the longest suffix of the vector whose first element
     *          does not satisfy the predicate <code>p</code>.
     */
    final def dropWhile(p: A => Boolean): Vector[A] = DropWhile(this, p)

    /**
     * Returns the longest prefix of this vector whose elements satisfy
     * the predicate <code>p</code>.
     *
     * @param   p the test predicate.
     * @return  the longest prefix of this vector whose elements satisfy
     *          the predicate <code>p</code>.
     */
    final def takeWhile(p: A => Boolean): Vector[A] = TakeWhile(this, p)

    /**
     * @return  <code>this(i, this.size + j)</code>.
     */
    final def offset(i: Int, j: Int): Vector[A] = Offset(this, i, j)

    /**
     * @return  <code>this.slice(n, this.size)</code>.
     */
    final def slice(n: Int): Vector[A] = Slice(this, n)

    /**
     * @return <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection.slice(n, m))</code>
     */
    final def slice(n: Int, m: Int): Vector[A] = Slice(this, n, m)

    /**
     * @return  <code>(this(0, m), this(m, this.size))</code>, where <code>val m = Math.min(i, this.size)</code>.
     */
    final def splitAt(i: Int): (Vector[A], Vector[A]) = SplitAt(this, i)

    /**
     * Returns the longest prefix of the vector whose elements all satisfy
     * the given predicate, and the rest of the vector.
     *
     * @param   p the test predicate
     * @return  a pair consisting of the longest prefix of the vector whose
     *          elements all satisfy <code>p</code>, and the rest of the vector.
     */
    final def span(p: A => Boolean): (Vector[A], Vector[A]) = Span(this, p)

    /**
     * @return  <code>this.span{ e => !p(e) }</code>.
     */
    final def break(p: A => Boolean): (Vector[A], Vector[A]) = Break(this, p)

    /**
     * @return  an <code>Iterator</code> of this vector.
     */
    final def elements: Iterator[A] = iterator

    /**
     * Alias of <code>this.elements</code>
     */
    final def iterator: Iterator[A] = VectorIterator(this)

    /**
     * @return  a <code>java.util.Iterator</code> of this vector.
     */
    final def jclListIterator: java.util.ListIterator[A] = jcl.VectorListIterator(this)


    final def linearAccessSeq: Seq[A] = LinearAccessSeq(this)

   /**
     * @return  a <code>RandomAccessSeq.Mutable</code> projection of this vector.
     */
    def randomAccessSeq: RandomAccessSeq.Mutable[A] = VectorRandomAccessSeq(this)

   /**
     * @return  a <code>Stream</code> of this vector.
     */
    final def stream: Stream[A] = VectorStream(this)

   /**
     * @return  a <code>Vector.Triple</code> of this vector.
     */
    def triple: Vector.Triple[A] = VectorTriple(this)

    /**
     * @return  a new <code>Array</code> which enumerates all elements of this vector.
     */
    final def toArray: Array[A] = ToArray(this)

    /**
     * @return  a new <code>java.util.ArrayList</code> which enumerates all elements of this vector.
     */
    final def toJclArrayList: java.util.ArrayList[A] = jcl.ToArrayList(this)

    /**
     * @return  a <code>List</code> which enumerates all elements of this vector.
     */
    final def toList: List[A] = ToList(this)

    /**
     * @return  a string representation of this vector.
     */
    override def toString: String = ToString(this)

    /**
     * Alias of <code>this.randomAccessSeq.first</code>
     */
    final def first: A = First(this)

    /**
     * Alias of <code>this.randomAccessSeq.last</code>
     */
    final def last: A = Last(this)

    /**
     * @pre     <code>!this.isEmpty</code>
     * @return  the vector without its last element.
     */
    final def init: Vector[A] = Init(this)

    /**
     * Alias of <code>this.randomAccessSeq.firstOption</code>
     */
    def firstOption: Option[A] = FirstOption(this)

    /**
     * Alias of <code>this.randomAccessSeq.lastOption</code>
     */
    def lastOption: Option[A] = LastOption(this)

    /**
     * Alias of <code>this.first</code>
     */
    final def head: A = Head(this)

    /**
     * @pre     <code>!this.isEmpty</code>
     * @return  the vector without its first element.
     */
    final def tail: Vector[A] = Tail(this)

    /**
     * Alias of <code>this.isEmpty</code>
     */
    final def isNil: Boolean = IsNil(this)

    /**
     * Returns all the elements of this vector that satisfy the
     * predicate <code>p</code>. The order of the elements is preserved.
     *
     * @param   p the predicate used to filter the vector.
     * @return  the elements of this vector satisfying <code>p</code>.
     */
    def filter(p: A => Boolean): Vector[A] = Filter(this, p)

    /**
     * Returns all the elements of this vector that satisfy the
     * predicate <code>p</code>. The order of the elements is preserved.
     * Unlike <code>this.filter</code>, this requires no intermediate buffer,
     * but the state of this vector is unpredictable after calling this.
     *
     * @param   p the predicate used to filter the vector.
     * @return  the elements of this vector satisfying <code>p</code>.
     */
    def mutatingFilter(p: A => Boolean): Vector[A] = MutatingFilter(this, p)

    /**
     * @return  <code>this.filter{ e => !p(e) }</code>.
     */
    final def remove(p: A => Boolean): Vector[A] = Remove(this, p)

    /**
     * @return  <code>this.mutatingFilter{ e => !p(e) }</code>.
     */
    final def mutatingRemove(p: A => Boolean): Vector[A] = MutatingRemove(this, p)

    /**
     * @return  <code>(this.filter(p), this.remove(p))</code>.
     */
    final def partition(p: A => Boolean): (Vector[A], Vector[A]) = Partition(this, p)

    /**
     * @return  <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection.map(f))</code>
     */
    def map[B](f: A => B): Vector[B] = Map(this, f)

    /**
     * @return  <code>Vector.flatten(this.map(f))</code>.
     */
    final def flatMap[B](f: A => Vector[B]): Vector[B] = FlatMap(this, f)

    final def asVectorOf[B]: Vector[B] = AsVectorOf[A, B](this)

    def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = Loop(this, i, j, f)

    /**
     * Alias of <code>this.randomAccessSeq.count</code>
     */
    def count(p: A => Boolean): Int = Count(this, p)

    /**
     * Alias of <code>this.elements.foreach</code>.
     */
    def foreach(f: A => Unit): Unit = Foreach(this, f)

    /**
     * Apply a function <code>f</code> to all elements of this vector.
     * <code>f</code> may be called in any order, and
     * there is no guarantee <code>f</code> calls are single-threaded.
     *
     * @param   f a function that is applied to every element.
     */
    def pareach(f: A => Unit): Unit = Pareach(this, f)

    /**
     * Alias of <code>this.elements.find</code>.
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
    def seek(p: A => Boolean): Option[A] = Seek(this, p)

    /**
     * Alias of <code>this.elements.contains</code>.
     */
    final def contains(e: Any): Boolean = Contains(this, e)

    /**
     * Alias of <code>this.elements.forall</code>.
     */
    final def forall(p: A => Boolean): Boolean = Forall(this, p)

    /**
     * Alias of <code>this.elements.exists</code>.
     */
    final def exists(p: A => Boolean): Boolean = Exists(this, p)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>this.reduceLeft(op)</code>
     */
    def reduce(op: (A, A) => A): A = Reduce(this, op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>this.reduceerLeft(op)</code>
     */
    def reducer(op: (A, A) => A): Vector[A] = Reducer(this, op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>this.foldLeft(z)(op)</code>
     */
    final def fold(z: A)(op: (A, A) => A): A = Fold(this, z, op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>this.folderLeft(op)</code>
     */
    final def folder(z: A)(op: (A, A) => A): Vector[A] = Folder(this, z, op)

    /**
     * Alias of <code>this.elements.foldLeft</code>.
     */
    final def foldLeft[B](z: B)(op: (B, A) => B): B = FoldLeft(this, z, op)

    /**
     * Alias of <code>this.elements.foldRight</code>.
     */
    final def foldRight[B](z: B)(op: (A, B) => B): B = FoldRight(this, z, op)

    /**
     * Alias of <code>this.elements.reduceLeft</code>.
     */
    final def reduceLeft[B >: A](op: (B, A) => B): B = ReduceLeft(this, op)

    /**
     * Alias of <code>this.elements.reduceRight</code>.
     */
    final def reduceRight[B >: A](op: (A, B) => B): B = ReduceRight(this, op)

    /**
     * Returns the prefix sum of this vector.
     *
     * @return  <code>[z, op(z, this(0)), op(op(z, this(0)), this(1)),...]</code>
     */
    final def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = FolderLeft(this, z, op)

    /**
     * @return  <code>this.reverse.folderLeft(z){ (b, a) => op(a, b) }</code>
     */
    final def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = FolderRight(this, z, op)

    /**
     * @pre     <code>!isEmpty</code>
     * @return  <code>this.tail.folderLeft(this.head)</code>
     */
    final def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = ReducerLeft(this, op)

    /**
     * @pre     <code>!isEmpty</code>
     * @return  <code>this.reverse.reducerLeft{ (b, a) => op(a, b) }</code>
     */
    final def reducerRight[B >: A](op: (A, B) => B): Vector[B] = ReducerRight(this, op)

    def parallel: Vector[A] = Parallel(this)
    def parallel(grainSize: Int): Vector[A] = Parallel(this, grainSize)
    def unparallel: Vector[A] = Unparallel(this)
    def isParallel: Boolean = IsParallel(this)
    def defaultGrainSize: Int = DefaultGrainSize(this)

    /**
     * Sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt the comparison function
     * @return  this vector sorted according to the comparison function <code>lt</code>.
     */
    def sortWith(lt: (A, A) => Boolean): Vector[A] = SortWith(this, lt)

    /**
     * @return  <code>this.sortWith{ ... }</code>.
     */
    final def sort(implicit c: A => Ordered[A]): Vector[A] = Sort(this, c)

    /**
     * @return  <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection ++ that)</code>
     */
    final def append(that: Vector[A]): Vector[A] = Append(this, that)

    /**
     * Returns a non-writable circular vector from this vector.
     *
     * @return  <code>[this(0),...,this(this.size-1),this(0),...,this(this.size-1),...n times...]</code>
     */
    def cycle(n: Int): Vector[A] = Cycle(this, n)

    /**
     * @return  <code>Vector.range(0, this.size)</code>
     */
    final def indices: Vector[Int] = Indices(this)

    /**
     * Returns a vector whose elements are lazy.
     */
    def lazyValues : Vector[A] = LazyValues(this)

    /**
     * @return  <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection.reverse)</code>
     */
    def reverse: Vector[A] = Reverse(this)

    /**
     * @return  <code>this(i, this.size) ++ this(0, i)</code>.
     */
    final def rotate(i: Int): Vector[A] = Rotate(this, i)

    /**
     * Returns steps with specified stride <code>n</code>.
     *
     * @return  <code>[this(0), this(n), this(2*n), ...]</code>.
     */
    def step(n: Int): Vector[A] = Step(this, n)

    /**
     * Reorders with specified Int vector <code>iv</code>.
     *
     * @return  <code>[this(iv(0)), this(iv(1)), this(iv(2)), ...]</code>.
     */
    final def permutation(iv: Vector[Int]): Vector[A] = Permutation(this, iv)

    /**
     * Returns a vector formed from this vector and the specified vector
     * <code>that</code> by associating each element of the former with
     * the element at the same position in the latter.
     *
     * @pre     <code>this.size == that.size</code>
     * @return  <code>[(this(0), that(0)), (this(1), that(1)), (this(2), that(2)), ...]</code>.
     */
    final def zip[B](that: Vector[B]): Vector[(A, B)] = Zip(this, that)

    /**
     * Returns this vector after applying <code>f</code>.
     *
     * @return  this vector.
     */
    final def sideEffect(f: Vector[A] => Any): Vector[A] = SideEffect(this, f)

    /**
     * Copies all the elements into another.
     *
     * @pre     <code>this.size == that.size</code>.
     * @pre     <code>that</code> is writable.
     * @return  this vector.
     */
    def copyTo[B >: A](that: Vector[B]): Vector[A] = CopyTo(this, that)

    /**
     * Converts to a strict collection.
     *
     * @return  non-writable vector.
     */
    def force: Vector[A] = Force(this)

    /**
     * Returns a shallow copy of this vector. (The elements themselves are not copied.)
     *
     * @return  a writable clone of this vector.
     */
    override def clone: Vector[A] = Clone(this)

    def bounds: Vector[A] = Bounds(this)

    /**
     * @return  a read only alias of this vector.
     */
    def readOnly: Vector[A] = ReadOnly(this)

    /**
     * @return  <code>that</code>.
     */
    final def always[B](that: Vector[B]): Vector[B] = Always(this, that)

    /**
     * @return  an empty vector.
     */
    final def clear: Vector[A] = Clear(this)

    /**
     * @return  an alias of this vector, but any override is turned off.
     */
    final def cut: Vector[A] = Cut(this)

    /**
     * @return  an alias of this vector.
     */
    final def identity: Vector[A] = Identity(this)

    /**
     * Alias of <code>this.size</code>
     */
    final def length: Int = size


    final def writer(i: Int): (A => Unit) = Writer(this, i)

    /**
     * Alias of <code>this.window</code>
     */
    final def apply(n: Int, m: Int): Vector[A] = window(n, m)

    /**
     * Alias of <code>this.append</code>
     */
    final def ++(that: Vector[A]): Vector[A] = append(that)

    /**
     * Alias of <code>this.foldLeft</code>
     */
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    /**
     * Alias of <code>this.foldRight</code>
     */
    final def :\[B](z: B)(op: (A, B) => B): B = foldRight(z)(op)

    /**
     * Returns a set entry tuple, which is useful for <code>Peg.switch</code>.
     *
     * @return  <code>(this, p)</code>
     */
    final def -->(p: Peg[A]): (Vector[A], Peg[A]) = (this, p)
}
