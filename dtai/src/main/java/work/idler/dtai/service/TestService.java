package work.idler.dtai.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试业务逻辑层
 *
 * @author iscolt
 * @date 2019/07/11
 */
@Service
public class TestService {

//    @Secured("ROLE_ADMIN")
//    public String admin() {
//        return "hello admin";
//    }
//
//    @PreAuthorize("hasRole('ADMIN') and hasRole('DBA')")
//    public String dba() {
//        return "hello dba";
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN', 'DBA', 'USER')")
//    public String user() {
//        return "You are so ordinary";
//    }
}
