

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object StringVector {
    def apply(u: String): Vector[Char] = new StringVector(u)
}

class StringVector(val string: String) extends Vector[Char] with NotWritable[Char] {
    override def size = string.length
    override def apply(i: Long) = string.charAt(i.toInt)
}


object ToString {
    def apply[Char](v: Vector[Char]): String = v match {
        case v: StringVector => v.string
        case _ => {
            val sb = new StringBuilder(v.size.toInt)
            v.foreach(sb.append(_))
            sb.toString
        }
    }
}
