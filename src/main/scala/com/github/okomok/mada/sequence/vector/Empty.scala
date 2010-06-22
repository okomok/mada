

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


private[mada] case class Empty[A]() extends Vector[A] {
    override def start = 0
    override def end = 0
}
