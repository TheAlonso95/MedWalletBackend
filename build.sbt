name := "AuthService"
version := "0.1"
scalaVersion := "2.13.10"

//modules
lazy val container = project
  .in(file("./container"))
  .aggregate(domain, persistence, infrastructure, common)
  .settings(
    name := "container",
    libraryDependencies ++= Seq()
  )

lazy val common = project
  .in(file("./common"))
  .settings(
    name := "common",
    libraryDependencies ++= Seq()
  )

lazy val domain = project
  .in(file("./domain"))
  .aggregate(domain_core, domain_application, common)
  .settings(
    name := "domain",
    libraryDependencies ++= Seq()
  )
lazy val domain_core = project
  .in(file("./domain/core"))
  .dependsOn(common)
  .settings(
    name := "domain-core",
    libraryDependencies ++= Seq("com.github.pureconfig" %% "pureconfig" % "0.17.2")
  )

lazy val domain_application = project
  .in(file("./domain/application"))
  .dependsOn(domain_core, common)
  .settings(
    name := "domain-application",
    libraryDependencies ++= Seq()
  )

lazy val persistence = project
  .in(file("./persistence"))
  .dependsOn(domain_core, domain_application, common)
  .settings(
    name := "persistence",
    libraryDependencies ++= Seq()
  )

lazy val infrastructure = project
  .in(file("./infrastructure"))
  .dependsOn(domain_core, domain_application, common)
  .settings(
    name := "infrastructure",
    libraryDependencies ++= Seq()
  )