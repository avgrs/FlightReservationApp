package com.flightreservation.service;

import com.flightreservation.dto.ReservationRequest;
import com.flightreservation.entity.Reservation;

public interface ReservationService {
	
	Reservation bookFlight(ReservationRequest request );

}
