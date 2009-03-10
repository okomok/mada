

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Stringize {
    def apply[A](from: Iterable[Char]): String = {
        val sb = new StringBuilder
        for (ch <- from.projection) {
            sb.append(ch)
        }
        sb.toString
    }
}
