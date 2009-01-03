

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Parser {
    val FAILED: Long = -1

    def any[A]: Parser[A] = parser.Any_[A]
    def begin[A]: Parser[A] = parser.Begin[A]
    def end[A]: Parser[A] = parser.End[A]
    def eps[A]: Parser[A] = parser.Eps[A]
    def error[A]: Parser[A] = parser.Error[A]
    def fail[A]: Parser[A] = parser.Fail[A]
    def fromString(str: String): Parser[Char] = parser.FromString(str)
    def fromVector[A](v: Vector[A]): Parser[A] = parser.FromVector(v)
    def icase(str: String): Parser[Char] = parser.Icase(str)
    def lowerCaseScan(p: Parser[Char]): Parser[Char] = parser.LowerCaseScan(p)
    def range[A](i: A, j: A)(implicit c: A => Ordered[A]): Parser[A] = parser.Range(i, j)(c)
    def set[A](es: A*): Parser[A] = parser.Set(es: _*)
    def single[A](e: A): Parser[A] = parser.Single(e)

    def __*[A]: Parser[A] = any[A].star
    def __*?[A](p: Parser[A]): Parser[A] = any[A].starBefore(p)
    def __*~[A](p: Parser[A]): Parser[A] = any[A].starUntil(p)
    def ?=[A](p: Parser[A]): Parser[A] = p.lookAhead
    def ?![A](p: Parser[A]): Parser[A] = p.lookAhead.not
    def ?<=[A](p: Parser[A]): Parser[A] = p.lookBehind
    def ?<![A](p: Parser[A]): Parser[A] = p.lookBehind.not
    def ?<<=[A](p: Parser[A]): Parser[A] = p.lookBack
    def ?<<![A](p: Parser[A]): Parser[A] = p.lookBack.not

    type ParserProxy[A] = parser.ParserProxy[A]
}


trait Parser[A] {
    import parser._

    def parse(v: Vector[A], first: Long, last: Long): Long
    def length: Long = throw new UnsupportedOperationException("Parser.length")
    protected final val FAILED = Parser.FAILED

    final def action(f: Vector[A] => Any): Parser[A] = Action(this, f)
    final def and(that: Parser[A]): Parser[A] = And(this, that)
    final def andIf(pred: Vector[A] => Boolean): Parser[A] = AndIf(this, pred)
    final def lookAhead: Parser[A] = LookAhead(this)
    final def lookBack: Parser[A] = LookBack(this)
    final def lookBehind: Parser[A] = LookBehind(this)
    final def minus(that: Parser[A]) = Minus(this, that)
    final def not: Parser[A] = Not(this)
    final def plus: Parser[A] = Plus(this)
    final def plusBefore(that: Parser[A]): Parser[A] = PlusBefore(this, that)
    final def plusUntil(that: Parser[A]): Parser[A] = PlusUntil(this, that)
    final def opt: Parser[A] = Opt(this)
    final def optBefore(that: Parser[A]): Parser[A] = OptBefore(this, that)
    final def optUntil(that: Parser[A]): Parser[A] = OptUntil(this, that)
    final def or(that: Parser[A]): Parser[A] = Or(this, that)
    final def prescan[Z](f: Vector[Z] => Vector[A]): Parser[Z] = Prescan(this, f)
    final def repeat(min: Long, max: Long): Parser[A] = Repeat(this, min, max)
    final def seqAnd(that: Parser[A]): Parser[A] = SeqAnd(this, that)
    final def seqOr(that: Parser[A]): Parser[A] = SeqOr(this, that)
    final def star: Parser[A] = Star(this)
    final def starBefore(that: Parser[A]): Parser[A] = StarBefore(this, that)
    final def starUntil(that: Parser[A]): Parser[A] = StarUntil(this, that)
    final def unmap[Z](f: Z => A): Parser[Z] = Unmap(this, f)
    final def xor(that: Parser[A]): Parser[A] = Xor(this, that)

    final def unary_! : Parser[A] = not
    final def &(that: Parser[A]): Parser[A] = and(that)
    final def |(that: Parser[A]): Parser[A] = or(that)
    final def -(that: Parser[A]): Parser[A] = minus(that)
    final def ^(that: Parser[A]): Parser[A] = xor(that)
    final def &&(that: Parser[A]): Parser[A] = seqAnd(that)
    final def ||(that: Parser[A]): Parser[A] = seqOr(that)
    final def ~(that: Parser[A]): Parser[A] = seqAnd(that)
    final def * : Parser[A] = star
    final def *?(that: Parser[A]): Parser[A] = starBefore(that)
    final def *~(that: Parser[A]): Parser[A] = starUntil(that)
    final def + : Parser[A] = plus
    final def +?(that: Parser[A]): Parser[A] = plusBefore(that)
    final def +~(that: Parser[A]): Parser[A] = plusUntil(that)
    final def ? : Parser[A] = opt
    final def ??(that: Parser[A]): Parser[A] = optBefore(that)
    final def ?~(that: Parser[A]): Parser[A] = optUntil(that)
    final def ^^(f: Vector[A] => Any): Parser[A] = action(f)

    final def parse(v: Vector[A]): Long = Parse(this, v)
    final def matches(v: Vector[A]): Boolean = Matches(this, v)
}
