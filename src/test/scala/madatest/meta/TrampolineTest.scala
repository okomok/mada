

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._
// import junit.framework.Assert._


class TrampolineTest {
/*
    trait even[n <: Int] extends BounceFunction {
        override type apply0 = `if`[n == _0I, done[`true`], call[odd[n#decrement]], Bounce]
    }

    trait odd[n <: Int] extends BounceFunction {
        override type apply0 = `if`[n == _0I, done[`false`], call[even[n#decrement]], Bounce]
    }

    assert[done[`true`]#isDone]
/*
    assertLower[trampoline[even[_8I]#apply0], Any]
    assertLower[trampoline[even[_8I]#apply0], Object]

    assertLower[trampoline[done[`true`]], Boolean]
    */
    assertSame[trampoline[done[`true`]], `true`]


    assert[trampoline[even[_0I]#apply0]] // cyclic reference after all.
    /*
    assertLower[trampoline[even[_8I]#apply0], Boolean]
    assert[trampoline[even[_8I]#apply0]]
    assert[trampoline[odd[_7I]#apply0]]
*/
*/
    def testTrivial = ()
}
