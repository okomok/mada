
package mada.range


trait Output[E] {
    type OutputType = Output[E]
    type ElementType = E
    def _write(e: ElementType): Unit
    def << (e: ElementType): Output[E] = { _write(e); this }

    implicit def fromFunction[R](f: E => R) = new Output[E] {
        override def _write(e: E) = { f(e); }
    }
}
