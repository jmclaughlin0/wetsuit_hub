package com.nyxgroup.wetsuit_hubweb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WetsuitsRepository extends JpaRepository<Wetsuit, UUID>{

}
