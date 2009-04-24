

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


import Meta._


/**
 * Metafunction always returning <code>a</code>
 */
final class always[a <: Object] extends FunctionV {
    override type isBind = `false`

    override type apply0[Void] = a
    override type apply1[b1 <: Object] = a
    override type apply2[b1 <: Object, b2 <: Object] = a
    override type apply3[b1 <: Object, b2 <: Object, b3 <: Object] = a
}
