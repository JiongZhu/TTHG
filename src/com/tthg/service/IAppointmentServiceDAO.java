package com.tthg.service;

import java.util.List;

import com.tthg.entity.Appointment;

public interface IAppointmentServiceDAO {
	void add(Appointment appointment);
	void delete(int[] ids);
	void update(Appointment appointment);
	List<Appointment> searchAll();
	List<Appointment> searchAppointment(Appointment appointment);
}
