

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package blend


// See: http://michid.wordpress.com/2010/06/18/type-level-programming-equality/


@specializer
sealed abstract class IsSame[A, B] {
    def apply: Boolean
}


object IsSame {

    implicit def _ofSame[A] = new IsSame[A, A] {
        override val apply = true
    }

    def _ofNotSame[A, B] = new IsSame[A, B] {
        override val apply = false
    }

}
