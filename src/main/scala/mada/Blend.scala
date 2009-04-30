

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import blend._


/**
 * Gets runtime and meta programming to blend.
 */
object Blend {

    /**
     * Alias of <code>blend.If</code>
     */
    val If = blend.If

    /**
     * Alias of <code>blend.If.madaBlendIf</code>
     */
    def `if`[b <: Meta.Boolean] = If.madaBlendIf[b]

}
