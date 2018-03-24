package edu.knoldus.crud.models

import play.api.libs.json.{Format, Json}

object User {
  implicit val format: Format[User] = Json.format[User]
}

case class User(userId: Int, userName: String, age: Int)