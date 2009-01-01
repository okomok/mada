

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


// Action

object Action {
    def apply[A](p: Parser[A], f: Vector[A] => Unit): Parser[A] = apply(p, { (s: Scanner[A], i: Long, j: Long) => f(s.window(i, j)) })
    def apply[A](p: Parser[A], f: (Scanner[A], Long, Long) => Unit): Parser[A] = new ActionParser(p, f)
}

class ActionParser[A](override val self: Parser[A], f: (Scanner[A], Long, Long) => Unit) extends ParserProxy[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        val cur = self.parse(s, first, last)
        if (cur != FAILED && s.isActionable) {
            f(s, first, last)
        }
        cur
    }
}


// NoActions

object NoActions {
    def apply[A](p: Parser[A]): Parser[A] = new SetActionsParser(p, false)
}

class SetActionsParser[A](override val self: Parser[A], b: Boolean) extends ParserProxy[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        var cur = 0L

        val old = s.isActionable
        try {
            s.setActionable(b)
            cur = self.parse(s, first, last)
        } finally {
            s.setActionable(old)
        }

        cur
    }
}


// LazyActions

object LazyActions {
    def apply[A](p: Parser[A]): Parser[A] = new LazyActionsParser(p)
}

class LazyActionsParser[A](override val self: Parser[A]) extends ParserProxy[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        var cur = 0L

        val old = s.isActionable
        try {
            s.setActionable(false)
            cur = self.parse(s, first, last)
            if (cur != FAILED) {
                s.setActionable(old)
                cur = self.parse(s, first, last)
            }
        } finally {
            s.setActionable(old)
        }

        cur
    }
}
