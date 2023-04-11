package com.flightreservation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightreservation.dto.ReservationUpdateRequest;
import com.flightreservation.entity.Reservation;
import com.flightreservation.repository.ReservationRepository;

@RestController
public class ReservationRestController {
	
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	
	@RequestMapping("/reservation/{id}")
	public Reservation findReservation( @PathVariable("id") Long id) {
		Optional<Reservation> findById = reservationRepo.findById(id);
		Reservation reservation = findById.get();
		return reservation;
	}
	
	
	@POST
	@RequestMapping("/reservation")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Optional<Reservation> findById = reservationRepo.findById(request.getId());
		Reservation reservation = findById.get();
		reservation.setCheckedIn(request.isCheckedIn());
		reservation.setNumberOfBags(request.getNumberofBags());
		return reservationRepo.save(reservation);
		
	
	
}
}