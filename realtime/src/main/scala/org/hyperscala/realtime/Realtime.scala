package org.hyperscala.realtime

import com.outr.net.http.session.Session
import org.hyperscala.event.BrowserEvent
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.hyperscala.jquery.stylesheet.jQueryStyleSheet
import org.hyperscala.jquery.{jQuerySerializeForm, jQuery}
import org.hyperscala.module.Module
import org.hyperscala.realtime.event.browser.BrowserError
import org.hyperscala.web.useragent.UserAgent
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.web.module.IdentifyTags
import org.powerscala.Version
import org.hyperscala.html._
import org.powerscala.log.Logging
import org.powerscala.property.Property
import org.powerscala.json._

/**
 * Realtime is a module that connects real-time communication between client and server so changes to the server
 * propagate to the client and events (defined to do so) propagate to the server.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Realtime extends Module with Logging {
  val debugLogging = Property[Boolean](default = Some(false))
  val pingDelay = Property[Long](default = Some(60000))
  val updateFrequency = Property[Long](default = Some(10000))
  val reconnect = Property[Boolean](default = Some(true))
  val errorLogger = Property[BrowserErrorEvent => Unit](default = Some((evt: BrowserErrorEvent) => error(evt)))

  debugLogging.change.on {        // Make sure debug info gets spit out when debugLogging is enabled
    case evt => {
      logger.multiplier = if (evt.newValue) 2.0 else 1.0
    }
  }

  RealtimeJSON.init()

  def name = "realtime"

  def version = Version(1, 1)

  override def dependencies = List(jQuery, jQuerySerializeForm, jQueryStyleSheet, IdentifyTags)

  override def init(website: Website) = {
    website.register("/js/communicate.js", "communicate.js")
    website.register("/js/realtime.js", "realtime.js")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Meta(httpEquiv = "expires", content = "0")
    webpage.head.contents += new tag.Script(src = "/js/communicate.js")
    webpage.head.contents += new tag.Script(src = "/js/realtime.js")

    val js =
      s"""$$(document).ready(function() {
         |  realtime.init({
         |    debug: ${debugLogging()},
         |    reconnect: ${reconnect()},
         |    path: '${webpage.website.webSocketPath.get}',
         |    pingDelay: ${pingDelay()},
         |    updateFrequency: ${updateFrequency()},
         |    siteId: '${webpage.website.id}',
         |    pageId: '${webpage.pageId}'
         |  });
         |});""".stripMargin
    webpage.head.contents += new tag.Script(content = JavaScriptString(js))

    RealtimePage(webpage)
  }

  def send[Event <: AnyRef](event: Event): JavaScriptContent = JavaScriptString(s"realtime.send(${toJSON(event).compact.replaceAll("'", """\'""").replaceAll("\"", "'")});")
}

case class BrowserErrorEvent(webpage: Webpage, error: BrowserError) {
  override def toString = {
    val ua = UserAgent(webpage)
    s"Page: ${webpage.getClass.getName}, $ua, Remote: ${webpage.website.request.derivedRemoteHost} - $error"
  }
}