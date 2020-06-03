package com.hollysys.tn.myspringboot;

import com.hollysys.tn.entity.User;
import com.hollysys.tn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class MySpringBootApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void find() {
        Map<String, Object> map = new HashMap<>();
        List<User> list = userService.find(map);
        System.out.println("list =====> " + list);
        System.out.println("createUser =====> " + list.get(0).getCreateUser());
    }

    @Test
    public void update() {
        User user = new User();
        user.setEmail("Mr.lusy@outlook.com");
        user.setId(1);
        user.setVersion(2);
        int i = userService.update(user);
        System.out.println(" ==========> " + i);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setCode("linHai");
        user.setName("林海");
        user.setEmail("linHai@hollysys.net");
        user.setNickName("333");
        user.setPassword("123456");
        user.setRegTime("202020");

        User result =  userService.insert(user);
        System.out.println("result ================> " + result);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void addRedis() throws Exception {
        //stringRedisTemplate.opsForValue().set("aaa", "111");
        //stringRedisTemplate.opsForValue().get("aaa");

        //stringRedisTemplate.opsForHash().put("ooo", "ff", "333");
        //stringRedisTemplate.opsForHash().get("ooo", "bb").toString();

        Set<Object> aa = stringRedisTemplate.opsForHash().keys("ooo");

        System.out.println( " redis =======> " + aa );
        //Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        User user = new User();
        user.setCode("2222");
        user.setName("4444");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("user", user);
//        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
//        Thread.sleep(1000);
//        boolean exists=redisTemplate.hasKey("com.neo.f");
//        if(exists){
//            System.out.println("exists is true");
//        }else{
//            System.out.println("exists is false");
//        }
    }

}
