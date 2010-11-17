

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package sequencetest.reactivetest.festtest
package sampleprogresstest


import org.testng.annotations._
import org.fest.swing.fixture.FrameFixture

import javax.swing
import java.awt
import com.github.okomok.mada
import FestUtil.textAsName
import mada.sequence._
import mada.sequence.reactive.{Swing, Beans}
import mada.eval


object Primes {
    val primes: List[Int] = 2 :: 3 :: List.iterate(5)(n => n + 2).filter(isPrime(_))
    def isPrime(n: Int): Boolean = primes.tail.takeWhile(p => p * p <= n).forall(notDivs(n, _))
    def notDivs(n: Int, p: Int): Boolean = n % p != 0

    def inEdt = {
        Reactive.origin {
            eval.Async
        } catching {
            case t: Throwable => {
                t.printStackTrace
                throw t
            }
        } generate {
            primes
        } shift {
            eval.InEdt
        }
    }
}


class PrimesProgressGuiTest
    extends NotFestSuite
//    extends FestTestNGSuite
{
    private var fx: FrameFixture = null
    private var monitor: swing.ProgressMonitor = null
    private val gate = new java.util.concurrent.CountDownLatch(1)

    override protected def onSetUp {
        val f = mada.eval.InEdt {
            val frame = new swing.JFrame("ProgressMonitor Sample")
            frame.setLayout(new awt.GridLayout(0, 1))
            val startButton = new swing.JButton("Start") textAsName
            val resultLabel = new swing.JLabel("Result") textAsName

            frame.add(startButton)
            frame.add(resultLabel)

            for (actionEvent <- Swing.ActionPerformed(startButton)) {
                val parent = actionEvent.getSource.asInstanceOf[awt.Component]
                val n = 20000
                monitor = new swing.ProgressMonitor(parent, "Loading Progress", "Getting Startet...", 0, n)
                val ps = Primes.inEdt
                ps.zipWithIndex foreach { case (x, i) =>
                    monitor.setProgress(i)
                    monitor.setNote("Calculated " + i + " primes")
                    if (i == n-1) {
                        resultLabel.setText(x.toString)
                        ps.close()
                        gate.countDown()
                    }
                }
            }

            frame.setSize(300, 200)
            frame.setVisible(true)
            frame
        }

        fx = new FrameFixture(robot, f())
        fx.show()
    }

    @Test def testTrivial {
        fx.button("Start").click
        gate.await
        fx.label("Result").requireText("224737")
    }
}