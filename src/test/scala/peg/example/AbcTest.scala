

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest; package example


    import com.github.okomok.mada
    import mada.peg._
    import mada.peg.Compatibles._

    class AbcTest extends org.scalatest.junit.JUnit3Suite {
        val S, A, B = new Rule[Char]

        S ::= ~(A >> !"b") >> from("a").+ >> B >> !("a"|"b"|"c")
        A ::= "a" >> A.? >> "b"
        B ::= ("b" >> B.? >> "c")//{ println(_) }

        def testTrivial {
            assert(S matches "abc")
            assert(S matches "aabbcc")
            assert(S matches "aaabbbccc")
            assert(!(S matches "aaabbccc"))
        }
    }
