

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package autotest


import com.github.okomok.mada

import mada.{auto, Auto}


case class TrivialResource[A](res: A) extends Auto[A] {
    var began = false
    var ended = false
    override def foreach(f: A => Unit) = {
        began = true
        try {
            f(res)
        } finally {
            ended = true
        }
    }
}
