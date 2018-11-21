package telran.cars.dto;


import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DatesForRent implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String carNumber;
    long driverId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate rentDate;
    int days;

    public DatesForRent(String carNumber, long driverId, LocalDate rentDate, int days) {
        this.carNumber = carNumber;
        this.driverId = driverId;
        this.rentDate = rentDate;
        this.days = days;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public long getDriverId() {
        return driverId;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public int getDays() {
        return days;
    }
}
