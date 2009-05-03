

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import blend._


/**
 * Gets runtime and meta programming to blend.
 */
object Blend {

    /**
     * Conditional compilation based on meta boolean value.
     */
    def `if`[A, b <: Meta.Boolean](block: => A)(implicit _if: If[A, b]): Then[A] = _if(block)

    /**
     * Conditional compilation based on meta boolean value.
     */
    def doIf[b <: Meta.Boolean](block: => Unit)(implicit _doIf: DoIf[b]): Unit = _doIf(block)

}
