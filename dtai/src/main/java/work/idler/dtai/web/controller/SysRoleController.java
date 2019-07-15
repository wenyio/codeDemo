package work.idler.dtai.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.idler.dtai.model.entity.SysRole;
import work.idler.dtai.repository.SysRoleMapper;

import java.util.List;

/**
 * This is Description
 *
 * @author iscolt
 * @date 2019/07/12
 */
@RestController
public class SysRoleController {

    @Autowired
    private SysRoleMapper roleMapper;

    @GetMapping("roles")
    public void getALl() {
        List<SysRole> sysRoles = roleMapper.selectAll();
        System.out.println(sysRoles);
    }
}

