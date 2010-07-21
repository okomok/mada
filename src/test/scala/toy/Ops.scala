

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package ops


import junit.framework.Assert._


package vv {
    trait V[A]

    object V {

        class GoOps[A](v: V[A]) {
            def go: Unit = {}
        }
        implicit def toGoOps[A](v: V[A]): GoOps[A] = new GoOps(v)

    }
}


class OpsTezt {
    def testCompile(x: Int): Unit = {
        val v = new vv.V[Int] {}
        v.go
    }
}
