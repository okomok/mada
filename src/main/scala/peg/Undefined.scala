

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
case class Undefined[A]() extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        throw new java.lang.AssertionError("undefined")
    }
}
