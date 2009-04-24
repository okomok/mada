

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * The root of the "meta class" hierarchy
 */
trait Object {
    type isBind <: Meta.Boolean
    type IsBoxed <: Meta.Boolean
}
