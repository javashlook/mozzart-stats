package com.mozzartbet.stats.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mozzartbet.stats.domain.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, BigInteger> {

	List<Team> findByNameStartsWithIgnoreCase(String nameStartsWith);

}
