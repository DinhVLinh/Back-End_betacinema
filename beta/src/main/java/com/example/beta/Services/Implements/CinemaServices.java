package com.example.beta.Services.Implements;

import com.example.beta.Model.Cinema;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.CinemaRepository;
import com.example.beta.Services.Interfaces.ICinemaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class CinemaServices implements ICinemaServices {
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private ReponseObject cinemaReponse;

    @Override
    public ReponseObject<Cinema> addCinema(Cinema cinema) {
        Cinema newCinema = Cinema.builder()
                .nameOfCinema(cinema.getNameOfCinema())
                .rooms(cinema.getRooms())
                .address(cinema.getAddress())
                .code(cinema.getCode())
                .isActive(cinema.getIsActive())
                .description(cinema.getDescription())
                .build();
        cinemaRepository.save(newCinema);
        return cinemaReponse.reponseSuccess("them cinema thanh cong",cinema);
    }

    @Override
    public ReponseObject<Cinema> updateCinema( Cinema cinema) {
        var cinemaFix = cinemaRepository.findById(cinema.getId());

        if(cinemaFix.isEmpty()){
            return cinemaReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"khong tim thay cinema",null);
        }

        cinemaFix.get().setNameOfCinema(cinema.getNameOfCinema());
        cinemaFix.get().setCode(cinema.getCode());
        cinemaFix.get().setIsActive(cinema.getIsActive());
        cinemaFix.get().setAddress(cinema.getAddress());
        cinemaFix.get().setDescription(cinema.getDescription());
        cinemaFix.get().setIsActive(cinema.getIsActive());
        cinemaRepository.save(cinemaFix.get());
        return cinemaReponse.reponseSuccess("cap nhat cinema thanh cong",cinemaFix.get());
    }

    @Override
    public ReponseObject<String> deleteCinema(int id) {
        var cinema = cinemaRepository.findById(id);
        if (cinema.isEmpty()){
            return cinemaReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"khong tim thay cinema",null);

        }
        cinemaRepository.delete(cinema.get());
        return cinemaReponse.reponseSuccess("xoa cinema thanh cong",cinema.get());
    }

    @Override
    public ReponseObject<Cinema> getCinemaById(int id) {
        var cinema = cinemaRepository.findById(id);
        if(cinema.isEmpty()){
            return cinemaReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"cinema not found",null);
        }
        return cinemaReponse.reponseSuccess("get cinenma successfully",cinema.get());
    }


}
