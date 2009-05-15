

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object traversable {


    @aliasOf("Traversable")
    type Type[+A] = Traversable[A]


// constructors

    /**
     * Creates a traversable starting from <code>t<code>.
     */
    def bind[A](t: Traverser[A]): Traversable[A] = Bind(t)

    /**
     * Creates a traversable starting from <code>t<code>, which is evaluated by-name.
     */
    def bindName[A](t: => Traverser[A]): Traversable[A] = new BindName(t)

    /**
     * The empty traversable
     */
    val empty: Traversable[Nothing] = Empty

    /**
     * Typed <code>empty</code>
     */
    def emptyOf[A]: Traversable[A] = empty

    /**
     * Creates a traversable initially containing the specified elements.
     */
    def of[A](from: A*) = fromIterable(from)

    /**
     * A traversable with a single element.
     */
    def single[A](e: A): Traversable[A] = Single(e)

    /**
     * Refers a traversable by lazy.
     */
    def byLazy[A](tr: => Traversable[A]): Traversable[A] = new ByLazy(tr)

    /**
     * Refers a traversable by name.
     */
    def byName[A](tr: => Traversable[A]): Traversable[A] = new ByName(tr)

    /**
     * Creates an infinite traversable repeatedly applying <code>op</code>.
     */
    def iterate[A](z: A)(op: A => A): Traversable[A] = Iterate(z, op)

    /**
     * Repeats <code>e</code> infinitely.
     */
    def repeat[A](e: A): Traversable[A] = Repeat(e)

    /**
     * Unfolds right to left.
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Traversable[B] = UnfoldRight(z, op)


// conversions

    @returnThat
    def from[A](to: Traversable[A]) = to

  // compatibles

    @compatibles
    def compatibles: Compatibles = Traversable

    @conversion
    def fromIterable[A](from: Iterable[A]): Traversable[A] = new FromIterable(from)


// detail

    private[mada] def throwIfNegative(n: Int, method: String): Unit = {
        if (n < 0) {
            throw new IllegalArgumentException("traversable." + method + Tuple1(n))
        }
    }

}
