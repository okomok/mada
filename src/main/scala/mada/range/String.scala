
package mada.range


object FromString {
    def apply(a: String): Range[Char] = new StringRange(a)
}

case class StringRange(base: String) extends IndexAccessRange[Char] {
    override def _get(i: Long) = base.charAt(i.toInt)
    override def _size = base.length
}


object Stringize {
    def apply(r: Range[Char]): String = {
        val sb = new StringBuilder
        r.foreach(sb.append(_))
        sb.toString
    }

    object Operator {
        trait MadaRangeStringizeLeft {
            def stringize: String
        }
        implicit def toMadaRangeStringizeLeft(l: Range[Char]) = l match {
            case StringRange(base) => new MadaRangeStringizeLeft { override def stringize = base }
            case _ => new MadaRangeStringizeLeft { override def stringize = apply(l) }
        }
    }
}
