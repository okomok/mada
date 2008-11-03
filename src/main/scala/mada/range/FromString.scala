
package mada.range


object FromString {
    def apply[A](s: String): StringRange = new StringRange(s)
}

class StringRange(val string: String)
        extends PointerRange[Char](new StringPointer(string, 0), new StringPointer(string, string.length)) {
    override def size = string.length
}

class StringPointer(private val s: String, private var i: Int)
        extends PointerAdapter[Long, Char, StringPointer](new NumberPointer(i)) {
    override def _read = s.charAt(derefBase)
    override def _clone = new StringPointer(s, derefBase)
    private def derefBase: Int = *(base).toInt
}
