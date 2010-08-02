

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada
import mada.dual._
import mada.dual.{assert => dassert}
import junit.framework.Assert._


// Result:
//     _NO_ memoization. (That is, there's no global instantiation analysys around.)


class MemoizeTest extends org.scalatest.junit.JUnit3Suite {

    def testMemoize {
        import Tarai._
        import mada.dual.nat.peano.Literal._

        //type t = tarai[_15, _10, _1]
        //meta.assertSame[`true`, t# ===[_15]]
        //println(runtarai(15, 10, 1))

        ()
    }

    /*
    def testMemoize2 {
        import mada.dual.nat.dense.Literal._
        import mada.dual.nat.dense.{Nil, ::, _1B, _0B}
        import SlowFibonacci2._


        {//19s
            type t = fibonacci[_15, nat.peano._15]
            meta.assertSame[`true`, t# ===[_0B ::_1B ::_0B ::_0B ::_0B ::_1B ::_1B ::_0B ::_0B ::_1B :: Nil]]
        }
    }
    */

    def woofoo {
        import mada.dual.nat.dense.Literal._
        import mada.dual.nat.dense.{Nil, ::, _1B, _0B}
        import SlowFibonacci2._

        /*
        {//19s
            type t = fibonacci[_12, nat.peano._12]
            meta.assertSame[`true`, t# ===[_0B :: _0B :: _0B :: _0B :: _1B :: _0B :: _0B :: _1B :: Nil]]
        }*/

        {//19s
            type t = fibonacci[_15, nat.peano._15]# + [fibonacci[_12, nat.peano._12]]
            meta.assertSame[`true`, t# ===[_0B :: _1B :: _0B :: _0B :: _1B :: _1B :: _1B :: _1B :: _0B :: _1B :: Nil]]
        }
    }

    /*
    def testFib {
        import mada.dual.nat.dense.Literal._
        import mada.dual.nat.dense.{Nil, ::, _1B, _0B}

        import SlowFibonacci._
        /*
        {
            type t = fibonacci[_12]
            meta.assertSame[`true`, t# ===[_0B :: _0B :: _0B :: _0B :: _1B :: _0B :: _0B :: _1B :: Nil]]
        }*/

        {
            type t = fibonacci[_15]
            meta.assertSame[`true`, t# ===[_0B ::_1B ::_0B ::_0B ::_0B ::_1B ::_1B ::_0B ::_0B ::_1B :: Nil]]
        }
/*
    {
            type t = fibonacci[_0B ::_1B ::_1B ::_1B ::_1B :: Nil]
            meta.assertSame[`true`, t# ===[_0B ::_0B ::_0B ::_1B ::_0B ::_1B ::_0B ::_0B ::_0B ::_1B ::_0B ::_0B ::_1B ::_1B ::_0B ::_1B ::_0B ::_0B ::_1B ::_1B :: Nil]]
        }
        ()
*/
    }
    */

}
