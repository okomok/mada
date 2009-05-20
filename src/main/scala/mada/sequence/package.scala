

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object sequence {


    @aliasOf("Sequence")
    type Type[+A] = Sequence[A]


// constructors

    /**
     * Creates a sequence initially containing the specified elements.
     */
    object of {
        def apply[A](from: A*): Sequence[A] = fromSIterable(from)
        // def unapplySeq[A](from: Sequence[A]): Option[Seq[A]] = Some(from.toSeq)
    }

    /**
     * The empty sequence
     */
    val empty: Sequence[Nothing] = Empty()

    /**
     * Typed <code>empty</code>
     */
    def emptyOf[A]: Sequence[A] = empty

    /**
     * A sequence with a single element.
     */
    def single[A](e: A): Sequence[A] = Single(e)

    /**
     * Refers a sequence by lazy.
     */
    def byLazy[A](seq: => Sequence[A]): Sequence[A] = new ByLazy(seq)

    /**
     * Refers a sequence by name.
     */
    def byName[A](seq: => Sequence[A]): Sequence[A] = new ByName(seq)

    /**
     * Creates an infinite sequence repeatedly applying <code>op</code>.
     */
    def iterate[A](z: A)(op: A => A): Sequence[A] = Iterate(z, op)

    /**
     * Repeats <code>e</code> infinitely.
     */
    def repeat[A](e: A): Sequence[A] = Repeat(e)

    /**
     * Unfolds right to left.
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Sequence[B] = UnfoldRight(z, op)

    /**
     * Creates a sequence starting from <code>it<code>.
     */
    def bind[A](it: Iterator[A]): Sequence[A] = Bind(it)

    /**
     * Creates a sequence starting from <code>it<code>, which is evaluated by-name.
     */
    def bindName[A](it: => Iterator[A]): Sequence[A] = new BindName(it)

    /**
     * Constructs a sequence from traversing block.
     */
    def block[A](op: Yield[A] => Unit): Sequence[A] = Block(op)

    @aliasOf("Function1[A, Unit]")
    type Yield[-A] = Function1[A, Unit]


// conversions

    @returnThat
    def from[A](to: Sequence[A]): Sequence[A] = to

    @compatibleConversion
    def unstringize(from: String): Sequence[Char] = Unstringize(from)

    @compatibleConversion
    def fromSIterable[A](from: Iterable[A]): Sequence[A] = FromSIterable(from)

    @compatibleConversion
    def fromJIterable[A](from: java.lang.Iterable[A]): Sequence[A] = FromJIterable(from)

    @compatibleConversion
    def fromJObjectInput(from: java.io.ObjectInput): Sequence[AnyRef] = FromJObjectInput(from)

    @compatibleConversion
    def fromJReader(from: java.io.Reader): Sequence[Char] = FromJReader(from)


// detail

    private[mada] def throwIfNegative(n: Int, method: String): Unit = {
        if (n < 0) {
            throw new IllegalArgumentException("sequence." + method + Tuple1(n))
        }
    }

}
