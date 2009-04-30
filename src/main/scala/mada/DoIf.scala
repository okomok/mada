

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


// See: Meta-Programming with Scala: Conditional Compilation and Loop Unrolling
//      at http://michid.wordpress.com/2008/10/29/meta-programming-with-scala-conditional-compilation-and-loop-unrolling/


/**
 * Provides conditional compilation based on meta boolean value.
 */
object DoIf {

    /**
     * Following statements are evaluated if <code>b</code> is <code>`true`</code>.
     */
    def doIf[b <: Meta.Boolean] = Meta.unmeta[b]

    object MadaDoIfTrue {
        // Without Yclosure-elim, by-name parameter is not free.
        def apply(block: => Unit): Unit = block
    }
    object MadaDoIfFalse {
        def apply(block: => Unit): Unit = ()
    }

    implicit def madaDoIfTrue(t: Meta.`true`) = MadaDoIfTrue
    implicit def madaDoIfFalse(f: Meta.`false`) = MadaDoIfFalse

}
