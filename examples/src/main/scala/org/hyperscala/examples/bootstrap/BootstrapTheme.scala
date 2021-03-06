package org.hyperscala.examples.bootstrap

import org.hyperscala.bootstrap.Bootstrap
import org.hyperscala.bootstrap.component._
import org.hyperscala.css.attributes.{Display, Position}
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.selector.Selector
import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BootstrapTheme extends Webpage with Example {
  require(Bootstrap)

  body.role := "document"

  val bodyStyle = head.selector(Selector.element[tag.Body])
  bodyStyle.paddingTop := 70.px
  bodyStyle.paddingBottom := 30.px

  val dropdownMenuStyle = head.selector(Selector(".theme-dropdown .dropdown-menu"))
  dropdownMenuStyle.position := Position.Static
  dropdownMenuStyle.display := Display.Block
  dropdownMenuStyle.marginBottom := 20.px

  val showcaseStyle = head.selector(Selector(".theme-showcase > p > .btn"))
  showcaseStyle.margin := "5px 0"

  body.contents += new NavBar(brand = Some("Bootstrap theme"), theme = NavBarTheme.Dark) {
    addLink("#", "Home", active = true)
    addLink("#", "About")
    addLink("#", "Contact")
    val dropdown = addDropdown("Dropdown")
    dropdown.addLink("#", "Action")
    dropdown.addLink("#", "Another Action")
    dropdown.addLink("#", "Something else here")
    dropdown.addDivider()
    dropdown.addHeader("Nav header")
    dropdown.addLink("#", "Separated link")
    dropdown.addLink("#", "One more separated link")
  }

  body.contents += new Container {
    clazz += "theme-showcase"
    role := "main"

    contents += new Jumbotron {
      contents += new tag.H1 {
        contents += "Hello, world!"
      }
      contents += new tag.P {
        contents += "This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique."
      }
      contents += new tag.P {
        contents += new Button("Learn more »", buttonStyle = ButtonStyle.Primary, buttonSize = ButtonSize.Large)
      }
    }

    contents += new Header("Buttons")
    contents += new tag.P {
      contents += new Button("Default", buttonStyle = ButtonStyle.Default, buttonSize = ButtonSize.Large)
      contents += new Button("Primary", buttonStyle = ButtonStyle.Primary, buttonSize = ButtonSize.Large)
      contents += new Button("Success", buttonStyle = ButtonStyle.Success, buttonSize = ButtonSize.Large)
      contents += new Button("Info", buttonStyle = ButtonStyle.Info, buttonSize = ButtonSize.Large)
      contents += new Button("Warning", buttonStyle = ButtonStyle.Warning, buttonSize = ButtonSize.Large)
      contents += new Button("Danger", buttonStyle = ButtonStyle.Danger, buttonSize = ButtonSize.Large)
      contents += new Button("Link", buttonStyle = ButtonStyle.Link, buttonSize = ButtonSize.Large)
    }
    contents += new tag.P {
      contents += new Button("Default", buttonStyle = ButtonStyle.Default, buttonSize = ButtonSize.Default)
      contents += new Button("Primary", buttonStyle = ButtonStyle.Primary, buttonSize = ButtonSize.Default)
      contents += new Button("Success", buttonStyle = ButtonStyle.Success, buttonSize = ButtonSize.Default)
      contents += new Button("Info", buttonStyle = ButtonStyle.Info, buttonSize = ButtonSize.Default)
      contents += new Button("Warning", buttonStyle = ButtonStyle.Warning, buttonSize = ButtonSize.Default)
      contents += new Button("Danger", buttonStyle = ButtonStyle.Danger, buttonSize = ButtonSize.Default)
      contents += new Button("Link", buttonStyle = ButtonStyle.Link, buttonSize = ButtonSize.Default)
    }
    contents += new tag.P {
      contents += new Button("Default", buttonStyle = ButtonStyle.Default, buttonSize = ButtonSize.Small)
      contents += new Button("Primary", buttonStyle = ButtonStyle.Primary, buttonSize = ButtonSize.Small)
      contents += new Button("Success", buttonStyle = ButtonStyle.Success, buttonSize = ButtonSize.Small)
      contents += new Button("Info", buttonStyle = ButtonStyle.Info, buttonSize = ButtonSize.Small)
      contents += new Button("Warning", buttonStyle = ButtonStyle.Warning, buttonSize = ButtonSize.Small)
      contents += new Button("Danger", buttonStyle = ButtonStyle.Danger, buttonSize = ButtonSize.Small)
      contents += new Button("Link", buttonStyle = ButtonStyle.Link, buttonSize = ButtonSize.Small)
    }
    contents += new tag.P {
      contents += new Button("Default", buttonStyle = ButtonStyle.Default, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Primary", buttonStyle = ButtonStyle.Primary, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Success", buttonStyle = ButtonStyle.Success, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Info", buttonStyle = ButtonStyle.Info, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Warning", buttonStyle = ButtonStyle.Warning, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Danger", buttonStyle = ButtonStyle.Danger, buttonSize = ButtonSize.ExtraSmall)
      contents += new Button("Link", buttonStyle = ButtonStyle.Link, buttonSize = ButtonSize.ExtraSmall)
    }

    contents += new Header("Tables")
    contents += new Row {
      contents += new Column {
        medium := Some(6)

        contents += new Table {
          contents += new TableRow {
            contents += new tag.Th(content = "#")
            contents += new tag.Th(content = "First Name")
            contents += new tag.Th(content = "Last Name")
            contents += new tag.Th(content = "Username")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "1")
            contents += new tag.Td(content = "Mark")
            contents += new tag.Td(content = "Otto")
            contents += new tag.Td(content = "@mdo")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "2")
            contents += new tag.Td(content = "Jacob")
            contents += new tag.Td(content = "Thornton")
            contents += new tag.Td(content = "@fat")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "3")
            contents += new tag.Td(content = "Larry")
            contents += new tag.Td(content = "the Bird")
            contents += new tag.Td(content = "@twitter")
          }
        }
      }
      contents += new Column {
        medium := Some(6)

        contents += new Table {
          striped := true

          contents += new TableRow {
            contents += new tag.Th(content = "#")
            contents += new tag.Th(content = "First Name")
            contents += new tag.Th(content = "Last Name")
            contents += new tag.Th(content = "Username")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "1")
            contents += new tag.Td(content = "Mark")
            contents += new tag.Td(content = "Otto")
            contents += new tag.Td(content = "@mdo")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "2")
            contents += new tag.Td(content = "Jacob")
            contents += new tag.Td(content = "Thornton")
            contents += new tag.Td(content = "@fat")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "3")
            contents += new tag.Td(content = "Larry")
            contents += new tag.Td(content = "the Bird")
            contents += new tag.Td(content = "@twitter")
          }
        }
      }
    }
    contents += new Row {
      contents += new Column {
        medium := Some(6)

        contents += new Table {
          bordered := true

          contents += new TableRow {
            contents += new tag.Th(content = "#")
            contents += new tag.Th(content = "First Name")
            contents += new tag.Th(content = "Last Name")
            contents += new tag.Th(content = "Username")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "1", rowSpan = 2)
            contents += new tag.Td(content = "Mark")
            contents += new tag.Td(content = "Otto")
            contents += new tag.Td(content = "@mdo")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "Mark")
            contents += new tag.Td(content = "Otto")
            contents += new tag.Td(content = "@mdo")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "2")
            contents += new tag.Td(content = "Jacob")
            contents += new tag.Td(content = "Thornton")
            contents += new tag.Td(content = "@fat")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "3")
            contents += new tag.Td(content = "Larry the Bird", colSpan = 2)
            contents += new tag.Td(content = "@twitter")
          }
        }
      }
      contents += new Column {
        medium := Some(6)

        contents += new Table {
          condensed := true

          contents += new TableRow {
            contents += new tag.Th(content = "#")
            contents += new tag.Th(content = "First Name")
            contents += new tag.Th(content = "Last Name")
            contents += new tag.Th(content = "Username")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "1")
            contents += new tag.Td(content = "Mark")
            contents += new tag.Td(content = "Otto")
            contents += new tag.Td(content = "@mdo")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "2")
            contents += new tag.Td(content = "Jacob")
            contents += new tag.Td(content = "Thornton")
            contents += new tag.Td(content = "@fat")
          }
          contents += new TableRow {
            contents += new tag.Td(content = "3")
            contents += new tag.Td(content = "Larry the Bird", colSpan = 2)
            contents += new tag.Td(content = "@twitter")
          }
        }
      }
    }

    contents += new Header("Thumbnails")
    contents += new HolderImage(200, 200, "A generic square placeholder image with a white border around it, making it resemble a photograph taken with an old instant camera")

    contents += new Header("Labels")
    contents += new tag.H1 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.H2 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.H3 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.H4 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.H5 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.H6 {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }
    contents += new tag.P {
      contents += new Label(LabelStyle.Default, "Default")
      contents += new Label(LabelStyle.Primary, "Primary")
      contents += new Label(LabelStyle.Success, "Success")
      contents += new Label(LabelStyle.Info, "Info")
      contents += new Label(LabelStyle.Warning, "Warning")
      contents += new Label(LabelStyle.Danger, "Danger")
    }

    contents += new Header("Badges")
    contents += new tag.P {
      contents += new tag.A(href = "#") {
        contents += "Inbox"
        contents += new Badge("42")
      }
    }
    contents += new Tabs {
      tabs := false
      pills := true

      contents += new TabEntry {
        active := true

        contents += new tag.A(href = "#") {
          contents += "Home"
          contents += new Badge("42")
        }
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#") {
          contents += "Profile"
        }
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#") {
          contents += "Messages"
          contents += new Badge("3")
        }
      }
    }

    contents += new Header("Dropdown menus")
    contents += new tag.Div(clazz = List("dropdown", "theme-dropdown", "clearfix")) {
      contents += new tag.A(id = "dropdownMenu1", href = "#", role = "button", clazz = List("sr-only", "dropdown-toggle")) {
        data("toggle", "dropdown")
        contents += "Dropdown"

        contents += new tag.B(clazz = List("caret"))
      }
      contents += new tag.Ul(clazz = List("dropdown-menu"), role = "menu") {
        ariaLabelledBy := "dropdownMenu1"
        contents += new tag.Li(clazz = List("active"), role = "presentation") {
          contents += new tag.A(role = "menuitem", href = "#") {
            contents += "Action"
          }
        }
        contents += new tag.Li(role = "presentation") {
          contents += new tag.A(role = "menuitem", href = "#") {
            contents += "Another action"
          }
        }
        contents += new tag.Li(role = "presentation") {
          contents += new tag.A(role = "menuitem", href = "#") {
            contents += "Something else here"
          }
        }
        contents += new tag.Li(role = "presentation", clazz = List("divider"))
        contents += new tag.Li(role = "presentation") {
          contents += new tag.A(role = "menuitem", href = "#") {
            contents += "Separated link"
          }
        }
      }
    }

    contents += new Header("Navs")
    contents += new Tabs {
      role := "tablist"

      contents += new TabEntry {
        active := true

        contents += new tag.A(href = "#", content = "Home")
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#", content = "Profile")
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#", content = "Messages")
      }
    }
    contents += new Tabs {
      tabs := false
      pills := true

      contents += new TabEntry {
        active := true

        contents += new tag.A(href = "#", content = "Home")
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#", content = "Profile")
      }
      contents += new TabEntry {
        contents += new tag.A(href = "#", content = "Messages")
      }
    }

    contents += new Header("Navbars")
    contents += new NavBar(brand = Some("Project name"), top = false) {
      addLink("#", "Home", active = true)
      addLink("#", "About")
      addLink("#", "Contact")
      val dropdown = addDropdown("Dropdown")
      dropdown.addLink("#", "Action")
      dropdown.addLink("#", "Another Action")
      dropdown.addLink("#", "Something else here")
      dropdown.addDivider()
      dropdown.addHeader("Nav header")
      dropdown.addLink("#", "Separated link")
      dropdown.addLink("#", "One more separated link")
    }
    contents += new NavBar(brand = Some("Project name"), theme = NavBarTheme.Dark, top = false) {
      addLink("#", "Home", active = true)
      addLink("#", "About")
      addLink("#", "Contact")
      val dropdown = addDropdown("Dropdown")
      dropdown.addLink("#", "Action")
      dropdown.addLink("#", "Another Action")
      dropdown.addLink("#", "Something else here")
      dropdown.addDivider()
      dropdown.addHeader("Nav header")
      dropdown.addLink("#", "Separated link")
      dropdown.addLink("#", "One more separated link")
    }

    contents += new Header("Alerts")
    contents += Alert.success("Well done!", "You successfully read this important alert message.")
    contents += Alert.info("Heads up!", "This alert needs your attention, but it's not super important.")
    contents += Alert.warning("Warning!", "Best check yo self, you're not looking too good.")
    contents += Alert.danger("Oh snap!", "Change a few things up and try submitting again.")

    contents += new Header("Progress bars")
    contents += ProgressBar(0.6, ProgressType.Default)
    contents += ProgressBar(0.4, ProgressType.Success)
    contents += ProgressBar(0.2, ProgressType.Info)
    contents += ProgressBar(0.6, ProgressType.Warning)
    contents += ProgressBar(0.8, ProgressType.Danger)
    contents += ProgressBar(0.6, ProgressType.Default, striped = true)
    contents += new ProgressBar {
      addProgress(0.35, ProgressType.Success)
      addProgress(0.2, ProgressType.Warning)
      addProgress(0.1, ProgressType.Danger)
    }

    contents += new Header("List groups")
    contents += new Row {
      contents += new Column(small = Some(4)) {
        contents += new ListGroup {
          addText("Cras justo odio")
          addText("Dapibus ac facilisis in")
          addText("Morbi leo risus")
          addText("Porta ac consectetur ac")
          addText("Vestibulum at eros")
        }
      }
      contents += new Column(small = Some(4)) {
        contents += new ListGroup {
          addLink("#", "Cras justo odio", active = true)
          addLink("#", "Dapibus ac facilisis in")
          addLink("#", "Morbi leo risus")
          addLink("#", "Porta ac consectetur ac")
          addLink("#", "Vestibulum at eros")
        }
      }
      contents += new Column(small = Some(4)) {
        contents += new ListGroup {
          add("#", "List group item heading", "Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.", active = true)
          add("#", "List group item heading", "Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.")
          add("#", "List group item heading", "Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.")
        }
      }
    }

    contents += new Header("Panels")
    contents += new Row {
      contents += new Column(small = Some(4)) {
        contents += new Panel("Panel title", Some("Panel content"), PanelType.Default)
        contents += new Panel("Panel title", Some("Panel content"), PanelType.Primary)
      }
      contents += new Column(small = Some(4)) {
        contents += new Panel("Panel title", Some("Panel content"), PanelType.Success)
        contents += new Panel("Panel title", Some("Panel content"), PanelType.Info)
      }
      contents += new Column(small = Some(4)) {
        contents += new Panel("Panel title", Some("Panel content"), PanelType.Warning)
        contents += new Panel("Panel title", Some("Panel content"), PanelType.Danger)
      }
    }

    contents += new Header("Wells")
    contents += new Well {
      contents += new tag.P {
        contents += "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sed diam eget risus varius blandit sit amet non magna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Cras mattis consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Aenean lacinia bibendum nulla sed consectetur."
      }
    }
  }
}