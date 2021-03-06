

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package lazytest


import com.github.okomok.mada

import junit.framework.Assert._


class A


class LazyTezt {

    def foo(a: => A) = {a; new A()}

    def useA(a: A): Unit = ()

    def testTrivial: Unit = {

        lazy val a: A = foo(a)
     //   useA(a) // stack overflow
        ()
    }

}
