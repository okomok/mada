

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

        private[mada] type if_[then <: Object, _else <: then] <: then
        private[mada] type lazyIf[then <: Function0, _else <: Function0 { type Result0 <: then#Result0 }] <: then#Result0
    }

    sealed trait `true` extends Boolean {
        override type and[that <: Boolean] = that
        override type or[that <: Boolean] = `true`
        override type not = `false`

        private[mada] override type if_[then <: Object, _else <: then] = then
        private[mada] override type lazyIf[then <: Function0, _else <: Function0 { type Result0 <: then#Result0 }] = then#apply0
    }

    sealed trait `false` extends Boolean {
        override type and[that <: Boolean] = `false`
        override type or[that <: Boolean] = that
        override type not = `true`

        private[mada] override type if_[then <: Object, _else <: then] = _else
        private[mada] override type lazyIf[then <: Function0, _else <: Function0 { type Result0 <: then#Result0 }] = _else#apply0
    }

    // TODO: Move to Operators.
    type &&[a <: Boolean, b <: Boolean] = a#and[b]
    type ||[a <: Boolean, b <: Boolean] = a#or[b]
    type ![a <: Boolean] = a#not

    type `if`[cond <: Boolean, then <: Object, _else <: then] = cond#if_[then, _else]
    type lazyIf[cond <: Boolean, then <: Function0, _else <: Function0 { type Result0 <: then#Result0 }] = cond#lazyIf[then, _else]

}
