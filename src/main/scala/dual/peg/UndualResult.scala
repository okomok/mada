

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


import scala.collection.immutable.{Seq => SSeq}


sealed abstract class UndualResult[+T] {
    def get: T
    def next: SSeq[scala.Any]
}

final case class UndualSuccess[+T](override val get: T, override val next: SSeq[scala.Any]) extends UndualResult[T]

final case class UndualFailure(override val next: SSeq[scala.Any]) extends UndualResult[Nothing] {
    override def get = throw new NoSuchElementException("peg.UndualFailure.get")
}
