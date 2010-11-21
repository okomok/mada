

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


sealed abstract class Exit

case object End extends Exit

case object Closed extends Exit // TODO?

case class Thrown(what: Throwable) extends Exit
