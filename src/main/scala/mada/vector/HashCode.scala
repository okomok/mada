

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


// See: http://java.sun.com/javase/6/docs/api/java/util/List.html#hashCode()

private[mada] object HashCode_ {
    def apply[A](v: Vector[A]): Int = {
        var code = 1
        var start = v.start; val end = v.end
        while (start != end) {
            code = 31 * code + v(start).hashCode
            start += 1
        }
        code
    }
}
