

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


sealed abstract class Overload

object Overload {
    private[util] val value = new Overload{}
    implicit def _fromUnit(from: Unit): Overload = value
}
