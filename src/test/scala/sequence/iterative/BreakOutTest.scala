

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada
import mada.sequence.Iterative


class BreakOutTest extends org.scalatest.junit.JUnit3Suite {

    def testSList = {
        val sl: scala.collection.immutable.List[Int] = Iterative(1,2,3,4,5).breakOut
        expect(Iterative(1,2,3,4,5))(Iterative.from(sl))
    }

    def testSSet = {
        val sl: scala.collection.immutable.HashSet[Int] = Iterative(1,2,3).breakOut
        expect(3)(sl.size)
        assert(sl.contains(1))
        assert(sl.contains(2))
        assert(sl.contains(3))
    }

    def testSMap = {
        val sl: scala.collection.immutable.HashMap[Int, String] = Iterative((1,"1"),(2,"2"),(3,"3")).breakOut
        expect(3)(sl.size)
        expect("1")(sl(1))
        expect("2")(sl(2))
        expect("3")(sl(3))
    }

}
