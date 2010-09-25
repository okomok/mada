

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest; package doctest


    import com.github.okomok.mada
    import mada.sequence.reactive.Swing
    import javax.swing


    import com.github.okomok.mada
    import mada.sequence.reactive.Swing
    import javax.swing

    class SwingTezt {
//    class SwingTest extends org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            val frame = new swing.JFrame("SwingTest")
            val label = new swing.JLabel("testTrivial")
            frame.getContentPane.add(label)
            frame.setDefaultCloseOperation(swing.JFrame.EXIT_ON_CLOSE)
            frame.pack
            frame.setVisible(true)

            val mouse = Swing.Mouse(label)
            for {
                _ <- mouse.Pressed.take(10).doing(_ => println("pressed"))
                _ <- mouse.Dragged.takeUntil(mouse.Released).then(println("released"))
            } {
                println("dragging")
            }

            Thread.sleep(20000)
        }
    }


    /*
    class SwingTezt { // extends org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            val frame = new swing.JFrame("SwingTest")
            val label1 = new swing.JLabel("Left")
            val label2 = new swing.JLabel("Right")
            frame.getContentPane.add(label1, java.awt.BorderLayout.WEST)
            frame.getContentPane.add(label2, java.awt.BorderLayout.EAST)
            frame.setDefaultCloseOperation(swing.JFrame.EXIT_ON_CLOSE)
            frame.pack
            frame.setVisible(true)

            val l = Swing.Mouse(label1).Clicked
            val r = Swing.Mouse(label2).Clicked
            l.merge(r).
                take(5).
                scanLeft(0){(b, _) =>  b + 1}.
                foreach{i => println("click count: " + i)}

            Thread.sleep(10000)
        }
    }
    */

