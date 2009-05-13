

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object traverser {


// aliases

    @aliasOf("Traversable")
    type Type[+A] = Traverser[A]

    @aliasOf("Traversable")
    val compatibles: Compatibles = Traverser


// methods

    /**
     * The universal end traverser
     */
    val theEnd = new Traverser[Nothing] {
        override def isEnd = true
        override def deref = throw new NoSuchElementException("deref on end traverser")
        override def increment = throw new UnsupportedOperationException("increment on end traverser")
    }


// conversions

    @returnThat
    def from[A](to: Traverser[A]) = to

    @conversion
    def fromIterator[A](from: Iterator[A]): Traverser[A] = new FromIterator(from)

}
