

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import peg._


/**
 * Contains utility methods operating on <code>Peg</code>.
 */
object Peg extends Aliases with Conversions with Compatibles with Operators {


// constants

    /**
     * Alias of <code>Vector.SINGULAR</code> specifying the parsing failure.
     */
    final val FAILURE = 0x80000000


// exceptions

    /**
     * Thrown if <code>width</code> is required but not constant.
     */
    class NotConstantWidth(msg: String) extends UnsupportedOperationException(msg)


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
    def error[A]: Peg[A] = Error[A]

    /**
     * Doesn't match any input.
     */
    def fail[A]: Peg[A] = failImpl.asInstanceOf[Peg[A]]
    private val failImpl: Peg[Any] = lookaround3 { (_, _, _) => false }

    /**
     * Mathches case-insensitively.
     */
    def icase(v: Vector[Char]): Peg[Char] = lowerCaseRead(fromVector(Vector.lowerCase(v)))

    /**
     * Zero-width assertion if region meets condition <code>pred</code>
     */
    def lookaround[A](pred: Vector.Pred[A]): Peg[A] = Lookaround3(Vector.triplify(pred))

    /**
     * Zero-width assertion if region meets condition <code>pred</code> (no heap allocations)
     */
    def lookaround3[A](pred: Vector.Pred3[A]): Peg[A] = Lookaround3(pred)

    /**
     * Reads input as lower cases, then tries to match.
     */
    def lowerCaseRead(p: Peg[Char]): Peg[Char] = p readMap { v => Vector.lowerCase(v) }

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
    def `lazy`[A](p: => Peg[A]): Peg[A] = Lazy(p)

    /**
     * Constructs a pseudo try-catch expression in Peg.
     */
    def `try`[A](p: Peg[A]): Try[A] = Try(p)


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
     * Chooses the shortest match.
     */
    def shortest[A](ps: Iterable[Peg[A]]): Peg[A] = Shortest(ps)


// set

    /**
     * Matches any element of set.
     */
    def multiple[A](es: A*): Peg[A] = Multiple(Iterables.toHashSet(es))

    /**
     * Matches any element of set.
     */
    def multiple[A](es: scala.collection.Set[A]): Peg[A] = Multiple(es)

    /**
     * Matches a key, then tries to match its value.
     */
    def switch[A](es: (A, Peg[A])*): Peg[A] = Switch(Iterables.toHashMap(es))

    /**
     * Matches a key, then tries to match its value.
     */
    def switch[A](es: scala.collection.Map[A, Peg[A]]): Peg[A] = Switch(es)


// verify

    /**
     * Throws VerificationException if p doesn't match.
     */
    def verify[A](p: Peg[A]): Peg[A] = Verify(p)

    @alias val VerificationException = peg.VerificationException
    @alias type VerificationException[A] = peg.VerificationException[A]

}


/**
 * The PEG parser combinator:
 * <ul>
 * <li/>Sequence: <code>e1 >> e2</code>
 * <li/>Ordered choice: <code>e1 | e2</code>
 * <li/>Zero-or-more: <code>e.*</code>
 * <li/>One-or-more: <code>e.+</code>
 * <li/>Optional: <code>e.?</code>
 * <li/>And-predicate: <code>~e</code> (positive lookahead)
 * <li/>Not-predicate: <code>!e</code> (negative lookahead)
 * </ul><p/>
 *
 * <ul>
 * <li/>PEG parsing is "possessive". You may need <code>*.before</code> etc.
 * <li/><code>java.util.regex</code> is trivially compatible to <code>mada.Peg</code>.
 * </ul>
 */
trait Peg[A] extends Companion[Peg.type] {

    override def companion = Peg


// kernel interface

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
     * @throws  NotConstantWidth if this peg doesn't have constant width.
     */
    def width: Int = throw new Peg.NotConstantWidth("Peg.unknown")


// set

    /**
     * Matches <code>this</code> and <code>that</code>.
     * <code>that</code> parses sub-region which <code>this</code> matches.
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
     * Matches <code>this</code>, but not <code>that</code>.
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
     * Doesn't work unless this peg has constant width.
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
     * @see     >|> as alias.
     */
    final def seqOr(that: Peg[A]): Peg[A] = (this >> that.?) | that

    /**
     * Equivalent to <code>!this | this >> that</code>, but parses <code>this</code> once.
     *
     * @see     >-> as alias.
     */
    final def seqImply(that: Peg[A]): Peg[A] = SeqImply(this, that)

    /**
     * Goes sequence as long as possible.
     *
     * @return  <code>that >> this.?</code>.
     * @see     >?>: as alias.
     */
    final def seqOpt_:(that: Peg[A]): Peg[A] = that >> this.?


// quantifiers

    /**
     * Zero-or-more
     *
     * @pre     <code>this</code> is not instance of <code>ZeroWidth</code>.
     * @see     * as alias.
     */
    final def star: Quantified[A] = { throwIfZeroWidth("star"); repeat(0, ()) }

    /**
     * One-or-more
     *
     * @pre     <code>this</code> is not instance of <code>ZeroWidth</code>.
     * @see     + as alias.
     */
    final def plus: Quantified[A] = { throwIfZeroWidth("plus"); repeat(1, ()) }

    /**
     * Optional
     *
     * @see     ? as alias.
     */
    final def opt: Quantified[A] = repeat(0, 1)

    /**
     * Repeats exactly <code>n</code> times.
     */
    final def repeat(n: Int): Quantified[A] = Repeat(this, n, n)

    /**
     * Repeats at least <code>n</code> times.
     */
    final def repeat(n: Int, u: Unit): Quantified[A] = Repeat(this, n, Math.MAX_INT)

    /**
     * Repeats at least <code>n</code> but not more than <code>m</code> times.
     */
    final def repeat(n: Int, m: Int): Quantified[A] = Repeat(this, n, m)


// assertions

    /**
     * And-predicate; lookahead zero-width assertion
     *
     * @see     unary_~ as alias.
     */
    final def lookahead: Peg[A] = Lookahead(this)

    /**
     * Lookbehind zero-width assertion
     *
     * @see     <=~ as alias.
     */
    final def lookbehind: Peg[A] = Lookbehind(this)

    /**
     * Lookback zero-width assertion; looking over input as reversed.
     *
     * @see     <<~ as alias.
     */
    final def lookback: Peg[A] = Lookback(this)


// action

    /**
     * Associates semantic action.
     *
     * @see     apply as alias.
     */
    final def act(f: Peg.Action[A]): Peg[A] = Act3(this, Vector.triplify(f))

    /**
     * Associates semantic action. (no heap allocations)
     */
    final def act3(f: Peg.Action3[A]): Peg[A] = Act3(this, f)


// utilities

    /**
     * Matches if matched region meets condition <code>pred</code>.
     */
    final def andIf(pred: Vector.Pred[A]): Peg[A] = AndIf3(this, Vector.triplify(pred))

    /**
     * Matches if matched region meets condition <code>pred</code>. (no heap allocations)
     */
    final def andIf3(pred: Vector.Pred3[A]): Peg[A] = AndIf3(this, pred)

    /**
     * Returns an alias of this peg.
     */
    final def identity: Peg[A] = Identity(this)

    /**
     * Overrides <code>toString</code> to return <code>name</code>.
     */
    final def named(name: String) = Named(this, name)

    /**
     * Temporarily converts input using one-to-one projection <code>f</code>.
     *
     * @pre     <code>v.size == f(v).size</code> for any <code>v</code>.
     */
    final def readMap[Z](f: Vector[Z] => Vector[A]): Peg[Z] = ReadMap(this, f)

    /**
     * @return  <code>readMap{ v => v.map(f) }</code>.
     */
    final def unmap[Z](f: Z => A): Peg[Z] = readMap{ v => v.map(f) }

    /**
     * Returns synchronized one.
     */
    final def synchronize: Peg[A] = Synchronize(this)


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
     * Splits input using this peg.
     */
    final def split(v: Vector[A]): Iterable[Vector[A]] = { throwIfZeroWidth("split"); Split(this, v) }

    /**
     * Tokenizes input using this peg.
     */
    final def tokenize(v: Vector[A]): Iterable[Vector[A]] = Tokenize(this, v)

    /**
     * Filters input using this peg.
     */
    final def filterFrom(v: Vector[A]): Iterable[A] = FilterFrom(this, v)


// aliases

    /**
     * Alias of <code>act</code>
     */
    final def apply(f: Peg.Action[A]): Peg[A] = act(f)

    /**
     * And-predicate; alias of <code>lookahead</code>
     */
    final def unary_~ : Peg[A] = lookahead

    /**
     * Not-predicate; alias of <code>lookahead.negate</code>
     */
    final def unary_! : Peg[A] = lookahead.negate

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
    final def >|>(that: Peg[A]): Peg[A] = seqOr(that)

    /**
     * Alias of <code>seqImply</code>
     */
    final def >->(that: Peg[A]): Peg[A] = seqImply(that)

    /**
     * Alias of <code>seqOpt_:</code>
     */
    final def >?>:(that: Peg[A]): Peg[A] = seqOpt_:(that)

    /**
     * Zero-or-more; alias of <code>star</code>
     */
    final def * : Quantified[A] = star

    /**
     * One-or-more; alias of <code>plus</code>
     */
    final def + : Quantified[A] = plus

    /**
     * Optional; alias of <code>opt</code>
     */
    final def ? : Quantified[A] = opt

    /**
     * Alias of <code>lookbehind</code>
     */
    final def <=~ : Peg[A] = lookbehind

    /**
     * Alias of <code>lookbehind.negate</code>
     */
    final def <=! : Peg[A] = lookbehind.negate

    /**
     * Alias of <code>lookback</code>
     */
    final def <<~ : Peg[A] = lookback

    /**
     * Alias of <code>lookback.negate</code>
     */
    final def <<! : Peg[A] = lookback.negate


// misc

    /**
     * @return  <code>this</code>.
     */
    final def asPeg: Peg[A] = this

    /**
     * @return  <code>(e, this)</code>.
     */
    final def inCase(e: A): (A, Peg[A]) = (e, this)


// implementation helpers

    private def throwIfZeroWidth(method: String): Unit = {
        if (IsZeroWidth(this)) {
            throw new IllegalArgumentException(method + " doesn't allow zero-width")
        }
    }
}
