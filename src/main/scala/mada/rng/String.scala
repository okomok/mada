
package mada.rng


// StringConversion

object StringConversion extends StringConversion

trait StringConversion {
    implicit def madaRngFromString(from: String) = FromString(from)
    implicit def madaRngToString(from: Rng[Char]) = StringizeImpl(from).stringize
}


// FromString

object FromString {
    def apply(a: String): Rng[Char] = new StringRng(a)
}

case class StringRng(base: String) extends IndexAccessRng[Char] {
    override def _get(i: Long) = base.charAt(i.toInt)
    override def _size = base.length
}


// Stringize

object Stringize extends Stringize

trait Stringize {
    trait MadaRngStringize extends StringizeImpl.Operator
    implicit def toMadaRngStringize(r: Rng[Char]) = StringizeImpl(r)
}

object StringizeImpl {
    trait Operator {
        def stringize: String
    }

    def apply(r: Rng[Char]) = r match {
        case StringRng(b) => new Operator {
            def stringize = b
        }
        case _ => new Operator {
            def stringize = impl(r)
        }
    }

    private def impl(r: Rng[Char]): String = {
        val sb = new StringBuilder
        r.foreach(sb.append(_))
        sb.toString
    }
}
