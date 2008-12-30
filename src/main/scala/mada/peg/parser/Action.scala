

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Action {
    def apply[A](p: Parser[A], f: Vector[A] => Unit): Parser[A] = apply(p, { (s: Scanner[A], i: Long, j: Long) => f(s.window(i, j)) })
    def apply[A](p: Parser[A], f: (Scanner[A], Long, Long) => Unit): Parser[A] = new ActionParser(p, f)
}

class ActionParser[A](p: Parser[A], f: (Scanner[A], Long, Long) => Unit) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        val cur = p.parse(s, first, last)
        if (cur != FAILED && s.isActionable) {
            f(s, first, last)
        }
        cur
    }
}


object NoActions {
    def apply[A](p: Parser[A]): Parser[A] = new SetActionsParser(p, false)
}

object WithActions {
    def apply[A](p: Parser[A]): Parser[A] = new SetActionsParser(p, true)
}

class SetActionsParser[A](p: Parser[A], b: Boolean) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        var cur = 0L

        val old = s.isActionable
        s.setActionable(b)
        try {
            cur = p.parse(s, first, last)
        } finally {
            s.setActionable(old)
        }

        cur
    }
}
