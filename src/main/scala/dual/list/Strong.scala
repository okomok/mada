

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


/**
 * Sequence-derivation helper to work around suboptimal `nsc.symtab.Types.Type.isGround`.
 * (See "C++ Template Metaprgramming" 5.10 and C.3.7)
 */
abstract class Strong[ys <: List](final override protected val delegate: ys) extends TrivialForwarder {
    final override protected type delegate = ys
}
