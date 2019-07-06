package work.idler.dtai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.idler.dtai.model.entity.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试控制器
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/29
 * @Description: work.idler.dtai.controller
 * @version: 1.0
 */
@RestController
public class TestController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/test1")
    public void test1() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("name", "iscolt");
        String name = operations.get("name");
        System.out.println(name);
        ValueOperations operations1 = redisTemplate.opsForValue();
        Test test = new Test();
        test.setName("abc");
        test.setId(1L);
        operations1.set("abc", test);
        Test test1 = (Test) operations1.get("abc");
        System.out.println(test1);
    }


}
