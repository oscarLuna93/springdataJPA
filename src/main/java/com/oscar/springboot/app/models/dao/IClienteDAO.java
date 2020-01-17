package com.oscar.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oscar.springboot.app.models.entity.Cliente;

public interface IClienteDAO extends PagingAndSortingRepository<Cliente, Long> {
	
}
