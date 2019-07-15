package work.idler.dtai.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试hello控制器
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/30
 * @Description: work.idler.dtai.web.controller
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

    @ApiOperation(value = "adminHello请求", notes = "无参")
    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Admin Hello";
    }

    @ApiOperation(value = "adminHello请求", notes = "无参")
    @GetMapping("/admin/hello/test")
    public String adminHelloTest() {
        return "Admin Hello Test 你 暴露了";
    }

//    @ApiOperation(value = "adminHello请求", notes = "无参")
//    @GetMapping("/admin/login")
//    public String adminLogin() {
//        return "Admin login 你 被流放了";
//    }

    @ApiOperation(value = "userHello请求", notes = "无参")
    @GetMapping("/user/hello")
    public String userHello() {
        return "User Hello";
    }

    @ApiOperation(value = "rootHello请求", notes = "无参")
    @GetMapping("/root/hello")
    public String rootHello() {
        return "Root Hello";
    }
}
