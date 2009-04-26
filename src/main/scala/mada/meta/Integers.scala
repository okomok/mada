

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta integers.
 */
trait Integers { this: Meta.type =>

    sealed trait Integer extends Object with Operatable_+ {
        type increment[_] <: Integer
        type decrement[_] <: Integer
        type negate[_] <: Integer

        type add[that <: Integer] <: Integer
        type minus[that <: Integer] <: Integer
        type multiply[that <: Integer] <: Integer
/*
        // Results in illegal cyclic reference. -Yrecursion is needed?
        type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
*/
        override type Self = Integer
        override type operate_+[that <: Self] = add[that]
    }

/*
    sealed trait _0I extends Integer {
        override type increment[_] = _1I
        override type decrement[_] = throwError
        override type negate[_] = _0I

        override type add[that <: Integer] = that
        override type minus[that <: Integer] = that#negate[_]
        override type multiply[that <: Integer] = _0I
    }

    sealed trait _1I extends Integer {
        override type increment[_] = _2I
        override type decrement[_] = _0I
        override type negate[_] = throwError

        override type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        override type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        override type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
    }

    sealed trait _2I extends Integer {
        override type increment[_] = _3I
        override type decrement[_] = _1I
        override type negate[_] = throwError

        override type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        override type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        override type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
    }

    sealed trait _3I extends Integer {
        override type increment[_] = _4I
        override type decrement[_] = _2I
        override type negate[_] = throwError

        override type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        override type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        override type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
    }

    sealed trait _4I extends Integer {
        override type increment[_] = _5I
        override type decrement[_] = _3I
        override type negate[_] = throwError

        override type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        override type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        override type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
    }

    sealed trait _5I extends Integer {
        override type increment[_] = throwError
        override type decrement[_] = _4I
        override type negate[_] = throwError

        override type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        override type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        override type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
    }
*/
/*
    // No longer compiles in human history.
    // It seems "ETI" like C++: all the possible computation is instantiated!?

    sealed trait _6I extends Integer {
        override type increment[_] = _7I
        override type decrement[_] = _5I
        override type negate[_] = throwError

        override type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        override type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        override type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
    }


    sealed trait _7I extends Integer {
        override type increment[_] = _8I
        override type decrement[_] = _6I
        override type negate[_] = throwError

        override type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        override type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        override type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
    }

    sealed trait _8I extends Integer {
        override type increment[_] = _9I
        override type decrement[_] = _7I
        override type negate[_] = throwError

        override type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        override type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        override type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
    }

    sealed trait _9I extends Integer {
        override type increment[_] = _10I
        override type decrement[_] = _8I
        override type negate[_] = throwError

        override type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        override type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        override type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
    }

    sealed trait _10I extends Integer {
        override type increment[_] = throwError
        override type decrement[_] = _9I
        override type negate[_] = throwError

        override type add[that <: Integer] = decrement[_]#add[that]#increment[_]
        override type minus[that <: Integer] = decrement[_]#minus[that]#increment[_]
        override type multiply[that <: Integer] = decrement[_]#multiply[that]#add[that]
    }
*/
}
