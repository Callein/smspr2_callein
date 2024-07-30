package com.thc.smspr2.repository;

import com.thc.smspr2.domain.Tbpostlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbpostlikeRepository extends JpaRepository<Tbpostlike, String> {
    Tbpostlike findByTbpostIdAndTbuserId(String tbpostId, String tbuserId);
    boolean existsByTbpostIdAndTbuserId(String tbpostId, String tbuserId);
}