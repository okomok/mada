

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * The root of the "meta class" hierarchy
 */
trait Object {
    type eq[that <: Object] <: Meta.Boolean // probably infeasible. isInt etc will be feasible in 2.8, though.
    type identityHashCode[_] <: Meta.Integer // how?

//    type equals[that <: Object] = eq[that]
    type hashCode[_] = identityHashCode[_]

    type isBind <: Meta.Boolean
    type isBoxed <: Meta.Boolean
}


/**
 * Boxes <code>a</code> into <code>Object</code>.
 */
sealed trait box[a] extends Object {
    override type isBind = Meta.`false`
    override type isBoxed = Meta.`true`
    type unbox = a
}
