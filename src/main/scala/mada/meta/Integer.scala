

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta integers.
 */
trait Integers { this: Meta.type =>

    sealed trait Integer extends Object with Operatable_+ {
        type increment[void] <: Integer
        type decrement[void] <: Integer
        type negate[void] <: Integer
        type plus[that <: Integer] <: Integer
        type minus[that <: Integer] <: Integer
        type multiply[that <: Integer] <: Integer

        override type Self = Integer
        override type operate_+[that <: Integer] = plus[that]
    }

    final class _0 extends Integer {
        override type increment[void] = _1
        override type decrement[void] = throwError[void]
        override type negate[void] = _0
        override type plus[that <: Integer] = that
        override type minus[that <: Integer] = that#negate[void]
        override type multiply[that <: Integer] = _0
    }

    sealed trait IntegerImpl[d <: Integer, i <: Integer] extends Integer {
        override type increment[void] = i
        override type decrement[void] = d

        // Results in illegal cyclic reference, anyway: without -Yrecursion flag.
        override type negate[void] = d#negate[void]#decrement[void]
        override type plus[that <: Integer] = d#plus[that]#increment[void]
        override type minus[that <: Integer] = d#minus[that]#increment[void]
        override type multiply[that <: Integer] = d#multiply[that]#plus[d]
    }

    // No recursions like C macros.

    final class _1 extends IntegerImpl[_0, _2]
    final class _2 extends IntegerImpl[_1, _3]
    final class _3 extends IntegerImpl[_2, _4]
    final class _4 extends IntegerImpl[_3, _5]
    final class _5 extends IntegerImpl[_4, _6]
    final class _6 extends IntegerImpl[_5, _7]
    final class _7 extends IntegerImpl[_6, _8]
    final class _8 extends IntegerImpl[_7, _9]
    final class _9 extends IntegerImpl[_8, _10]
    final class _10 extends IntegerImpl[_9, Nothing]

/*
    final class succ[n <: Integer] extends Integer {
        // This recursive call crashes compiler. Maybe -Yrecursion flag is needed?
        // private type `this` = succ[n]
        // override type increment[void] = succ[`this`]
        override type decrement[void] = n
        override type negate[void] = n#negate[void]#decrement[void]
        override type plus[that <: Integer] = n#plus[that]#increment[void]
        override type minus[that <: Integer] = n#minus[that]#increment[void]
        override type multiply[that <: Integer] = n#multiply[that]#plus[n]
    }

    type _1 = succ[_0]
    type _2 = succ[_1]
    type _3 = succ[_2]
    type _4 = succ[_3]
    type _5 = succ[_4]
    type _6 = succ[_5]
    type _7 = succ[_6]
    type _8 = succ[_7]
    type _9 = succ[_8]
    type _10 = succ[_9]
*/

}
