

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package autotest


    import mada.auto._
    import java.nio.channels
    import java.nio.channels.Channels

    class DocTest {
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
