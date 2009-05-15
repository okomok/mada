

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object traversable {


    @aliasOf("Traversable")
    type Type[+A] = Traversable[A]


// constructors

    /**
     * Creates a traversable initially containing the specified elements.
     */
    object of {
        def apply[A](from: A*): Traversable[A] = fromIterable(from)
        // def unapplySeq[A](from: Traversable[A]): Option[Seq[A]] = Some(from.toSeq)
    }

    /**
     * The empty traversable
     */
    val empty: Traversable[Nothing] = Empty

    /**
     * Typed <code>empty</code>
     */
    def emptyOf[A]: Traversable[A] = empty

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

    /**
     * Creates a traversable starting from <code>t<code>.
     */
    def bind[A](t: Traverser[A]): Traversable[A] = Bind(t)

    /**
     * Creates a traversable starting from <code>t<code>, which is evaluated by-name.
     */
    def bindName[A](t: => Traverser[A]): Traversable[A] = new BindName(t)

    /**
     * Constructs a traversable from traversing block.
     */
    def block[A](op: Yield[A] => Unit): Traversable[A] = Block(op)

    @aliasOf("Function1[A, Unit]")
    type Yield[-A] = Function1[A, Unit]


// conversions

    @returnThat
    def from[A](to: Traversable[A]) = to

  // compatibles

    @compatibles
    def compatibles: Compatibles = Traversable

    @conversion
    def fromIterable[A](from: Iterable[A]): Traversable[A] = new FromIterable(from)

    @conversion
    def fromJioObjectInput(from: java.io.ObjectInput): Traversable[AnyRef] = new FromJioObjectInput(from)

    @conversion
    def fromJioReader(from: java.io.Reader): Traversable[Char] = new FromJioReader(from)


// detail

    private[mada] def throwIfNegative(n: Int, method: String): Unit = {
        if (n < 0) {
            throw new IllegalArgumentException("traversable." + method + Tuple1(n))
        }
    }

}
