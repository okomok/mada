

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import junit.framework.Assert._


class EqualsTest extends junit.framework.TestCase {
    def testNull: Unit = {
        assertFalse(vector.range(1, 3) == null)
    }
}
