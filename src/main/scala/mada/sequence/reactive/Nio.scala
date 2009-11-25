

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


import java.nio.channels.{Selector, SelectionKey}


case class Select(_1: Selector) extends Reactive[SelectionKey] {
    override def subscribe(k: Reactor[SelectionKey]) = {
        while (true) {
            if (_1.select != 0) {
                val keys = _1.selectedKeys
                for (key <- iterative.from(keys)) {
                    k.react(key.asInstanceOf[SelectionKey])
                }
                keys.clear
            }
        }
    }
}
