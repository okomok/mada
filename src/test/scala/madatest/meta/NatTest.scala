

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.hetero


import mada.Meta._
// import junit.framework.Assert._


class NatTest {
    def testTrivial: Unit = {

        assert[_0N == _0N]
        assert[_0N != _1N]

        assert[_1N == _1N]
        assert[_1N != _2N]
        assert[_1N != _3N]

        assert[_1N#increment == _2N]
        assert[++[_1N] == _2N]

        assert[_1N#increment#increment == _3N]
        assert[_2N#increment#decrement == _2N]

        assert[_5N#decrement#decrement#decrement == _2N]
        assert[_8N == _7N#increment#increment#decrement]
        assert[_7N#decrement#increment#increment == _7N#increment#increment#decrement]

// illegal cyclic reference involving ++
  //      assert[++[++[_2N]] == _4N]
  //      assert[--[++[_3N]] == _3N]

      ()
    }
}
