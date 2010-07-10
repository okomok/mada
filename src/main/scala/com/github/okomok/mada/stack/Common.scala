

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package stack


private[mada] class Common {

    @returnThat
    def from[A](to: Stack[A]): Stack[A] = to

}
