

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

    @aliasOf("Adapter[A, A]")
    type TransformAdapter[A] = Adapter[A, A]


// constants

    /**
     * @return  <code>Math.MIN_INT</code>, which is the reserved index by <code>mada.Vector</code>.
     */
    final val SINGULAR = 0x80000000


// constructors

    /**
     * Creates a sequence initially containing the specified elements.
     */
    object Of {
        def apply[A](from: A*): Vector[A] = fromSIterable(from)
        def unapplySeq[A](from: Vector[A]): Option[Seq[A]] = Some(from.toSVector)
    }

    /**
     * @return  an empty vector.
     */
    def empty[A]: Vector[A] = Empty.apply[A]

    /**
     * Concatenate all argument sequences into a single vector.
     *
     * @param   vs  the given argument sequences
     * @return  the projection vector created from the concatenated arguments
     */
    def concat[A](vs: Vector[A]*): Vector[A] = Concat(vs)

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


// conversions

    @returnThat
    def from[A](to: Vector[A]): Vector[A] = to

    @compatibleConversion
    def unstringize(from: String): Vector[Char] = Unstringize(from)

    @compatibleConversion
    def fromArray[A](from: Array[A]): Vector[A] = FromArray(from)

    @compatibleConversion
    def fromCell[A](from: Cell[A]): Vector[A] = FromCell(from)

    @compatibleConversion
    def fromOption[A](from: Option[A]): Vector[A] = FromOption(from)

    @compatibleConversion
    def fromProduct(from: Product): Vector[Any] = FromProduct(from)

    @compatibleConversion
    def fromSVector[A](from: scala.collection.Vector[A]): Vector[A] = FromSVector(from)

    @compatibleConversion
    def fromJCharSequence(from: java.lang.CharSequence): Vector[Char] = FromJCharSequence(from)

    @compatibleConversion
    def fromJList[A](from: java.util.List[A]): Vector[A] = FromJList(from)

    @conversion
    def fromSIterable[A](from: Iterable[A]): Vector[A] = FromSIterable(from)

    @conversion
    def fromJIterable[A](from: java.lang.Iterable[A]): Vector[A] = FromJIterable(from)

}
