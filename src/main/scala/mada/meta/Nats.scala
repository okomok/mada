

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta natural numbers,
 * which has constant-time methods only.
 */
trait Nats { this: Meta.type =>

    sealed trait Nat extends Object with Operatable_== with Operatable_++ with Operatable_-- {
        type increment[_] <: Nat
        type decrement[_] <: Nat

        type equals[that <: Nat] <: Boolean

        override type Self = Nat
        override type operate_==[that <: Self] = equals[that]
        override type operate_++[_] = increment[_]
        override type operate_--[_] = decrement[_]

        private[mada] type is0 <: Boolean
        private[mada] type is1 <: Boolean
        private[mada] type is2 <: Boolean
        private[mada] type is3 <: Boolean
        private[mada] type is4 <: Boolean
        private[mada] type is5 <: Boolean
        private[mada] type is6 <: Boolean
        private[mada] type is7 <: Boolean
        private[mada] type is8 <: Boolean
        private[mada] type is9 <: Boolean
        private[mada] type is10 <: Boolean
    }

    sealed trait _0N extends Nat {
        override type increment[_] = _1N
        override type decrement[_] = throwError

        override type equals[that <: Nat] = that#is0

        private[mada] override type is0 = `true`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _1N extends Nat {
        override type increment[_] = _2N
        override type decrement[_] = _0N

        override type equals[that <: Nat] = that#is1

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `true`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _2N extends Nat {
        override type increment[_] = _3N
        override type decrement[_] = _1N

        override type equals[that <: Nat] = that#is2

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `true`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _3N extends Nat {
        override type increment[_] = _4N
        override type decrement[_] = _2N

        override type equals[that <: Nat] = that#is3

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `true`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _4N extends Nat {
        override type increment[_] = _5N
        override type decrement[_] = _3N

        override type equals[that <: Nat] = that#is4

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `true`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _5N extends Nat {
        override type increment[_] = _6N
        override type decrement[_] = _4N

        override type equals[that <: Nat] = that#is5

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `true`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _6N extends Nat {
        override type increment[_] = _7N
        override type decrement[_] = _5N

        override type equals[that <: Nat] = that#is6

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `true`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _7N extends Nat {
        override type increment[_] = _8N
        override type decrement[_] = _6N

        override type equals[that <: Nat] = that#is7

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `true`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _8N extends Nat {
        override type increment[_] = _9N
        override type decrement[_] = _7N

        override type equals[that <: Nat] = that#is8

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `true`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _9N extends Nat {
        override type increment[_] = _10N
        override type decrement[_] = _8N

        override type equals[that <: Nat] = that#is9

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `true`
        private[mada] override type is10 = `false`
    }

    sealed trait _10N extends Nat {
        override type increment[_] = throwError
        override type decrement[_] = _9N

        override type equals[that <: Nat] = that#is10

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `true`
    }

}
