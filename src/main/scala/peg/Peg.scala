

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


object Peg extends Common with Compatibles {

// methodization
    sealed class _OfFuncArg[A](_this: Peg[A]) {
        final def act(f: Action[A]): Peg[A] = Act(_this, f)
        final def act3(f: Action3[A]): Peg[A] = Act3(_this, f)
        final def apply(f: Action[A]): Peg[A] = act(f)
        final def andIf(pred: sequence.vector.Pred[A]): Peg[A] = AndIf(_this, pred)
        final def andIf3(pred: sequence.vector.Pred3[A]): Peg[A] = AndIf3(_this, pred)
    }
    implicit def _ofFuncArg[A](_this: Peg[A]): _OfFuncArg[A] = new _OfFuncArg(_this)

}


/**
 * The PEG parser combinator:
 * <ul>
 * <li/>Sequence: <code>e1 &gt;&gt; e2</code>
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
trait Peg[-A] {


// kernel interface

    /**
     * Parses specified region of input sequence.vector.
     * This apparently legacy interface is designed so that heap-allocation is removal.
     *
     * @return  next position if parsing succeeds, <code>peg.FAILURE</code> otherwise.
     */
    def parse(v: sequence.Vector[A], first: Int, last: Int): Int

    /**
     * Returns the matched width.
     *
     * @throws  NotConstantWidth if this peg doesn't have constant width.
     */
    def width: Int = throw new NotConstantWidth("peg.unknown")


// set

    /**
     * Matches <code>this</code> and <code>that</code>.
     * <code>that</code> parses sub-region which <code>this</code> matches.
     *
     * @see     &amp; as alias.
     */
    final def and[B <: A](that: Peg[B]): Peg[B] = And[B](this, that)

    /**
     * Ordered choice
     *
     * @see     | as alias.
     */
    final def or[B <: A](that: Peg[B]): Peg[B] = Or[B](this, that)

    /**
     * Matches <code>this</code>, but not <code>that</code>.
     *
     * @see     - as alias.
     */
    final def minus[B <: A](that: Peg[B]): Peg[B] = Minus[B](this, that)

    /**
     * Matches <code>this</code> or <code>that</code>, but not both.
     *
     * @see     ^ as alias.
     */
    final def xor[B <: A](that: Peg[B]): Peg[B] = Xor[B](this, that)

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
     * @see     &gt;&gt; as alias.
     */
    final def seqAnd[B <: A](that: Peg[B]): Peg[B] = SeqAnd[B](this, that)

    /**
     * @return  <code>(this &gt;&gt; that.?) | that</code>.
     * @see     &gt;|&gt; as alias.
     */
    final def seqOr[B <: A](that: Peg[B]): Peg[B] = SeqOr[B](this, that)

    /**
     * Equivalent to <code>!this | this &gt;&gt; that</code>, but parses <code>this</code> once.
     *
     * @see     &gt;-&gt; as alias.
     */
    final def seqImply[B <: A](that: Peg[B]): Peg[B] = SeqImply[B](this, that)

    /**
     * Goes sequence as long as possible.
     *
     * @return  <code>that &gt;&gt; this.?</code>.
     * @see     &gt;?&gt;: as alias.
     */
    final def seqOpt_:[B <: A](that: Peg[B]): Peg[B] = SeqOpt[B](this, that)


// quantifiers

    /**
     * Zero-or-more
     *
     * @see     * as alias.
     */
    @pre("`this` is not instance of `ZeroWidth`.")
    final def star: Quantified[A] = Star(this)

    /**
     * One-or-more
     *
     * @see     + as alias.
     */
    @pre("`this` is not instance of `ZeroWidth`.")
    final def plus: Quantified[A] = Plus(this)

    /**
     * Optional
     *
     * @see     ? as alias.
     */
    final def opt: Quantified[A] = Opt(this)

    /**
     * Repeats exactly <code>n</code> times.
     */
    final def repeat(n: Int): Quantified[A] = Repeat(this, n, n)

    /**
     * Repeats at least <code>n</code> times.
     */
    final def repeat(n: Int, u: Unit): Quantified[A] = Repeat(this, n, java.lang.Integer.MAX_VALUE)

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
     * @see     &gt;=~ as alias.
     */
    final def lookbehind: Peg[A] = Lookbehind(this)

    /**
     * Lookback zero-width assertion; looking over input as reversed.
     *
     * @see     &gt;&gt;~ as alias.
     */
    final def lookback: Peg[A] = Lookback(this)


// action

    /**
     * Associates semantic action.
     *
     * @see     apply as alias.
     */
//    final def act(f: peg.Action[A]): Peg[A] = Act(this, f)

    /**
     * Associates semantic action. (no heap allocations)
     */
//    final def act3(f: peg.Action3[A]): Peg[A] = Act3(this, f)


// utilities

    /**
     * Matches if matched region meets condition <code>pred</code>.
     */
//    final def andIf(pred: sequence.vector.Pred[A]): Peg[A] = AndIf(this, pred)

    /**
     * Matches if matched region meets condition <code>pred</code>. (no heap allocations)
     */
//    final def andIf3(pred: sequence.vector.Pred3[A]): Peg[A] = AndIf3(this, pred)

    /**
     * Returns an alias of this peg.
     */
    final def identity: Peg[A] = Identity(this)

    /**
     * Overrides <code>toString</code> to return <code>name</code>.
     */
    final def named(name: String): Peg[A] = Named(this, name)

    /**
     * Temporarily converts input using one-to-one projection <code>f</code>.
     */
    @pre("`v.size == f(v).size` for any `v`.")
    final def readMap[Z](f: sequence.Vector[Z] => sequence.Vector[A]): Peg[Z] = ReadMap(this, f)

    @equivalentTo("readMap{ v => v.map(f) }")
    final def unmap[Z](f: Z => A): Peg[Z] = Unmap(this, f)

    /**
     * Reads input as lower cases, then tries to match.
     */
    final def lowerCaseRead(implicit pre: Peg[A] <:< Peg[Char]): Peg[Char] = LowerCaseRead(pre(this))

    /**
     * Returns synchronized one.
     */
    final def synchronize: Peg[A] = Synchronize(this)


// algorithms

    /**
     * Finds <code>sequence.vector.Region</code> which this peg matches.
     */
    final def find[B <: A](v: sequence.Vector[B]): Option[sequence.Vector[B]] = {
        val (i, j) = findRange(v, v.start, v.end)
        if (j == FAILURE) {
            None
        } else {
            Some(v(i, j))
        }
    }

    /**
     * Finds a range of sequence.vector.
     */
    final def findRange(v: sequence.Vector[A], _start: Int, end: Int): (Int, Int) = {
        var start = _start
        while (start != end) {
            val cur = parse(v, start, end)
            if (cur != FAILURE) {
                return (start, cur)
            }
            start += 1
        }
        (start, FAILURE)
    }

    /**
     * Returns position where parsing succeeds.
     */
    final def lookingAt(v: sequence.Vector[A]): Option[Int] = {
        val cur = parse(v, v.start, v.end)
        if (cur == FAILURE) {
            None
        } else {
            Some(cur)
        }
    }

    /**
     * Returns true iif input is fully matched.
     */
    final def matches(v: sequence.Vector[A]): Boolean = {
        val e = v.end
        e == parse(v, v.start, e)
    }

    /**
     * Splits input using this peg.
     */
    final def split[B <: A](v: sequence.Vector[B]): sequence.Iterative[sequence.Vector[B]] = Split(this, v)

    /**
     * Tokenizes input using this peg.
     */
    final def tokenize[B <: A](v: sequence.Vector[B]): sequence.Iterative[sequence.Vector[B]] = Tokenize(this, v)

    /**
     * Filters input using this peg.
     */
    final def filterFrom[B <: A](v: sequence.Vector[B]): sequence.Iterative[B] = FilterFrom(this, v)


// aliases

//    @aliasOf("act")
//    final def apply(f: peg.Action[A]): Peg[A] = act(f)

    /**
     * And-predicate
     */
    @aliasOf("lookahead")
    final def unary_~ : Peg[A] = lookahead

    /**
     * Not-predicate
     */
    @aliasOf("lookahead.negate")
    final def unary_! : Peg[A] = lookahead.negate

    @aliasOf("negate")
    final def unary_- : Peg[A] = negate

    @aliasOf("and")
    final def &[B <: A](that: Peg[B]): Peg[B] = and(that)

    /**
     * Ordered choice
     */
    @aliasOf("or")
    final def |[B <: A](that: Peg[B]): Peg[B] = or(that)

    @aliasOf("minus")
    final def -[B <: A](that: Peg[B]): Peg[B] = minus(that)

    @aliasOf("xor")
    final def ^[B <: A](that: Peg[B]): Peg[B] = xor(that)

    /**
     * Sequence
     */
    @aliasOf("seqAnd")
    final def >>[B <: A](that: Peg[B]): Peg[B] = seqAnd(that)

    @aliasOf("seqOr")
    final def >|>[B <: A](that: Peg[B]): Peg[B] = seqOr(that)

    @aliasOf("seqImply")
    final def >->[B <: A](that: Peg[B]): Peg[B] = seqImply(that)

    @aliasOf("seqOpt_:")
    final def >?>:[B <: A](that: Peg[B]): Peg[B] = seqOpt_:(that)

    /**
     * Zero-or-more
     */
    @aliasOf("star")
    final def * : Quantified[A] = star

    /**
     * One-or-more
     */
    @aliasOf("plus")
    final def + : Quantified[A] = plus

    /**
     * Optional
     */
    @aliasOf("opt")
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
    final def of[B <: A]: Peg[B] = this

    @returnThis
    final def asPeg: Peg[A] = this

    @equivalentTo("(e, this)")
    final def inCase[B <: A](e: B): (B, Peg[B]) = (e, this)

}
