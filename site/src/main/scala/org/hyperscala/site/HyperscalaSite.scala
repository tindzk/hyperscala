package org.hyperscala.site

import com.outr.net.http.filter.PathFilter
import com.outr.net.http.jetty.JettyApplication
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.session.MapSession
import org.hyperscala.examples.Example
import org.hyperscala.examples.basic._
import org.hyperscala.examples.bootstrap.{BootstrapSignin, BootstrapTheme}
import org.hyperscala.examples.comparison.PlayHelloWorldPage
import org.hyperscala.examples.contenteditor.{ContentEditorExample, EditablePageExample}
import org.hyperscala.examples.createjs.CreateJSExample
import org.hyperscala.examples.fabricjs._
import org.hyperscala.examples.helloworld.HelloWorldPage
import org.hyperscala.examples.screen.{ScreenExample, SinglePageSiteExample}
import org.hyperscala.examples.svg.{BasicSVGExample, DynamicSVGExample, SVGShapesExample}
import org.hyperscala.examples.todomvc.TodoMVC
import org.hyperscala.examples.ui._
import org.hyperscala.examples.ux.SingleSelectListExample
import org.hyperscala.hello.HelloSite
import org.hyperscala.realtime.Realtime
import org.hyperscala.site.extra.HyperscalaGenerator
import org.hyperscala.web.{Scope, WebpageHandler, Website}
import org.powerscala.log.Logger

import scala.collection.mutable.ListBuffer

/**
 * @author Matt Hicks <matt@outr.com>
 */
object HyperscalaSite extends Website with JettyApplication {
  Realtime      // Make sure Realtime is initialized

  // Setup file logging
  Logger.Root.configureFileLogging("hyp")
  // Configure System.out and System.err to go to logger
  Logger.configureSystem()

  override type S = MapSession

  override def init() = {
    super.init()
    HelloSite.initialize()    // Make sure HelloSite is initialized
  }

  override def defaultPort = 8889

  val siteAbout = page(HyperscalaAbout, Scope.Application, "/about.html", "/")
  val siteExamples = page(HyperscalaExamples, Scope.Application, "/examples.html")
  val siteGenerator = page(new HyperscalaGenerator, Scope.Page, "/generator.html")
  val siteDocumentation = page(HyperscalaDocumentation, Scope.Application, "/documentation.html")

  // Basic
  val hello = example(new HelloWorldPage, "Basic", "Hello World", Scope.Request)
  val style = example(new StyleSheetExample, "Basic", "Style Sheet", Scope.Request)
  val large = example(new LargePageExample, "Basic", "Large Page", Scope.Request)
  val dynamicPage = example(new DynamicPageExample, "Basic", "Dynamic Page", Scope.Page)
  //    val numberGuess = page(new HyperscalaExample(new NumberGuess), Scope.Page, "/example/number_guess.html")
  val scoped = example(ScopedExample, "Basic", "Scoped", Scope.Page)
  val encodedImage = example(new EncodedImageExample, "Basic", "Encoded Image", Scope.Page)
  val connected = example(new ConnectedExample, "Basic", "Connected", Scope.Page)
  val static = example(new StaticHTMLExample, "Basic", "Static HTML", Scope.Request)
  val form = example(new FormExample, "Basic", "Form", Scope.Request)
  val dynamic = example(new DynamicContentExample, "Basic", "DynamicContent", Scope.Page)

  // Web
  val userAgent = example(new UserAgentExample, "Web", "UserAgent", Scope.Request)

  // Realtime
  val realTime = example(new RealtimeExample, "Realtime", "Realtime", Scope.Session)
  val realTimeDate = example(new RealtimeDateExample, "Realtime", "Realtime Date", Scope.Page)
  val realTimeWebpage = example(new RealtimeWebpageExample, "Realtime", "Realtime Webpage", Scope.Session)
  val realTimeForm = example(new RealtimeFormExample, "Realtime", "Realtime Form", Scope.Page)
  val chat = example(new ChatExample, "Realtime", "Chat", Scope.Page)
  val pageChange = example(new PageChangeWarningExample, "Realtime", "Page Change Warning", Scope.Page)
  val realtimeFrame = example(new RealtimeFrameExample, "Realtime", "RealtimeFrame", Scope.Page)

  // UI
  val visual = example(new VisualExample, "UI", "Visual", Scope.Session)
  val visualize = example(new VisualizeExample, "UI", "Visualize", Scope.Session)
  val visualizeAdvanced = example(new VisualizeAdvancedExample, "UI", "Visualize Advanced", Scope.Session)
  val autoComplete = example(new AutoCompleteExample, "UI", "AutoComplete", Scope.Page)
  val tabs = example(new TabsExample, "UI", "Tabs", Scope.Page)
  val tree = example(new TreeExample, "UI", "Tree", Scope.Page)
  val clipboard = example(new ClipboardExample, "UI", "Clipboard", Scope.Page)
  val multiSelect = example(new MultiSelectExample, "UI", "Multi Select", Scope.Page)
  val caseForm = example(new CaseFormExample, "UI", "Case Form", Scope.Page)
  val typedSelect = example(new TypedSelectExample, "UI", "Typed Select", Scope.Page)
  val gallery = example(new GalleryExample, "UI", "Gallery", Scope.Page)
  val modalComponent = example(new ModalComponentExample, "UI", "Modal Component", Scope.Page)
  val scriptLoader = example(new ScriptLoaderExample, "UI", "Script Loader", Scope.Page)
  val socialSharing = example(new SocialSharingExample, "UI", "Social Sharing", Scope.Page)
  val socialMetadata = example(new SocialMetadataExample, "UI", "Social Meta Data", Scope.Page)

  // Wrapper
  val nivoSlider = example(new NivoSliderExample, "Wrapper", "Nivo Slider", Scope.Page)
  val gritter = example(new GritterExample, "Wrapper", "Gritter", Scope.Page)
  val spectrum = example(new SpectrumExample, "Wrapper", "Spectrum", Scope.Page)
  val colorPicker = example(new ColorPickerExample, "Wrapper", "Color Picker", Scope.Page)
  val dialog = example(new DialogExample, "Wrapper", "Dialog", Scope.Page)
  val progressBar = example(new ProgressBarExample, "Wrapper", "Progress Bar", Scope.Page)
  val spinner = example(new SpinnerExample, "Wrapper", "Spinner", Scope.Page)
  val busyDialog = example(new BusyDialogExample, "Wrapper", "Busy Dialog", Scope.Page)
  val dropReceiver = example(new DropReceiverExample, "Wrapper", "Drop Receiver", Scope.Page)
  val confirmDialog = example(new ConfirmDialogExample, "Wrapper", "Confirm Dialog", Scope.Page)
  val select2 = example(new Select2Example, "Wrapper", "Select2", Scope.Page)
  val jQueryEvents = example(new jQueryEventsExample, "Wrapper", "jQueryEvents", Scope.Request)
  val jCanvas = example(new jCanvasExample, "Wrapper", "jCanvas", Scope.Page)
  val basketJS = example(new BasketJSExample, "Wrapper", "BasketJS", Scope.Page)
  val datePicker = example(new DatePickerExample, "Wrapper", "Date Picker", Scope.Page)
  val justifiedGallery = example(new JustifiedGalleryExample, "Wrapper", "Justified Gallery", Scope.Page)
  val dropzone = example(new DropzoneExample, "Wrapper", "Dropzone", Scope.Page)
  val videoJS = example(new VideoJSExample, "Wrapper", "VideoJS", Scope.Page)
  val webFontLoader = example(new WebFontLoaderExample, "Wrapper", "WebFontLoader", Scope.Page)
  val contentEditor = example(new ContentEditorExample(this), "Wrapper", "Content Editor", Scope.Page)
  val contentEditorContent = page(new EditablePageExample(null), Scope.Page, "/example/wrapper/content_editor_content.html")

  // Advanced
  val fileUploader = example(new FileUploaderExample, "Advanced", "File Uploader", Scope.Page)
  val dynamicURL = example(new DynamicURLExample, "Advanced", "Dynamic URL", Scope.Page)
  val history = example(new HistoryExample, "Advanced", "History", Scope.Page)
  val changeable = example(new ChangeableExample, "Advanced", "Changeable", Scope.Page)
  val compliance = example(new ComplianceExample, "Advanced", "Compliance", Scope.Page)
  val coordinates = example(new CoordinatesExample, "Advanced", "Coordinates", Scope.Page)
  val pageLoader = example(new PageLoaderExample, "Advanced", "Page Loader", Scope.Page)
  val screen = example(new ScreenExample, "Advanced", "Screen", Scope.Page)
  addHandler(screen, "/example/advanced/screen2.html")
  addHandler(screen, "/example/advanced/screen3.html")
  val singlePageSite = example(new SinglePageSiteExample, "Advanced", "Single Page Site", Scope.Page)
  addHandler(singlePageSite, "/example/advanced/login.html")
  addHandler(singlePageSite, "/example/advanced/authenticated1.html")
  addHandler(singlePageSite, "/example/advanced/authenticated2.html")
  addHandler(singlePageSite, "/example/advanced/authenticated3.html")

  // Module
  val externalStyle = example(new ExternalStyleExample, "Module", "External Style", Scope.Page)
  val headScript = example(new HeadScriptExample, "Module", "Head Script", Scope.Page)

  // SVG
  val basicSVG = example(new BasicSVGExample, "SVG", "BasicSVG", Scope.Page)
  val shapesSVG = example(new SVGShapesExample, "SVG", "SVGShapes", Scope.Page)
  val dynamicSVG = example(new DynamicSVGExample, "SVG", "DynamicSVG", Scope.Page)

  // Bootstrap
  val bootstrapSignIn = example(new BootstrapSignin, "Bootstrap", "Sign In", Scope.Page, minimalistic = true)
  val bootstrapTheme = example(new BootstrapTheme, "Bootstrap", "Theme", Scope.Page, minimalistic = true)

  // UX
  val singleSelectList = example(new SingleSelectListExample, "UX", "SingleSelectList", Scope.Page)

  val createJS = example(new CreateJSExample, "CreateJS", "EaselJS", Scope.Page)

  val fabricJS = example(new FabricJSExample, "FabricJS", "FabricJS", Scope.Page)
  val tileBoard = example(new TileBoardExample, "FabricJS", "TileBoard", Scope.Page)
  val fabricIntro1 = example(new FabricIntroPart1, "FabricJS", "Intro Part 1", Scope.Page)
  val fabricIntro2 = example(new FabricIntroPart2, "FabricJS", "Intro Part 2", Scope.Page)
  val fabricIntro3 = example(new FabricIntroPart3, "FabricJS", "Intro Part 3", Scope.Page)

  // Comparison
  val playComparison = example(new PlayHelloWorldPage, "Comparison", "Play - Hello World", Scope.Page)
  val todoMVC = example(new TodoMVC(HyperscalaSite.this), "Comparison", "TODO MVC", Scope.Session, minimalistic = true)

  handlers.add(PathFilter("/hello", HelloSite))

  addClassPath("/images/", "images/")
  addClassPath("/css/", "css/")
  addClassPath("/js/", "js/")

  register("/form.css", "form.css")   // For Bootstrap

  protected def createSession(request: HttpRequest, id: String) = new MapSession(id, this)

  def run() = main(Array.empty)

  private lazy val _examples = ListBuffer.empty[ExampleEntry]
  def examples = _examples.toList

  def example(creator: => Example, group: String, name: String, scope: Scope = Scope.Page, minimalistic: Boolean = false): WebpageHandler = {
    val filename = s"${group.toLowerCase}/${name.replaceAll(" ", "_").toLowerCase}.html"
    val path = s"/example/$filename"
    _examples += ExampleEntry(group, name, path)
    val pageCreator = () => {
      val e = creator
      if (!minimalistic) e.require(HyperscalaExample)
      e
    }
    page(pageCreator(), scope, path)
  }
}

case class ExampleEntry(group: String, name: String, path: String)