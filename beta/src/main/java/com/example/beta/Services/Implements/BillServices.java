package com.example.beta.Services.Implements;

import com.example.beta.Model.*;
import com.example.beta.Payload.Converter.BillConverter;
import com.example.beta.Payload.DTO.BillDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.*;
import com.example.beta.Services.Interfaces.IBillServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.Optional;

@Service
public class BillServices implements IBillServices {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ReponseObject billReponse;
    @Autowired
    private BillConverter billConverter;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private BillFoodRepository billFoodRepository;
    @Autowired
    private BillTicketRepository billTicketRepository;
    @Autowired
    private TicketsRepository ticketsRepository;


    @Override
    public ReponseObject<BillDto> addBill(Bill bill) {
        Optional<BillFood> billFood = billFoodRepository.findById(bill.getId());
        if (billFood.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill food not found", null);
        }

        Optional<Food> food = foodRepository.findById(billFood.get().getFoodId());
        if (food.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill food not found", null);
        }

        Optional<Promotions> promotions = promotionRepository.findById(bill.getPromotionId());
        if (promotions.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "promotions food not found", null);
        }

        Optional<BillTickets> billTickets = billTicketRepository.findById(bill.getId());
        if (billTickets.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill tickets not found", null);
        }

        Optional<Tickets> tickets = ticketsRepository.findById(billTickets.get().getTicketId());
        if (tickets.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "tickets not found", null);
        }


        Bill billNew = Bill.builder()
                .name(bill.getName())
                .createTime(bill.getCreateTime())
                .isActive(bill.getIsActive())
                .promotionId(bill.getPromotionId())
                .totalMoney((
                        (billFood.get().getQuantity() * food.get().getPrice())
                                + (billTickets.get().getQuantity() * tickets.get().getPriceTicket()))
                        * (promotions.get().getPercent() * promotions.get().getQuantity()))
                .updateTime(bill.getUpdateTime())
                .customerId(bill.getCustomerId())
                .tradingCode(bill.getTradingCode())
                .billStatusId(bill.getBillStatusId())
                .build();
        billRepository.save(billNew);
        return billReponse.reponseSuccess("add bill successfully", billConverter.billDto(bill));
    }

    @Override
    public ReponseObject<BillDto> updateBill(Bill bill) {
        Optional<BillFood> billFood = billFoodRepository.findById(bill.getId());
        if (billFood.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill food not found", null);
        }

        Optional<Food> food = foodRepository.findById(billFood.get().getFoodId());
        if (food.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill food not found", null);
        }

        Optional<Promotions> promotions = promotionRepository.findById(bill.getPromotionId());
        if (promotions.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "promotions  not found", null);
        }

        var billUpdate = billRepository.findById(bill.getId());

        if (billUpdate.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill not found", null);
        }


        billUpdate.get().setCreateTime(bill.getCreateTime());
        billUpdate.get().setUpdateTime(bill.getUpdateTime());
        billUpdate.get().setCustomerId(bill.getCustomerId());
        billUpdate.get().setIsActive(bill.getIsActive());
        billUpdate.get().setName(bill.getName());
        billUpdate.get().setTotalMoney((billFood.get().getQuantity() * food.get().getPrice()) * (promotions.get().getPercent() * promotions.get().getQuantity()));
        billUpdate.get().setTradingCode(bill.getTradingCode());
        billUpdate.get().setPromotionId(bill.getPromotionId());
        billUpdate.get().setBillStatusId(bill.getBillStatusId());

        billRepository.save(billUpdate.get());
        return billReponse.reponseSuccess("update bill successfully", billConverter.billDto(billUpdate.get()));
    }

    @Override
    public ReponseObject<BillDto> deleteBill(int id) {
        var bill = billRepository.findById(id);
        if (bill.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill not found", null);
        }
        billRepository.delete(bill.get());
        return billReponse.reponseSuccess("delete bill successfully", billConverter.billDto(bill.get()));
    }

    @Override
    public ReponseObject<BillDto> getBillById(int id) {
        var bill = billRepository.findById(id);
        if (bill.isEmpty()) {
            return billReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST, "bill not found", null);
        }
        billRepository.delete(bill.get());
        return billReponse.reponseSuccess("get bill successfully", billConverter.billDto(bill.get()));
    }
}
