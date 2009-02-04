

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility types and methods operating on <code>Vector</code>.
 */
object Vectors {
    import vec._


// constants

    /**
     * @return  <code>Math.MIN_INT</code>, which is the reserved index by <code>mada.Vector</code>.
     */
    final val NULL_INDEX = 0x80000000


// exceptions

    /**
     * Thrown if vector is not readable.
     */
    class NotReadableException[A](val vector: Vector[A]) extends RuntimeException

    /**
     * Thrown if vector is not writable.
     */
    class NotWritableException[A](val vector: Vector[A]) extends RuntimeException


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
     * @param   vs the given argument sequences
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
     * @param   vv The vector which returns on each call to next
     *             a new vector whose elements are to be concatenated to the result.
     * @return  the newly created writable vector (not a projection into <code>vv</code>).
     */
    def flatten[A](vv: Vector[Vector[A]]): Vector[A] = Flatten(vv)

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
     * Reverts <code>Vector[A]#divide</code>.
     *
     * @pre     each vector is the same size except for the last one.
     */
    def undivide[A](vv: Vector[Vector[A]]): Vector[A] = Undivide(vv)

    /**
     * @return  flatten(vv.map({ v => sep.append(v) }))
     */
    def untokenize[A](vv: Vector[Vector[A]], sep: Vector[A]): Vector[A] = Untokenize(vv, sep)

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


// compatibles(from)

    /**
     * Converts an <code>Array</code> to vector.
     */
    def fromArray[A](from: Array[A]): Vector[A] = FromArray(from)

    /**
     * Converts a <code>Cell</code> to vector.
     */
    def fromCell[A](from: Cell[A]): Vector[A] = FromCell(from)

    /**
     * Converts a <code>java.lang.CharSequence</code> to vector.
     */
    def fromJclCharSequence(from: java.lang.CharSequence): Vector[Char] = jcl.FromCharSequence(from)

    /**
     * Converts a <code>java.util.List</code> to vector.
     */
    def fromJclList[A](from: java.util.List[A]): Vector[A] = jcl.FromList(from)

    /**
     * Converts an <code>Option</code> to vector.
     */
    def fromOption[A](from: Option[A]): Vector[A] = FromOption(from)

    /**
     * Converts a <code>Product</code> to vector.
     */
    def fromProduct(from: Product): Vector[Any] = FromProduct(from)

    /**
     * Converts a <code>RandomAccessSeq</code> to vector.
     */
    def fromRandomAccessSeq[A](from: RandomAccessSeq[A]): Vector[A] = FromRandomAccessSeq(from)

    /**
     * Converts a <code>String</code> to vector.
     */
    def fromString(from: String): Vector[Char] = FromString(from)


// compatibles(to)

    /**
     * Converts a vector to <code>java.lang.CharSequence</code>.
     */
    def toJclCharSequence(from: Vector[Char]): java.lang.CharSequence = jcl.ToCharSequence(from)

    /**
     * Converts a vector to <code>Iterator</code>.
     */
    def toIterator[A](from: Vector[A]): Iterator[A] = ToIterator(from)

    /**
     * Converts a vector to <code>RandomAccessSeq.Mutable</code>.
     */
    def toRandomAccessSeq[A](from: Vector[A]): RandomAccessSeq.Mutable[A] = ToRandomAccessSeq(from)

    /**
     * Converts a vector to <code>java.util.ListIterator</code>.
     */
    def toJclListIterator[A](from: Vector[A]): java.util.ListIterator[A] = jcl.ToListIterator(from)

    /**
     * Converts a vector to <code>Seq</code>.
     */
    def toLinearAccessSeq[A](from: Vector[A]): Seq[A] = ToLinearAccessSeq(from)

    /**
     * Converts a vector to <code>Stream</code>.
     */
    def toStream[A](from: Vector[A]): Stream[A] = ToStream(from)


// incompatibles(from)

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


// incompatibles(to)

    /**
     * Converts a vector of <code>Char</code> to <code>String</code>.
     */
    def stringize(from: Vector[Char]): String = Stringize(from)

    /**
     * Converts a vector to <code>List</code>.
     */
    def toList[A](from: Vector[A]): List[A] = ToList(from)

    /**
     * Converts a vector to <code>Array</code>.
     */
    def toArray[A](from: Vector[A]): Array[A] = ToArray(from)

    /**
     * Converts a vector to <code>java.util.ArrayList</code>.
     */
    def toJclArrayList[A](from: Vector[A]): java.util.ArrayList[A] = jcl.ToArrayList(from)


// triplify

    /**
     * Converts a <code>Func</code> to <code>Func3</code>.
     */
    def triplify[A, B](f: Func[A, B]): Func3[A, B] = Triplify(f)

    /**
     * Converts a <code>Func3</code> to <code>Func</code>.
     */
    def untriplify[A, B](f: Func3[A, B]): Func[A, B] = Untriplify(f)


// aliases

    /**
     * Alias of <code>Vector[A]</code>
     */
    type Type[A] = Vector[A]

    /**
     * Alias of <code>Vector</code>
     */
    val Compatibles = Vector

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
     * Alias of <code>vec.Adapter</code>
     */
    val Adapter = vec.Adapter

    /**
     * Alias of <code>vec.Adapter</code>
     */
    type Adapter[Z, A] = vec.Adapter[Z, A]

    /**
     * Alias of <code>vec.Adapter.Proxy</code>
     */
    type VectorProxy[A] = Adapter.Proxy[A]

    /**
     * Alias of <code>vec.NotWritable</code>
     */
    type NotWritable[A] = vec.NotWritable[A]

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
}
