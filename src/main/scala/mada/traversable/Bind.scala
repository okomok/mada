

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


object bind {

    def apply[A](t: => Traverser[A]) = new Traversable[A] {
        override def start = t
        override def isRetraversable = true
    }

    def once[A](t: Traverser[A]) = new Traversable[A] {
        override val start = t
        override def isRetraversable = false
    }

}
