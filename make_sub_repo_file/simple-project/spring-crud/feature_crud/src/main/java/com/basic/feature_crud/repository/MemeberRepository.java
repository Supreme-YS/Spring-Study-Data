package com.basic.feature_crud.repository;

import com.basic.feature_crud.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeberRepository extends JpaRepository<Member, Long> {
}
