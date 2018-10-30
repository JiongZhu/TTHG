package com.tthg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tthg.dao.IAppointmentDAO;
import com.tthg.entity.Appointment;
import com.tthg.service.IAppointmentServiceDAO;
@Service
public class AppointmentServiceDAO implements IAppointmentServiceDAO {
	@Autowired
	private IAppointmentDAO appointmentDAO;

	public IAppointmentDAO getAppointmentDAO() {
		return appointmentDAO;
	}

	public void setAppointmentDAO(IAppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}
	
	@Override
	public void add(Appointment appointment) {
		appointmentDAO.add(appointment);
	}

	@Override
	public void delete(int[] ids) {
		appointmentDAO.delete(ids);
	}

	@Override
	public void update(Appointment appointment) {
		appointmentDAO.update(appointment);
	}

	@Override
	public List<Appointment> searchAll() {
		return appointmentDAO.searchAll();
	}

	@Override
	public List<Appointment> searchAppointment(Appointment appointment) {
		return appointmentDAO.searchAppointment(appointment);
	}
}
