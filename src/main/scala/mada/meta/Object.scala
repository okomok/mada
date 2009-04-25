

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * The root of the "meta class" hierarchy
 */
trait Object {
    type isBind[void] <: Meta.Boolean
    type isBoxed[void] <: Meta.Boolean
}


/**
 * Boxes <code>a</code> into <code>Object</code>.
 */
final class newObject[a] extends Object {
    override type isBind[void] = Meta.`false`
    override type isBoxed[void] = Meta.`true`
    type unbox = a
}
