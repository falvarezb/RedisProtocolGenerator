package fjab.redis

object RedisProtocolGenerator {

  /**
   * See https://redis.io/topics/mass-insert for definition of Redis protocol
   *
   *  *<args><cr><lf>
   *  $<len><cr><lf>
   *  <arg0><cr><lf>
   *  $<len><cr><lf>
   *  <arg1><cr><lf>
   *  ...
   *  $<len><cr><lf>
   *  <argN><cr><lf>
   *
   * @param cmd Redis command (e.g. SET mykey myvalue)
   * @return Redis Protocol representation of Redis command
   */
  def generateRedisProtocol(cmd: String): String = {

    val cmdList = cmd.split(" ").toList
    val lineBreak: String = "\r\n"

    cmdList.foldLeft(s"*${cmdList.length}${lineBreak}")((acc, item) => acc + s"$$${item.getBytes("UTF-8").length}${lineBreak}${item}${lineBreak}")
  }

}
