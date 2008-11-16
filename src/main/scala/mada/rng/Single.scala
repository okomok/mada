
package mada.rng


class SingleIndexAccess[A](val base: A) extends IndexAccess[A] {
    override def _get(i: Long) = { Assert("out of SingleRng", i == 0); base }
    override def _size = 1
}
