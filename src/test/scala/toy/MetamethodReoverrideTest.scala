

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package metamethodreoverridetest



import com.github.okomok.mada.dual._

trait A {
    type isB <: Boolean
}

trait B extends A {
    override type isB = `true`
}

trait C extends A {
}

object Woo {
//    val k: C#isB = throw new Error
//    val i: Int = k
//    free.assertSame[Nothing, C#isB]
}
