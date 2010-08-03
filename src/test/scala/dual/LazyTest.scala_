

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada
import mada.dual._
import mada.dual.nat.dense.Literal._
import mada.dual.nat.dense.{Nil, ::, _1B, _0B}


// The same type expressions in local scope are instantiated only once.
// (Note if it were applied in case of global scope, it would mean memoization support.)


class LazyTezt { // extends org.scalatest.junit.JUnit3Suite {

    import SlowFibonacci._

    class Foo[n <: Nat] {
        type f = fibonacci[n#increment#decrement] // 30s
        type g = fibonacci[n#increment#decrement] // 0s
        type h = fibonacci[n#increment#decrement] // 0s
    }

    type t1 = Foo[_15]#f
    meta.assertSame[`true`, t1# ===[_0B ::_1B ::_0B ::_0B ::_0B ::_1B ::_1B ::_0B ::_0B ::_1B :: Nil]]

    type t2 = Foo[_15]#g
    meta.assertSame[`true`, t2# ===[_0B ::_1B ::_0B ::_0B ::_0B ::_1B ::_1B ::_0B ::_0B ::_1B :: Nil]]

    type t3 = Foo[_15]#h
    meta.assertSame[`true`, t3# ===[_0B ::_1B ::_0B ::_0B ::_0B ::_1B ::_1B ::_0B ::_0B ::_1B :: Nil]]
}


/* Compiles in the same duration as above.
class LazyTezt { // extends org.scalatest.junit.JUnit3Suite {

    import SlowFibonacci._

    class Foo[n <: Nat] {
        type f = fibonacci[n]
    }

    type t1 = Foo[_15]#f
    meta.assertSame[`true`, t1# ===[_0B ::_1B ::_0B ::_0B ::_0B ::_1B ::_1B ::_0B ::_0B ::_1B :: Nil]]
}
*/
