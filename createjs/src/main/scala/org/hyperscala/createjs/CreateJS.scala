package org.hyperscala.createjs

import com.outr.net.http.session.Session
import org.hyperscala.easeljs.EaselJS
import org.hyperscala.module.Module
import org.hyperscala.tweenjs.TweenJS
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.Version

/**
 * Matt Hicks <matt@outr.com>
 */
object CreateJS extends Module {
  val name = "createJS"
  val version = Version(0, 8, 0)

  override def dependencies = List(EaselJS, TweenJS)

  override def init(website: Website) = {}

  override def load(webpage: Webpage) = {}
}
