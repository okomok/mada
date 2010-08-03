

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Bind[xs <: Seq](override val: xs) extends AbstractSeq {
    override type = xs
}
