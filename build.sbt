name := "AuthService"
version := "0.1"
scalaVersion := "2.13.10"

//modules
lazy val container = project
  .in(file("./container"))
  .aggregate(domain, persistence, infrastructure)
  .settings(
    name := "container",
    libraryDependencies ++= Seq()
  )

lazy val domain = project
  .in(file("./domain"))
  .aggregate(domain_core, domain_application)
  .settings(
    name := "domain",
    libraryDependencies ++= Seq()
  )
lazy val domain_core = project
  .in(file("./domain/core"))
  .aggregate()
  .dependsOn()
  .settings(
    name := "domain-core",
    libraryDependencies ++= Seq()
  )

lazy val domain_application = project
  .in(file("./domain/application"))
  .dependsOn(domain_core)
  .settings(
    name := "domain-application",
    libraryDependencies ++= Seq()
  )

lazy val persistence = project
  .in(file("./persistence"))
  .dependsOn(domain_core, domain_application)
  .settings(
    name := "persistence",
    libraryDependencies ++= Seq()
  )

lazy val infrastructure = project
  .in(file("./infrastructure"))
  .dependsOn(domain_core, domain_application)
  .settings(
    name := "infrastructure",
    libraryDependencies ++= Seq()
  )