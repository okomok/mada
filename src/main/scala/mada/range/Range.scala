
package mada.range


trait Range[E] {
    protected def _begin: Pointer[E]
    protected def _end: Pointer[E]

    final def begin = _begin
    final def end = _end
    final def traversalTag = begin.traversalTag

    final def toIterator = new scala.Iterator[E] {
        private val first = begin
        override def hasNext = first !== end
        override def next() = { first++; first.read }
    }
}
