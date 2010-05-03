

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


import javax.swing
import reactive.Swing


class SwingTest {


    def testTrivial(off: Int): Unit = {
        val frame = new swing.JFrame("SwingTest")
        val label = new swing.JLabel("testTrivial")
        frame.getContentPane.add(label)
        frame.setDefaultCloseOperation(swing.JFrame.EXIT_ON_CLOSE)
        frame.pack
        frame.setVisible(true)

        var closed = false

        val x = new Swing.MouseClicked(label)
        x.take {
            3
        } forLoop { e =>
            println("clicked")
        } endWith {
            x.close
            closed = true
        } start

        Thread.sleep(10000)
        assertTrue(closed)
    }

    def testTrivial2(off: Int): Unit = {
        val frame = new swing.JFrame("SwingTest")
        val label = new swing.JLabel("testTrivial")
        frame.getContentPane.add(label)
        frame.setDefaultCloseOperation(swing.JFrame.EXIT_ON_CLOSE)
        frame.pack
        frame.setVisible(true)

        val pressedSeq = new Swing.MousePressed(label)

        pressedSeq forLoop { _ =>
            println("pressed")
            val draggedSeq = new Swing.MouseDragged(label)
            val releasedSeq = new Swing.MouseReleased(label)
            draggedSeq before {
                releasedSeq
            } endWith {
                println("released")
                draggedSeq.close
                releasedSeq.close
            } start
        } start

        Thread.sleep(20000)
        pressedSeq.close
    }

/*
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
            } start
        } mouseClicked { y =>
            y.take {
                3
            } forLoop { e =>
                println("clicked")
            } endWith {
                x.close
                closed = true
            } start
        } start

        Thread.sleep(10000)
        assertTrue(closed)
    }


    def testTrivial2: Unit = { // broken
        val frame = new swing.JFrame("SwingTest")
        val label = new swing.JLabel("testTrivial")
        frame.getContentPane.add(label)
        frame.setDefaultCloseOperation(swing.JFrame.EXIT_ON_CLOSE)
        frame.pack
        frame.setVisible(true)

        import reactive.Swing

        var closed = false

        val ms = new Swing.MouseEventFrom(label)
        ms.mousePressed { y =>
            y.forLoop { e =>
                println("pressed")
                val m = new Swing.MouseMotionEventFrom(label)
                val msr = new Swing.MouseEventFrom(label)
                m.mouseDragged { k =>
                    k.until {
                        msr.mouseReleased { y =>
                            y.endWith {
                                print("released")
                                m.close
                                msr.close
                            } start
                        }
                    } start
                } start
            } start
        } start

        Thread.sleep(10000)
        ms.close
    }
*/
}
