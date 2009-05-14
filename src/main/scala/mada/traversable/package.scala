

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object traversable {


// aliases

    @aliasOf("Traversable")
    type Type[+A] = Traversable[A]

    @aliasOf("Traversable")
    def compatibles: Compatibles = Traversable


// bind

    /**
     * Creates a traversable starting from <code>t<code>, which is evaluated by-name.
     */
    def bind[A](t: => Traverser[A]) = new Traversable[A] {
        override def start = t
    }

    /**
     * Creates a traversable starting from <code>t<code>.
     */
    def bindOnce[A](t: Traverser[A]) = new Traversable[A] {
        override def start = t
    }


// empty

    /**
     * The empty traversable
     */
    val empty: Traversable[Nothing] = new Empty

    @aliasOf("empty")
    def emptyOf[A]: Traversable[A] = empty


// single

    /**
     * A traversable with a single element.
     */
    def single[A](e: A): Traversable[A] = new Single(e)


// conversions

    @returnThat
    def from[A](to: Traversable[A]) = to

  // compatibles

    @conversion
    def fromIterable[A](from: Iterable[A]): Traversable[A] = new FromIterable(from)

    @aliasOf("fromIterable")
    def fromValues[A](from: A*) = fromIterable(from)

}
