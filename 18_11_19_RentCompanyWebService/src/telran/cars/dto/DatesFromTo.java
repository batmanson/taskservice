package telran.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class DatesFromTo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate from;
    LocalDate to;

    public DatesFromTo(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public DatesFromTo() {
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
}
