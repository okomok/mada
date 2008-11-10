
package mada.range


object StringConversion extends StringConversion

trait StringConversion {
    implicit def madaRangeFromString(from: String) = FromString(from)
    implicit def madaRangeToString(from: Range[Char]) = Stringize.toMadaRangeStringize(from).stringize
}


object FromString {
    def apply(a: String): Range[Char] = new StringRange(a)
}

case class StringRange(base: String) extends IndexAccessRange[Char] {
    override def _get(i: Long) = base.charAt(i.toInt)
    override def _size = base.length
}


object Stringize {
    trait MadaRangeStringize {
        def stringize: String
    }
    def toMadaRangeStringize(r: Range[Char]) = r match {
        case StringRange(b) => new MadaRangeStringize {
            def stringize = b
        }
        case _ => new MadaRangeStringize {
            def stringize = StringizeImpl(r)
        }
    }
}

object StringizeImpl {
    def apply(r: Range[Char]): String = {
        val sb = new StringBuilder
        r.foreach(sb.append(_))
        sb.toString
    }
}

