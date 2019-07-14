package com.raju.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.raju.models.Fascilities;

@Repository
public interface FascilitiesRepository extends JpaRepository<Fascilities,Long> {
	@Query("Select t1 from Fascilities t1 inner join t1.employee t2 where t1.employeeId=?1")
	public List<Fascilities> getJoin(Long employeeId);
	
	@Query("Select t1.id,t1.fascilityName,t1.employeeId from Fascilities t1 inner join t1.employee t2 where t1.employeeId=?1")
	public List<Object []> getJoinOther(Long employeeId);
}
