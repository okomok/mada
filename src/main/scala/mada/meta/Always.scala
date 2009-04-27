

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


import Meta._


/**
 * Metafunction always returning <code>a</code>
 */
trait always[a <: Object] extends Function0 with Function1 with Function2 with Function3 {
    override type isBind = `false`

    override type apply0 = a

    override type Argument11 = Object
    override type apply1[v1 <: Argument11] = a

    override type Argument21 = Object
    override type Argument22 = Object
    override type apply2[v1 <: Argument21, v2 <: Argument22] = a

    override type Argument31 = Object
    override type Argument32 = Object
    override type Argument33 = Object
    override type apply3[v1 <: Argument31, v2 <: Argument32, v3 <: Argument33] = a
}
