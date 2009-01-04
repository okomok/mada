

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Compatibles {
    implicit def array2madaVector[A](from: Array[A]): Vector[A] = Vector.arrayVector(from)
    implicit def cell2madaVector[A](from: Cell[A]): Vector[A] = Vector.cellVector(from)
    implicit def option2madaVector[A](from: Option[A]): Vector[A] = Vector.optionVector(from)
    implicit def product2madaVector(from: Product): Vector[Any] = Vector.productVector(from)
    implicit def randomAccessSeq2madaVector[A](from: RandomAccessSeq[A]): Vector[A] = Vector.randomAccessSeqVector(from)
    implicit def string2madaVector(from: String): Vector[Char] = Vector.stringVector(from)
}