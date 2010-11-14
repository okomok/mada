

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg; package prettyprinter


private
case class Trash() extends PrettyPrinter {
    override def close() = ()
    override def print[A](p: Peg[A]) = p
}
