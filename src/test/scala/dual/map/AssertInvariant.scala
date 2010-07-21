

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada

import mada.dual._
import junit.framework.Assert._


object AssertInvariant {
    def apply[m <: map.bstree.BSTree](m: m) {
        if (m.isEmpty.undual) {
            assertEquals(0, m.size.undual)
        } else  {
            var sz = 1
            if (!m.left.isEmpty.undual) {
                AssertKeyLT(m.left, m.key)
                AssertInvariant(m.left)
                sz += m.left.size.undual
            }
            if (!m.right.isEmpty.undual) {
                AssertKeyGT(m.right, m.key)
                AssertInvariant(m.right)
                sz += m.right.size.undual
            }
            assertEquals(m.size.undual, sz)
        }
    }
}

object AssertKeyLT {
    def apply[m <: map.bstree.BSTree, k <: Any](m: m, k: k) {
        if (!m.isEmpty.undual) {
            assertTrue(m.ord.compare(m.key, k).undual < 0)
            AssertKeyLT(m.left, k)
            AssertKeyLT(m.right, k)
        }
    }
}

object AssertKeyGT {
    def apply[m <: map.bstree.BSTree, k <: Any](m: m, k: k) {
        if (!m.isEmpty.undual) {
            assertTrue(m.ord.compare(k, m.key).undual < 0)
            AssertKeyGT(m.left, k)
            AssertKeyGT(m.right, k)
        }
    }
}
