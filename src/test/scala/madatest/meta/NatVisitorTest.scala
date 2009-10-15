

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._
//import junit.framework.Assert._

/*
class NatVisitorTest {

    trait vt[m <: Nat] extends nat.Visitor {
        type Result = Nat
        type visitZero = m
        type visitSucc[n <: Nat] = n#accept[vt[Succ[m]]]
    }

    type add[n <: Nat, m <: Nat] = n#accept[vt[m]]

    type add1[n <: Nat, m <: Nat] = n#accept[vt[m]]#increment // doesn't work.


    type add2[n <: Nat, m <: Nat] = n#increment#accept[vt[m#increment]] // works.
    assertSame[add2[_2N, _3N], _7N]


    type inc3[n <: Nat] = n#increment#increment#decrement#increment#increment // works.

    type addx[n <: Nat, m <: Nat] = n#add[m] // works.
    assertSame[addx[_4N, _3N], _7N]

    type addx1[n <: Nat, m <: Nat] = n#add[m]#increment // works!
    assertSame[addx1[_4N, _3N], _8N]

    type addxx1[n <: Nat, m <: Nat] = add[n, m]#increment // doesn't work.
    // assertSame[addxx1[_4N, _3N], _8N]

    type addxxx3[n <: Nat, m <: Nat] = add[n#increment#increment, m#increment] // works.
    assertSame[addxxx3[_2N, _3N], _8N]

    assertSame[inc3[_2N], _5N]

    assertSame[add[_3N, _0N], _3N]
    assertSame[add[_3N, _2N], _5N]

    def testTrivial: Unit = {
        // println(unmeta[add1[_3N, _2N], scala.Int]) // could not find implicit value
        // println(unmeta[add[_3N, _2N]#increment, scala.Int])
    }

    assertSame[add[_3N, _2N]#increment, _6N] // ok
//    assertSame[add1[_3N, _2N], _6N] // error

}
*/
