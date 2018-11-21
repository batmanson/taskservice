package telran.cars.dto;


import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DatesForReturn implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String carNumber;
	@JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate returnDate;
    int gasTankPercent;
    int damages;

    public DatesForReturn(String carNumber, LocalDate returnDate, int gasTankPercent, int damages) {
        this.carNumber = carNumber;
        this.returnDate = returnDate;
        this.gasTankPercent = gasTankPercent;
        this.damages = damages;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getGasTankPercent() {
        return gasTankPercent;
    }

    public int getDamages() {
        return damages;
    }
}
