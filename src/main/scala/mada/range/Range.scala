
package mada.range


trait Range[E] {
    protected def _begin: Pointer[E]
    protected def _end: Pointer[E]

    final def begin = _begin
    final def end = _end
    final def traversalTag = begin.traversalTag

//    final def >>[To](f: Range[E] => To): To = f(this)
    final def >>[To](f: RangeFunction[To]): To = (f.fromRange[E])(this)

    final def toIterator = new Iterator[E] {
        private val first = begin
        override def hasNext = first != end
        override def next() = { first++/; first.read }
    }
}
