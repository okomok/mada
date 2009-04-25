

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta integers.
 */
trait Nats { this: Meta.type =>

    sealed trait Nat extends Object with Operatable_+
    {
        type increment <: Nat
        type plus[that <: Nat] <: Nat
        type multiply[that <: Nat] <: Nat

        override type Self = Nat
        override type operate_+[that <: Nat] = plus[that]
    }


    // Workarond: `final class` crashes compiler.

    /*final class*/ sealed trait _0N extends Nat {
        override type increment = _1N
        override type plus[that <: Nat] = that
        override type multiply[that <: Nat] = _0N
    }

    sealed trait _1N extends Nat {
        override type increment = _2N
        override type plus[that <: Nat] = _0N#plus[that]#increment
        override type multiply[that <: Nat] = _0N#multiply[that]#plus[that]
    }

    sealed trait _2N extends Nat {
        override type increment = _3N
        override type plus[that <: Nat] = _1N#plus[that]#increment
        override type multiply[that <: Nat] = _1N#multiply[that]#plus[that]
    }

    sealed trait _3N extends Nat {
        override type increment = Nothing
        override type plus[that <: Nat] = _2N#plus[that]#increment
        override type multiply[that <: Nat] = _2N#multiply[that]#plus[that]
    }

/*
    sealed trait NatImpl[m <: Nat] extends Nat {
        override type increment = m
        override type plus[that <: Nat] = m#plus[that]#increment
        override type multiply[that <: Nat] = m#multiply[that]#plus[m]
    }

    // No recursions like C macros.
    final class _1N extends NatImpl[_2N]
    final class _2N extends NatImpl[_3N]
    final class _3N extends NatImpl[Nothing]
    final class _succ[n <: Nat] extends Nat {
        // This recursive call crashes compiler. Maybe -Yrecursion flag is needed?
        private type `this` = _succ1[n]
        override type increment = _succ2[`this`]
        override type plus[that <: Nat] = n#plus[that]#increment
        override type multiply[that <: Nat] = n#multiply[that]#plus[n]
    }

    type _1N = _succ[_0N]
    type _2N = _succ[_1N]
    type _3N = _succ[_2N]
    type _4N = _succ[_3N]
    type _5N = _succ[_4N]
    type _6N = _succ[_5N]
    type _7N = _succ[_6N]
    type _8N = _succ[_7N]
    type _9N = _succ[_8N]
    type _1N0 = _succ[_9N]
*/
    assertEquals[_3N, _1N + _2N]
    assertEquals[_2N, _1N + _1N]
    assertEquals[_3N, _2N + _1N]

    assertEquals[_3N, _1N#plus[_2N]]
    assertEquals[_2N, _1N#plus[_1N]]

}
