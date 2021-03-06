

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package armtest


    import com.github.okomok.mada.arm.{scope, use}
    import java.nio.channels
    import java.nio.channels.Channels

    class DocTezt { // extends org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            scope {
                val source = use(Channels.newChannel(System.in))
                val dest = use(Channels.newChannel(System.out))
                channelCopy(source, dest)
            }
        }

        def channelCopy(src: channels.ReadableByteChannel, dest: channels.WritableByteChannel) {
            // exercise.
        }
    }
