
package mada.range


object FromString {
    def apply(a: String): Range[Char] = {
        val ia = new IndexAccess[Char] {
            override def _set(i: Long, e: Char) = { throw new ErrorNonWritableIndexAccess("String") }
            override def _get(i: Long) = a.charAt(i.toInt)
            override def _length = a.length
        }
        FromIndexAccess(ia)
    }
}
