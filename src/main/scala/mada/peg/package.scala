

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object peg {


// aliases

    @aliasOf("Peg")
    type Type[A] = Peg[A]

    @aliasOf("vector.Func[A, Unit]")
    type Action[A] = vector.Func[A, Unit]

    @aliasOf("vector.Func3[A, Unit]")
    type Action3[A] = vector.Func3[A, Unit]

    @compatibles
    val compatibles: Compatibles = Peg


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
     * Matches the beginning of input.
     */
    def begin[A]: Peg[A] = Begin[A]()

    /**
     * Matches the end of input.
     */
    def end[A]: Peg[A] = End[A]()

    /**
     * @return  <code>eps[A] act { _ => body }</code>.
     */
    def call[A](body: => Unit): Peg[A] = new Call[A](body)

    /**
     * Matches any one element.
     */
    def dot[A]: Peg[A] = Dot[A]()

    /**
     * Epsilon; Matches an empty input.
     */
    def eps[A]: Peg[A] = Eps[A]()

    /**
     * Always throws an Error.
     */
    def error[A]: Peg[A] = Error[A]()

    /**
     * Doesn't match any input.
     */
    def fail[A]: Peg[A] = Fail[A]()

    /**
     * Mathches case-insensitively.
     */
    def icase(v: Vector[Char]): Peg[Char] = Icase(v)

    /**
     * Zero-width assertion if region meets condition <code>pred</code>
     */
    def lookaround[A](pred: vector.Pred[A]): Peg[A] = Lookaround(pred)

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
    def regex(str: String): Peg[Char] = Regex(str)

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

    def fromChar(from: Char): Peg[Char] = FromChar(from)
    def unstringize(from: String): Peg[Char] = Unstringize(from)
    def fromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = FromRegexPattern(from)
    def fromVector[A](from: Vector[A]): Peg[A] = FromVector(from)
    def fromSIterable[A](from: scala.Iterable[A]): Peg[A] = FromSIterable(from)
    def fromSRegex(from: scala.util.matching.Regex): Peg[Char] = FromSRegex(from)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def unstringizeBy(from: String)(pred: (Char, Char) => Boolean): Peg[Char] = UnstringizeBy(from, pred)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def fromVectorBy[A](from: Vector[A])(pred: (A, A) => Boolean): Peg[A] = FromVectorBy(from, pred)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def fromSIterableBy[A](from: Iterable[A])(pred: (A, A) => Boolean): Peg[A] = FromSIterableBy(from, pred)


// detail

    private[mada] def throwIfZeroWidth[A](p: Peg[A], method: String): Unit = {
        if (IsZeroWidth(p)) {
            throw new IllegalArgumentException(method + " doesn't allow zero-width")
        }
    }
}
