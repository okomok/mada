

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Provides meta integers.
 * Meta.Int is strongly-typed, meaning that it can support <code>equals</code>
 * but can't be used as recursive specializer.
 */
@provider
trait Ints { this: Meta.type =>

    trait Int extends Operatable {
        private[mada] type `this` <: Int

        type increment <: Int
        type decrement <: Int
        type equals[that <: Int] <: Boolean

        type add[that <: Int] <: Int
        type minus[that <: Int] <: Int
        type multiply[that <: Int] <: Int

        final override type Operand_== = Int
        final override type operator_==[that <: Int] = equals[that]

        final override type operator_++ = increment
        final override type operator_-- = decrement

        final override type Operand_+ = Int
        final override type operator_+[that <: Int] = add[that]
        final override type Operand_- = Int
        final override type operator_-[that <: Int] = minus[that]
        final override type Operand_* = Int
        final override type operator_*[that <: Int] = multiply[that]

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

        // Parameterized FoldLeftFunction won't work, hence z can't be used as basis.
        type FoldLeftFunction = Function2 { type Argument22 >: Int; type Result2 <: Argument21 }
        type foldLeft[z <: op#Result2, op <: FoldLeftFunction] <: op#Result2

        type toNat <: Nat
        type toList <: List
    }

    sealed trait _0I extends Int {
        override type increment = _1I
        override type decrement = Nothing
        override type equals[that <: Int] = that#is0

        override type add[that <: Int] = that
        override type minus[that <: Int] = error
        override type multiply[that <: Int] = _0I

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

        override type foldLeft[z <: op#Result2, op <: FoldLeftFunction] = z
        override type toNat = _0N
        override type toList = nil
    }

    sealed trait PositiveInt extends Int {
        final override type add[that <: Int] = decrement#add[that]#increment
        final override type minus[that <: Int] = decrement#minus[that]#increment
        final override type multiply[that <: Int] = decrement#multiply[that]#add[that]

        final override type foldLeft[z <: op#Result2, op <: FoldLeftFunction] = decrement#foldLeft[op#apply2[z, decrement], op]
        final override type toList = cons[decrement, decrement#toList]
    }

    sealed trait _1I extends Int with PositiveInt {
        override type increment = _2I
        override type decrement = _0I
        override type equals[that <: Int] = that#is1

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

        override type toNat = _1N
    }

    sealed trait _2I extends Int with PositiveInt {
        override type increment = _3I
        override type decrement = _1I
        override type equals[that <: Int] = that#is2

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

        override type toNat = _2N
    }

    sealed trait _3I extends Int with PositiveInt {
        override type increment = _4I
        override type decrement = _2I
        override type equals[that <: Int] = that#is3

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

        override type toNat = _3N
    }

    sealed trait _4I extends Int with PositiveInt {
        override type increment = _5I
        override type decrement = _3I
        override type equals[that <: Int] = that#is4

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

        override type toNat = _4N
    }

    sealed trait _5I extends Int with PositiveInt {
        override type increment = _6I
        override type decrement = _4I
        override type equals[that <: Int] = that#is5

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

        override type toNat = _5N
    }

    sealed trait _6I extends Int with PositiveInt {
        override type increment = _7I
        override type decrement = _5I
        override type equals[that <: Int] = that#is6

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

        override type toNat = _6N
    }

    sealed trait _7I extends Int with PositiveInt {
        override type increment = _8I
        override type decrement = _6I
        override type equals[that <: Int] = that#is7

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

        override type toNat = _7N
     }

    sealed trait _8I extends Int with PositiveInt {
        override type increment = _9I
        override type decrement = _7I
        override type equals[that <: Int] = that#is8

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

        override type toNat = _8N
    }

    sealed trait _9I extends Int with PositiveInt {
        override type increment = _10I
        override type decrement = _8I
        override type equals[that <: Int] = that#is9

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

        override type toNat = _9N
    }

    sealed trait _10I extends Int with PositiveInt {
        override type increment = Nothing
        override type decrement = _9I
        override type equals[that <: Int] = that#is10

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

        override type toNat = _10N
    }

}
