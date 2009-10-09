

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toy; package lazytest


import junit.framework.Assert._


class A


class LazyTest {

    def foo(a: => A) = {a; new A()}

    def useA(a: A): Unit = ()

    def testTrivial: Unit = {

        lazy val a: A = foo(a)
     //   useA(a) // stack overflow
        ()
    }

}
