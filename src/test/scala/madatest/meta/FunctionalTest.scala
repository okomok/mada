

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._


trait FunctionalTest {

    type inc[n <: Nat] = n#increment
    type foo[p <: Product2 { type _2 <: Nat }] = inc[select2nd[p]]
    assertSame[_4N, foo[pair[_2N, _3N]]]

    type bar[n <: Nat, m <: Nat] = project1st[n, m]#increment
    assertSame[_4N, bar[_3N, _5N]]

}
