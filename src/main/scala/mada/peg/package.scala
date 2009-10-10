

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object peg {


// aliases

    @aliasOf("Peg")
    type Type[A] = Peg[A]

    @aliasOf("sequence.vector.Func[A, Unit]")
    type Action[A] = sequence.vector.Func[A, Unit]

    @aliasOf("sequence.vector.Func3[A, Unit]")
    type Action3[A] = sequence.vector.Func3[A, Unit]

    @compatibles
    val Compatibles: Compatibles = Peg


// constants

    /**
     * Alias of <code>sequence.vector.SINGULAR</code> specifying the parsing failure.
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
     * @param   n   the increment count
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

    @equivalentTo("eps[A] act { _ => body }")
    def call[A](body: => Unit): Peg[A] = Call[A](util.byName(body))

    /**
     * Matches any one element.
     */
    def dot[A]: Peg[A] = Dot[A]()

    /**
     * Epsilon; Matches an empty input.
     */
    def eps[A]: Peg[A] = Eps[A]()

    /**
     * Doesn't match any input.
     */
    def fail[A]: Peg[A] = Fail[A]()

    /**
     * Mathches case-insensitively.
     */
    def icase(v: sequence.Vector[Char]): Peg[Char] = Icase(v)

    /**
     * Zero-width assertion if region meets condition <code>pred</code>
     */
    def lookaround[A](pred: sequence.vector.Pred[A]): Peg[A] = Lookaround(pred)

    /**
     * Zero-width assertion if region meets condition <code>pred</code> (no heap allocations)
     */
    def lookaround3[A](pred: sequence.vector.Pred3[A]): Peg[A] = Lookaround3(pred)

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

    /**
     * Always results in assertion failure.
     */
    def undefined[A]: Peg[A] = Undefined[A]()


// pseudo

    /**
     * Constructs a Peg by-lazy.
     */
    def byLazy[A](p: => Peg[A]): Peg[A] = ByLazy(util.byLazy(p))

    /**
     * Constructs a Peg by-name.
     */
    def byName[A](p: => Peg[A]): Peg[A] = ByName(util.byName(p))

    /**
     * Constructs a pseudo try-catch expression.
     */
    def `try`[A](p: Peg[A]): Try[A] = Try(p)


// best

    /**
     * Chooses the longest match.
     */
    def longest[A](ps: Peg[A]*): Peg[A] = Longest(sequence.iterative.from(ps))

    /**
     * Chooses the longest match.
     */
    def longest[A](ps: sequence.Iterative[Peg[A]]): Peg[A] = Longest(ps)

    /**
     * Chooses the shortest match.
     */
    def shortest[A](ps: Peg[A]*): Peg[A] = Shortest(sequence.iterative.from(ps))

    /**
     * Chooses the shortest match.
     */
    def shortest[A](ps: sequence.Iterative[Peg[A]]): Peg[A] = Shortest(ps)


// set

    /**
     * Matches any element of set.
     */
    def multiple[A](es: A*): Peg[A] = Multiple(sequence.iterative.from(es).toSHashSet)

    /**
     * Matches any element of set.
     */
    def multiple[A](es: scala.collection.Set[A]): Peg[A] = Multiple(es)

    /**
     * Matches a key, then tries to match its value.
     */
    def switch[A](es: (A, Peg[A])*): Peg[A] = Switch(sequence.iterative.from(es).toSHashMap)

    /**
     * Matches a key, then tries to match its value.
     */
    def switch[A](es: scala.collection.Map[A, Peg[A]]): Peg[A] = Switch(es)


// symbol

    type SymbolSet[A] = Peg[A] with scala.collection.mutable.Set[sequence.Vector[A]]

    @equivalentTo("symbolSet(vs)(c)")
    def symbolSet[A](vs: sequence.Vector[A]*)(implicit c: Compare[A]): SymbolSet[A] = symbolSet(sequence.iterative.from(vs))(c)

    /**
     * Returns a peg to optimize the form <code>k1|k2|k3|...</code>.
     */
    def symbolSet[A](vs: sequence.Iterative[sequence.Vector[A]])(lt: compare.Func[A]): SymbolSet[A] = {
        val r = new TheSymbolSet(new TSTree[A, Unit](lt))
        for (v <- vs) {
            r += v
        }
        r
    }

    type SymbolMap[A] = Peg[A] with scala.collection.mutable.Map[sequence.Vector[A], Peg[A]]

    @equivalentTo("symbolMap(es)(c)")
    def symbolMap[A](es: (sequence.Vector[A], Peg[A])*)(implicit c: Compare[A]): SymbolMap[A] = symbolMap(sequence.iterative.from(es))(c)

    /**
     * Returns a peg to optimize the form <code>(k1 >> p1)|(k2 >> p2)|(k3 >> p3)|...</code>.
     */
    def symbolMap[A](es: sequence.Iterative[(sequence.Vector[A], Peg[A])])(lt: compare.Func[A]): SymbolMap[A] = {
        val r = new TheSymbolMap(new TSTree[A, Peg[A]](lt))
        for (e <- es) {
            r += Tuple2(e._1, e._2)
        }
        r
    }

    /**
     * A short-cut to create a symbol entry.
     */
    def entry[A](k: sequence.Vector[A], v: Peg[A]): (sequence.Vector[A], Peg[A]) = (k, v)


// verify

    /**
     * Throws VerificationException if p doesn't match.
     */
    def verify[A](p: Peg[A]): Peg[A] = Verify(p)


// conversion

    @compatibleConversion
    def fromChar(from: Char): Peg[Char] = FromChar(from)

    @compatibleConversion
    def unstringize(from: String): Peg[Char] = Unstringize(from)

    @compatibleConversion
    def fromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = FromRegexPattern(from)

    @compatibleConversion
    def fromSequence[A](from: sequence.Iterative[A]): Peg[A] = FromSequence(from)

    @compatibleConversion
    def fromSIterable[A](from: scala.Iterable[A]): Peg[A] = FromSIterable(from)

    @compatibleConversion
    def fromSRegex(from: scala.util.matching.Regex): Peg[Char] = FromSRegex(from)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def unstringizeBy(from: String)(pred: (Char, Char) => Boolean): Peg[Char] = UnstringizeBy(from, pred)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def fromSequenceBy[A](from: sequence.Iterative[A])(pred: (A, A) => Boolean): Peg[A] = FromSequenceBy(from, pred)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def fromSIterableBy[A](from: Iterable[A])(pred: (A, A) => Boolean): Peg[A] = FromSIterableBy(from, pred)

}
