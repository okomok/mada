

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
    def range[A](i: A, j: A)(implicit c: A => Ordered[A]): Parser[A] = parser.Range(i, j)(c)
    def set[A](es: A*): Parser[A] = parser.Set(es: _*)
    def single[A](e: A): Parser[A] = parser.Single(e)

    def __*[A]: Parser[A] = any[A].star
    def __*?[A](p: Parser[A]): Parser[A] = any[A].starBefore(p)
    def __*~[A](p: Parser[A]): Parser[A] = any[A].starUntil(p)
    def ?=[A](p: Parser[A]): Parser[A] = p.before
    def ?![A](p: Parser[A]): Parser[A] = p.before.not
    def ?<=[A](p: Parser[A]): Parser[A] = p.after
    def ?<![A](p: Parser[A]): Parser[A] = p.after.not
    def ?<<=[A](p: Parser[A]): Parser[A] = p.behind
    def ?<<![A](p: Parser[A]): Parser[A] = p.behind.not

    type ParserProxy[A] = parser.ParserProxy[A]
}


trait Parser[A] {
    import parser._

    def parse(s: Scanner[A], first: Long, last: Long): Long
    def length: Long = throw new UnsupportedOperationException("Parser.length")
    protected final val FAILED = Parser.FAILED

    final def action(f: Vector[A] => Unit): Parser[A] = Action(this, f)
    final def after: Parser[A] = After(this)
    final def and(that: Parser[A]): Parser[A] = And(this, that)
    final def andIf(pred: Vector[A] => Boolean): Parser[A] = AndIf(this, pred)
    final def before: Parser[A] = Before(this)
    final def behind: Parser[A] = Behind(this)
    final def lazyActions: Parser[A] = LazyActions(this)
    final def noActions: Parser[A] = NoActions(this)
    final def not: Parser[A] = Not(this)
    final def plus: Parser[A] = Plus(this)
    final def opt: Parser[A] = Opt(this)
    final def or(that: Parser[A]): Parser[A] = Or(this, that)
    final def repeat(min: Long, max: Long): Parser[A] = Repeat(this, min, max)
    final def star: Parser[A] = Star(this)
    final def starBefore(that: Parser[A]): Parser[A] = StarBefore(this, that)
    final def starUntil(that: Parser[A]): Parser[A] = StarUntil(this, that)
    final def then(that: Parser[A]): Parser[A] = Then(this, that)
    final def unmap[Z](f: Z => A): Parser[Z] = Unmap(this, f)

    final def apply(f: Vector[A] => Unit): Parser[A] = action(f)
    final def unary_! : Parser[A] = not
    final def ~(that: Parser[A]): Parser[A] = then(that)
    final def |(that: Parser[A]): Parser[A] = or(that)
    final def * : Parser[A] = star
    final def *?(that: Parser[A]): Parser[A] = starBefore(that)
    final def *~(that: Parser[A]): Parser[A] = starUntil(that)
    final def + : Parser[A] = plus
    final def ? : Parser[A] = opt

    final def parse(v: Vector[A]): Long = Parse(this, v)
    final def matches(v: Vector[A]): Boolean = Matches(this, v)
}
