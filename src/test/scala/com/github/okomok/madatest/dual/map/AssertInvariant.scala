

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada

import mada.dual._
import junit.framework.Assert._


object AssertInvariant {
    def apply[m <: Map](m: m): scala.Unit = {
        if (m.isEmpty.undual) {
            assertEquals(0, m.size.undual)
        } else  {
            var sz = 1
            if (!m.left.isEmpty.undual) {
                assertTrue(m.ord.compare(m.left.key, m.key).undual < 0)
                AssertInvariant(m.left)
                sz += m.left.size.undual
            }
            if (!m.right.isEmpty.undual) {
                assertTrue(m.ord.compare(m.key, m.right.key).undual < 0)
                AssertInvariant(m.right)
                sz += m.right.size.undual
            }
            assertEquals(m.size.undual, sz)
        }
    }
}
