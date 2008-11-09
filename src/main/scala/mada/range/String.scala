
package mada.range


object FromString {
    def apply(a: String): Range[Char] = {
        val ia = new IndexAccess[Char] {
            override def _get(i: Long) = a.charAt(i.toInt)
            override def _size = a.length
        }
        new IndexAccessRange(ia) {
            override def stringize = a
        }
    }
}

object Stringize {
    def apply(r: Range[Char]): String = {
        val sb = new StringBuilder
        r.foreach(sb.append(_))
        sb.toString
    }
}

object UnsafeStringize {
    def apply[A](r: Range[A]): String = {
        Stringize(r.asRangeOf[Char])
    }
}
