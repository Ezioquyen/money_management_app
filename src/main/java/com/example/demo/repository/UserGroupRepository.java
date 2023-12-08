package com.example.demo.repository;

import com.example.demo.entity.UserGroup;
import com.example.demo.serializable.UserGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup,UserGroupId> {
}
