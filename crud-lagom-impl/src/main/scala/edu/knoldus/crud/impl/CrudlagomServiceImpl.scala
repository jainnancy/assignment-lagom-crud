package main.scala.edu.knoldus.user.impl

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall
import edu.knoldus.crud.models.User
import main.scala.edu.knoldus.user.api.CrudlagomService

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

class CrudlagomServiceImpl extends CrudlagomService {

  val usersList: ListBuffer[User] = new ListBuffer[User]

  override def showUser(id: Int): ServiceCall[NotUsed, User] = ServiceCall { _ =>
    Future.successful(usersList
      .filter(user => user.userId == id)
      .head
    )
  }

  override def addUser: ServiceCall[User, ListBuffer[User]] = ServiceCall { request =>
    val user = User(request.userId,request.userName,request.age)
    usersList += user
    Future.successful(usersList)
  }

  override def deleteUser(id: Int): ServiceCall[NotUsed, ListBuffer[User]] = ServiceCall { _ =>
    val deleteuser = usersList.filter(user => user.userId == id)
    usersList --= deleteuser
    Future.successful(usersList)
  }

  override def updateUsername(id: Int, name: String): ServiceCall[NotUsed, ListBuffer[User]] = ServiceCall { _ =>
    val updateuser = usersList.filter(user => user.userId == id).head
    val updatedUser = User(id,name,updateuser.age)
    usersList -= updateuser
    usersList += updatedUser
    Future.successful(usersList)
  }

  override def updateUserage(id: Int, age: Int): ServiceCall[NotUsed, ListBuffer[User]] = ServiceCall { _ =>
    val updateuser = usersList.filter(user => user.userId == id).head
    val updatedUser = User(id, updateuser.userName, age)
    usersList -= updateuser
    usersList += updatedUser
    Future.successful(usersList)
  }

  override def updateBoth(id: Int, name: String, age: Int): ServiceCall[NotUsed, ListBuffer[User]] = {
    _ =>
      val updateuser = usersList.filter(user => user.userId == id).head
      val updatedUser = User(id, name, age)
      usersList -= updateuser
      usersList += updatedUser
      Future.successful(usersList)
  }
}
