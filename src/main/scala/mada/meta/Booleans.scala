

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta Boolean functionalities.
 */
trait Booleans { this: Meta.type =>

    trait Boolean extends Object with Operatable_==  {
        type and[that <: Boolean] <: Boolean
        type or[that <: Boolean] <: Boolean
        type not <: Boolean
        type equals[that <: Boolean] <: Boolean

        override type Self = Boolean
        override type operate_==[that <: Self] = equals[that]

        private[mada] type isTrue <: Boolean
        private[mada] type isFalse <: Boolean


        // Seems millenium problem...

        private[mada] type _if[then <: R, _else <: R, R <: Object] <: R
        private[mada] type anyIf[then, _else]

        private[mada] type natIf[then <: Nat, _else <: Nat] <: Nat

        private[mada] type fIf[then <: Function0, _else <: Function0] <: Function0

    }

    trait `true` extends Boolean {
        override type and[that <: Boolean] = that
        override type or[that <: Boolean] = `true`
        override type not = `false`
        override type equals[that <: Boolean] = that#isTrue

        private[mada] override type isTrue = `true`
        private[mada] override type isFalse = `false`




        private[mada] override type _if[then <: R, _else <: R, R <: Object] = then
        private[mada] override type anyIf[then, _else] = then

        private[mada] override type natIf[then <: Nat, _else <: Nat] = then

        private[mada] override type fIf[then <: Function0, _else <: Function0] = then
    }

    trait `false` extends Boolean {
        override type and[that <: Boolean] = `false`
        override type or[that <: Boolean] = that
        override type not = `true`
        override type equals[that <: Boolean] = that#isFalse

        private[mada] override type isTrue = `false`
        private[mada] override type isFalse = `true`



        private[mada] override type _if[then <: R, _else <: R, R <: Object] = _else
        private[mada] override type anyIf[then, _else] = _else

        private[mada] override type natIf[then <: Nat, _else <: Nat] = _else

        private[mada] override type fIf[then <: Function0, _else <: Function0] = then
    }

    trait IfOf[T <: Object] extends Function2 {
        override type Argument21 = T
        override type Argument22 = T
        override type apply2[then <: T, _else <: T] <: T
    }

    // TODO: Move to Operators.
    type &&[a <: Boolean, b <: Boolean] = a#and[b]
    type ||[a <: Boolean, b <: Boolean] = a#or[b]
    type ![a <: Boolean] = a#not

    type `if`[cond <: Boolean, then <: R, _else <: R, R <: Object] = cond#_if[then, _else, R]
    type anyIf[cond <: Boolean, then, _else] = cond#anyIf[then, _else]

    type natIf[cond <: Boolean, then <: Nat, _else <: Nat] = cond#natIf[then, _else]

    type fIf[cond <: Boolean, then <: Function0, _else <: Function0] = cond#fIf[then, _else]


    trait If[R <: Object] extends Function3 {
        override type Argument31 <: Boolean
        override type Argument32 <: R
        override type Argument33 <: R
        override type apply3[cond <: Argument31, then <: Argument32, _else <: Argument33] = cond#_if[then, _else, R]
    }

}
