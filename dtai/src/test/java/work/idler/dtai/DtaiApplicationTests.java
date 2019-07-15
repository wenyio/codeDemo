package work.idler.dtai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import work.idler.dtai.model.entity.SysUser;
import work.idler.dtai.repository.SysRoleMapper;
import work.idler.dtai.repository.SysUserMapper;
import work.idler.dtai.repository.SysUserRoleMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtaiApplication.class)
//@Transactional
//@Rollback
@MapperScan("work.idler.dtai.repository")
public class DtaiApplicationTests {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Test
    public void insertUser() {
        SysUser user = new SysUser();
        user.setUsername("user");
        user.setPassword(new BCryptPasswordEncoder(10).encode("5211"));
        user.setEnabled(true);
        user.setLocked(false);
        sysUserMapper.insert(user);
    }

}
