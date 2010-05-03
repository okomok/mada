

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package varargcovarianttest


import com.github.okomok.mada

object VarargCovariantTest {

    trait A
    class B extends A

    def foo(k: A*) = ()

    def compileMe() = {
        foo(new B, new B)
        ()
    }

}
