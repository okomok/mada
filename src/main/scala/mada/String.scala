

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


// Workaround: str.elements needs the implicit conversion of String->RichString.


object StringCompatibles {
    implicit def string2iterator(str: String): Iterator[Char] = StringIterator(str)
}

object StringIterator {
    def apply(str: String): Iterator[Char] = str.elements
}
