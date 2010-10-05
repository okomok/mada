

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


@compilerBug("2.8.0-SNAPSHOT")
private
case class Single[A](/*@volatile ..compiler error!*/ var _1: A) extends Vector[A] {
    override def start = 0
    override def end = 1
    override def apply(i: Int) = _1
    override def update(i: Int, e: A) = _1 = e
}
