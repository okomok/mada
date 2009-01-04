

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


class Actions(private var enabled: Boolean) {
    def this() = this(true)

    def isEnabled: Boolean = enabled
    def setEnabled(b: Boolean): Unit = enabled = b

    def apply[A](f: A => Any)(a: A): Unit = if (isEnabled) f(a)

    def disabled[A](p: Parser[A]): Parser[A] = new DisabledActionsParser(p)
    def buffered[A](p: Parser[A]): Parser[A] = new BufferedActionsParser(p)

    class DisabledActionsParser[A](override val self: Parser[A]) extends Parser.ParserProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            val old = isEnabled
            setEnabled(false)
            try {
                return self.parse(v, first, last)
            } finally {
                setEnabled(old)
            }
        }
    }

    class BufferedActionsParser[A](override val self: Parser[A]) extends Parser.ParserProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            val old = isEnabled
            setEnabled(false)
            try {
                if (self.parse(v, first, last) != FAILED) {
                    setEnabled(old)
                    return self.parse(v, first, last)
                } else {
                    FAILED
                }
            } finally {
                setEnabled(old)
            }
        }
    }
}