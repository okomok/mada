
package mada.range


trait Output[-E] {
    def _write(e: E): Unit
    def << (e: E): Output[E] = { _write(e); this }
}


case class FunctionOutput[E, R](f: E => R) extends Output[E] {
    override def _write(e: E) = { f(e); }
}
