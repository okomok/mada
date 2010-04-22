

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


// See: http://michid.wordpress.com/2010/01/29/scala-type-level-encoding-of-the-ski-calculus/


package madatest; package toy; package skitest

import mada.meta.assertSame

object SKITest {

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

    trait A0 extends Term {
        type apply[x <: Term] = c
    }
    trait A1 extends Term {
        type apply[x <: Term] = x#apply[A0]
    }
    trait A2 extends Term {
        type apply[x <: Term] = x#apply[A1]
    }

    type NN1 = b[R]#apply[b[R]]#apply[A0]
    //assertSame[NN1, c]
}
