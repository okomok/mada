

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Repeat[A](_1: A) extends GeneratorOnce[A] {
    override def generate = out(_1)
    override val head = _1
}
