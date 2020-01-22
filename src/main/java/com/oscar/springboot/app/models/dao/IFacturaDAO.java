package com.oscar.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.oscar.springboot.app.models.entity.Factura;

public interface IFacturaDAO extends CrudRepository<Factura, Long>{

}
