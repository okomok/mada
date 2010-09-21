

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Iterate[A](_1: A, _2: A => A) extends TrivialGenerator[A] {
    private var acc = _1
    override def generateOne = { out(acc); acc = _2(acc) }
    override val head = _1
}
