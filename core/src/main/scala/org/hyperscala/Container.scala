package org.hyperscala

import js.{Instruction, JavaScriptContext}
import org.sgine.scene.MutableContainer

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
trait Container extends MutableContainer[WebContent] with WebContent {
  override protected def children = contents.map{_.xml}

  override def beforeRender() {
    super.beforeRender()

    contents.foreach{_.beforeRender()}
  }

  override def afterRender() {
    super.afterRender()

    contents.foreach{_.afterRender()}
  }

  override val contents = new HTMLContents

  class HTMLContents extends VisibleContents {
    override def clear() {
      if (JavaScriptContext.inContext && reference != None) {
        val ref = reference.get
        Instruction(output = Some("while (%s.hasChildNodes()) { %s.removeChild(%s.lastChild); }\r\n".format(ref, ref, ref)))
      }
      super.clear()
    }

    override def apply(index: Int) = super.apply(index)

    override def length = super.length

    override def +=(child: WebContent) = {
      if (JavaScriptContext.inContext && reference != None) {
        val content = child.render.replaceAll("\n", """\\""" + "\n").replaceAll("'", """\\""" + "'")
        Instruction(output = Some("%s.innerHTML += %s;\r\n".format(reference.get, content)))
      }
      super.+=(child)
    }

    override def -=(child: WebContent) = {
      super.-=(child)
    }
  }
}