

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import peg._


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
trait Peg[A] {


// kernel interface

    /**
     * Parses specified region of input vector.
     * This apparently legacy interface is designed so that heap-allocation is removal.
     *
     * @return  next position if parsing succeeds, <code>peg.FAILURE</code> otherwise.
     */
    def parse(v: Vector[A], first: Int, last: Int): Int

    /**
     * Returns the matched width.
     *
     * @throws  NotConstantWidth if this peg doesn't have constant width.
     */
    def width: Int = throw new peg.NotConstantWidth("peg.unknown")


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
    final def act(f: peg.Action[A]): Peg[A] = Act3(this, vector.triplify(f))

    /**
     * Associates semantic action. (no heap allocations)
     */
    final def act3(f: peg.Action3[A]): Peg[A] = Act3(this, f)


// utilities

    /**
     * Matches if matched region meets condition <code>pred</code>.
     */
    final def andIf(pred: vector.Pred[A]): Peg[A] = AndIf3(this, vector.triplify(pred))

    /**
     * Matches if matched region meets condition <code>pred</code>. (no heap allocations)
     */
    final def andIf3(pred: vector.Pred3[A]): Peg[A] = AndIf3(this, pred)

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
     * Finds <code>vector.Region</code> which this peg matches.
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

    @aliasOf("act")
    final def apply(f: peg.Action[A]): Peg[A] = act(f)

    /**
     * And-predicate; alias of <code>lookahead</code>
     */
    final def unary_~ : Peg[A] = lookahead

    /**
     * Not-predicate; alias of <code>lookahead.negate</code>
     */
    final def unary_! : Peg[A] = lookahead.negate

    @aliasOf("negate")
    final def unary_- : Peg[A] = negate

    @aliasOf("and")
    final def &(that: Peg[A]): Peg[A] = and(that)

    /**
     * Ordered choice; alias of <code>or</code>
     */
    final def |(that: Peg[A]): Peg[A] = or(that)

    @aliasOf("minus")
    final def -(that: Peg[A]): Peg[A] = minus(that)

    @aliasOf("xor")
    final def ^(that: Peg[A]): Peg[A] = xor(that)

    /**
     * Sequence; alias of <code>seqAnd</code>
     */
    final def >>(that: Peg[A]): Peg[A] = seqAnd(that)

    @aliasOf("seqOr")
    final def >|>(that: Peg[A]): Peg[A] = seqOr(that)

    @aliasOf("seqImply")
    final def >->(that: Peg[A]): Peg[A] = seqImply(that)

    @aliasOf("seqOpt_:")
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

    @aliasOf("lookbehind")
    final def <=~ : Peg[A] = lookbehind

    @aliasOf("lookbehind.negate")
    final def <=! : Peg[A] = lookbehind.negate

    @aliasOf("lookback")
    final def <<~ : Peg[A] = lookback

    @aliasOf("lookback.negate")
    final def <<! : Peg[A] = lookback.negate


// misc

    @returnThis
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


object Peg extends Compatibles {

// operators

    sealed class MadaPegChar(_1: Peg[Char]) {
        def lowerCaseRead = peg.lowerCaseRead(_1)
    }
    implicit def madaPegChar(_1: Peg[Char]): MadaPegChar = new MadaPegChar(_1)

    sealed class MadaPegByName[A](_1: => Peg[A]) {
        def `lazy` = peg.`lazy`(_1)
    }
    implicit def madaPegByName[A](_1: => Peg[A]): MadaPegByName[A] = new MadaPegByName(_1)

    sealed class MadaPegIterablePeg[A](_1: Iterable[Peg[A]]) {
        def longest = peg.longest(_1)
        def shortest = peg.shortest(_1)
    }
    implicit def madaPegIterablePeg[A](_1: Iterable[Peg[A]]): MadaPegIterablePeg[A] = new MadaPegIterablePeg(_1)

}
