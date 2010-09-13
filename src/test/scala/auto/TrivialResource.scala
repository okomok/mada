

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package autotest


import com.github.okomok.mada

import mada.{auto, Auto}


case class TrivialResource[A](res: A, b: Boolean = false) extends auto.Resource[A] {
    var began = false
    var ended = false

    override def begin = {
        if (b) throw new Error
        began = true
    }
    override def end = ended = true
    override def get = res
}
