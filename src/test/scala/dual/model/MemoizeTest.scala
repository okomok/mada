

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package modeltest


import com.github.okomok.mada
import mada.dual._
import mada.dual.{assert => dassert}
import junit.framework.Assert._


import mada.dual.nat.dense.Literal._
import mada.dual.nat.dense.{Nil, ::, _1B, _0B}
import FastFibonacci2._


class MemoizeTest {

    trait testMemoize {
        type t = fibonacci[_15#plus[_12]]
        free.assertSame[`true`, t# equal[_0B ::_1B ::_0B ::_0B ::_0B ::_0B ::_1B ::_0B ::_1B ::_1B ::_1B ::_1B ::_1B ::_1B ::_1B ::_1B ::_0B ::_1B :: Nil]]
    }

}

