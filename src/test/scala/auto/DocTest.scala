

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package autotest


    import com.github.okomok.mada.Auto
    import java.nio.channels
    import java.nio.channels.Channels

    class DocTezt { // extends org.scalatest.junit.JUnit3Suite {
        def testTrivial: Unit = {
            for {
                source <- Auto(Channels.newChannel(System.in))
                dest <- Auto(Channels.newChannel(System.out))
            } {
                channelCopy(source, dest)
            }
        }

        def channelCopy(src: channels.ReadableByteChannel, dest: channels.WritableByteChannel) {
            // exercise.
        }
    }
