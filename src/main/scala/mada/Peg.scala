

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Peg</code>.
 */
object Peg extends peg.Compatibles {
    import peg._


// constants

    /**
     * Alias of <code>Vector.NULL_INDEX</code> specifying the parsing failure.
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
     * @param i the increment count
     */
    def advance[A](n: Int): Peg[A] = Advance[A](n)

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
     * @return  <code>eps[A] act { _ => f() }</code>.
     */
    def call[A](f: Unit => Any): Peg[A] = eps[A] act { _ => f() }

    /**
     * Epsilon; Matches an empty input.
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
     * Mathches case-insensitively.
     */
    def icase(v: Vector[Char]): Peg[Char] = lowerCaseRead(fromVector(Vector.lowerCase(v)))

    /**
     * Reads input as lower cases, then tries to match.
     */
    def lowerCaseRead(p: Peg[Char]): Peg[Char] = p readMap { v => Vector.lowerCase(v) }

    /**
     * Matches range values.
     */
    def range[A](i: A, j: A)(implicit c: A => Ordered[A]): Peg[A] = Range(i, j)(c)

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
    def `lazy`[A](p: => Peg[A]): Peg[A] = Lazy(p)

    /**
     * Constructs a synchronized Peg object. This will be unused.
     */
    def `synchronized`[A](p: Peg[A]): Peg[A] = Synchronized(p)

    /**
     * Constructs a pseudo try-catch expression in Peg.
     */
    def `try`[A](p: Peg[A]) = Try(p)


// best

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


// set

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


// alias compatibles

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: peg.Compatibles = this

  // from

    /**
     * Alias of <code>madaPegFromSingle</code>
     */
    def fromChar(from: Char): Peg[Char] = madaPegFromChar(from)

    /**
     * Alias of <code>madaPegFromRegex</code>
     */
    def fromRegex(from: scala.util.matching.Regex): Peg[Char] = madaPegFromRegex(from)

    /**
     * Alias of <code>madaPegFromRegexPattern</code>
     */
    def fromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = madaPegFromRegexPattern(from)

    /**
     * Alias of <code>madaPegFromString</code>
     */
    def fromString(from: String): Peg[Char] = madaPegFromString(from)

    /**
     * Alias of <code>madaPegFromVector)</code>
     */
    def fromVector[A](from: Vector[A]): Peg[A] = madaPegFromVector(from)


// alias methods

  // any

    /**
     * @return  <code>any[A].*</code>.
     */
    def __*[A]: Peg[A] = any[A].*

    /**
     * @return  <code>any[A].starBefore(p)</code>.
     */
    def __*?[A](p: Peg[A]): Peg[A] = any[A].starBefore(p)

    /**
     * @return  <code>any[A].starUntil(p)</code>.
     */
    def __*>>[A](p: Peg[A]): Peg[A] = any[A].starUntil(p)

  // look

    /**
     * @return  <code>~p</code>.
     */
    def ?~[A](p: Peg[A]): Peg[A] = ~p

    /**
     * @return  <code>!p</code>.
     */
    def ?![A](p: Peg[A]): Peg[A] = !p

    /**
     * @return  <code>p.lookBehind</code>.
     */
    def ?<~[A](p: Peg[A]): Peg[A] = p.lookBehind

    /**
     * @return  <code>p.lookBehind.negate</code>.
     */
    def ?<![A](p: Peg[A]): Peg[A] = p.lookBehind.negate

    /**
     * @return  <code>p.lookBack</code>.
     */
    def ?<<~[A](p: Peg[A]): Peg[A] = p.lookBack

    /**
     * @return  <code>p.lookBack.negate</code>.
     */
    def ?<<![A](p: Peg[A]): Peg[A] = p.lookBack.negate


// aliases

    /**
     * Alias of <code>peg.PegProxy</code>
     */
    type PegProxy[A] = peg.PegProxy[A]

    /**
     * Alias of <code>peg.Rule</code>
     */
    type Rule[A] = peg.Rule[A]

    /**
     * Alias of <code>Vector.Func[A, Any]</code>
     */
    type Action[A] = Vector.Func[A, Any]

    /**
     * Alias of <code>Vector.Func3[A, Any]</code>
     */
    type Action3[A] = Vector.Func3[A, Any]

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
     * Alias of <code>peg.StackActions</code>
     */
    type StackActions[A, B] = peg.StackActions[A, B]

    /**
     * Alias of <code>peg.CapturingGroups</code>
     */
    type CapturingGroups[K, A] = peg.CapturingGroups[K, A]

    /**
     * Alias of <code>peg.Memoizer</code>
     */
    type Memoizer[A] = peg.Memoizer[A]

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


/**
 * The PEG parser combinator:
 * <ul>
 * <li/>Sequence: <code>e1 >> e2</code>
 * <li/>Ordered choice: <code>e1 | e2</code>
 * <li/>Zero-or-more: <code>e.*</code>
 * <li/>One-or-more: <code>e.+</code>
 * <li/>Optional: <code>e.?</code>
 * <li/>And-predicate: <code>~e</code> (not <code>&e</code>)
 * <li/>Not-predicate: <code>!e</code>
 * </ul><p/>
 *
 * Note PEG operations are always possessive unlike regular expression default behavior.
 */
trait Peg[A] {
    import peg._

    /**
     * Parses specified region of input Vector.
     * This apparently legacy interface is designed so that heap-allocation is removal.
     *
     * @return  next position if parsing succeeds, <code>Peg.FAILURE</code> otherwise.
     */
    def parse(v: Vector[A], first: Int, last: Int): Int

    /**
     * Returns the matched width.
     *
     * @throws  UnsupportedOperationException if width is not fixed.
     */
    def width: Int = throw new UnsupportedOperationException("Peg.width")


// set

    /**
     * Matches <code>this</code> and <code>that</code>.
     *
     * @see     & as alias.
     */
    final def and(that: Peg[A]): Peg[A] = And(this, that)

    /**
     * Ordered choice
     *
     * @see     | as alias.
     */
    final def or(that: Peg[A]): Peg[A] = Or(this, that)

    /**
     * Matches <code>this</code> but not <code>that</code>.
     *
     * @see     - as alias.
     */
    final def minus(that: Peg[A]) = Minus(this, that)

    /**
     * Matches <code>this</code> or <code>that</code>, but not both.
     *
     * @see     ^ as alias.
     */
    final def xor(that: Peg[A]): Peg[A] = Xor(this, that)

    /**
     * Matches if this peg not match, then advances <code>width</code>.
     * Doesn't work unless this peg has fixed width.
     *
     * @see     unary_- as alias.
     */
    final def negate: Peg[A] = Negate(this)


// sequencing

    /**
     * Sequence
     *
     * @see     >> as alias.
     */
    final def seqAnd(that: Peg[A]): Peg[A] = SeqAnd(this, that)

    /**
     * @return  <code>(this >> that.?) | that</code>.
     * @see     || as alias.
     */
    final def seqOr(that: Peg[A]): Peg[A] = (this >> that.?) | that


// star

    /**
     * Zero-or-more; possessive.
     *
     * @see     * as alias.
     */
    final def star: Peg[A] = Star(this)
                        // = this starUntil !this (if speed and actions are unneeded.)

    /**
     * @return  <code>this starUntil ~that</code>.
     */
    final def starBefore(that: Peg[A]): Peg[A] = this starUntil ~that

    /**
     * Matches everything up to and including <code>that</code>.
     */
    final def starUntil(that: Peg[A]): Peg[A] = StarUntil(this, that)


// plus

    /**
     * One-or-more
     *
     * @return  <code>this >> this.*</code>.
     * @see     + as alias.
     */
    final def plus: Peg[A] = this >> this.*

    /**
     * @return  <code>this >> (this starBefore that)</code>.
     */
    final def plusBefore(that: Peg[A]): Peg[A] = this >> (this starBefore that)

    /**
     * @return  <code>this >> (starUntil that)</code>.
     */
    final def plusUntil(that: Peg[A]): Peg[A] = this >> (this starUntil that)


// optional

    /**
     * Optional
     *
     * @return  <code>this | Peg.eps[A]</code>.
     * @see     ? as alias.
     */
    final def opt: Peg[A] = this | Peg.eps[A]

    /**
     * @return  <code>(this >> ~that) | ~that </code>.
     */
    final def optBefore(that: Peg[A]): Peg[A] = (this >> ~that) | ~that

    /**
     * @return  <code>(this >> that) | that</code>.
     */
    final def optUntil(that: Peg[A]): Peg[A] = (this >> that) | that


// assertions

    /**
     * And-predicate; look-ahead zero-width assertion
     *
     * @see     unary_~ as alias.
     */
    final def lookAhead: Peg[A] = LookAhead(this)

    /**
     * Look-behind zero-width assertion
     */
    final def lookBehind: Peg[A] = LookBehind(this)

    /**
     * Look-back zero-width assertion; looking over input as reversed.
     */
    final def lookBack: Peg[A] = LookBack(this)


// action

    /**
     * Associates semantic action.
     *
     * @see     apply as alias.
     */
    final def act(f: Peg.Action[A]): Peg[A] = Act(this, f)

    /**
     * Associates semantic action. (no heap allocation)
     */
    final def act3(f: Peg.Action3[A]): Peg[A] = Act3(this, f)


// utilities

    /**
     * Matches if <code>p</code> too returns true.
     */
    final def andIf(p: Vector.Func[A, Boolean]): Peg[A] = AndIf(this, p)

    /**
     * Returns an alias of this peg.
     */
    final def identity: Peg[A] = Identity(this)

    /**
     * Overrides <code>toString</code> to return <code>name</code>.
     */
    final def named(name: String) = Named(this, name)

    /**
     * Repeats at least <code>min</code> times and at most <code>max</code> times.
     */
    final def repeat(min: Int, max: Int): Peg[A] = Repeat(this, min, max)

    /**
     * Temporarily converts input using one-to-one projection <code>f</code>.
     *
     * @pre     <code>v.size == f(v).size</code> for any <code>v</code>.
     */
    final def readMap[Z](f: Vector[Z] => Vector[A]): Peg[Z] = ReadMap(this, f)

    /**
     * @return  <code>readMap{ v => v.map(f) }</code>.
     */
    final def unmap[Z](f: Z => A): Peg[Z] = readMap({ v => v.map(f) })


// algorithms

    /**
     * Finds <code>Vector.Region</code> which this peg matches.
     */
    final def find(v: Vector[A]): Option[Vector[A]] = Find(this, v)

    /**
     * Returns position where parsing succeeds.
     */
    final def lookingAt(v: Vector[A]): Option[Int] = LookingAt(this, v)

    /**
     * Returns true iif input is fully matched.
     */
    final def matches(v: Vector[A]): Boolean = Matches(this, v)

    /**
     * Tokenizes using this peg.
     */
    final def tokenize(v: Vector[A]): Iterator[Vector[A]] = Tokenize(this, v)

    /**
     * Filters input using this peg.
     */
    final def filterFrom(v: Vector[A]): Iterator[A] = FilterFrom(this, v)


// aliases

    /**
     * Alias of <code>act</code>
     */
    final def apply(f: Peg.Action[A]): Peg[A] = act(f)

    /**
     * And-predicate; alias of <code>lookAhead</code>
     */
    final def unary_~ : Peg[A] = lookAhead

    /**
     * Not-predicate; alias of <code>lookAhead.negate</code>
     */
    final def unary_! : Peg[A] = lookAhead.negate

    /**
     * Alias of <code>negate</code>
     */
    final def unary_- : Peg[A] = negate

    /**
     * Alias of <code>and</code>
     */
    final def &(that: Peg[A]): Peg[A] = and(that)

    /**
     * Ordered choice; alias of <code>or</code>
     */
    final def |(that: Peg[A]): Peg[A] = or(that)

    /**
     * Alias of <code>minus</code>
     */
    final def -(that: Peg[A]): Peg[A] = minus(that)

    /**
     * Alias of <code>xor</code>
     */
    final def ^(that: Peg[A]): Peg[A] = xor(that)

    /**
     * Sequence; alias of <code>seqAnd</code>
     */
    final def >>(that: Peg[A]): Peg[A] = seqAnd(that)

    /**
     * Alias of <code>seqOr</code>
     */
    final def ||(that: Peg[A]): Peg[A] = seqOr(that)

    /**
     * Zero-or-more; alias of <code>star</code>
     */
    final def * : Peg[A] = star

    /**
     * Alias of <code>starBefore</code>
     */
    final def *?(that: Peg[A]): Peg[A] = starBefore(that)

    /**
     * Alias of <code>starUntil</code>
     */
    final def *>>(that: Peg[A]): Peg[A] = starUntil(that)

    /**
     * One-or-more; alias of <code>plus</code>
     */
    final def + : Peg[A] = plus

    /**
     * Alias of <code>plusBefore</code>
     */
    final def +?(that: Peg[A]): Peg[A] = plusBefore(that)

    /**
     * Alias of <code>plusUntil</code>
     */
    final def +>>(that: Peg[A]): Peg[A] = plusUntil(that)

    /**
     * Optional; alias of <code>opt</code>
     */
    final def ? : Peg[A] = opt

    /**
     * Alias of <code>optBefore</code>
     */
    final def ??(that: Peg[A]): Peg[A] = optBefore(that)

    /**
     * Alias of <code>optUntil</code>
     */
    final def ?>>(that: Peg[A]): Peg[A] = optUntil(that)

    /**
     * Zero-width positive lookahead
     *
     * @return  <code>this >> ~that</code>.
     */
    final def >>?~(that: Peg[A]): Peg[A] = this >> ~that

    /**
     * Zero-width negative lookahead
     *
     * @return  <code>this >> !that</code>.
     */
    final def >>?!(that: Peg[A]): Peg[A] = this >> !that

    /**
     * Zero-width positive lookbehind
     *
     * @return  <code>this >> that.lookBehind</code>.
     */
    final def >>?<~(that: Peg[A]): Peg[A] = this >> that.lookBehind

    /**
     * Zero-width negative lookbehind
     *
     * @return  <code>this >> that.lookBehind.negate</code>.
     */
    final def >>?<!(that: Peg[A]): Peg[A] = this >> that.lookBehind.negate

    /**
     * Zero-width positive lookback
     *
     * @return  <code>this >> that.lookBack</code>.
     */
    final def >>?<<~(that: Peg[A]): Peg[A] = this >> that.lookBack

    /**
     * Zero-width negative lookback
     *
     * @return  <code>this >> that.lookBack.negate</code>.
     */
    final def >>?<<!(that: Peg[A]): Peg[A] = this >> that.lookBack.negate


// misc

    /**
     * @return  <code>(e, this)</code>.
     */
    final def inCase(e: A): (A, Peg[A]) = (e, this)
}
