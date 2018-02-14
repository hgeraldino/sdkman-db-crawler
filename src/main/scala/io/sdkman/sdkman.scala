package io

import java.util.concurrent.Executors

import org.mongodb.scala.bson._

import scala.concurrent.ExecutionContext

package object sdkman {

  implicit val mongoExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  implicit def documentToVersion(doc: Document): Version =
    Version(
      field("candidate", doc),
      field("version", doc),
      field("platform", doc),
      field("url", doc))

  private def field(n: String, d: Document) = d.get[BsonString](n).map(_.asString.getValue).get
}