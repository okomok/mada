
package mada.range


object FromArray {
    def apply[A](a: Array[A]): Range[A] = {
        val ia = new IndexAccess[A] {
            override def _set(i: Long, e: A) = a(i.toInt) = e
            override def _get(i: Long) = a(i.toInt)
            override def _size = a.length
        }
        FromIndexAccess(ia)
    }

    def apply[A](es: A*): Range[A] = {
        val a = new Array[A](es.length)
        var i = 0
        for (e <- es.elements) {
            a(i) = e
            i = i + 1
        }
        apply(a)
    }
}

object ToArray {
    def apply[A](r: Range[A]): Array[A] = {
        r.traversal match {
            case ForwardTraversal => inForward(r)
            case SinglePassTraversal => inForward(r.copy)
        }
    }

    private def inForward[A](r: Range[A]): Array[A] = {
        val a = new Array[A](r.distance.toInt)
        var i = 0
        r.foreach({(e: A) => a(i) = e; i = i + 1})
        a
    }
}
