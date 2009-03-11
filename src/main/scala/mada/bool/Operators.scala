

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.bool


/**
 * Contains operators for <code>Boolean</code>.
 */
trait Operators {
    sealed class MadaBooleans(_1: Boolean) {
        def implies(_2: => Boolean) = Booleans.implies(_1, _2)
    }
    implicit def madaBooleans(_1: Boolean): MadaBooleans = new MadaBooleans(_1)
}
