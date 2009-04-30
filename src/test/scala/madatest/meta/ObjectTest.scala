

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._
// import junit.framework.Assert._


class ObjectTest {
    def testTrivial: Unit = {

        trait My extends Object {
            type secret1 <: Object
        }

        trait my extends My {
            type secret2 <: Object
        }

        nullOf[asInstanceOf[my, My]#secret1]
//        nullOf[asInstanceOf[my, My]#secret2] // error!

      ()
    }
}
