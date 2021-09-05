package com.bitproject.repository;

import com.bitproject.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material,Integer> {
}
