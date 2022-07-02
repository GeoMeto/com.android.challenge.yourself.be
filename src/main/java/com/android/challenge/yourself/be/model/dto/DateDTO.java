package com.android.challenge.yourself.be.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DateDTO implements Serializable {
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private java.util.Date date;

    public DateDTO(String date) throws ParseException {
        this.date = new SimpleDateFormat("dd-MM-yyyy").parse(date);
    }
}
