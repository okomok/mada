

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta natural numbers,
 * which have constant-time methods only.
 */
trait Nats { this: Meta.type =>

    trait Nat extends Object with Operatable_== with Operatable_++ with Operatable_-- {
        type increment <: Nat
        type decrement <: Nat
        type equals[that <: Nat] <: Boolean

        override type Self = Nat
        override type operate_==[that <: Self] = equals[that]
        override type operate_++ = increment
        override type operate_-- = decrement

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

    trait _0N extends Nat {
        override type increment = _1N
        override type decrement = throwError
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

    trait _1N extends Nat {
        override type increment = _2N
        override type decrement = _0N
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

    trait _2N extends Nat {
        override type increment = _3N
        override type decrement = _1N
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

    trait _3N extends Nat {
        override type increment = _4N
        override type decrement = _2N
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

    trait _4N extends Nat {
        override type increment = _5N
        override type decrement = _3N
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

    trait _5N extends Nat {
        override type increment = _6N
        override type decrement = _4N
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

    trait _6N extends Nat {
        override type increment = _7N
        override type decrement = _5N
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

    trait _7N extends Nat {
        override type increment = _8N
        override type decrement = _6N
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

    trait _8N extends Nat {
        override type increment = _9N
        override type decrement = _7N
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

    trait _9N extends Nat {
        override type increment = _10N
        override type decrement = _8N
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

    trait _10N extends Nat {
        override type increment = throwError
        override type decrement = _9N
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
