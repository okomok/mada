

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Stringize {
    def apply[A](it: Iterator[Char]): String = {
        val sb = new StringBuilder
        for (ch <- it) {
            sb.append(ch)
        }
        sb.toString
    }
}
