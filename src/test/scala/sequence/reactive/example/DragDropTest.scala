

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest;
package sequencetest; package reactivetest; package example


    import com.github.okomok.mada
    import mada.sequence.reactive
    import javax.swing

    class DragDropTest extends
        NotSuite {
        //org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            val frame = mada.util.inEdt {
                val frame = new swing.JFrame("DragDropTest")
                val label = new swing.JLabel("testTrivial")
                frame.getContentPane.add(label)

                reactive.block {
                    val mouse = reactive.Swing.Mouse(label)
                    val p = mouse.Pressed.take(10).each
                    println("pressed at: " + (p.getX, p.getY))
                    val d = mouse.Dragged.stepTime(100).
                        takeUntil(mouse.Released).then(println("released")).each
                    println("dragging at: " + (d.getX, d.getY))
                }

                frame.pack
                frame.setVisible(true)
                frame
            }

            Thread.sleep(20000)
            mada.util.inEdt {
                frame.setVisible(false)
            }
        }
    }


/*
class DragDropTezt {
//class DragDropTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial {
        val frame = new swing.JFrame("SwingTest")
        val label = new swing.JLabel("testTrivial")
        frame.getContentPane.add(label)
        frame.setDefaultCloseOperation(swing.JFrame.EXIT_ON_CLOSE)
        frame.pack
        frame.setVisible(true)

        val mouse = reactive.Swing.Mouse(label)
        for {
            _ <- mouse.Pressed.take(10).doing(println("pressed"))
            e <- mouse.Dragged.takeUntil(mouse.Released).then(println("released"))
        } {
            println("dragging at: " + (e.getX, e.getY))
        }

        Thread.sleep(20000)
    }
}
*/
