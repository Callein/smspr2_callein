package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbuser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TbuserRepository extends JpaRepository<Tbuser, String> {
    Tbuser findByUsername(String username);
    Tbuser findByUsernameAndPassword(String username, String password);
    /*
    // 로그인 기능 모두 구현하고 쓸것!! 나중에 확인합시다!!
    // 최초 조회 시 조인이 같이 하기위해 쓰는 어노테이션
    // 꼭 조인할때만 쓸것!
     */
    @EntityGraph(attributePaths = {"tbuserRoleType.roleType"})
    Optional<Tbuser> findEntityGraphRoleTypeById(String id);
}
