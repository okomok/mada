

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import blend._


/**
 * Gets runtime and meta programming to blend.
 */
object Blend {

    /**
     * Alias of <code>blend.DoIf</code>
     */
    val DoIf = blend.DoIf

    /**
     * Alias of <code>blend.DoIf.madaBlendDoIf</code>
     */
    def doIf[b <: Meta.Boolean] = DoIf.madaBlendDoIf[b]

}
