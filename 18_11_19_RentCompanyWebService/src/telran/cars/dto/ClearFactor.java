package telran.cars.dto;


import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClearFactor implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate currentDate;
    int days;

    public ClearFactor(LocalDate currentDate, int days) {
        this.currentDate = currentDate;
        this.days = days;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public int getDays() {
        return days;
    }
}
