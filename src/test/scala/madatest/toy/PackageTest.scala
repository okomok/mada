

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.toy.package_


import junit.framework.Assert._


package your {
    object My {
        private val P = my.`package`
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

class PackageTest {
    def buz = {
     //   foo(3)
     ()
    }
}

