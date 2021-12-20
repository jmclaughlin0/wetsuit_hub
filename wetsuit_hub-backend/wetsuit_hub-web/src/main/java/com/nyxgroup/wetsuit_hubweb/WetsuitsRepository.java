package com.nyxgroup.wetsuit_hubweb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WetsuitsRepository extends JpaRepository<Wetsuit, UUID>{

}
