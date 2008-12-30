

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Parser {
    val FAILED: Long = -1

    def any[A]: Parser[A] = parser.Any_.apply[A]
    def error[A]: Parser[A] = parser.Error.apply[A]
    def fail[A]: Parser[A] = parser.Fail.apply[A]
    def fromIterator[A](it: Iterator[A]): Parser[A] = parser.FromIterator(it)
    def fromString(str: String): Parser[Char] = parser.FromString(str)
    def single[A](e: A): Parser[A] = parser.Single(e)
}


trait Parser[A] {
    import parser._

    final val FAILED = Parser.FAILED
    def parse(s: Scanner[A], begin: Long, end: Long): Long

    def after(n: Long): Parser[A] = After(this, n)
    def append(that: Parser[A]): Parser[A] = Append(this, that)
    def before: Parser[A] = Before(this)
    def fail: Parser[A] = Parser.fail[A]
    def not: Parser[A] = Not(this)
    def opt: Parser[A] = Opt(this)
    def or(that: Parser[A]): Parser[A] = Or(this, that)
    def repeat(min: Long, max: Long): Parser[A] = Repeat(this, min, max)
    def star: Parser[A] = Star(this)
    def starBefore(that: Parser[A]): Parser[A] = StarBefore(this, that)
    def starUntil(that: Parser[A]): Parser[A] = StarUntil(this, that)
    def unmap[Z](f: Z => A): Parser[Z] = Unmap(this, f)
}
