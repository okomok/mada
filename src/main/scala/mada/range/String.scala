
package mada.range


// StringConversion

object StringConversion extends StringConversion

trait StringConversion {
    implicit def madaRangeFromString(from: String) = FromString(from)
    implicit def madaRangeToString(from: Range[Char]) = StringizeImpl(from).stringize
}


// FromString

object FromString {
    def apply(a: String): Range[Char] = new StringRange(a)
}

case class StringRange(base: String) extends IndexAccessRange[Char] {
    override def _get(i: Long) = base.charAt(i.toInt)
    override def _size = base.length
}


// Stringize

object Stringize extends Stringize

trait Stringize {
    trait MadaRangeStringize extends StringizeImpl.Operator
    implicit def toMadaRangeStringize(r: Range[Char]) = StringizeImpl(r)
}

object StringizeImpl {
    trait Operator {
        def stringize: String
    }

    def apply(r: Range[Char]) = r match {
        case StringRange(b) => new Operator {
            def stringize = b
        }
        case _ => new Operator {
            def stringize = impl(r)
        }
    }

    private def impl(r: Range[Char]): String = {
        val sb = new StringBuilder
        r.foreach(sb.append(_))
        sb.toString
    }
}
