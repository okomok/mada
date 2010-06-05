

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package package_


import junit.framework.Assert._


package your {
    object My {
        val P = my.`package`
    }

   // trait My[A]

    package object my {
        def foo[A](e: A) = ()

    }

    package my {
        object bar
    }
}

import your.My._

class PackageTezt {
    def buz = {
     //   foo(3)
     your.My.P.foo(3)
     ()
    }
}

