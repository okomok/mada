

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private[peg]
class Common {


// aliases

    @annotation.aliasOf("sequence.vector.Func[A, Unit]")
    type Action[-A] = sequence.vector.Func[A, Unit]

    @annotation.aliasOf("sequence.vector.Func3[A, Unit]")
    type Action3[-A] = sequence.vector.Func3[A, Unit]

    @annotation.compatibles
    lazy val Compatibles: Compatibles = Peg


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
    def advance(n: Int): Peg[Any] = Advance(n)

    /**
     * Matches the beginning of input.
     */
    lazy val begin: Peg[Any] = Begin()

    /**
     * Matches the end of input.
     */
    lazy val end: Peg[Any] = End()

    @annotation.equivalentTo("eps act { _ => body }")
    def call(body: => Unit): Peg[Any] = Call(body)

    /**
     * Matches any one element.
     */
    lazy val dot: Peg[Any] = Dot()

    /**
     * Epsilon; Matches an empty input.
     */
    lazy val eps: Peg[Any] = Eps()

    /**
     * Doesn't match any input.
     */
    lazy val fail: Peg[Any] = Fail()

    /**
     * java.lang.Mathches case-insensitively.
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
    def range[A](i: A, j: A)(implicit c: Ordering[A]): Peg[A] = Range(i, j, c)

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
    lazy val undefined: Peg[Any] = Undefined()


// pseudo

    /**
     * Constructs a Peg by-lazy.
     */
    def `lazy`[A](p: => Peg[A]): Peg[A] = Lazy(p)

    /**
     * Constructs a Peg by-name.
     */
    def byName[A](p: => Peg[A]): Peg[A] = ByName(p)

    /**
     * Constructs a pseudo try-catch expression.
     */
    def `try`[A](p: Peg[A]): Try[A] = Try(p)


// best

    /**
     * Chooses the longest match.
     */
    def longest[A](ps: Peg[A]*): Peg[A] = Longest(sequence.Iterative.from(ps))

    /**
     * Chooses the longest match.
     */
    def longest[A](ps: sequence.Iterative[Peg[A]]): Peg[A] = Longest(ps)

    /**
     * Chooses the shortest match.
     */
    def shortest[A](ps: Peg[A]*): Peg[A] = Shortest(sequence.Iterative.from(ps))

    /**
     * Chooses the shortest match.
     */
    def shortest[A](ps: sequence.Iterative[Peg[A]]): Peg[A] = Shortest(ps)


// set

    /**
     * Matches any element of set.
     */
    def multiple[A](es: A*): Peg[A] = Multiple(sequence.Iterative.from(es).breakOut: scala.collection.mutable.HashSet[A])

    /**
     * Matches any element of set.
     */
    def multiple[A](es: scala.collection.Set[A]): Peg[A] = Multiple(es)

    /**
     * Matches a key, then tries to match its value.
     */
    def switch[A](es: (A, Peg[A])*): Peg[A] = Switch(sequence.Iterative.from(es).breakOut: scala.collection.mutable.HashMap[A, Peg[A]])

    /**
     * Matches a key, then tries to match its value.
     */
    def switch[A](es: scala.collection.Map[A, Peg[A]]): Peg[A] = Switch(es)


// symbol

    type SymbolSet[A] = Peg[A] with scala.collection.mutable.Set[sequence.Vector[A]]

    @annotation.equivalentTo("symbolSet(vs)(c)")
    def symbolSet[A](vs: sequence.Vector[A]*)(implicit c: Ordering[A]): SymbolSet[A] = symbolSet(sequence.Iterative.from(vs))(c)

    /**
     * Returns a peg to optimize the form <code>k1|k2|k3|...</code>.
     */
    def symbolSet[A](vs: sequence.Iterative[sequence.Vector[A]])(c: Ordering[A]): SymbolSet[A] = {
        val r = new TheSymbolSet(new TSTree[A, Unit]()(c))
        for (v <- vs) {
            r += v
        }
        r
    }

    type SymbolMap[A] = Peg[A] with scala.collection.mutable.Map[sequence.Vector[A], Peg[A]]

    @annotation.equivalentTo("symbolMap(es)(c)")
    def symbolMap[A](es: (sequence.Vector[A], Peg[A])*)(implicit c: Ordering[A]): SymbolMap[A] = symbolMap(sequence.Iterative.from(es))(c)

    /**
     * Returns a peg to optimize the form <code>(k1 >> p1)|(k2 >> p2)|(k3 >> p3)|...</code>.
     */
    def symbolMap[A](es: sequence.Iterative[(sequence.Vector[A], Peg[A])])(c: Ordering[A]): SymbolMap[A] = {
        val r = new TheSymbolMap(new TSTree[A, Peg[A]]()(c))
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

    @annotation.compatibleConversion
    def fromChar(from: Char): Peg[Char] = FromChar(from)

    @annotation.compatibleConversion
    def unstringize(from: String): Peg[Char] = Unstringize(from)

    @annotation.compatibleConversion
    def fromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = FromRegexPattern(from)

    @annotation.compatibleConversion
    def fromSequence[A](from: sequence.Iterative[A]): Peg[A] = FromSequence(from)

    @annotation.compatibleConversion
    def fromSIterable[A](from: scala.Iterable[A]): Peg[A] = FromSIterable(from)

    @annotation.compatibleConversion
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
