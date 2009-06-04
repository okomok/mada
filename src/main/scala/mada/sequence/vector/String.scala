

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object Unstringize {
    def apply(from: String): Vector[Char] = new UnstringizeVector(from)
}

private[mada] class UnstringizeVector(val from: String) extends Vector[Char] {
    override def start = 0
    override def end = from.length
    override def apply(i: Int) = from.charAt(i)

    override def force = this
}


private[mada] object Stringize {
    def apply(from: Vector[Char]): String = from match {
        case from: UnstringizeVector => from.from // conversion fusion
        case _ => {
            val sb = new StringBuilder(from.size)
            for (e <- from) {
                sb.append(e)
            }
            sb.toString
        }
    }
}


case class LowerCase(_1: Vector[Char]) extends Forwarder[Char] {
    override protected val delegate = _1.map(java.lang.Character.toLowerCase(_))
}

case class UpperCase(_1: Vector[Char]) extends Forwarder[Char] {
    override protected val delegate = _1.map(java.lang.Character.toUpperCase(_))
}
