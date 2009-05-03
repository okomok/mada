

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


// See: Meta-Programming with Scala: Conditional Compilation and Loop Unrolling
//      at http://michid.wordpress.com/2008/10/29/meta-programming-with-scala-conditional-compilation-and-loop-unrolling/


/**
 * Contains implicit objects for <code>doIf</code>.
 */
object DoIf {

    implicit object doIf_true extends DoIf[Meta.`true`] {
        override def apply(block: => Unit) = block
    }

    implicit object doIf_false extends DoIf[Meta.`false`] {
        override def apply(block: => Unit) = ()
    }

}

/**
 * Executes a block based on meta boolean value.
 */
trait DoIf[b <: Meta.Boolean] {
    def apply(block: => Unit): Unit
}
