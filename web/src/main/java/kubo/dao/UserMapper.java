package kubo.dao;

import kubo.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User ,Integer> {
    /**
     * 根据字段查询
     * @param map
     * @return
     */
    List<User> selectByUserName(String username);
}