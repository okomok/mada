

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta Boolean functionalities.
 */
trait Booleans { this: Meta.type =>

    trait Boolean extends Object {
        type and[that <: Boolean] <: Boolean
        type or[that <: Boolean] <: Boolean
        type not <: Boolean
        type equals[that <: Boolean] <: Boolean

        override type Operand_== = Boolean
        override type operator_==[that <: Boolean] = equals[that]

        override type Operand_&& = Boolean
        override type operator_&&[that <: Boolean] = and[that]
        override type Operand_|| = Boolean
        override type operator_||[that <: Boolean] = or[that]
        override type operator_! = not

        private[mada] type isTrue <: Boolean
        private[mada] type isFalse <: Boolean

        private[mada] type _if[then <: Object, _else <: Object] <: Object
        // private[mada] type _if_[then <: Object, _else <: then#This] <: then#This // doesn't work in dependent context.
        // private[mada] type ifThen[then <: Object] <: then // doesn't work, too.
        private[mada] type natIf[then <: Nat, _else <: Nat] <: Nat
    }

    sealed trait `true` extends Boolean {
        override type and[that <: Boolean] = that
        override type or[that <: Boolean] = `true`
        override type not = `false`
        override type equals[that <: Boolean] = that#isTrue

        private[mada] override type isTrue = `true`
        private[mada] override type isFalse = `false`

        private[mada] override type _if[then <: Object, _else <: Object] <: then
        // private[mada] override type _if_[then <: Object, _else <: then#This] = then#`this`
        // private[mada] override type ifThen[then <: Object] = then
        private[mada] override type natIf[then <: Nat, _else <: Nat] = then
    }

    sealed trait `false` extends Boolean {
        override type and[that <: Boolean] = `false`
        override type or[that <: Boolean] = that
        override type not = `true`
        override type equals[that <: Boolean] = that#isFalse

        private[mada] override type isTrue = `false`
        private[mada] override type isFalse = `true`

        private[mada] override type _if[then <: Object, _else <: Object] <: _else
        // private[mada] override type _if_[then <: Object, _else <: then#This] = _else
        // private[mada] override type ifThen[then <: Object] = Nothing
        private[mada] override type natIf[then <: Nat, _else <: Nat] = _else
    }

    type `if`[cond <: Boolean, then <: Object, _else <: Object] = cond#_if[then, _else]
    // type _if_[cond <: Boolean, then <: Object, _else <: then#This] = cond#_if_[then, _else]
    // type ifThen[cond <: Boolean, then <: Object] = cond#ifThen[then]
    type natIf[cond <: Boolean, then <: Nat, _else <: Nat] = cond#natIf[then, _else]

    implicit val unmeta_true_Boolean = Unmeta[`true`, scala.Boolean](true)
    implicit val unmeta_false_Boolean = Unmeta[`false`, scala.Boolean](false)

}
