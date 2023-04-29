package pt.med.wallet.persistence

import akka.actor.ActorSystem
import com.redis.RedisClient

class RedisConnection(implicit actorSystem: ActorSystem) {

  val redis = new RedisClient() // connect to Redis
}
