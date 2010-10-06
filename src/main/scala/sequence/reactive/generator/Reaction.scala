

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Reaction[A](from: A => Unit) extends (A => Unit) {
    override def apply(x: A) = from(x)
}
