

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


/**
 * Thrown if <code>width</code> is required but not constant.
 */
class NotConstantWidth(msg: String) extends UnsupportedOperationException(msg)
