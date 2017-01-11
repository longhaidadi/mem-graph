package ict.ada.gdb.service;

import redis.clients.jedis.Jedis;

/**
 * Created by lon on 17-1-10.
 */
public class RedisInstance {
    private static Jedis jedis = null;

    static {
        jedis = new Jedis("127.0.0.1",6379);
    }
    private static RedisInstance ourInstance = new RedisInstance();

    public static RedisInstance getInstance() {
        return ourInstance;
    }

    private RedisInstance() {

    }

    public Jedis getService(){
        return jedis;
    }
}
