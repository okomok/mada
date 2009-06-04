

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object vector {


// aliases

    @aliasOf("Vector")
    type Type[A] = Vector[A]

    @aliasOf("Vector[A] => B")
    type Func[A, B] = Vector[A] => B

    @aliasOf("(Vector[A], Int, Int) => B")
    type Func3[A, B] = (Vector[A], Int, Int) => B

    @aliasOf("Func[A, Boolean]")
    type Pred[A] = Func[A, Boolean]

    @aliasOf("Func3[A, Boolean]")
    type Pred3[A] = Func3[A, Boolean]

    @aliasOf("Vector")
    val compatibles: Compatibles = Vector


// constants

    /**
     * @return  <code>Math.MIN_INT</code>, which is the reserved index by <code>mada.Vector</code>.
     */
    final val SINGULAR = 0x80000000


// constructors

    @returnThat
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
    def flatten[A](its: Iterative[iterative.Sequence[A]]): Vector[A] = Flatten(its)

    /**
     * @return  <code>v.filter(_.isLeft).map(_.left.get)</code>.
     */
    def lefts[A, B](v: Vector[Either[A, B]]): Vector[A] = v.filter(_.isLeft).map(_.left.get)

    /**
     * @return  <code>v.filter(_.isRight).map(_.right.get)</code>.
     */
    def rights[A, B](v: Vector[Either[A, B]]): Vector[B] = v.filter(_.isRight).map(_.right.get)

    @packageObjectBrokenOverload
    object range {
        /**
         * Creates a vector containing of successive integers.
         *
         * @param   i   the value of the first element of the vector
         * @param   j   the value of the last element of the vector plus 1
         * @return  the sorted vector of all integers in range [i, j).
         */
        def apply(i: Int, j: Int): Vector[Int] = Range(i, j)

        /**
         * @return  <code>range(i, Math.MAX_INT)</code>.
         */
        def apply(i: Int, u: Unit): Vector[Int] = Range(i, Math.MAX_INT)
    }

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
    def byLazy[A](v: => Vector[A]) = ByLazy(function.ofLazy(v))


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

    @aliasOf("vector.Lexical")
    val lexical = vector.Lexical

    /**
     * Converts to lower case letters.
     */
    def lowerCase(v: Vector[Char]): Vector[Char] = v.map(java.lang.Character.toLowerCase(_))

    /**
     * Converts to upper case letters.
     */
    def upperCase(v: Vector[Char]): Vector[Char] = v.map(java.lang.Character.toUpperCase(_))


// conversions

  // compatibles

    // from
    def fromArray[A](from: Array[A]): Vector[A] = FromArray(from)
    def fromCell[A](from: Cell[A]): Vector[A] = FromCell(from)
    def fromJclCharSequence(from: java.lang.CharSequence): Vector[Char] = jcl.CharSequenceToVector(from)
    def fromJclList[A](from: java.util.List[A]): Vector[A] = jcl.ListToVector(from)
    def fromOption[A](from: Option[A]): Vector[A] = FromOption(from)
    def fromProduct(from: Product): Vector[Any] = FromProduct(from)
    def fromRandomAccessSeq[A](from: scala.collection.Vector[A]): Vector[A] = FromRandomAccessSeq(from)
    def unstringize(from: String): Vector[Char] = Unstringize(from)

    // to
    def toJclCharSequence(from: Vector[Char]): java.lang.CharSequence = jcl.CharSequenceFromVector(from)
    def toIterable[A](from: Vector[A]): Iterable[A] = ToIterable(from)
    def toJclIterable[A](from: Vector[A]): java.lang.Iterable[A] = jcl.IterableFromVector(from)
    def toRandomAccessSeq[A](from: Vector[A]): scala.collection.mutable.Vector[A] = ToRandomAccessSeq(from)
    def toLinearAccessSeq[A](from: Vector[A]): Seq[A] = ToLinearAccessSeq(from)
    def toProduct[A](from: Vector[A]): Product = ToProduct(from)
    def toOrdered[A](from: Vector[A])(implicit c: compare.GetOrdered[A]): Ordered[Vector[A]] = ToOrdered(from, c)

  // incompatibles

    // from
    def fromIterable[A](from: Iterable[A]): Vector[A] = FromIterable(from)
    def fromJclIterable[A](from: java.lang.Iterable[A]): Vector[A] = jcl.IterableToVector(from)
    def of[A](from: A*): Vector[A] = fromIterable(from)
    def stringize(from: Vector[Char]): String = Stringize(from)

    // to
    def toList[A](from: Vector[A]): scala.List[A] = from.toIterable.toList
    def toArray[A](from: Vector[A]): Array[A] = ToArray(from)
    def toJclArrayList[A](from: Vector[A]): java.util.ArrayList[A] = jcl.ArrayListFromVector(from)

}
