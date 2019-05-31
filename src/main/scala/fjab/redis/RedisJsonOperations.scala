package fjab.redis

import akka.actor.ActorSystem
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods.parse
import redis.RedisClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RedisJsonOperations(val host: String, val port: Int, val pwd: Option[String]) {

  implicit val system = ActorSystem()

  def insertJson(key: String, value: String): Future[Boolean] = {
    lazy val redis: RedisClient = RedisClient(host, port, pwd)
    redis.set(key, value)
  }

  def readJson(key: String): Future[Option[Ticket]] = {
    implicit val formats = DefaultFormats
    lazy val redis: RedisClient = RedisClient(host, port, pwd)
    redis.get(key).map(_.map(x => parse(x.utf8String).extract[Ticket]))
  }
}

object RedisJsonOperations {
  def apply(host: String, port: Int, pwd: Option[String] = None): RedisJsonOperations = {
    new RedisJsonOperations(host, port, pwd)
  }
}
