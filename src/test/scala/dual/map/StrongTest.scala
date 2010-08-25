

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada
import mada.dual._
import nat.dense.Literal._


class StrongTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        final class myMap extends map.Strong(map.sorted(nat.naturalOrdering).put(_3, _13).put(_5, _15).put(_1, _11)) {
            type self = myMap
        }
        val myMap = new myMap

        free.assert(myMap.get(_3).get.equal(_13))
    }

}
