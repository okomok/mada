

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


package object iterative {


    @aliasOf("Iterative")
    type Type[+A] = Iterative[A]


// constructors

    /**
     * Creates a sequence initially containing the specified elements.
     */
    object Of {
        def apply[A](from: A*): Iterative[A] = fromSIterable(from)
        def unapplySeq[A](from: Iterative[A]): Option[Seq[A]] = Some(from.toSSequence)
    }

    /**
     * The empty sequence
     */
    val empty: Iterative[Nothing] = Empty()

    /**
     * Typed <code>empty</code>
     */
    def emptyOf[A]: Iterative[A] = empty

    /**
     * A sequence with a single element.
     */
    def single[A](e: A): Iterative[A] = Single(e)

    /**
     * Refers a sequence by lazy.
     */
    def byLazy[A](seq: => Iterative[A]): Iterative[A] = ByLazy(function.ofLazy(seq))

    /**
     * Refers a sequence by name.
     */
    def byName[A](seq: => Iterative[A]): Iterative[A] = ByName(function.ofName(seq))

    /**
     * Creates an infinite sequence repeatedly applying <code>op</code>.
     */
    def iterate[A](z: A)(op: A => A): Iterative[A] = Iterate(z, op)

    /**
     * Repeats <code>e</code> infinitely.
     */
    def repeat[A](e: A): Iterative[A] = Repeat(e)

    /**
     * Unfolds right to left.
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Iterative[B] = UnfoldRight(z, op)

    /**
     * Creates a sequence starting from <code>it<code>.
     */
    def bind[A](it: Iterator[A]): Iterative[A] = Bind(it)

    /**
     * Creates a sequence starting from <code>it<code>, which is evaluated by-name.
     */
    def bindName[A](it: => Iterator[A]): Iterative[A] = BindName(function.ofName(it))

    /**
     * Constructs a sequence from traversing block.
     */
    def block[A](op: Yield[A] => Unit): Iterative[A] = Block(op)

    @aliasOf("Function1[A, Unit]")
    type Yield[-A] = Function1[A, Unit]


// conversions

    @returnThat
    def from[A](to: Iterative[A]): Iterative[A] = to

    @compatibleConversion
    def unstringize(from: String): Iterative[Char] = Unstringize(from)

    @compatibleConversion
    def fromSIterable[A](from: Iterable[A]): Iterative[A] = FromSIterable(from)

    @compatibleConversion
    def fromJIterable[A](from: java.lang.Iterable[A]): Iterative[A] = FromJIterable(from)

    @compatibleConversion
    def fromJObjectInput(from: java.io.ObjectInput): Iterative[AnyRef] = FromJObjectInput(from)

    @compatibleConversion
    def fromJReader(from: java.io.Reader): Iterative[Char] = FromJReader(from)


// detail

    private[mada] def throwIfNegative(n: Int, method: String): Unit = {
        if (n < 0) {
            throw new IllegalArgumentException("sequence." + method + Tuple1(n))
        }
    }

}
