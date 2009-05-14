

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


// In fact, most of all views are inherently lazy.
class asName[A](tr: => Traversable[A]) extends Forwarder[A] {
    final def _1 = function.byName(tr)
    override protected def delegate = _1()
}
