
package mada.range


trait Output[-E] {
    protected def _write(e: E): Unit
    final def << (e: E): Output[E] = { _write(e); this }
}


case class FunctionOutput[E, R](f: E => R) extends Output[E] {
    override def _write(e: E) = { f(e); }
}
