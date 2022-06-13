package us.devfast.cheetah.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.devfast.cheetah.entity.RoleEntity;
import us.devfast.cheetah.repository.RoleRepository;
import us.devfast.cheetah.service.RoleService;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Get role id by role name
     *
     * @param roleName string role name : Customer || Admin || Supper-Admin
     * @return int id || 0
     */
    @Override
    public int getRoleIdByName(String roleName) {
        RoleEntity role = this.roleRepository.findRoleEntitiesByName(roleName);
        if (role != null)
            return role.getId();

        return 0;
    }
}
