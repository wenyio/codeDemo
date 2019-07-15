package work.idler.dtai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement//开启事务管理
@MapperScan("work.idler.dtai.repository")//与dao层的@Mapper二选一写上即可(主要作用是扫包)
public class DtaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DtaiApplication.class, args);
    }

}
