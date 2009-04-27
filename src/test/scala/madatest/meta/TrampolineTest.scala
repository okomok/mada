

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._
// import junit.framework.Assert._


class TrampolineTest {
/*
    trait even[n <: Nat] extends BounceFunction {
        override type apply0 = `if`[n == _0N, done[`true`], call[odd[n#decrement]], Bounce]
    }

    trait odd[n <: Nat] extends BounceFunction {
        override type apply0 = `if`[n == _0N, done[`false`], call[even[n#decrement]], Bounce]
    }

    assert[done[`true`]#isDone]
/*
    assertLower[trampoline[even[_8N]#apply0], Any]
    assertLower[trampoline[even[_8N]#apply0], Object]

    assertLower[trampoline[done[`true`]], Boolean]
    */
    assertSame[trampoline[done[`true`]], `true`]


    assert[trampoline[even[_0N]#apply0]] // cyclic reference after all.
    /*
    assertLower[trampoline[even[_8N]#apply0], Boolean]
    assert[trampoline[even[_8N]#apply0]]
    assert[trampoline[odd[_7N]#apply0]]
*/
*/
    def testTrivial = ()
}
