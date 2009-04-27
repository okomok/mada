

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta integers.
 */
trait Integers { this: Meta.type =>

    trait Integer extends Object with Operatable_+ {
        type increment <: Integer
        type decrement <: Integer
        type negate <: Integer

        type add[that <: Integer] <: Integer
        type minus[that <: Integer] <: Integer
        type multiply[that <: Integer] <: Integer
/*
        // Results in illegal cyclic reference. -Yrecursion is needed?
        type add[that <: Integer] = decrement#add[that]#increment
        type minus[that <: Integer] = decrement#minus[that]#increment
        type multiply[that <: Integer] = decrement#multiply[that]#add[that]
*/
        override type Self = Integer
        override type operate_+[that <: Self] = add[that]
    }

/*
    trait _0I extends Integer {
        override type increment = _1I
        override type decrement = throwError
        override type negate = _0I

        override type add[that <: Integer] = that
        override type minus[that <: Integer] = that#negate
        override type multiply[that <: Integer] = _0I
    }

    trait _1I extends Integer {
        override type increment = _2I
        override type decrement = _0I
        override type negate = throwError

        override type add[that <: Integer] = decrement#add[that]#increment
        override type minus[that <: Integer] = decrement#minus[that]#increment
        override type multiply[that <: Integer] = decrement#multiply[that]#add[that]
    }

    trait _2I extends Integer {
        override type increment = _3I
        override type decrement = _1I
        override type negate = throwError

        override type add[that <: Integer] = decrement#add[that]#increment
        override type minus[that <: Integer] = decrement#minus[that]#increment
        override type multiply[that <: Integer] = decrement#multiply[that]#add[that]
    }

    trait _3I extends Integer {
        override type increment = _4I
        override type decrement = _2I
        override type negate = throwError

        override type add[that <: Integer] = decrement#add[that]#increment
        override type minus[that <: Integer] = decrement#minus[that]#increment
        override type multiply[that <: Integer] = decrement#multiply[that]#add[that]
    }

    trait _4I extends Integer {
        override type increment = _5I
        override type decrement = _3I
        override type negate = throwError

        override type add[that <: Integer] = decrement#add[that]#increment
        override type minus[that <: Integer] = decrement#minus[that]#increment
        override type multiply[that <: Integer] = decrement#multiply[that]#add[that]
    }

    trait _5I extends Integer {
        override type increment = throwError
        override type decrement = _4I
        override type negate = throwError

        override type add[that <: Integer] = decrement#add[that]#increment
        override type minus[that <: Integer] = decrement#minus[that]#increment
        override type multiply[that <: Integer] = decrement#multiply[that]#add[that]
    }
*/
/*
    // No longer compiles in human history.
    // It seems "ETI" like C++: all the possible computation is instantiated!?

    trait _6I extends Integer {
        override type increment = _7I
        override type decrement = _5I
        override type negate = throwError

        override type add[that <: Integer] = decrement#add[that]#increment
        override type minus[that <: Integer] = decrement#minus[that]#increment
        override type multiply[that <: Integer] = decrement#multiply[that]#add[that]
    }


    trait _7I extends Integer {
        override type increment = _8I
        override type decrement = _6I
        override type negate = throwError

        override type add[that <: Integer] = decrement#add[that]#increment
        override type minus[that <: Integer] = decrement#minus[that]#increment
        override type multiply[that <: Integer] = decrement#multiply[that]#add[that]
    }

    trait _8I extends Integer {
        override type increment = _9I
        override type decrement = _7I
        override type negate = throwError

        override type add[that <: Integer] = decrement#add[that]#increment
        override type minus[that <: Integer] = decrement#minus[that]#increment
        override type multiply[that <: Integer] = decrement#multiply[that]#add[that]
    }

    trait _9I extends Integer {
        override type increment = _10I
        override type decrement = _8I
        override type negate = throwError

        override type add[that <: Integer] = decrement#add[that]#increment
        override type minus[that <: Integer] = decrement#minus[that]#increment
        override type multiply[that <: Integer] = decrement#multiply[that]#add[that]
    }

    trait _10I extends Integer {
        override type increment = throwError
        override type decrement = _9I
        override type negate = throwError

        override type add[that <: Integer] = decrement#add[that]#increment
        override type minus[that <: Integer] = decrement#minus[that]#increment
        override type multiply[that <: Integer] = decrement#multiply[that]#add[that]
    }
*/
}
