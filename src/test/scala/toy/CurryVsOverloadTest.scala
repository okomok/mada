

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package curryvsoverloadtest


import com.github.okomok.mada

object CurryVsOverloadStaticTezt {

    def foo(i: Int)(d: Double) = ()
    def foo(i: Int)(j: Int) = ()

    def compileMe: Unit = {
        // foo(3)(4) // error
    }

}
