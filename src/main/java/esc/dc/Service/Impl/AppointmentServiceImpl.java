package esc.dc.Service.Impl;

import esc.dc.Model.Appointment;
import esc.dc.Repository.AppointmentRepository;
import esc.dc.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AppointmentServiceImpl implements AppointmentService {


    @Autowired
    private AppointmentRepository AppointmentRepository;


    @Override
    public List<Appointment> findAll() {
        return AppointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> findById(int id) {
        return AppointmentRepository.findById(id);
    }


    @Override
    public Appointment save(Appointment Appointment) {
        return AppointmentRepository.save(Appointment);
    }
}
