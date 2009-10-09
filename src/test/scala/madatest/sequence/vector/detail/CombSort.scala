

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package vectortest; package detail


object CombSort {
    import mada.sequence._

    def apply[A](v: Vector[A], f: (A, A) => Boolean): Unit = {
        val (first, last) = (v.start, v.end)

        var gap = last - first
        if (gap < 1)
            return

        var first2 = last
        var swapped = false
        do {
            var newgap = (gap * 10 + 3) / 13
            if (newgap < 1)
                newgap = 1

            first2 += (newgap - gap) // paren is needed in scala 2.7.1
            gap = newgap
            swapped = false

            var (target1, target2) = (first, first2)
            while (target2 != last) {
                if (f(v(target2), v(target1))) {
                    vector.stl.iterSwap(v, target1)(v, target2)
                    swapped = true
                }
                target1 += 1; target2 += 1
            }
        } while ((gap > 1) || swapped)
    }

    def apply[A <% Ordered[A]](v: Vector[A]): Unit = apply(v, (_: A) < (_: A))
}



class CombSortTest {
    import mada.sequence._
    import junit.framework.Assert._

    def expected = vector.fromArray(Array(0, 0, 4, 4, 6, 8,11,12,13,14,15,17,18,19,23))
    def example =  vector.fromArray(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4))

    def testTrivial: Unit ={
        val v = example
        CombSort(v)
        assertEquals(expected, v)
    }
}
