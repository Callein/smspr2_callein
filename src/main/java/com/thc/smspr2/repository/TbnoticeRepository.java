package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbnotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbnoticeRepository extends JpaRepository<Tbnotice, String> {
}
