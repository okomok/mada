

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object traversable {

    @aliasOf("Traversable")
    type Type[+A] = Traversable[A]


    @returnThat
    def from[A](to: Traversable[A]) = to

    /**
     * Creates retraversable from a traverser.
     */
    def bind[A](t: => Traverser[A]) = new Traversable[A] {
        override def start = t
        override def isRetraversable = true
    }

    /**
     * Creates non-retraversable from a traverser.
     */
    def bindOnce[A](t: Traverser[A]) = new Traversable[A] {
        override def start = t
        override def isRetraversable = false
    }

}
