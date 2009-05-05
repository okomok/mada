

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: scalax.util.control.TailRec, or
//      http://blog.richdougherty.com/2009/04/tail-calls-tailrec-and-trampolines.html


@provider
trait Trampoline { this:Meta.type =>
/*
    type RESULT = Boolean

    trait BounceFunction extends Function0 {
        override type apply0 <: Bounce
    }

    sealed trait Bounce[A] with Function0 {
        private[mada] type isDone <: Boolean
        type apply0 <: A // gets the result.
        private[mada] type thunk <: Function0 { type apply0 <: Bounce[A] }
    }

    trait done[r <: Boolean] extends Bounce {
        override type isDone = `true`
        override type apply0 = r
    }

    trait call[t <: Function0 { type apply0 <: Bounce { type result <: RESULT } }] extends Bounce {
        override type isDone = `false`
        override type apply0 = Nothing
        override type thunk = t
    }

//    type trampoline[b <: Bounce] = trampoline0[b]
    type trampoline[b <: Bounce] = `if`[b#isDone, b#apply0, trampoline1[b#thunk#apply0], RESULT]
    type trampoline1[b <: Bounce] = `if`[b#isDone, b#apply0, trampoline2[b#thunk#apply0], RESULT]
    type trampoline2[b <: Bounce] = Nothing
    */
/*
    trait getResult[b <: Bounce] extends Function0 {
        override type apply0 = b#result
    }
*/


/*
// Hmm, `if` seems to lose nested apply0 type in Function0 { type apply0 <: RESULT }.

    trait trampoline0[b <: Bounce] extends Function0 {
        override type apply0 = `if`[b#isDone, b, trampoline1[b#thunk#apply0], Function0 { type apply0 <: RESULT }]#apply0
    }

    trait trampoline1[b <: Bounce] extends Function0 {
        override type apply0 = `if`[b#isDone, b, trampoline2[b#thunk#apply0], Function0 { type apply0 <: RESULT }]#apply0
    }

    trait trampoline2[b <: Bounce] extends Function0 {
        override type apply0 = `if`[b#isDone, b, trampoline3[b#thunk#apply0], Function0 { type apply0 <: RESULT }]#apply0
    }

    trait trampoline3[b <: Bounce] extends Function0 {
        override type apply0 = `if`[b#isDone, b, trampoline4[b#thunk#apply0], Function0 { type apply0 <: RESULT }]#apply0
    }

    trait trampoline4[b <: Bounce] extends Function0 {
        override type apply0 = `if`[b#isDone, b, trampoline5[b#thunk#apply0], Function0 { type apply0 <: RESULT }]#apply0
    }

    trait trampoline5[b <: Bounce] extends Function0 {
        override type apply0 = `if`[b#isDone, b, trampoline6[b#thunk#apply0], Function0 { type apply0 <: RESULT }]#apply0
    }

    trait trampoline6[b <: Bounce] extends Function0 {
        override type apply0 = `if`[b#isDone, b, trampoline7[b#thunk#apply0], Function0 { type apply0 <: RESULT }]#apply0
    }

    trait trampoline7[b <: Bounce] extends Function0 {
        override type apply0 = `if`[b#isDone, b, trampoline8[b#thunk#apply0], Function0 { type apply0 <: RESULT }]#apply0
    }

    trait trampoline8[b <: Bounce] extends Function0 {
        override type apply0 = `if`[b#isDone, b, trampoline9[b#thunk#apply0], Function0 { type apply0 <: RESULT }]#apply0
    }

    trait trampoline9[b <: Bounce] extends Function0 {
        override type apply0 = `if`[b#isDone, b, trampoline10[b#thunk#apply0], Function0 { type apply0 <: RESULT }]#apply0
    }

    trait trampoline10[b <: Bounce] extends Function0 {
        override type apply0 = Nothing
    }
*/
}
