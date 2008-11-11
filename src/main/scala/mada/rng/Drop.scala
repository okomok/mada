
package mada.rng


import java.lang.Math.min


object Drop {
    def apply[A](r: Rng[A], n: Long): Rng[A] = {
        val p = r.begin; val q = r.end
        r.traversal match {
            case _: RandomAccessTraversal => {
                p += min(q - p, n)
                p <=< q
            }
            case _: SinglePassTraversal => {
                var m = n
                while (m != 0 && p != q) { ++(p); m = m - 1 }
                p <=< q
            }
        }
    }
}

object DropWhile {
    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = r.findPointerOf(!f(_)) <=< r.end
}
