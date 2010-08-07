

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package modeltest


import com.github.okomok.mada
import mada.dual._
import mada.dual.{assert => dassert}
import junit.framework.Assert._


import mada.dual.nat.dense.Literal._
import mada.dual.nat.dense.{Nil, ::, _1B, _0B}
//import FastFibonacci._ // takes 6s.
import FastFibonacci2._
//import SlowFibonacci._ // takes forever.
//import SlowFibonacci2._ // takes forever.


class MemoizeTest {

    trait testMemoize {
        type t = fibonacci[_15# +[_12]]
        meta.assertSame[`true`, t# ===[_0B ::_1B ::_0B ::_0B ::_0B ::_0B ::_1B ::_0B ::_1B ::_1B ::_1B ::_1B ::_1B ::_1B ::_1B ::_1B ::_0B ::_1B :: Nil]]
    }

}

