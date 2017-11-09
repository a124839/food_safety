package com.ichinait.food.db.mapper.plus;

import java.util.List;

import com.ichinait.food.db.entity.plus.UserPlus;
import com.ichinait.food.dto.user.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapperPlus {
	List<UserPlus> selectUserListByConditions(UserDTO conditions);

}
