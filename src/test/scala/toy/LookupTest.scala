

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package lookuptest


import com.github.okomok.mada

import junit.framework.Assert._

class A {
    def foo: Unit = ()
}

class B {
    def foo(x: Int): Unit = ()
}

object B {
    implicit def _BtoA(x: B): A = new A
}


class LookupTezt {
    val b = new B
//    val x = b.foo()
    val y = b.foo(3)
}
