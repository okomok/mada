

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend


// See: meta.Programming with Scala: Conditional Compilation and Loop Unrolling
//      at http://michid.wordpress.com/2008/10/29/meta-programming-with-scala-conditional-compilation-and-loop-unrolling/


@specializer
sealed abstract class DoIf[b <: meta.Boolean] {
    def apply(block: => Unit): Unit
}


object DoIf {

    implicit object ofTrue extends DoIf[meta.`true`] {
        override def apply(block: => Unit) = block
    }

    implicit object ofFalse extends DoIf[meta.`false`] {
        override def apply(block: => Unit) = ()
    }

}
