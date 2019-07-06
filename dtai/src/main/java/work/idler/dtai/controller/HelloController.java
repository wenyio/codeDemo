package work.idler.dtai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/30
 * @Description: work.idler.dtai.controller
 * @version: 1.0
 */
@Api(value = "测试")
@RestController
public class HelloController {

    @ApiOperation(value = "hello请求", notes = "无参")
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
