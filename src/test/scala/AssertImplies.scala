

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest


import com.github.okomok.mada

import junit.framework.Assert._


object AssertImplies {
    private def apply(message: String, pre: Boolean, post: => Boolean) {
        val sb = new StringBuilder
        if (message ne null) {
            sb.append(message).append(' ')
        }

        if (pre && !post)
            fail(sb.
                append("failed to imply").toString )
    }

    def apply(pre: Boolean, post: => Boolean) {
        apply(null, pre, post)
    }
}
