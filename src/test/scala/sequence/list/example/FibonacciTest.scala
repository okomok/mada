

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest
package listtest; package example


    import com.github.okomok.mada.sequence._

    class FibonacciTest extends org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            lazy val fibs: List[Int] = 0 :: 1 :: fibs.zip(fibs.tail).map2(_ + _)
            expect(832040)(fibs.nth(30))
        }
    }
