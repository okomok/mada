

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


// See: Meta-Programming with Scala: Conditional Compilation and Loop Unrolling
//      at http://michid.wordpress.com/2008/10/29/meta-programming-with-scala-conditional-compilation-and-loop-unrolling/


/**
 * Provides conditional compilation based on meta boolean value.
 */
object If {

    /**
     * Following statements are evaluated if <code>b</code> is <code>`true`</code>.
     */
    def madaBlendIf[b <: Meta.Boolean] = null.asInstanceOf[b]

    object MadaBlendIfTrue {
        // Without Yclosure-elim, by-name parameter is not free.
        def apply(block: => Unit): Unit = block
    }

    object MadaBlendIfFalse {
        def apply(block: => Unit): Unit = ()
    }

    implicit def madaBlendIfTrue(t: Meta.`true`): MadaBlendIfTrue.type = MadaBlendIfTrue
    implicit def madaBlendIfFalse(f: Meta.`false`): MadaBlendIfFalse.type = MadaBlendIfFalse

}
