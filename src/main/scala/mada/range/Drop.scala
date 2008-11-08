
package mada.range


import java.lang.Math.min


object Drop {
    def apply[A](r: Range[A], n: Long): Range[A] = {
        val p = r.begin; val q = r.end
        r.traversal match {
            case RandomAccessTraversal => {
                p += min(q - p, n)
                p <=< q
            }
            case SinglePassTraversal => {
                var m = n
                while (m != 0 && p != q) { ++(p); m = m - 1 }
                p <=< q
            }
        }
    }
}

object DropWhile {
    def apply[A](r: Range[A], f: A => Boolean): Range[A] = r.findPointerOf(!f(_)) <=< r.end
}
