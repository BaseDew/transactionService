package org.acme.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acme.dto.CreateTransactionRequest;
import org.acme.service.TransactionService;

@Path("/transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class TransactionsController {
    private final TransactionService transactionService;


    @GET
    @Path("test")
    public Response testEndpoint() {
        return Response.ok("TransactionsController is working!").build();
    }

    @GET
    @Path("/{userId}")
    public Response getAllAccountTransactions(String userId) {
        return Response.ok(transactionService.getAllAccountTransactions(userId)).build();
    }

    @GET
    @Path("/userId/{transactionId}")
    public Response getTransactionById(String transactionId) {
        return Response.ok(transactionService.getTransactionById(transactionId)).build();
    }

    @POST
    public Response createTransactionAndDeductFunds(CreateTransactionRequest transactionRequest) {
        transactionService.createTransactionAndDeductFunds(transactionRequest);
        return Response.status(Response.Status.CREATED).build();
    }


}
