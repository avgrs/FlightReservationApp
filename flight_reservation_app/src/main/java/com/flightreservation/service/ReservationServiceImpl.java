package com.flightreservation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightreservation.dto.ReservationRequest;
import com.flightreservation.entity.Flight;
import com.flightreservation.entity.Passenger;
import com.flightreservation.entity.Reservation;
import com.flightreservation.repository.FlightRepository;
import com.flightreservation.repository.PassengerRepository;
import com.flightreservation.repository.ReservationRepository;
import com.flightreservation.utilities.PDFgenerator;


@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	
	@Autowired
	private FlightRepository flightRepo;
	
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	
	@Autowired
	private PDFgenerator pdfGenerator;


	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		
		
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		passengerRepo.save(passenger);
		
		
		long flightId = request.getFlightId();
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(0);
		
		String filePath = "D:\\Spring Boot\\flight_reservation_app\\tickets" +reservation.getId() +".pdf";
		
		Reservation savedreservation = reservationRepo.save(reservation);
		
		
		pdfGenerator.generateItinerary(reservation, filePath);
		
		return savedreservation;
	}

}
