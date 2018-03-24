package main.scala.edu.knoldus.user.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import edu.knoldus.crud.models.User

import scala.collection.mutable.ListBuffer

object CrudlagomService {
  val TOPIC_NAME = "users"
}

trait CrudlagomService extends Service {

  def showUser(id: Int): ServiceCall[NotUsed, User]

  def addUser: ServiceCall[User, ListBuffer[User]]

  def updateUsername(id: Int, name: String): ServiceCall[NotUsed, ListBuffer[User]]

  def updateUserage(id: Int, age: Int): ServiceCall[NotUsed, ListBuffer[User]]

  def updateBoth(id: Int, name: String, age: Int): ServiceCall[NotUsed, ListBuffer[User]]

  def deleteUser(id: Int): ServiceCall[NotUsed, ListBuffer[User]]

  override final def descriptor: Descriptor = {

    import Service._
    named("crud-lagom")
      .withCalls(
        restCall(Method.POST, "/api/adduser", addUser _),
        restCall(Method.GET, "/api/showuser/:id", showUser _),
        restCall(Method.DELETE, "/api/deleteuser/:id", deleteUser _),
        restCall(Method.PUT, "/api/updateUsername/:id/:name", updateUsername _),
        restCall(Method.PUT, "/api/updateUserage/:id/:age", updateUserage _),
        restCall(Method.PUT, "/api/updateUserdetails/:id/:name/:age", updateBoth _)
      )
      .withAutoAcl(true)

  }
}




