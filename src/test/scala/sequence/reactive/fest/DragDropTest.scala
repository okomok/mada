

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

import scala.util.continuations


/*
object Rx {

    def block[A](ctx: BlockContext => A @continuations.cpsParam[A, Any]): Unit = continuations.reset(ctx(new BlockContext))

    class BlockContext {
        // loop{...
        def apply[A](ctx: BlockContext => A @continuations.cpsParam[Any, Any]): reactive.Exit @continuations.cpsParam[Any, Any] = {
            println("BlockContext.apply")
            val c = new BlockContext
            ctx(c)
            assert(c._xs != null)
            assert(c._f != null)
            new reactive.BlockContext{}.each { new Reactive[reactive.Exit] {
                override def forloop(f: reactive.Exit => Unit, k: reactive.Exit => Unit) {
                    println("hey")
                    c._xs.onExit(q => f(q)).foreach(x => c._f(x))
                }
            } }
        }
    }

    class BlockContext {
        var _xs: Reactive[Any] = null
        var _f: Any => Any = null
        // next(...
        def apply[A](xs: Reactive[A]): A @continuations.cpsParam[Any, Any] = {
            println("BlockContext.apply")
            continuations.shift { (k: A => Any) => _xs = xs; _f = k.asInstanceOf[Any => Any] } //xs.foreach(function.discard(k)) }
        }
    }

}
*/

/*
    class Forloop[A](xs: Reactive[A]) {
        import reactive.Exit
        import reactive.BlockContext.each
        import scala.util.continuations._
        def foreach(g: A => Any @cpsParam[Unit, Any]): Exit @cpsParam[Any, Unit] = each {
            new Reactive[Exit] {
                override def forloop(cp: Exit => Unit, k: Exit => Unit) {
                    xs.onExit(q => cp(q)).forloop(x => reset{g(x);()}, k)
                }
            }
        }
    }
*/

class DragDropTest extends
//    NotFestSuite
    FestTestNGSuite
{
    private var fixt: FrameFixture = null

    override protected def onSetUp {
        val jf = mada.eval.InEdt {
            val jf = new swing.JFrame("DragDropTest")
            val jl = new swing.JLabel("Drag") |< (_.setName("Drag")) |< (jf.getContentPane.add(_))

            reactive.block { * =>
                val mouse = reactive.Swing.Mouse(jl)
                for (p <- *(mouse.Pressed)) {
                    println("pressed at: " + (p.getX, p.getY))
                    for (d <- *(mouse.Dragged.stepTime(100).takeUntil(mouse.Released))) {
                        println("dragging at: " + (d.getX, d.getY))
                    }
                    println("released")
                    999 // check value-discarding.
                }
            }

/*
            Rx.block { loop =>
                val ex = loop { next =>
                    val mouse = reactive.Swing.Mouse(jl)
                    val p = next(mouse.Pressed)
                    println("pressed at: " + (p.getX, p.getY))
                    val ex = loop { next =>
                        val d = next(mouse.Dragged.stepTime(100).takeUntil(mouse.Released))
                        println("dragging at: " + (d.getX, d.getY))
                    }
                    println("released: " + ex)
                    99
                }
                println("hooo: " + ex)
            }
*/
/*
            reactive.block { next =>
                val mouse = reactive.Swing.Mouse(jl)
                val p = next.head(mouse.Pressed)
                println("pressed at: " + (p.getX, p.getY))
                for (d <- next.until(mouse.Dragged.stepTime(100), mouse.Released)) {
                    println("dragging at: " + (d.getX, d.getY))
                }
                println("released")
                99
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