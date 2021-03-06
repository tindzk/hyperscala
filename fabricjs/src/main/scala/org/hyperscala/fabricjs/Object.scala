package org.hyperscala.fabricjs

import org.hyperscala.fabricjs.event._
import org.hyperscala.fabricjs.paint.{GradientPaint, Paint}
import org.hyperscala.fabricjs.prop.ObjectProperty
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.javascript.dsl._
import org.powerscala.hierarchy.{Element, ChildLike}
import org.powerscala.{Color, Unique}
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class Object(val name: String) extends Listenable with Element[Listenable] {
  val id = Unique()

  private var _properties = List.empty[ObjectProperty[_]]
  private[fabricjs] var _events = List.empty[ObjectEventProcessor[_ <: ObjectEvent]]

  def properties = _properties

  lazy val angle = prop("angle", 0.0)
  lazy val backgroundColor = prop[Color]("backgroundColor", null)
  lazy val borderColor = prop[Color]("borderColor", Color.immutable(102, 153, 255, 0.75))
  lazy val borderOpacityWhenMoving = prop("borderOpacityWhenMoving", 0.4)
  lazy val borderScaleFactor = prop("borderScaleFactor", 1.0)
  lazy val centeredRotation = prop("centeredRotation", true)
  lazy val centeredScaling = prop("centeredScaling", false)
  lazy val clipTo = prop[JavaScriptContent]("clipTo", null)
  lazy val cornerColor = prop[Color]("cornerColor", Color.immutable(102, 153, 255, 0.5))
  lazy val cornerSize = prop("cornerSize", 12.0)
  lazy val evented = prop("evented", true)
  lazy val fill = prop[Paint]("fill", Color.Black)
  lazy val fillRule = prop("fillRule", "nonzero")
  lazy val flipX = prop("flipX", false)
  lazy val flipY = prop("flipY", false)
  lazy val globalCompositeOperation = prop("globalCompositeOperation", "source-over")
  lazy val hasBorders = prop("hasBorders", true)
  lazy val hasControls = prop("hasControls", true)
  lazy val hasRotationPoint = prop("hasRotationPoint", true)
  lazy val height = prop("height", 0.0)
  lazy val hoverCursor = prop[String]("hoverCursor", null)
  lazy val includeDefaultValues = prop("includeDefaultValues", true)
  lazy val left = prop("left", 0.0)
  lazy val lockMovementX = prop("lockMovementX", false)
  lazy val lockMovementY = prop("lockMovementY", false)
  lazy val lockRotation = prop("lockRotation", false)
  lazy val lockScalingFlip = prop("lockScalingFlip", false)
  lazy val lockScalingX = prop("lockScalingX", false)
  lazy val lockScalingY = prop("lockScalingY", false)
  lazy val lockUniScaling = prop("locakUniScaling", false)
  lazy val minScaleLimit = prop("minScaleLimit", 0.01)
  lazy val oCoords = prop[JavaScriptContent]("oCoords", null)
  lazy val opacity = prop("opacity", 1.0)
  lazy val originX = prop("originX", "left")
  lazy val originY = prop("originY", "top")
  lazy val padding = prop("padding", 0.0)
  lazy val perPixelTargetFind = prop("perPixelTargetFind", false)
  lazy val rotationPointOffset = prop("rotationPointOffset", 40.0)
  lazy val scaleX = prop("scaleX", 1.0)
  lazy val scaleY = prop("scaleY", 1.0)
  lazy val selectable = prop("selectable", true)
  lazy val shadow = prop[String]("shadow", null)
  lazy val stateProperties = prop[JavaScriptContent]("stateProperties", null)
  lazy val stroke = prop[Paint]("stroke", null)
  lazy val strokeDashArray = prop[JavaScriptContent]("strokeDashArray", null)
  lazy val strokeLineCap = prop[String]("strokeLineCap", "butt")
  lazy val strokeLineJoin = prop[String]("strokeLineJoin", "miter")
  lazy val strokeMiterLimit = prop("strokeMiterLimit", 10.0)
  lazy val strokeWidth = prop("strokeWidth", 1.0)
  lazy val top = prop("top", 0.0)
  lazy val transformMatrix = prop[JavaScriptContent]("transformMattrix", null)
  lazy val transparentCorners = prop("transparentCorners", true)
  lazy val visible = prop("visible", true)
  lazy val width = prop("width", 0.0)

  lazy val addedEvent = new AddedEventProcessor(this)
  lazy val removedEvent = new RemovedEventProcessor(this)
  lazy val selectedEvent = new SelectedEventProcessor(this)
  lazy val modifiedEvent = new ModifiedEventProcessor(this)
  lazy val rotatingEvent = new RotatingEventProcessor(this)
  lazy val scalingEvent = new ScalingEventProcessor(this)
  lazy val movingEvent = new MovingEventProcessor(this)
  lazy val mouseDownEvent = new MouseDownEventProcessor(this)
  lazy val mouseUpEvent = new MouseUpEventProcessor(this)

  def basic() = {
    hasBorders := false
    hasControls := false
    selectable := false
  }

  def canvas = root[StaticCanvas]

  protected[fabricjs] def addToCanvas(canvas: StaticCanvas, group: Option[Group]) = {
    group match {
      case Some(g) => canvas.eval(s"FabricJS.addToGroup('${g.id}', '$id', $construct);")
      case None => canvas.eval(s"FabricJS.add('${canvas.id}', '$id', $construct);")
    }
    val special = specialProps
    if (special.nonEmpty) {
      canvas.eval(special.mkString("\n"))
    }
    _events.foreach {
      case processor if processor.js() != null => {
        val handler =
          s"""function(options) {
             |  ${toJS(processor.name, processor.js())}
             |}""".stripMargin
        canvas.eval(s"FabricJS.objectEvent('$id', '${processor.name}', $handler);")
      }
      case _ => // Value must not be null
    }
    canvas.renderAll()
  }

  protected[fabricjs] def construct = s"new fabric.$name($props)"

  protected[fabricjs] def props = properties.map(p => p.get.map(v => p.name -> v)).flatten.collect {
    case (key, value) if value == null || !value.isInstanceOf[GradientPaint] => s"$key: ${toJS(key, value)}"
  }.mkString("{", ", ", "}")

  protected[fabricjs] def specialProps = properties.map(p => p.get.map(v => p.name -> v)).flatten.collect {
    case (key, value) if value.isInstanceOf[GradientPaint] => toJS(key, value)
  }

  protected[fabricjs] def toJS(key: String, value: Any) = value match {
    case paint: Paint => paint.toJS(this, key)
    case _ => JavaScriptContent.toJS(value)
  }

  protected def prop[T](name: String, default: T)(implicit manifest: Manifest[T]) = synchronized {
    val p = new ObjectProperty[T](name, this)(manifest)
    p := default
    _properties = p :: _properties
    p
  }
}