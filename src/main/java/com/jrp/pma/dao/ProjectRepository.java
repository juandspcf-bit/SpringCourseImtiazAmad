package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jrp.pma.dto.ChartData;
import com.jrp.pma.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{
	@Override
	public List<Project> findAll();
	@Query(nativeQuery=true, value="SELECT pro.stage as label, count(*) as value FROM PROJECT as pro " + 
			"group by(pro.stage)")
	public List<ChartData> getProjectStatus();
	
}
