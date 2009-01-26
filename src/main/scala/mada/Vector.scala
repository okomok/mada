

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada

/**
 * Contains utility methods operating on type <code>Vector</code>.
 */
object Vector {
    import vec._

    def concat[A](vs: Vector[A]*): Vector[A] = Concat(vs: _*)
    def empty[A]: Vector[A] = Empty.apply[A]
    def flatten[A](vv: Vector[Vector[A]]): Vector[A] = Flatten(vv)
    def flatten3[A](vv: Vector[Vector.Triple[A]]): Vector[A] = Flatten3(vv)
    def lefts[A, B](v: Vector[Either[A, B]]): Vector[A] = Lefts(v)
    def rights[A, B](v: Vector[Either[A, B]]): Vector[B] = Rights(v)
    def lowerCase(v: Vector[Char]): Vector[Char] = LowerCase(v)
    def upperCase(v: Vector[Char]): Vector[Char] = UpperCase(v)
    def range(i: Int, j: Int): Vector[Int] = Range(i, j)
    def single[A](e: A): Vector[A] = Single(e)
    def stringize(v: Vector[Char]): String = Stringize(v)
    def undivide[A](vv: Vector[Vector[A]]): Vector[A] = Undivide(vv)
    def undivide3[A](vv: Vector[Vector.Triple[A]]): Vector[A] = Undivide3(vv)
    def untokenize[A](vv: Vector[Vector[A]], sep: Vector[A]): Vector[A] = Untokenize(vv, sep)
    def untokenize3[A](vv: Vector[Vector.Triple[A]], sep: Vector[A]): Vector[A] = Untokenize3(vv, sep)
    def unzip[A, B](v: Vector[(A, B)]): (Vector[A], Vector[B]) = Unzip(v)
    def `lazy`[A](v: Vector[A]) = Lazy(v)
    def `synchronized`[A](v: Vector[A]) = Synchronized(v)

    val Compatibles = vec.Compatibles
    def arrayVector[A](from: Array[A]): Vector[A] = ArrayVector(from)
    def cellVector[A](from: Cell[A]): Vector[A] = CellVector(from)
    def jclCharSequenceVector(from: java.lang.CharSequence): Vector[Char] = jcl.CharSequenceVector(from)
    def jclListVector[A](from: java.util.List[A]): Vector[A] = jcl.ListVector(from)
    def optionVector[A](from: Option[A]): Vector[A] = OptionVector(from)
    def productVector(from: Product): Vector[Any] = ProductVector(from)
    def randomAccessSeqVector[A](from: RandomAccessSeq[A]): Vector[A] = RandomAccessSeqVector(from)
    def stringVector(from: String): Vector[Char] = StringVector(from)
    def tripleVector[A](from: Vector.Triple[A]): Vector[A] = TripleVector(from)
    def jclCharSequence(from: Vector[Char]): java.lang.CharSequence = jcl.VectorCharSequence(from)

    def fromIterator[A](from: Iterator[A]): Vector[A] = FromIterator(from)
    def fromJclIterator[A](from: java.util.Iterator[A]): Vector[A] = jcl.FromIterator(from)
    def fromValues[A](from: A*): Vector[A] = FromValues(from: _*)

    def triplify[A, B](f: Func[A, B]): Func3[A, B] = Triplify(f)
    def untriplify[A, B](f: Func3[A, B]): Func[A, B] = Untriplify(f)
    type Triple[A] = (Vector[A], Int, Int)
    type Func[A, B] = Vector[A] => B
    type Func3[A, B] = (Vector[A], Int, Int) => B

    def triples[A](from: Vector[Vector[A]]): Vector[Vector.Triple[A]] = VectorTriples(from)
    def triplesVector[A](from: Vector[Vector.Triple[A]]): Vector[Vector[A]] = TriplesVector(from)

    type NotReadableError[A] = vec.NotReadableError[A]
    type NotWritableError[A] = vec.NotWritableError[A]

    type VectorAdapter[Z, A] = vec.VectorAdapter[Z, A]
    type VectorProxy[A] = vec.VectorProxy[A]
    type NotWritable[A] = vec.NotWritable[A]

    type IntFileVector = vec.IntFileVector
    type LongFileVector = vec.LongFileVector
}

/**
 * Sequences that guarantees O(1) element access and O(1) length computation.
 * A vector can be writable but structurally-unmodifiable so that synchronization is unneeded.
 * Unless otherwise specified, these methods return projections.
 */
trait Vector[A] {
    import vec._

    /**
     * @return  the number of elements in this vector.
     */
    def size: Int

    /**
     * @param   i index of element to return.
     * @pre     this vector is readable.
     * @pre     <code>isDefinedAt(i)</code>.
     * @return  the element at the specified position in this vector.
     */
    def apply(i: Int): A = throw new NotReadableError(this)

    /**
     * Replaces the element at the specified position in this vector with
     * the specified element.
     *
     * @param   i index of element to replace.
     * @param   e element to be stored at the specified position.
     * @pre     this vector is writable.
     * @pre     <code>isDefinedAt(i)</code>.
     */
    def update(i: Int, e: A): Unit = throw new NotWritableError(this)

    /**
     * @return  this vector.
     */
    final def vector: Vector[A] = this

    def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsWith(this, that, p)
    final def equalsTo[B](that: Vector[B]): Boolean = EqualsTo(this, that)
    override def equals(that: Any): Boolean = Equals(this, that)

    override def hashCode: Int = HashCode(this)
    final def refHashCode: Int = super.hashCode

    final def divide(n: Int): Vector[Vector[A]] = Divide(this, n)
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
     * @return  <code>this(Math.min(n, this.size), this.size)</code>.
     */
    final def drop(n: Int): Vector[A] = Drop(this, n)

    /**
     * @return  <code>this(0, Math.min(n, this.size))</code>.
     */
    final def take(n: Int): Vector[A] = Take(this, n)

    final def dropWhile(p: A => Boolean): Vector[A] = DropWhile(this, p)
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
     * @return  <code>this.drop(n).take(m - n)</code>.
     */
    final def slice(n: Int, m: Int): Vector[A] = Slice(this, n, m)

    /**
     * @return  <code>(this(0, m), this(m, this.size))</code>, where <code>val m = Math.min(i, this.size)</code>.
     */
    final def splitAt(i: Int): (Vector[A], Vector[A]) = SplitAt(this, i)

    final def span(p: A => Boolean): (Vector[A], Vector[A]) = Span(this, p)
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
     * @return  the first element of this vector.
     * @pre     <code>!this.isEmpty</code>
     */
    final def first: A = First(this)

    /**
     * @return  the last element of this vector.
     * @pre     <code>!this.isEmpty</code>
     */
    final def last: A = Last(this)

    /**
     * @return  the vector without its last element.
     */
    final def init: Vector[A] = Init(this)

    /**
     * @return  the first element as an option.
     */
    def firstOption: Option[A] = FirstOption(this)

    /**
     * @return  the last element as an option.
     */
    def lastOption: Option[A] = LastOption(this)

    /**
     * Alias of <code>this.first</code>
     */
    final def head: A = Head(this)

    /**
     * @return  the vector without its first element.
     * @pre     <code>!this.isEmpty</code>
     */
    final def tail: Vector[A] = Tail(this)

    /**
     * Alias of <code>this.isEmpty</code>
     */
    final def isNil: Boolean = IsNil(this)

    def filter(p: A => Boolean): Vector[A] = Filter(this, p)
    def mutatingFilter(p: A => Boolean): Vector[A] = MutatingFilter(this, p)
    final def remove(p: A => Boolean): Vector[A] = Remove(this, p)
    final def mutatingRemove(p: A => Boolean): Vector[A] = MutatingRemove(this, p)
    final def partition(p: A => Boolean): (Vector[A], Vector[A]) = Partition(this, p)

    def map[B](f: A => B): Vector[B] = Map(this, f)
    final def flatMap[B](f: A => Vector[B]): Vector[B] = FlatMap(this, f)
    final def asVectorOf[B]: Vector[B] = AsVectorOf[A, B](this)

    def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = Loop(this, i, j, f)
    def count(p: A => Boolean): Int = Count(this, p)
    def foreach(f: A => Unit): Unit = Foreach(this, f)
    def pareach(f: A => Unit): Unit = Pareach(this, f)
    final def find(p: A => Boolean): Option[A] = Find(this, p)
    def seek(p: A => Boolean): Option[A] = Seek(this, p)
    final def contains(e: Any): Boolean = Contains(this, e)
    final def forall(p: A => Boolean): Boolean = Forall(this, p)
    final def exists(p: A => Boolean): Boolean = Exists(this, p)

    def reduce(op: (A, A) => A): A = Reduce(this, op)
    def reducer(op: (A, A) => A): Vector[A] = Reducer(this, op)
    final def fold(z: A)(op: (A, A) => A): A = Fold(this, z, op)
    final def folder(z: A)(op: (A, A) => A): Vector[A] = Folder(this, z, op)

    final def foldLeft[B](z: B)(op: (B, A) => B): B = FoldLeft(this, z, op)
    final def foldRight[B](z: B)(op: (A, B) => B): B = FoldRight(this, z, op)
    final def reduceLeft[B >: A](op: (B, A) => B): B = ReduceLeft(this, op)
    final def reduceRight[B >: A](op: (A, B) => B): B = ReduceRight(this, op)
    final def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = FolderLeft(this, z, op)
    final def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = FolderRight(this, z, op)
    final def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = ReducerLeft(this, op)
    final def reducerRight[B >: A](op: (A, B) => B): Vector[B] = ReducerRight(this, op)

    final def isDefinedAt(x: Int): Boolean = IsDefinedAt(this, x)
    final def isEmpty: Boolean = IsEmpty(this)

    def parallel: Vector[A] = Parallel(this)
    def parallel(grainSize: Int): Vector[A] = Parallel(this, grainSize)
    def unparallel: Vector[A] = Unparallel(this)
    def isParallel: Boolean = IsParallel(this)
    def defaultGrainSize: Int = DefaultGrainSize(this)

    def sortWith(lt: (A, A) => Boolean): Vector[A] = SortWith(this, lt)
    final def sort(implicit c: A => Ordered[A]): Vector[A] = Sort(this, c)

    final def append(that: Vector[A]): Vector[A] = Append(this, that)
    def cycle(n: Int): Vector[A] = Cycle(this, n)
    final def indices: Vector[Int] = Indices(this)
    def lazyValues : Vector[A] = LazyValues(this)
    def reverse: Vector[A] = Reverse(this)
    final def rotate(i: Int): Vector[A] = Rotate(this, i)
    def step(n: Int): Vector[A] = Step(this, n)
    final def permutation(iv: Vector[Int]): Vector[A] = Permutation(this, iv)
    final def zip[B](that: Vector[B]): Vector[(A, B)] = Zip(this, that)

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
    def readOnly: Vector[A] = ReadOnly(this)

    /**
     * Always returns <code>that</code>.
     *
     * @return  <code>that</code>.
     */
    final def always[B](that: Vector[B]): Vector[B] = Always(this, that)

    final def clear: Vector[A] = Clear(this)
    final def cut: Vector[A] = Cut(this)
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
     * Returns an set entry tuple, which is useful for <code>Peg.switch</code>.
     *
     * @return  <code>(this, p)</code>
     */
    final def -->(p: Peg[A]): (Vector[A], Peg[A]) = (this, p)
}
