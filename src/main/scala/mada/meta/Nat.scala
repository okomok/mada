

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta natural numbers.
 */
trait Nats { this: Meta.type =>

    sealed trait Nat extends Object {
        type increment <: Nat
        type decrement <: Nat
    }

    sealed trait _0N extends Nat {
        override type increment = _1N
        override type decrement = throwError
    }

    sealed trait _1N extends Nat {
        override type increment = _2N
        override type decrement = _0N
    }

    sealed trait _2N extends Nat {
        override type increment = _3N
        override type decrement = _1N
    }

    sealed trait _3N extends Nat {
        override type increment = _4N
        override type decrement = _2N
    }

    sealed trait _4N extends Nat {
        override type increment = _5N
        override type decrement = _3N
    }

    sealed trait _5N extends Nat {
        override type increment = _6N
        override type decrement = _4N
    }

    sealed trait _6N extends Nat {
        override type increment = _7N
        override type decrement = _5N
    }

    sealed trait _7N extends Nat {
        override type increment = _8N
        override type decrement = _6N
    }
    sealed trait _8N extends Nat {
        override type increment = _9N
        override type decrement = _7N
    }

    sealed trait _9N extends Nat {
        override type increment = _10N
        override type decrement = _8N
    }

    sealed trait _10N extends Nat {
        override type increment = throwError
        override type decrement = _9N
    }

}
