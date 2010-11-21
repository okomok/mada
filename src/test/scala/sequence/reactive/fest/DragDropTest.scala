

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package sequencetest.reactivetest.festtest
package dragdroptest


import org.testng.annotations._
import org.fest.swing.fixture.FrameFixture
import org.fest.swing.core.ComponentDragAndDrop

import javax.swing

import com.github.okomok.mada
import mada.util.|<
import mada.sequence.reactive
import mada.sequence.Reactive


class DragDropTest extends
    NotFestSuite
//    FestTestNGSuite
{
    private var fixt: FrameFixture = null

    override protected def onSetUp {
        val jf = mada.eval.InEdt {
            val jf = new swing.JFrame("DragDropTest")
            val jl = new swing.JLabel("Drag") |< (_.setName("Drag")) |< (jf.getContentPane.add(_))

            reactive.block { Y =>
                import Y._
                val mouse = reactive.Swing.Mouse(jl)
                val p = head(mouse.Pressed)
                println("pressed at: " + (p.getX, p.getY))
                for (d <- until(mouse.Dragged.stepTime(100), mouse.Released)) {
                    println("dragging at: " + (d.getX, d.getY))
                }
                println("released")
                99
            }
/*
            reactive.block { Y =>
                import Y._
                val mouse = reactive.Swing.Mouse(jl)
                val p = each(mouse.Pressed.take(10))
                println("pressed at: " + (p.getX, p.getY))
                val d = each(mouse.Dragged.stepTime(100).
                    takeUntil(mouse.Released).onExit(_ => println("released")))
                println("dragging at: " + (d.getX, d.getY))
            }
*/
            jf
        } apply

        fixt = new FrameFixture(robot, jf)
        fixt.show()
    }

    @Test
    def testTrivial {
        val dd = new ComponentDragAndDrop(robot)
        val jl = fixt.label("Drag").target
        dd.drag(jl, new java.awt.Point(1, 1))
        dd.dragOver(jl, new java.awt.Point(15, 10))
        dd.drop(jl, new java.awt.Point(1, 1))
    }
}