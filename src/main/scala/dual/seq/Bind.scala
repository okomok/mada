

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Bind[it <: Iterator](override val begin: it) extends AbstractSeq {
    override type begin = it
}
