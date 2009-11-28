

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


import javax.swing


class SwingTest {

/*
    def testTrivial(off: Int): Unit = {
        val frame = new swing.JFrame("SwingTest")
        val label = new swing.JLabel("testTrivial")
        frame.getContentPane.add(label)
        frame.setDefaultCloseOperation(swing.JFrame.EXIT_ON_CLOSE)
        frame.pack
        frame.setVisible(true)

        var closed = false

        val x = reactive.Swing.mouseClicked(label)
        x.take {
            3
        } forLoop { e =>
            println("clicked")
        } endWith {
            x.close
            closed = true
        } run

        Thread.sleep(10000)
        assertTrue(closed)
    }
*/

    def testTrivial(off: Int): Unit = {
        val frame = new swing.JFrame("SwingTest")
        val label = new swing.JLabel("testTrivial")
        frame.getContentPane.add(label)
        frame.setDefaultCloseOperation(swing.JFrame.EXIT_ON_CLOSE)
        frame.pack
        frame.setVisible(true)

        var closed = false

        val x = new reactive.Swing.MouseEventFrom(label)
        x.mouseEntered { y =>
            y.take {
                3
            } forLoop { e =>
                println("entered")
            } run
        } mouseClicked { y =>
            y.take {
                3
            } forLoop { e =>
                println("clicked")
            } endWith {
                x.close
                closed = true
            } run
        } run

        Thread.sleep(10000)
        assertTrue(closed)
    }

}
