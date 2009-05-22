

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object peg {


// aliases

    @aliasOf("Peg")
    type Type[A] = Peg[A]

    @aliasOf("Peg")
    val compatibles: Compatibles = Peg

    @aliasOf("vector.Func[A, Unit]")
    type Action[A] = vector.Func[A, Unit]

    @aliasOf("vector.Func3[A, Unit]")
    type Action3[A] = vector.Func3[A, Unit]


// constants

    /**
     * Alias of <code>vector.SINGULAR</code> specifying the parsing failure.
     */
    final val FAILURE = 0x80000000


// constructors

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Peg[A]): Peg[A] = to

    /**
     * Matches if it succeeds to advance.
     *
     * @param   i   the increment count
     */
    def advance[A](n: Int): Peg[A] = Advance[A](n)

    /**
     * Matches any one element.
     */
    def any[A]: Peg[A] = anyImpl.asInstanceOf[Peg[A]]
    private val anyImpl: Peg[Any] = advance(1)

    /**
     * Matches the beginning of input.
     */
    def begin[A]: Peg[A] = beginImpl.asInstanceOf[Peg[A]]
    private val beginImpl: Peg[Any] = lookaround3 { (v, i, _) => i == v.start }

    /**
     * Matches the end of input.
     */
    def end[A]: Peg[A] = endImpl.asInstanceOf[Peg[A]]
    private val endImpl: Peg[Any] = lookaround3 { (v, i, _) => i == v.end }

    /**
     * @return  <code>eps[A] act { _ => f() }</code>.
     */
    def call[A](f: Unit => Unit): Peg[A] = eps[A] act { _ => f() }

    /**
     * Epsilon; Matches an empty input.
     */
    def eps[A]: Peg[A] = epsImpl.asInstanceOf[Peg[A]]
    private val epsImpl: Peg[Any] = lookaround3 { (_, _, _) => true }

    /**
     * Always throws an Error.
     */
    def error[A]: Peg[A] = Error[A]()

    /**
     * Doesn't match any input.
     */
    def fail[A]: Peg[A] = failImpl.asInstanceOf[Peg[A]]
    private val failImpl: Peg[Any] = lookaround3 { (_, _, _) => false }

    /**
     * Mathches case-insensitively.
     */
    def icase(v: Vector[Char]): Peg[Char] = fromVector(vector.lowerCase(v)).lowerCaseRead

    /**
     * Zero-width assertion if region meets condition <code>pred</code>
     */
    def lookaround[A](pred: vector.Pred[A]): Peg[A] = Lookaround3(vector.triplify(pred))

    /**
     * Zero-width assertion if region meets condition <code>pred</code> (no heap allocations)
     */
    def lookaround3[A](pred: vector.Pred3[A]): Peg[A] = Lookaround3(pred)

    /**
     * Matches range values.
     */
    def range[A](i: A, j: A)(implicit c: Compare[A]): Peg[A] = Range(i, j, c)

    /**
     * @return  <code>fromRegexPattern(java.util.regex.Pattern.compile(str))</code>.
     * @see     java.util.regex
     */
    def regex(str: String): Peg[Char] = fromRegexPattern(java.util.regex.Pattern.compile(str))

    /**
     * Matches specified one element.
     */
    def single[A](e: A): Peg[A] = Single(e)


// pseudo

    /**
     * Constructs a lazy Peg object.
     */
    def byLazy[A](p: => Peg[A]): Peg[A] = new ByLazy(p)

    /**
     * Constructs a pseudo try-catch expression in
     */
    def `try`[A](p: Peg[A]): Try[A] = Try(p)


// best

    @packageObjectBrokenOverload
    object longest {
        /**
         * Chooses the longest match.
         */
        def apply[A](ps: Peg[A]*): Peg[A] = Longest(ps)

        /**
         * Chooses the longest match.
         */
        def apply[A](ps: Iterable[Peg[A]]): Peg[A] = Longest(ps)
    }


    @packageObjectBrokenOverload
    object shortest {
        /**
         * Chooses the shortest match.
         */
        def apply[A](ps: Peg[A]*): Peg[A] = Shortest(ps)

        /**
         * Chooses the shortest match.
         */
        def apply[A](ps: Iterable[Peg[A]]): Peg[A] = Shortest(ps)
    }


// set

    @packageObjectBrokenOverload
    object multiple {
        /**
         * Matches any element of set.
         */
        def apply[A](es: A*): Peg[A] = Multiple(sequence.fromSIterable(es).toSHashSet)

        /**
         * Matches any element of set.
         */
        def apply[A](es: scala.collection.Set[A]): Peg[A] = Multiple(es)
    }

    @packageObjectBrokenOverload
    object switch {
        /**
         * Matches a key, then tries to match its value.
         */
        def apply[A](es: (A, Peg[A])*): Peg[A] = Switch(sequence.fromSIterable(es).toSHashMap)

        /**
         * Matches a key, then tries to match its value.
         */
        def apply[A](es: scala.collection.Map[A, Peg[A]]): Peg[A] = Switch(es)
    }


// verify

    /**
     * Throws VerificationException if p doesn't match.
     */
    def verify[A](p: Peg[A]): Peg[A] = Verify(p)


// conversions

    def fromChar(from: Char): Peg[Char] = Single(from)
    def fromIterable[A](from: Iterable[A]): Peg[A] = FromIterable(from)
    def fromRegex(from: scala.util.matching.Regex): Peg[Char] = FromRegexPattern(from.pattern)
    def fromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = FromRegexPattern(from)
    def unstringize(from: String): Peg[Char] = FromVector(vector.unstringize(from))
    def fromVector[A](from: Vector[A]): Peg[A] = FromVector(from)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def fromIterableBy[A](from: Iterable[A])(pred: (A, A) => Boolean): Peg[A] = FromIterable(from, pred)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def unstringizeBy(from: String)(pred: (Char, Char) => Boolean): Peg[Char] = FromVector(vector.unstringize(from), pred)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def fromVectorBy[A](from: Vector[A])(pred: (A, A) => Boolean): Peg[A] = FromVector(from, pred)


// detail

    private[mada] def throwIfZeroWidth[A](p: Peg[A], method: String): Unit = {
        if (IsZeroWidth(p)) {
            throw new IllegalArgumentException(method + " doesn't allow zero-width")
        }
    }
}
