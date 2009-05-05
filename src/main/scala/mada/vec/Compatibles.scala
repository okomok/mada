

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains implicit conversions around <code>Vector</code>.
 */
@provider
trait Compatibles { this: Vector.type =>
    @returnthis val Compatibles: Compatibles = this
// from
    implicit def madaVectorFromArray[A](from: Array[A]): Vector[A] = fromArray(from)
    implicit def madaVectorFromCell[A](from: Cell[A]): Vector[A] = fromCell(from)
    implicit def madaVectorFromJclCharSequence(from: java.lang.CharSequence): Vector[Char] = fromJclCharSequence(from)
    implicit def madaVectorFromJclList[A](from: java.util.List[A]): Vector[A] = fromJclList(from)
    implicit def madaVectorFromOption[A](from: Option[A]): Vector[A] = fromOption(from)
    implicit def madaVectorFromProduct(from: Product): Vector[Any] = fromProduct(from)
    implicit def madaVectorFromRandomAccessSeq[A](from: RandomAccessSeq[A]): Vector[A] = fromRandomAccessSeq(from)
    implicit def madaVectorUnstringize(from: String): Vector[Char] = unstringize(from)
// to
    implicit def madaVectorToJclCharSequence(from: Vector[Char]): java.lang.CharSequence = toJclCharSequence(from)
    implicit def madaVectorToIterable[A](from: Vector[A]): Iterable[A] = toIterable(from)
//    implicit def madaVectorToRandomAccessSeq[A](from: Vector[A]): RandomAccessSeq.Mutable[A] = toRandomAccessSeq(from)
    implicit def madaVectorToProduct[A](from: Vector[A]): Product = toProduct(from)
//    implicit def madaVectorToStream[A](from: Vector[A]): Stream[A] = toStream(from)
    implicit def madaVectorToOrdered[A](from: Vector[A])(implicit c: Compare.GetOrdered[A]): Ordered[Vector[A]] = toOrdered(from)(c)
}
