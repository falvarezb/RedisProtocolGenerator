package fjab.redis

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}
import redis.embedded.RedisServer

class RedisJsonOperationsSpec extends FlatSpec with Matchers with BeforeAndAfterAll with ScalaFutures {

  val redisHost = "localhost"
  val redisPort = 6380
  val redisServer = new RedisServer(redisPort)

  override def beforeAll(): Unit = {
    redisServer.start()
  }

  override def afterAll(): Unit = {
    redisServer.stop()
  }

  "Json representation of Ticket" should "be inserted into Redis" in{

    val json = """
                 |{
                 |  "date": "2017-01-20",
                 |  "price": "120.2",
                 |  "passengers": [
                 |  {
                 |    "firstName": "Bill",
                 |    "lastName": "Jackson",
                 |    "seat": {
                 |        "coachNumber": 2,
                 |        "seatNumber": 10
                 |  }
                 |  },
                 |  {
                 |    "firstName": "Tom",
                 |    "lastName": "Selenium",
                 |    "seat": {
                 |        "coachNumber": 2,
                 |        "seatNumber": 12
                 |    }
                 |  }
                 |  ]
                 |
                 |}
               """.stripMargin

    RedisJsonOperations(redisHost, redisPort).insertJson("myjson", json).futureValue shouldBe true
  }

  "Json representation of Ticket" should "be retrieved from Redis" in{

    RedisJsonOperations(redisHost, redisPort).readJson("myjson").futureValue shouldBe Some(Ticket("2017-01-20","120.2",List(Passenger("Bill","Jackson",Seat(2,10)), Passenger("Tom","Selenium",Seat(2,12)))))
  }

}
