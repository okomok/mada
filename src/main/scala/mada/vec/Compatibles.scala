

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Provides implicit conversions around vectors.
 * These implicit conversion methods shall not called explicitly.
 * Note that any implicit conversion is efficient with regard to memory allocation,
 * whereas inefficent conversions must be called explicitly, e.g. <code>Vector.fromIterator</code>.
 */
object Compatibles {
    /**
     * @return  <code>to</code>.
     */
    def madaVector[A](to: Vector[A]): Vector[A] = to

    implicit def array2madaVector[A](from: Array[A]): Vector[A] = Vector.arrayVector(from)
    implicit def cell2madaVector[A](from: Cell[A]): Vector[A] = Vector.cellVector(from)
    implicit def jclCharSequence2madaVector(from: java.lang.CharSequence): Vector[Char] = Vector.jclCharSequenceVector(from)
    implicit def jclList2madaVector[A](from: java.util.List[A]): Vector[A] = Vector.jclListVector(from)
    implicit def option2madaVector[A](from: Option[A]): Vector[A] = Vector.optionVector(from)
    implicit def product2madaVector(from: Product): Vector[Any] = Vector.productVector(from)
    implicit def randomAccessSeq2madaVector[A](from: RandomAccessSeq[A]): Vector[A] = Vector.randomAccessSeqVector(from)
    implicit def string2madaVector(from: String): Vector[Char] = Vector.stringVector(from)

    implicit def madaVector2Iterator[A](from: Vector[A]): Iterator[A] = from.iterator
    implicit def madaVector2JclCharSequence(from: Vector[Char]): java.lang.CharSequence = Vector.jclCharSequence(from)
    implicit def madaVector2RandomAccessSeq[A](from: Vector[A]): RandomAccessSeq.Mutable[A] = from.randomAccessSeq
}
