

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


// See: http://michid.wordpress.com/2010/01/29/scala-type-level-encoding-of-the-ski-calculus/


package madatest; package toy; package skitest

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

object SKITestOriginal {

    trait Term {
      type ap[x <: Term] <: Term
      type eval <: Term
    }

    // The S combinator
    trait S extends Term {
      type ap[x <: Term] = S1[x]
      type eval = S
    }
    trait S1[x <: Term] extends Term {
      type ap[y <: Term] = S2[x, y]
      type eval = S1[x]
    }
    trait S2[x <: Term, y <: Term] extends Term {
      type ap[z <: Term] = S3[x, y, z]
      type eval = S2[x, y]
    }
    trait S3[x <: Term, y <: Term, z <: Term] extends Term {
      type ap[v <: Term] = x#ap[z]#ap[y#ap[z]]#ap[v] //eval#ap[v]
      type eval = x#ap[z]#ap[y#ap[z]]#eval
    }

    // The K combinator
    trait K extends Term {
      type ap[x <: Term] = K1[x]
      type eval = K
    }
    trait K1[x <: Term] extends Term {
      type ap[y <: Term] = K2[x, y]
      type eval = K1[x]
    }
    trait K2[x <: Term, y <: Term] extends Term {
      type ap[z <: Term] = x#ap[z] //eval#ap[z]
      type eval = x#eval
    }

    // The I combinator
    trait I extends Term {
      type ap[x <: Term] = I1[x]
      type eval = I
    }
    trait I1[x <: Term] extends Term {
      type ap[y <: Term] = x#ap[y] //eval#ap[y]
      type eval = x#eval
    }

    trait c extends Term {
      type ap[x <: Term] = c
      type eval = c
    }
    trait d extends Term {
      type ap[x <: Term] = d
      type eval = d
    }
    trait e extends Term {
      type ap[x <: Term] = e
      type eval = e
    }

     def Equals[A >: B <:B , B]=()

    Equals[Int, Int]     // compiles fine
    //Equals[String, Int] // won't compile

    // Ic -> c
    Equals[I#ap[c]#eval, c]

    // Kcd -> c
    Equals[K#ap[c]#ap[d]#eval, c]

    // KKcde -> d
    Equals[K#ap[K]#ap[c]#ap[d]#ap[e]#eval, d]

    // SIIIc -> Ic
    Equals[S#ap[I]#ap[I]#ap[I]#ap[c]#eval, c]

    // SKKc -> Ic
    Equals[S#ap[K]#ap[K]#ap[c]#eval, c]

    // SIIKc -> KKc
    Equals[S#ap[I]#ap[I]#ap[K]#ap[c]#eval, K#ap[K]#ap[c]#eval]

    // SIKKc -> K(KK)c
    Equals[S#ap[I]#ap[K]#ap[K]#ap[c]#eval, K#ap[K#ap[K]]#ap[c]#eval]

    // SIKIc -> KIc
    Equals[S#ap[I]#ap[K]#ap[I]#ap[c]#eval, K#ap[I]#ap[c]#eval]

    // SKIc -> Ic
    Equals[S#ap[K]#ap[I]#ap[c]#eval, c]

    // R = S(K(SI))K  (reverse)
    type R = S#ap[K#ap[S#ap[I]]]#ap[K]
    Equals[R#ap[c]#ap[d]#eval, d#ap[c]#eval]

    // b(a) = S(Ka)(SII)
    type b[a <: Term] = S#ap[K#ap[a]]#ap[S#ap[I]#ap[I]]

    trait A0 extends Term {
      type ap[x <: Term] = c
      type eval = A0
    }
    trait A1 extends Term {
      type ap[x <: Term] = x#ap[A0]#eval
      type eval = A1
    }
    trait A2 extends Term {
      type ap[x <: Term] = x#ap[A1]#eval
      type eval = A2
    }

    // Single iteration
    type NN1 = b[R]#ap[b[R]]#ap[A0]
    Equals[NN1#eval, c]

    // Double iteration
    type NN2 = b[R]#ap[b[R]]#ap[A1]
    Equals[NN2#eval, c]

    // Triple iteration
    type NN3 = b[R]#ap[b[R]]#ap[A2]
    Equals[NN3#eval, c]

    trait An extends Term {
      type ap[x <: Term] = x#ap[An]#eval
      type eval = An
    }
    // Infinite iteration: Smashes scalac's stack
      type NNn = b[R]#ap[b[R]]#ap[An]
      // Equals[NNn#eval, c]

}
