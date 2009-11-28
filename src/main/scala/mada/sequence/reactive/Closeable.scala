

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


/**
 * A closeable reactive.
 */
trait Closeable[+A] extends Reactive[A] with java.io.Closeable
