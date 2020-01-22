package com.oscar.springboot.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.oscar.springboot.app.models.dao.IFacturaDAO;
import com.oscar.springboot.app.models.entity.Factura;

public class FacturaServiceImpl implements IFacturaService{

	@Autowired
	private IFacturaDAO facturaDao;
	
	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}
	
}
