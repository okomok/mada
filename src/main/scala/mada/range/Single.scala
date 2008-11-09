
package mada.range


object Single {
    def apply[A](e: A): Range[A] = {
        val ia = new IndexAccess[A] {
            override def _get(i: Long) = { Assert("out of bounds", i == 0); e }
            override def _size = 1
        }
        new IndexAccessRange(ia) {
        }
    }
}
