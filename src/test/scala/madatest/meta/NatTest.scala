

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


import mada.Meta._
// import junit.framework.Assert._


class NatTest {
    def testTrivial: Unit = {

        assert[_0N == _0N]
        assert[_0N != _1N]

        assert[_1N == _1N]
        assert[_1N != _2N]
        assert[_1N != _3N]

        assert[_1N#increment[_] == _2N]
        assert[++[_1N] == _2N]

        assert[_1N#increment[_]#increment[_] == _3N]
        assert[_2N#increment[_]#decrement[_] == _2N]

        assert[_5N#decrement[_]#decrement[_]#decrement[_] == _2N]
        assert[_8N == _7N#increment[_]#increment[_]#decrement[_]]
        assert[_7N#decrement[_]#increment[_]#increment[_] == _7N#increment[_]#increment[_]#decrement[_]]

// illegal cyclic reference involving ++
  //      assert[++[++[_2N]] == _4N]
  //      assert[--[++[_3N]] == _3N]

      ()
    }
}
