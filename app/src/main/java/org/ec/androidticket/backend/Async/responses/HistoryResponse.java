package org.ec.androidticket.backend.Async.responses;

import org.ec.androidticket.backend.models.ticketing.HistoryDiet;

import java.util.List;

public class HistoryResponse
{
    int code;
    String base;
    List<HistoryDiet> historique;
}
