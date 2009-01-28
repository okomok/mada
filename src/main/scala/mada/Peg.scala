

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on type <code>Peg</code>.
 */
object Peg {
    import peg._

    /**
     * Specifies parsing failure.
     */
    final val FAILURE = -1

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


    /**
     * Alias of peg.Compatibles. Prefer this to that.
     */
    val Compatibles = peg.Compatibles

    /**
     * Converts a regex.Pattern to Peg.
     */
    def regexPatternPeg(from: java.util.regex.Pattern): Peg[Char] = RegexPatternPeg(from)

    /**
     * Converts a string to Peg.
     */
    def stringPeg(from: String): Peg[Char] = StringPeg(from)

    /**
     * Converts a vector to Peg.
     */
    def vectorPeg[A](from: Vector[A]): Peg[A] = VectorPeg(from)


    def __*[A]: Peg[A] = any[A].star
    def __*?[A](p: Peg[A]): Peg[A] = any[A].starBefore(p)
    def __*>>[A](p: Peg[A]): Peg[A] = any[A].starUntil(p)

    def ?~[A](p: Peg[A]): Peg[A] = p.lookAhead
    def ?![A](p: Peg[A]): Peg[A] = p.lookAhead.not
    def ?<~[A](p: Peg[A]): Peg[A] = p.lookBehind
    def ?<![A](p: Peg[A]): Peg[A] = p.lookBehind.not
    def ?<<~[A](p: Peg[A]): Peg[A] = p.lookBack
    def ?<<![A](p: Peg[A]): Peg[A] = p.lookBack.not


    /**
     * Alias of <code>peg.PegProxy</code>
     */
    type PegProxy[A] = peg.PegProxy[A]

    /**
     * Alias of <code>peg.Rule</code>
     */
    val Rule = peg.Rule

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
     * Alias of <code>peg.RangeActions</code>
     */
    type RangeActions[A] = peg.RangeActions[A]

    /**
     * Alias of <code>peg.CapturingGroups</code>
     */
    type CapturingGroups[K, A] = peg.CapturingGroups[K, A]

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
 * The PEG parser combinator.
 * Note PEG operations are always possessive unlike regular expression default behavior.
 */
trait Peg[A] {
    import peg._

    /**
     * Parses specified region of input Vector.
     * This apparently legacy interface is designed so that heap-allocation is removal.
     *
     * @return next position if parsing succeeds, FAILURE otherwise
     */
    def parse(v: Vector[A], first: Int, last: Int): Int

    /**
     * Returns length when parsing is fixed-length and succeeds, undefined otherwise.
     *
     * @return next position if parsing succeeds, FAILURE otherwise
     */
    def length: Int = throw new UnsupportedOperationException("Peg.length")

    final def and(that: Peg[A]): Peg[A] = And(this, that)
    final def or(that: Peg[A]): Peg[A] = Or(this, that)
    final def minus(that: Peg[A]) = Minus(this, that)
    final def xor(that: Peg[A]): Peg[A] = Xor(this, that)
    final def not: Peg[A] = Not(this)

    final def seqAnd(that: Peg[A]): Peg[A] = SeqAnd(this, that)
    final def seqOr(that: Peg[A]): Peg[A] = SeqOr(this, that)

    final def star: Peg[A] = Star(this)
    final def starBefore(that: Peg[A]): Peg[A] = StarBefore(this, that)
    final def starUntil(that: Peg[A]): Peg[A] = StarUntil(this, that)

    final def plus: Peg[A] = Plus(this)
    final def plusBefore(that: Peg[A]): Peg[A] = PlusBefore(this, that)
    final def plusUntil(that: Peg[A]): Peg[A] = PlusUntil(this, that)

    final def opt: Peg[A] = Opt(this)
    final def optBefore(that: Peg[A]): Peg[A] = OptBefore(this, that)
    final def optUntil(that: Peg[A]): Peg[A] = OptUntil(this, that)

    final def lookAhead: Peg[A] = LookAhead(this)
    final def lookBehind: Peg[A] = LookBehind(this)
    final def lookBack: Peg[A] = LookBack(this)

    final def act(f: Vector.Func3[A, Any]): Peg[A] = Act(this, f)
    final def andIf(pred: Vector.Func3[A, Boolean]): Peg[A] = AndIf(this, pred)
    final def identity: Peg[A] = Identity(this)
    final def memoize: Peg[A] = Memoize(this)
    final def named(name: String) = Named(this, name)
    final def repeat(min: Int, max: Int): Peg[A] = Repeat(this, min, max)

    final def readMap[Z](f: Vector[Z] => Vector[A]): Peg[Z] = ReadMap(this, f)
    final def unmap[Z](f: Z => A): Peg[Z] = Unmap(this, f)

    final def find(v: Vector[A]): Option[(Int, Int)] = Find(this, v)
    final def lookingAt(v: Vector[A]): Option[Int] = LookingAt(this, v)
    final def matches(v: Vector[A]): Boolean = Matches(this, v)

    final def tokenize(v: Vector[A]): Iterator[Vector[A]] = Tokenize(this, v)
    final def tokenize3(v: Vector[A]): Iterator[Vector.Triple[A]] = Tokenize3(this, v)
    final def filterFrom(v: Vector[A]): Iterator[A] = FilterFrom(this, v)

    final def apply(f: Vector.Func3[A, Any]): Peg[A] = act(f)
    final def unary_~ : Peg[A] = lookAhead
    final def unary_! : Peg[A] = lookAhead.not
    final def unary_- : Peg[A] = not
    final def &(that: Peg[A]): Peg[A] = and(that)
    final def |(that: Peg[A]): Peg[A] = or(that)
    final def -(that: Peg[A]): Peg[A] = minus(that)
    final def ^(that: Peg[A]): Peg[A] = xor(that)
    final def >>(that: Peg[A]): Peg[A] = seqAnd(that)
    final def ||(that: Peg[A]): Peg[A] = seqOr(that)
    final def * : Peg[A] = star
    final def *?(that: Peg[A]): Peg[A] = starBefore(that)
    final def *>>(that: Peg[A]): Peg[A] = starUntil(that)
    final def + : Peg[A] = plus
    final def +?(that: Peg[A]): Peg[A] = plusBefore(that)
    final def +>>(that: Peg[A]): Peg[A] = plusUntil(that)
    final def ? : Peg[A] = opt
    final def ??(that: Peg[A]): Peg[A] = optBefore(that)
    final def ?>>(that: Peg[A]): Peg[A] = optUntil(that)

    final def >>?~(that: Peg[A]): Peg[A] = seqAnd(that.lookAhead)
    final def >>?!(that: Peg[A]): Peg[A] = seqAnd(that.lookAhead.not)
    final def >>?<~(that: Peg[A]): Peg[A] = seqAnd(that.lookBehind)
    final def >>?<!(that: Peg[A]): Peg[A] = seqAnd(that.lookBehind.not)
    final def >>?<<~(that: Peg[A]): Peg[A] = seqAnd(that.lookBack)
    final def >>?<<!(that: Peg[A]): Peg[A] = seqAnd(that.lookBack.not)

    final def inCase(e: A): (A, Peg[A]) = (e, this)
}
