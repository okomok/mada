

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains explicit conversions around <code>Vector</code>.
 */
trait Conversions {

// compatibles
  // from
    def fromArray[A](from: Array[A]): Vector[A] = FromArray(from)
    def fromCell[A](from: Cell[A]): Vector[A] = FromCell(from)
    def fromJclCharSequence(from: java.lang.CharSequence): Vector[Char] = jcl.FromCharSequence(from)
    def fromJclList[A](from: java.util.List[A]): Vector[A] = jcl.FromList(from)
    def fromOption[A](from: Option[A]): Vector[A] = FromOption(from)
    def fromProduct(from: Product): Vector[Any] = FromProduct(from)
    def fromRandomAccessSeq[A](from: RandomAccessSeq[A]): Vector[A] = FromRandomAccessSeq(from)
    def fromString(from: String): Vector[Char] = FromString(from)
  // to
    def toJclCharSequence(from: Vector[Char]): java.lang.CharSequence = jcl.ToCharSequence(from)
    def toIterator[A](from: Vector[A]): Iterator[A] = ToIterator(from)
    def toRandomAccessSeq[A](from: Vector[A]): RandomAccessSeq.Mutable[A] = ToRandomAccessSeq(from)
    def toJclListIterator[A](from: Vector[A]): java.util.ListIterator[A] = jcl.ToListIterator(from)
    def toLinearAccessSeq[A](from: Vector[A]): Seq[A] = ToLinearAccessSeq(from)
    def toProduct[A](from: Vector[A]): Product = ToProduct(from)
    def toStream[A](from: Vector[A]): Stream[A] = ToStream(from)

// incompatibles
  // from
    def fromIterator[A](from: Iterator[A]): Vector[A] = FromIterator(from)
    def fromJclIterator[A](from: java.util.Iterator[A]): Vector[A] = jcl.FromIterator(from)
    def fromValues[A](from: A*): Vector[A] = fromIterator(from.elements)
    def stringize(from: Vector[Char]): String = Stringize(from)
  // to
    def toList[A](from: Vector[A]): List[A] = from.toIterator.toList
    def toArray[A](from: Vector[A]): Array[A] = ToArray(from)
    def toJclArrayList[A](from: Vector[A]): java.util.ArrayList[A] = jcl.ToArrayList(from)
}
