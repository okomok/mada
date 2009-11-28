

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


import java.nio.channels.{Selector, SelectionKey, ClosedSelectorException}


case class Selection1(_1: Selector) extends Forwarder[SelectionKey] {
    override protected val delegate: Reactive[SelectionKey] = new _Selection(_1, _.select)
}

case class Selection2(_1: Selector, _2: Long) extends Forwarder[SelectionKey] {
    override protected val delegate: Reactive[SelectionKey] = new _Selection(_1, _.select(_2))
}


private class _Selection(_1: Selector, _2: Selector => Long) extends Reactive[SelectionKey] {
    override def start(k: Reactor[SelectionKey]): Unit = {
        try {
            while (true) {
                if (_2(_1) != 0) {
                    val keys = _1.selectedKeys
                    for (key <- iterative.from(keys)) {
                        k.react(key.asInstanceOf[SelectionKey])
                    }
                    keys.clear
                }
            }
        } catch  {
            case _: ClosedSelectorException => return k.onEnd
        }
    }
}
