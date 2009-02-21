

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains implicit conversions around <code>Vector</code>.
 */
trait Compatibles {
    private val madaVector = new Conversions { }
// from
    implicit def madaVectorFromArray[A](from: Array[A]): Vector[A] = madaVector.fromArray(from)
    implicit def madaVectorFromCell[A](from: Cell[A]): Vector[A] = madaVector.fromCell(from)
    implicit def madaVectorFromJclCharSequence(from: java.lang.CharSequence): Vector[Char] = madaVector.fromJclCharSequence(from)
    implicit def madaVectorFromJclList[A](from: java.util.List[A]): Vector[A] = madaVector.fromJclList(from)
    implicit def madaVectorFromOption[A](from: Option[A]): Vector[A] = madaVector.fromOption(from)
    implicit def madaVectorFromProduct(from: Product): Vector[Any] = madaVector.fromProduct(from)
    implicit def madaVectorFromRandomAccessSeq[A](from: RandomAccessSeq[A]): Vector[A] = madaVector.fromRandomAccessSeq(from)
    implicit def madaVectorFromString(from: String): Vector[Char] = madaVector.fromString(from)
// to
    implicit def madaVectorToJclCharSequence(from: Vector[Char]): java.lang.CharSequence = madaVector.toJclCharSequence(from)
    implicit def madaVectorToIterator[A](from: Vector[A]): Iterator[A] = madaVector.toIterator(from)
    implicit def madaVectorToRandomAccessSeq[A](from: Vector[A]): RandomAccessSeq.Mutable[A] = madaVector.toRandomAccessSeq(from)
    implicit def madaVectorToJclListIterator[A](from: Vector[A]): java.util.ListIterator[A] = madaVector.toJclListIterator(from)
    implicit def madaVectorToLinearAccessSeq[A](from: Vector[A]): Seq[A] = madaVector.toLinearAccessSeq(from)
    implicit def madaVectorToProduct[A](from: Vector[A]): Product = madaVector.toProduct(from)
    implicit def madaVectorToStream[A](from: Vector[A]): Stream[A] = madaVector.toStream(from)
}
