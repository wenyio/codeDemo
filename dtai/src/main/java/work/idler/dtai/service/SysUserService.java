package work.idler.dtai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import work.idler.dtai.model.entity.SysUser;
import work.idler.dtai.repository.SysUserMapper;

/**
 * 系统用户业务逻辑
 *
 * @author iscolt
 * @date 2019/07/12
 */

@Service
public class SysUserService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.loadUserByUserName(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("账户不存在");
        }
        sysUser.setSysRoles(sysUserMapper.geUserRoleByUId(sysUser.getId()));
        return null;
    }
}
