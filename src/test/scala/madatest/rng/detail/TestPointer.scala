
//
// Copyright (c) David Abrahams 2001.
// Copyright (c) Jeremy Siek 2001-2003.
// Copyright (c) Thomas Witt 2002.
//
// Use, modification and distribution is subject to the
// Boost Software License, Version 1.0.
// (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)
//



// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng.detail


import mada.rng._;
import mada.rng.Pointer._
import junit.framework.Assert._


object TestReadablePointer {
    def apply[T](i1: Pointer[T], v: T) {
        assertEquals(*(i1), v)
    }
}


object TestWritablePointer {
    def apply[T](i1: Pointer[T], v: T) {
        *(i1) = v
    }
}


object TestForwardReadablePointer {
    def apply[T](iv: (Pointer[T], T), jv: (Pointer[T], T)) {
        val i2 = iv._1.copy
        val i3 = iv._1.copy
        assertEquals(i2, i3)
        AssertNotEquals(iv._1, jv._1)
        AssertNotEquals(i2, jv._1)
        TestReadablePointer(iv._1, iv._2)
        TestReadablePointer(i2, iv._2)
        TestReadablePointer(i3, iv._2)

        assertEquals(iv._1, i2++)
        AssertNotEquals(iv._1, ++(i3))

        TestReadablePointer(i2, jv._2)
        TestReadablePointer(i3, jv._2)
        TestReadablePointer(iv._1, iv._2)
    }
}


object TestBidirectionalReadablePointer {
    // Preconditions: *(i) == v1, *(++(i)) == v2
    def apply[A](i: Pointer[A], v1: A, v2: A) {
        val j = i.copy
        ++(j)
        TestForwardReadablePointer((i, v1), (j, v2))
        ++(i)

        val i1 = i.copy; val i2 = i.copy

        assertEquals(i, i1--)
        AssertNotEquals(i, --(i2))

        TestReadablePointer(i, v2)
        TestReadablePointer(i1, v1)
        TestReadablePointer(i2, v1)

        --(i)
        assertEquals(i, i1)
        assertEquals(i, i2)
        ++(i1)
        ++(i2)

        TestReadablePointer(i, v1)
        TestReadablePointer(i1, v2)
        TestReadablePointer(i2, v2)
    }
}


object TestRandomAccessReadablePointer {
    def apply[A](i: Pointer[A], n: Int, vals: Array[A]) {
        TestBidirectionalReadablePointer(i, vals(0), vals(1))
        val j = i.copy

        var c = 0
        while (c < n-1) {
            assertEquals(i, j + c)
            assertEquals(*(i), vals(c))
            val x = j(c)
            assertEquals(*(i), x)
            assertEquals(*(i), *(j + c))
            ++(i)
            assertTrue(i > j)
            assertTrue(i >= j)
            assertTrue(j <= i)
            assertTrue(j < i)
            c = c+1
        }

        val k = j + n - 1

        c = 0
        while (c < n-1) {
            assertEquals(i, k - c)
            assertEquals(*(i), vals(n - 1 - c))
            val x = j(n - 1 - c)
            assertEquals(*(i), x)
            val q = k - c
            assertEquals(*(i), *(q))
            assertTrue(i > j)
            assertTrue(i >= j)
            assertTrue(j <= i)
            assertTrue(j < i)
            --(i)
            c = c+1
        }
    }
}
