

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import blend._


/**
 * Gets runtime and meta programming to blend.
 */
object Blend extends IfImplicits {

    /**
     * Conditional compilation based on meta boolean value.
     */
    def `if`[A, b <: Meta.Boolean](block: => A): If[A, b] = If.apply[A, b](block)

    /**
     * @return  <code>this</code>.
     */
    val IfImplicits: blend.IfImplicits = this

    /**
     * Conditional compilation based on meta boolean value.
     */
    def doIf[b <: Meta.Boolean](block: => Unit)(implicit c: DoIf[b]): Unit = c(block)

}
