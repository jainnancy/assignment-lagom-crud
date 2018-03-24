package edu.knoldus.crud.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader, LagomServer}
import com.softwaremill.macwire.wire
import main.scala.edu.knoldus.user.api.CrudlagomService
import main.scala.edu.knoldus.user.impl.CrudlagomServiceImpl
import play.api.libs.ws.ahc.AhcWSComponents

class CrudlagomLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication = {
    new CrudlagomApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }
  }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new CrudlagomApplication(context) with LagomDevModeComponents

}

abstract class CrudlagomApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents{
  override lazy val lagomServer: LagomServer = serverFor[CrudlagomService](wire[CrudlagomServiceImpl])

}