package esc.dc.Service;

import esc.dc.Model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    List<Appointment> findAll();

    Optional<Appointment> findById(int id);

    Appointment save(Appointment appointment);
}
