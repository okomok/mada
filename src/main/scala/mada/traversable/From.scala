

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


object from {

    @returnThat
    def apply[A](to: Traversable[A]) = to

}
