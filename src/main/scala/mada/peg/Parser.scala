

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Parser {
    val FAILED: Long = -1

    def any[A]: Parser[A] = parser.Any_.apply[A]
    def fromIterator[A](it: Iterator[A]): Parser[A] = parser.FromIterator(it)
    def single[A](e: A): Parser[A] = parser.Single(e)
}


trait Parser[A] {
    import parser._

    final val FAILED = Parser.FAILED
    def parse(s: Scanner[A], begin: Long, end: Long): Long

    def before: Parser[A] = Before(this)
    def repeat(min: Long, max: Long): Parser[A] = Repeat(this, min, max)
    def starBefore(q: Parser[A]): Parser[A] = StarBefore(this, q)
    def starUntil(q: Parser[A]): Parser[A] = StarUntil(this, q)
    def unmap[Z](f: Z => A): Parser[Z] = Unmap(this, f)
}
