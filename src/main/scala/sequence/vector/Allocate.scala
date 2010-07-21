

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


import scala.collection.mutable.ArraySeq


private[mada] case class Allocate[A](_1: Int) extends Forwarder[A] {
    override protected val delegate = from(new ArraySeq[A](_1))
}
