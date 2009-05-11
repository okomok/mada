

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains explicit conversions around <code>Vector</code>.
 */
@provider
trait Conversions { this: Vector.type =>

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
    def toStream[A](from: Vector[A]): Stream[A] = ToStream(from)
    def toOrdered[A](from: Vector[A])(implicit c: compare.GetOrdered[A]): Ordered[Vector[A]] = ToOrdered(from, c)

// incompatibles
  // from
    def fromIterable[A](from: Iterable[A]): Vector[A] = FromIterable(from)
    def fromJclIterable[A](from: java.lang.Iterable[A]): Vector[A] = jcl.IterableToVector(from)
    def fromValues[A](from: A*): Vector[A] = fromIterable(from)
    def stringize(from: Vector[Char]): String = Stringize(from)
  // to
    def toList[A](from: Vector[A]): List[A] = from.toIterable.toList
    def toArray[A](from: Vector[A]): Array[A] = ToArray(from)
    def toJclArrayList[A](from: Vector[A]): java.util.ArrayList[A] = jcl.ArrayListFromVector(from)
}
