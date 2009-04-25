

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

        private[mada] type _if[R, then <: R, _else <: R] <: R
        private[mada] type lazyIf[R, then <: Function0 { type Result0 <: R }, _else <: Function0 { type Result0 <: R }] <: R
    }

    sealed trait `true` extends Boolean {
        override type and[that <: Boolean] = that
        override type or[that <: Boolean] = `true`
        override type not = `false`

        private[mada] override type _if[R, then <: R, _else <: R] = then
        private[mada] override type lazyIf[R, then <: Function0 { type Result0 <: R }, _else <: Function0 { type Result0 <: R }] = then#apply0
    }

    sealed trait `false` extends Boolean {
        override type and[that <: Boolean] = `false`
        override type or[that <: Boolean] = that
        override type not = `true`

        private[mada] override type _if[R, then <: R, _else <: R] = _else
        private[mada] override type lazyIf[R, then <: Function0 { type Result0 <: R }, _else <: Function0 { type Result0 <: R }] = _else#apply0
    }

    // TODO: Move to Operators.
    type &&[a <: Boolean, b <: Boolean] = a#and[b]
    type ||[a <: Boolean, b <: Boolean] = a#or[b]
    type ![a <: Boolean] = a#not

    type `if`[R, cond <: Boolean, then <: R, _else <: R] = cond#_if[R, then, _else]
    type lazyIf[R, cond <: Boolean, then <: Function0 { type Result0 <: R }, _else <: Function0 { type Result0 <: R }] = cond#lazyIf[R, then, _else]

}
