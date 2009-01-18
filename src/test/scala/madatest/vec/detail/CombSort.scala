

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.detail


object CombSort {
    import mada._

    def apply[A](* : Vector[A], f: (A, A) => Boolean): Unit = {
        import *._
        val (first, last) = *.pair

        var gap = last - first
        if (gap < 1L)
            return

        var first2 = last
        var swapped = false
        do {
            var newgap = (gap * 10L + 3L) / 13L
            if (newgap < 1L)
                newgap = 1L

            first2 += (newgap - gap) // paren is needed in scala 2.7.1
            gap = newgap
            swapped = false

            var (target1, target2) = (first, first2)
            while (target2 != last) {
                if (f(*(target2), *(target1))) {
                    vec.stl.IterSwap(*, target1, *, target2)
                    swapped = true
                }
                target1 += 1; target2 += 1
            }
        } while ((gap > 1L) || swapped)
    }

    def apply[A <% Ordered[A]](v: Vector[A]): Unit = apply(v, (_: A) < (_: A))
}



class CombSortTest {
    import mada._
    import junit.framework.Assert._

    def expected = Vector.arrayVector(Array(0, 0, 4, 4, 6, 8,11,12,13,14,15,17,18,19,23))
    def example =  Vector.arrayVector(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4))

    def testTrivial: Unit ={
        val v = example
        CombSort(v)
        assertEquals(expected, v)
    }
}
