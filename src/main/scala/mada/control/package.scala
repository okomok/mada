

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods for control expressions.
 */
package object control {
    /**
     * Evaluates <code>body</code> infinite times.
     */
    def repeat[R](body: => R): Unit = while (true) body

    /**
     * Evaluates <code>body</code> <code>n</code> times.
     */
    def times[R](body: => R, n: Int): Unit = {
        var i = 0
        while (i != n) {
            body
            i += 1
        }
    }
}
