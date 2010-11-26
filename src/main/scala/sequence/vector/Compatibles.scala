

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


@annotation.compatibles
trait Compatibles {
    implicit def unstringize(from: String): Vector[Char] = Unstringize(from)
    implicit def fromArray[A](from: Array[A]): Vector[A] = FromArray(from)
    implicit def fromCell[A](from: Cell[A]): Vector[A] = FromCell(from)
    implicit def fromOption[A](from: Option[A]): Vector[A] = FromOption(from)
    implicit def fromProduct(from: Product): Vector[Any] = FromProduct(from)
    implicit def fromSIndexedSeq[A](from: scala.collection.IndexedSeq[A]): Vector[A] = FromSIndexedSeq(from)
    implicit def fromJList[A](from: java.util.List[A]): Vector[A] = FromJList(from)
    implicit def fromJCharSequence(from: java.lang.CharSequence): Vector[Char] = FromJCharSequence(from)

    implicit def fromJByteBuffer(from: java.nio.ByteBuffer): Vector[Byte] = FromJByteBuffer(from)
    implicit def fromJCharBuffer(from: java.nio.CharBuffer): Vector[Char] = FromJCharBuffer(from)
    implicit def fromJDoubleBuffer(from: java.nio.DoubleBuffer): Vector[Double] = FromJDoubleBuffer(from)
    implicit def fromJFloatBuffer(from: java.nio.FloatBuffer): Vector[Float] = FromJFloatBuffer(from)
    implicit def fromJIntBuffer(from: java.nio.IntBuffer): Vector[Int] = FromJIntBuffer(from)
    implicit def fromJLongBuffer(from: java.nio.LongBuffer): Vector[Long] = FromJLongBuffer(from)
    implicit def fromJShortBuffer(from: java.nio.ShortBuffer): Vector[Short] = FromJShortBuffer(from)
}
