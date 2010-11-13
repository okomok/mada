

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector; package stl


private
object OutputBy {
    def apply[A](f: A => Unit): Vector[A] = new OutputBy(f)
}

private
class OutputBy[A](f: A => Unit) extends OutputVector[A] {
    override def output(e: A) = f(e)
}
