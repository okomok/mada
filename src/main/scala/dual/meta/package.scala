

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


package object meta {


// assertion (Note case classes doesn't work well.)

    /**
     * assertion
     */
    // @elidable(ALL) // crashes compiler.
    def assert[a >: `true` <: `true`]: scala.Unit = ()

    /**
     * negative assertion
     */
    def assertNot[a >: `false` <: `false`]: scala.Unit = ()

    /**
     * assertion of identity equality
     */
    def assertSame[a >: b <: b, b]: scala.Unit = ()

    /**
     * assertion if <code>a</code> is lower than <code>b</code>.
     */
    def assertConforms[a <: b, b]: scala.Unit = ()

}
