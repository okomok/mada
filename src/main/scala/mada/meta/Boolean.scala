

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: http://www.assembla.com/wiki/show/metascala


/**
 * Contains meta Boolean functionalities.
 */
trait Booleans { this: Meta.type =>

    sealed trait Boolean extends Object {
        type and[that <: Boolean] <: Boolean
        type or[that <: Boolean] <: Boolean
        type not <: Boolean

        private[mada] type if_[then <: Object, else_ <: then] <: then
    }

    final class `true` extends Boolean {
        override type and[that <: Boolean] = that
        override type or[that <: Boolean] = `true`
        override type not = `false`

        private[mada] override type if_[then <: Object, else_ <: then] = then
    }

    final class `false` extends Boolean {
        override type and[that <: Boolean] = `false`
        override type or[that <: Boolean] = that
        override type not = `true`

        private[mada] override type if_[then <: Object, else_ <: then] = else_
    }

    // TODO: Move to Operators.
    type &&[a <: Boolean, b <: Boolean] = a#and[b]
    type ||[a <: Boolean, b <: Boolean] = a#or[b]
    type ![a <: Boolean] = a#not

    type `if`[cond <: Boolean, then <: Object, else_ <: then] = cond#if_[then, else_]

}
