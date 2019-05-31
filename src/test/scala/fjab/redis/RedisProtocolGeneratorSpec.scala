package fjab.redis

import org.scalatest.{FlatSpec, Matchers}

class RedisProtocolGeneratorSpec extends FlatSpec with Matchers {

  val lineBreak: String = "\r\n"

  "Representation of command 'SET mykey myvalue' in Redis protocol" should "be '*3\r\n$3\r\nSET\r\n$5\r\nmykey\r\n$7\r\nmyvalue\r\n' " in {

    RedisProtocolGenerator.generateRedisProtocol("SET mykey myvalue") shouldBe s"*3${lineBreak}$$3${lineBreak}SET${lineBreak}$$5${lineBreak}mykey${lineBreak}$$7${lineBreak}myvalue${lineBreak}"
  }

  "Representation of command 'SET mykey2 Diabétique' in Redis protocol" should "be '*3\r\n$3\r\nSET\r\n$5\r\nmykey\r\n$11\r\nDiabétique\r\n' " in {

    RedisProtocolGenerator.generateRedisProtocol("SET mykey Diabétique") shouldBe s"*3${lineBreak}$$3${lineBreak}SET${lineBreak}$$5${lineBreak}mykey${lineBreak}$$11${lineBreak}Diabétique${lineBreak}"
  }
}
