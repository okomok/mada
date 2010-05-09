

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package autotest


import com.github.okomok.mada

    import mada.auto._
    import java.nio.channels
    import java.nio.channels.Channels

    class DocTest extends junit.framework.TestCase {
        def testOff: Unit = ()

        def teztTrivial: Unit = {
            for {
                source <- use(Channels.newChannel(System.in))
                dest <- use(Channels.newChannel(System.out))
            } {
                channelCopy(source, dest)
            }
        }

        def channelCopy(src: channels.ReadableByteChannel, dest: channels.WritableByteChannel) {
            // exercise.
        }
    }
