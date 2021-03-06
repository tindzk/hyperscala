package org.hyperscala.examples.basic

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.ui.binder._
import org.hyperscala.ui.dynamic.{DynamicContent, DynamicString}
import org.hyperscala.web.Webpage
import org.powerscala.property.Property

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicContentExample extends Webpage with Example {
  body.contents += new tag.P {
    contents += "DynamicContent provides a mechanism to dynamically load and parse existing HTML content that can then be manipulated in Hyperscala before rendering to the browser."
  }

  val form = new SimpleDynamicForm
  body.contents += form
}

class SimpleDynamicForm extends DynamicContent(null) {
  def dynamicString = DynamicString.url("dynamic.html", SimpleDynamicForm.content)

  val person = Property[Person](default = Some(Person("John Doe", 123)))
  person.change.on {
    case evt => println("Person changed from %s to %s".format(evt.oldValue, evt.newValue))
  }

  val nameInput = bind[tag.Input, String]("i1", person, "name")
  val ageInput = bind[tag.Input, Int]("i2", person, "age")
  val button = load[tag.Button]("b1")
  button.clickEvent := RealtimeEvent()
  button.clickEvent.on {
    case evt => person := Person("Test User", 987)
  }

  button.contents.replaceWith("Do Something")
}

object SimpleDynamicForm {
  val content = getClass.getClassLoader.getResource("dynamic.html")
}

case class Person(name: String, age: Int)