

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


// See: http://michid.wordpress.com/2010/01/29/scala-type-level-encoding-of-the-ski-calculus/


package com.github.okomok.madatest; package toy; package skitest


import com.github.okomok.mada
import mada.meta.assertSame

object SKINonLazyTest {

    trait Term {
        type apply[x <: Term] <: Term
    }

    trait I extends Term {
        type apply[x <: Term] = x
    }

    trait K extends Term {
        type apply[x <: Term] = _K1[x]
    }

    trait _K1[x <: Term] extends Term {
        type apply[y <: Term] = x
    }

    trait S extends Term {
        type apply[x <: Term] = _S1[x]
    }

    trait _S1[x <: Term] extends Term {
        type apply[y <: Term] = _S2[x, y]
    }

    trait _S2[x <: Term, y <: Term] extends Term {
        type apply[z <: Term] = x#apply[z]#apply[y#apply[z]]
    }

    trait c extends Term {
        type apply[x <: Term] = c
    }

    trait d extends Term {
        type apply[x <: Term] = d
    }

    trait e extends Term {
        type apply[x <: Term] = e
    }

    // Ic -> c
    assertSame[I#apply[c], c]

    // Kcd -> c
    assertSame[K#apply[c]#apply[d], c]

    // KKcde -> d
    assertSame[K#apply[K]#apply[c]#apply[d]#apply[e], d]

    // SIIIc -> Ic
    assertSame[S#apply[I]#apply[I]#apply[I]#apply[c], I#apply[c]]

    // SKKc -> Ic
    assertSame[S#apply[K]#apply[K]#apply[c], I#apply[c]]

    // SIIKc -> KKc
    assertSame[S#apply[I]#apply[I]#apply[K]#apply[c], K#apply[K]#apply[c]]

    // SIKKc -> K(KK)c
    assertSame[S#apply[I]#apply[K]#apply[K]#apply[c], K#apply[K#apply[K]]#apply[c]]

    // SIKIc -> KIc
    assertSame[S#apply[I]#apply[K]#apply[I]#apply[c], K#apply[I]#apply[c]]

    // SKIc -> Ic
    assertSame[S#apply[K]#apply[I]#apply[c], c]

    // R = S(K(SI))K  (reverse)
    type R = S#apply[K#apply[S#apply[I]]]#apply[K]
    assertSame[R#apply[c]#apply[d], d#apply[c]]

    // b(a) = S(Ka)(SII)
    type b[a <: Term] = S#apply[K#apply[a]]#apply[S#apply[I]#apply[I]]
    type b2[a <: Term] = S#apply[K#apply[a]]#apply[S#apply[I]#apply[I]]


    trait b_[a <: Term] extends Term {
        type apply[x <: Term] = S#apply[K#apply[a]]#apply[S#apply[I]#apply[I]]#apply[x]
    }

    type cf = b[c]#apply[b[c]]
    assertSame[c#apply[cf], c]

    trait A0 extends Term {
        type apply[x <: Term] = c
    }
    trait A1 extends Term {
        type apply[x <: Term] = x#apply[A0]
    }
    trait A2 extends Term {
        type apply[x <: Term] = x#apply[A1]
    }
}


object SKITest {

    trait Term {
      type apply[x <: Term] <: Term
      type eval
    }

    // Implementation helpers
    trait Combinator[x <: Term] extends Term {
        override type eval = x
    }
    trait Applicator extends Term {
        protected type term <: Term
        override type apply[x <: Term] = term#apply[x]
        override type eval = term#eval
    }
    trait Constant[c <: Term] extends Term {
        override type apply[x <: Term] = c
        override type eval = c
    }

    // The S combinator
    trait S extends Combinator[S] {
        override type apply[x <: Term] = _S1[x]
    }
    trait _S1[x <: Term] extends Combinator[_S1[x]] {
        override type apply[y <: Term] = _S2[x, y]
    }
    trait _S2[x <: Term, y <: Term] extends Combinator[_S2[x, y]] {
        override type apply[z <: Term] = _S3[x, y, z]
    }
    trait _S3[x <: Term, y <: Term, z <: Term] extends Applicator {
        override protected type term = x#apply[z]#apply[y#apply[z]]
    }

    // The K combinator
    trait K extends Combinator[K] {
        override type apply[x <: Term] = _K1[x]
    }
    trait _K1[x <: Term] extends Combinator[_K1[x]] {
        override type apply[y <: Term] = x // _K2[x, y]
    }
    trait _K2[x <: Term, y <: Term] extends Applicator {
        override protected type term = x
    }

    // The I combinator
    trait I extends Combinator[I] {
        override type apply[x <: Term] = x // _I1[x]
    }
    trait _I1[x <: Term] extends Applicator {
        override protected type term = x
    }

    // Convenience
    trait apply0[x0 <: Term] extends Applicator {
        override protected type term = x0
    }
    trait apply1[x0 <: Term, x1 <: Term] extends Applicator {
        override protected type term = x0#apply[x1]
    }
    trait apply2[x0 <: Term, x1 <: Term, x2 <: Term] extends Applicator {
        override protected type term = x0#apply[x1]#apply[x2]
    }
    trait apply3[x0 <: Term, x1 <: Term, x2 <: Term, x3 <: Term] extends Applicator {
        override protected type term = x0#apply[x1]#apply[x2]#apply[x3]
    }
    trait apply4[x0 <: Term, x1 <: Term, x2 <: Term, x3 <: Term, x4 <: Term] extends Applicator {
        override protected type term = x0#apply[x1]#apply[x2]#apply[x3]#apply[x4]
    }


  // Tests

    trait c extends Constant[c]
    trait d extends Constant[d]
    trait e extends Constant[e]

    assertSame[Int, Int]     // compiles fine
    //assertSame[String, Int] // won't compile

    // Ic -> c
    assertSame[apply1[I, c]#eval, c]

    // Kcd -> c
    assertSame[apply2[K, c, d]#eval, c]

    // KKcde -> d
    assertSame[apply4[K, K, c, d, e]#eval, d]

    // SIIIc -> Ic
    assertSame[apply4[S, I, I, I, c]#eval, c]

    // SKKc -> Ic
    assertSame[apply3[S, K, K, c]#eval, c]

    // SIIKc -> KKc
    assertSame[apply4[S, I, I, K ,c]#eval, apply2[K, K, c]#eval]

    // SIKKc -> K(KK)c
    assertSame[apply4[S, I, K, K, c]#eval, apply2[K, apply1[K, K], c]#eval]

    // SIKIc -> KIc
    assertSame[apply4[S, I, K, I, c]#eval, apply2[K, I, c]#eval]

    // SKIc -> Ic
    assertSame[apply3[S, K, I, c]#eval, apply1[I, c]#eval]

    // R = S(K(SI))K  (reverse)
    type R = apply2[S, apply1[K, apply1[S, I]], K]
    assertSame[apply2[R, c, d]#eval, apply1[d, c]#eval]

    // b(a) = S(Ka)(SII)
    type b[a <: Term] = apply2[S, apply1[K, a], apply2[S, I, I]]

    trait A0 extends Term {
      type apply[x <: Term] = c
      type eval = A0
    }
    trait A1 extends Term {
      type apply[x <: Term] = x#apply[A0]
      type eval = A1
    }
    trait A2 extends Term {
      type apply[x <: Term] = x#apply[A1]
      type eval = A2
    }

    // Single iteration
    type NN1 = b[R]#apply[b[R]]#apply[A0]
    assertSame[NN1#eval, c]

    // Double iteration
    type NN2 = b[R]#apply[b[R]]#apply[A1]
    assertSame[NN2#eval, c]

    // Triple iteration
    type NN3 = b[R]#apply[b[R]]#apply[A2]
    assertSame[NN3#eval, c]

    trait An extends Term {
      type apply[x <: Term] = x#apply[An]
      type eval = An
    }
    // Infinite iteration: Smashes scalac's stack
      type NNn = b[R]#apply[b[R]]#apply[An]
      // assertSame[NNn#eval, c]

}
