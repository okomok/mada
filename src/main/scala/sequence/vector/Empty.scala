

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class Empty() extends Vector[Nothing] {
    override def start = 0
    override def end = 0
}
