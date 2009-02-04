

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Peg</code>.
 */
object Pegs {
    import peg._


// constants

    /**
     * @return  <code>Vectors.NULL_INDEX</code> specifying the parsing failure.
     */
    final val FAILURE = Vectors.NULL_INDEX


// constructors

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Peg[A]): Peg[A] = to

    /**
     * Matches if it succeeds to advance.
     * @param i the increment count
     */
    def advance[A](i: Int): Peg[A] = Advance[A](i)

    /**
     * Matches any one element.
     */
    def any[A]: Peg[A] = Any_[A]

    /**
     * Matches the beginning of input.
     */
    def begin[A]: Peg[A] = Begin[A]

    /**
     * Matches the end of input.
     */
    def end[A]: Peg[A] = End[A]

    /**
     * Matches an empty input while calling function.
     */
    def call[A](f: Unit => Any): Peg[A] = Call[A](f)

    /**
     * Matches an empty input, a.k.a epsilon.
     */
    def eps[A]: Peg[A] = Eps[A]

    /**
     * Always throws an Error.
     */
    def error[A]: Peg[A] = Error[A]

    /**
     * Doesn't match any input.
     */
    def fail[A]: Peg[A] = Fail[A]

    /**
     * Matches in case-insensitive.
     */
    def icase(v: Vector[Char]): Peg[Char] = Icase(v)

    /**
     * Reads input as lower cases, then tries to match.
     */
    def lowerCaseRead(p: Peg[Char]): Peg[Char] = LowerCaseRead(p)

    /**
     * Matches range values.
     */
    def range[A](i: A, j: A)(implicit c: A => Ordered[A]): Peg[A] = Range(i, j)(c)

    /**
     * Matches a regular expression.
     * @see java.util.regex
     */
    def regex(str: String): Peg[Char] = Regex(str)

    /**
     * Matches specified one element.
     */
    def single[A](e: A): Peg[A] = Single(e)

    /**
     * Constructs a lazy Peg object.
     */
    def `lazy`[A](p: => Peg[A]): Peg[A] = Lazy(p)

    /**
     * Constructs a synchronized Peg object. This will be unused.
     */
    def `synchronized`[A](p: Peg[A]): Peg[A] = Synchronized(p)

    /**
     * Constructs a pseudo try-catch expression in Pegs.
     */
    def `try`[A](p: Peg[A]) = Try(p)


    /**
     * Chooses the longest match.
     */
    def longest[A](ps: Peg[A]*): Peg[A] = Longest(ps)

    /**
     * Chooses the longest match.
     */
    def longest[A](ps: Iterable[Peg[A]]): Peg[A] = Longest(ps)

    /**
     * Chooses the shortest match.
     */
    def shortest[A](ps: Peg[A]*): Peg[A] = Shortest(ps)

    /**
     * Chooses the longest match.
     */
    def shortest[A](ps: Iterable[Peg[A]]): Peg[A] = Shortest(ps)


    /**
     * Matches any element of set.
     */
    def singles[A](es: A*): Peg[A] = Singles(es: _*)

    /**
     * Matches any element of set.
     */
    def singles[A](es: Set[A]): Peg[A] = Singles(es)

    /**
     * Matches a key, then tries to match its value.
     */
    def switch[A](es: (A, Peg[A])*): Peg[A] = Switch(es: _*)

    /**
     * Matches a key, then tries to match its value.
     */
    def switch[A](es: Map[A, Peg[A]]): Peg[A] = Switch(es)


// compatibles(from)

    /**
     * Converts a character to <code>Peg</code>.
     */
    def fromChar(from: Char): Peg[Char] = single(from)

    /**
     * Converts a <code>regex.Pattern</code> to <code>Peg</code>.
     */
    def fromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = FromRegexPattern(from)

    /**
     * Converts a string to <code>Peg</code>.
     */
    def fromString(from: String): Peg[Char] = FromString(from)

    /**
     * Converts a vector to <code>Peg</code>.
     */
    def fromVector[A](from: Vector[A]): Peg[A] = FromVector(from)


// alias methods

    def __*[A]: Peg[A] = any[A].star
    def __*?[A](p: Peg[A]): Peg[A] = any[A].starBefore(p)
    def __*>>[A](p: Peg[A]): Peg[A] = any[A].starUntil(p)

    def ?~[A](p: Peg[A]): Peg[A] = p.lookAhead
    def ?![A](p: Peg[A]): Peg[A] = p.lookAhead.not
    def ?<~[A](p: Peg[A]): Peg[A] = p.lookBehind
    def ?<![A](p: Peg[A]): Peg[A] = p.lookBehind.not
    def ?<<~[A](p: Peg[A]): Peg[A] = p.lookBack
    def ?<<![A](p: Peg[A]): Peg[A] = p.lookBack.not


// aliases

    /**
     * Alias of <code>Peg[A]</code>
     */
    type Type[A] = Peg[A]

    /**
     * Alias of <code>Peg</code>
     */
    val Compatibles = Peg

    /**
     * Alias of <code>peg.PegProxy</code>
     */
    type PegProxy[A] = peg.PegProxy[A]

    /**
     * Alias of <code>peg.Rule</code>
     */
    type Rule[A] = peg.Rule[A]

    /**
     * Alias of <code>peg.ASTreeBuilder</code>
     */
    val ASTreeBuilder = peg.ASTreeBuilder

    /**
     * Alias of <code>peg.ASTreeBuilder</code>
     */
    type ASTreeBuilder[T <: javax.swing.tree.DefaultMutableTreeNode] = peg.ASTreeBuilder[T]

    /**
     * Alias of <code>peg.SymbolSet</code>
     */
    val SymbolSet = peg.SymbolSet

    /**
     * Alias of <code>peg.SymbolMap</code>
     */
    val SymbolMap = peg.SymbolMap

    /**
     * Alias of <code>peg.SymbolSet</code>
     */
    type SymbolSet[A] = peg.SymbolSet[A]

    /**
     * Alias of <code>peg.SymbolMap</code>
     */
    type SymbolMap[A] = peg.SymbolMap[A]

    /**
     * Alias of <code>peg.ByNeedActions</code>
     */
    type ByNeedActions[A] = peg.ByNeedActions[A]

    /**
     * Alias of <code>peg.RegionActions</code>
     */
    type RegionActions[A] = peg.RegionActions[A]

    /**
     * Alias of <code>peg.CapturingGroups</code>
     */
    type CapturingGroups[K, A] = peg.CapturingGroups[K, A]

    /**
     * Alias of <code>peg.PrettyPrinter</code>
     */
    val PrettyPrinter = peg.PrettyPrinter

    /**
     * Alias of <code>peg.PrettyPrinter</code>
     */
    type PrettyPrinter = peg.PrettyPrinter

    /**
     * Throws VerificationException if p doesn't match.
     */
    def verify[A](p: Peg[A]): Peg[A] = Verify(p)

    /**
     * Alias of <code>peg.VerificationException</code>
     */
    val VerificationException = peg.VerificationException

    /**
     * Alias of <code>peg.VerificationException</code>
     */
    type VerificationException[A] = peg.VerificationException[A]
}
