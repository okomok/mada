

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


// See: Meta-Programming with Scala: Conditional Compilation and Loop Unrolling
//      at http://michid.wordpress.com/2008/10/29/meta-programming-with-scala-conditional-compilation-and-loop-unrolling/


/**
 * Intermediate trait for implicit conversions.
 */
sealed trait DoIf[b <: Meta.Boolean]

/**
 * Contains implicit conversions for <code>doIf</code>.
 */
trait DoIfImplicits { this: Blend.type =>

    object MadaBlendDoIfTrue {
        def apply(block: => Unit): Unit = block
    }

    object MadaBlendDoIfFalse {
        def apply(block: => Unit): Unit = ()
    }

    implicit def madaBlendDoIfTrue(t: DoIf[Meta.`true`]): MadaBlendDoIfTrue.type = MadaBlendDoIfTrue
    implicit def madaBlendDoIfFalse(f: DoIf[Meta.`false`]): MadaBlendDoIfFalse.type = MadaBlendDoIfFalse

}

private[mada] object DoIf {
    def apply[b <: Meta.Boolean]: DoIf[b] = new DoIf[b]{}
}
