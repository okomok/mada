

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object And {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = p.before then q
}


object AndIf {
    def apply[A](p: Parser[A], pred: Vector[A] => Boolean): Parser[A] = apply(p, ToScannerFunction(pred))
    def apply[A](p: Parser[A], pred: (Scanner[A], Long, Long) => Boolean): Parser[A] = new AndIfParser(p, pred)
}

class AndIfParser[A](override val self: Parser[A], pred: (Scanner[A], Long, Long) => Boolean) extends ParserProxy[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        val cur = self.parse(s, first, last)
        if (cur == FAILED || !pred(s, first, cur)) {
            FAILED
        } else {
            cur
        }
    }
}
