

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


trait Adapter[From, +To] extends Reactive[To] {
    def underlying: Reactive[From]
}
