package work.idler.dtai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work.idler.dtai.model.entity.Test;

/**
 * 测试持久层
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/29
 * @Description: work.idler.dtai.repository
 * @version: 1.0
 */
public interface TestRepository extends JpaRepository<Test, Long> {
}
