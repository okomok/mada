

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains implicit conversions around <code>Vector</code>.
 */
trait Compatibles {

    /**
     * Alias of <code>this</code>
     */
    final val madaVectorCompatibles: Compatibles = this


// compatibles(from)

    /**
     * Converts an <code>Array</code> to vector.
     */
    implicit def madaVectorFromArray[A](from: Array[A]): Vector[A] = FromArray(from)

    /**
     * Converts a <code>Cell</code> to vector.
     */
    implicit def madaVectorFromCell[A](from: Cell[A]): Vector[A] = FromCell(from)

    /**
     * Converts a <code>java.lang.CharSequence</code> to vector.
     */
    implicit def madaVectorFromJclCharSequence(from: java.lang.CharSequence): Vector[Char] = jcl.FromCharSequence(from)

    /**
     * Converts a <code>java.util.List</code> to vector.
     */
    implicit def madaVectorFromJclList[A](from: java.util.List[A]): Vector[A] = jcl.FromList(from)

    /**
     * Converts an <code>Option</code> to vector.
     */
    implicit def madaVectorFromOption[A](from: Option[A]): Vector[A] = FromOption(from)

    /**
     * Converts a <code>Product</code> to vector.
     */
    implicit def madaVectorFromProduct(from: Product): Vector[Any] = FromProduct(from)

    /**
     * Converts a <code>RandomAccessSeq</code> to vector.
     */
    implicit def madaVectorFromRandomAccessSeq[A](from: RandomAccessSeq[A]): Vector[A] = FromRandomAccessSeq(from)

    /**
     * Converts a <code>String</code> to vector.
     */
    implicit def madaVectorFromString(from: String): Vector[Char] = FromString(from)


// compatibles(to)

    /**
     * Converts a vector to <code>java.lang.CharSequence</code>.
     */
    implicit def madaVectorToJclCharSequence(from: Vector[Char]): java.lang.CharSequence = jcl.ToCharSequence(from)

    /**
     * Converts a vector to <code>Iterator</code>.
     */
    implicit def madaVectorToIterator[A](from: Vector[A]): Iterator[A] = ToIterator(from)

    /**
     * Converts a vector to <code>RandomAccessSeq.Mutable</code>.
     */
    implicit def madaVectorToRandomAccessSeq[A](from: Vector[A]): RandomAccessSeq.Mutable[A] = ToRandomAccessSeq(from)

    /**
     * Converts a vector to <code>java.util.ListIterator</code>.
     */
    implicit def madaVectorToJclListIterator[A](from: Vector[A]): java.util.ListIterator[A] = jcl.ToListIterator(from)

    /**
     * Converts a vector to <code>Seq</code>.
     */
    implicit def madaVectorToLinearAccessSeq[A](from: Vector[A]): Seq[A] = ToLinearAccessSeq(from)
    /**
     * Converts a vector to <code>Product</code>.
     */
    implicit def madaVectorToProduct[A](from: Vector[A]): Product = ToProduct(from)

    /**
     * Converts a vector to <code>Stream</code>.
     */
    implicit def madaVectorToStream[A](from: Vector[A]): Stream[A] = ToStream(from)
}
