

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains implicit conversions around <code>Peg</code>.
 */
object Peg {
    import Pegs._

    /**
     * @return  <code>Pegs.single(from)</code>.
     */
    implicit def char2madaPeg(from: Char): Peg[Char] = single(from)

    /**
     * @return  <code>Pegs.fromRegexPattern(from)</code>.
     */
    implicit def regexPattern2madaPeg(from: java.util.regex.Pattern): Peg[Char] = fromRegexPattern(from)

    /**
     * @return  <code>Pegs.fromString(from)</code>.
     */
    implicit def string2madaPeg(from: String): Peg[Char] = fromString(from)

    /**
     * @return  <code>Pegs.fromVector(from)</code>.
     */
    implicit def madaVector2madaPeg[A](from: Vector[A]): Peg[A] = fromVector(from)
}


/**
 * The PEG parser combinator.
 * Note PEG operations are always possessive unlike regular expression default behavior.
 */
trait Peg[A] {
    import peg._

    /**
     * Parses specified region of input Vectors.
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
    def length: Int = throw new UnsupportedOperationException("Pegs.length")

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

    final def act(f: Vectors.Func[A, Any]): Peg[A] = Act(this, f)
    final def act3(f: Vectors.Func3[A, Any]): Peg[A] = Act3(this, f)
    final def andIf(pred: Vectors.Func[A, Boolean]): Peg[A] = AndIf(this, pred)
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
    final def filterFrom(v: Vector[A]): Iterator[A] = FilterFrom(this, v)

    final def apply(f: Vectors.Func[A, Any]): Peg[A] = act(f)
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
