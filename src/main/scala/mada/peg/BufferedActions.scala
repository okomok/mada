

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// apply( ("abc" ^^ put(f)) >> "def" )

class BufferedActions[A] {
    private val queue = new java.util.ArrayDeque[(Vector[A] => Any, Vector[A])]

    def put(f: Vector[A] => Any)(v: Vector[A]): Unit = queue.add((f, v))
    def apply(p: Peg[A]) = new ApplyPeg(p)
    def fire: Peg[A] = new FirePeg
    def clear: Peg[A] = new ClearPeg

    class ApplyPeg(override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            val cur = self.parse(v, first, last)
            if (cur != FAILURE) {
                fireActions
            }
            clear
            cur
        }
    }

    class FirePeg extends Peg[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            fireActions
            first
        }
        override def length = 0
    }

    class ClearPeg extends Peg[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            queue.clear
            first
        }
        override def length = 0
    }

    private def fireActions: Unit = {
        val it = queue.iterator
        while (it.hasNext) {
            val (f, v) = it.next
            f(v)
        }
    }
}


/*
class Actions(private var enabled: Boolean) {
    def this() = this(true)

    def isEnabled: Boolean = enabled
    def setEnabled(b: Boolean): Unit = enabled = b

    def apply[A](f: A => Any)(a: A): Unit = if (isEnabled) f(a)

    def disabled[A](p: Peg[A]): Peg[A] = new DisabledActionsPeg(p)
    def buffered[A](p: Peg[A]): Peg[A] = new BufferedActionsPeg(p)

    class DisabledActionsPeg[A](override val self: Peg[A]) extends Peg.PegProxy[A] {
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

    class BufferedActionsPeg[A](override val self: Peg[A]) extends Peg.PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long): Long = {
            val old = isEnabled
            setEnabled(false)
            try {
                if (self.parse(v, first, last) != FAILURE) {
                    setEnabled(old)
                    return self.parse(v, first, last)
                } else {
                    FAILURE
                }
            } finally {
                setEnabled(old)
            }
        }
    }
}*/
