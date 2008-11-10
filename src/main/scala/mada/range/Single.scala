
package mada.range


case class Single[A](base: A) extends IndexAccessRange[A] {
    override def _get(i: Long) = { Assert("out of Single Range", i == 0); base }
    override def _size = 1
}
