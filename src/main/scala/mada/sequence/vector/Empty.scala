

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


case class Empty[A]() extends Vector[A] {
    override def start = 0
    override def end = 0
}
