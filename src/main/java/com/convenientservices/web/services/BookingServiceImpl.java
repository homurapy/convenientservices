package com.convenientservices.web.services;

import com.convenientservices.web.entities.Booking;
import com.convenientservices.web.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private BookingRepository repository;

    @Override
    public Booking findById (Long id) throws Exception {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Booking> findAll () {
        return repository.findAll();
    }

    @Override
    public Booking save (Booking booking) {
        return repository.save(booking);
    }

    @Override
    public List<Booking> getGoodBookings(Principal principal) {
        return this.findAllByUserName(principal.getName()).stream()
                .filter(booking -> booking.getDt().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Booking::getDt))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById (Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Booking> getOldBookings(Principal principal) {
        return this.findAllByUserName(principal.getName()).stream()
                .filter(booking -> booking.getDt().isBefore(LocalDateTime.now()))
                .sorted(Comparator.comparing(Booking::getDt))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllByUserName(String name) {
        return repository.findAllByUserUserName(name);
    }

    @Override
    public List<Booking> findAllByPosId (Long id) {
        return findAll().stream().filter(booking -> booking.getPointOfServices().getId().equals(id)).sorted((o1, o2) -> o1.getDt().compareTo(o2.getDt())).collect(Collectors.toList());
    }
}
