package work.idler.dtai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import work.idler.dtai.repository.TestRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtaiApplication.class)
@Transactional
@Rollback
public class DtaiApplicationTests {

    @Autowired
    TestRepository testRepository;

    @Test
    public void test() {
        for (int i=0; i<100; i++){
            work.idler.dtai.model.entity.Test test = new work.idler.dtai.model.entity.Test();
            test.setName("12"+i);
            testRepository.save(test);
        }
        //  测试分页
    }

    /**
     * 测试分页查询
     */
    @Test
    public void testPage() {

        // 获取查询结果
        Page<work.idler.dtai.model.entity.Test> pageInfo = testRepository.findAll(new PageRequest(1, 20));
        List<work.idler.dtai.model.entity.Test> tests = pageInfo.getContent();
        for (work.idler.dtai.model.entity.Test test : tests) {
            System.out.println(test.getName());
        }
    }
}
