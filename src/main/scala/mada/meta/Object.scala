

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * The root of the "meta class" hierarchy
 */
trait Object {
    type isBind <: Meta.Boolean
    type isBoxed <: Meta.Boolean
}


/**
 * Boxes <code>a</code> into <code>Object</code>.
 */
final class newObject[a] extends Object {
    override type isBind = Meta.`false`
    override type isBoxed = Meta.`true`
    type unbox = a
}
