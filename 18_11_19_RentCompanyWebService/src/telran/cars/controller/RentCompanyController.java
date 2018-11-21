package telran.cars.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static telran.cars.dto.RentCompanyConstants.*;

import telran.cars.dto.Car;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.ClearFactor;
import telran.cars.dto.DatesForRent;
import telran.cars.dto.DatesForReturn;
import telran.cars.dto.DatesFromTo;
import telran.cars.dto.Driver;
import telran.cars.dto.Model;
import telran.cars.dto.RentRecord;
import telran.cars.service.IRentCompany;
import telran.utils.Persistable;

@RestController
@ManagedResource
public class RentCompanyController {
    @Value("${fileName:data}")
    private String fileName;
    @Value("${finePercent:15}")
    private int finePercent;
    @Value("${gasPrice:10}")
    private int gasPrice;

    @PostConstruct
    void set() {
        setGasPrice(gasPrice);
        setFinePercent(finePercent);
    }
    
    @ManagedAttribute
    public int getFinePercent() {
        return finePercent;
    }

    @ManagedAttribute
    public void setFinePercent(int finePercent) {
        this.finePercent = finePercent;
        company.setFinePercent(finePercent);
    }

    @ManagedAttribute
    public int getGasPrice() {
        return gasPrice;
    }

    @ManagedAttribute
    public void setGasPrice(int gasPrice) {
        this.gasPrice = gasPrice;
        company.setGasPrice(gasPrice);
    }
	@Autowired
	IRentCompany company;

	
	@GetMapping(GET_ALL_CARS)
    public List<Car> getAllCars() {
        return company.getAllCars().collect(Collectors.toList());
    }

    @GetMapping(GET_ALL_DRIVERS)
    public List<Driver> getAllDrivers() {
        return company.getAllDrivers().collect(Collectors.toList());
    }

    @GetMapping(GET_ALL_MODELS)
    public List<String> getAllModelNames() {
        return company.getAllModelNames();
    }
	
    @PostMapping(ADD_MODEL)
    public CarsReturnCode addModel(@RequestBody Model model) {
        return company.addModel(model);
    }

    @PostMapping(ADD_CAR)
    public CarsReturnCode addCar(@RequestBody Car car) {
        return company.addCar(car);
    }

    @PostMapping(ADD_DRIVER)
    public CarsReturnCode addDriver(@RequestBody Driver driver) {
        return company.addDriver(driver);
    }

    @GetMapping(GET_MODEL + "/{modelName}")
    public Model getModel(@PathVariable("modelName") String modelName) {
        return company.getModel(modelName);
    }

    @GetMapping(GET_CAR + "/{carNumber}")
    public Car getCar(@PathVariable("carNumber") String carNumber) {
        return company.getCar(carNumber);
    }

    @GetMapping(GET_DRIVER + "/{licenseId}")
    public Driver getDriver(@PathVariable("licenseId") long licenseId) {
        return company.getDriver(licenseId);
    }

    @PostMapping(RENT_CAR)
    public CarsReturnCode rentCar(@RequestBody DatesForRent record) {
        return company.rentCar(record.getCarNumber(), record.getDriverId(), record.getRentDate(), record.getDays());
    }

    @PostMapping(RETURN_CAR)
    public CarsReturnCode returnCar(@RequestBody DatesForReturn record) {
        return company.returnCar(record.getCarNumber(), record.getReturnDate(), record.getGasTankPercent(), record.getDamages());
    }

    @PostMapping(CLEAR)
    public List<Car> clear(@RequestBody ClearFactor clearFactor) {
        return company.clear(clearFactor.getCurrentDate(), clearFactor.getDays());
    }

    @GetMapping(GET_CAR_DRIVERS + "/{carNumber}")
    public List<Driver> getCarDrivers(@PathVariable("carNumber") String carNumber) {
        return company.getCarDrivers(carNumber);
    }

    @GetMapping(GET_DRIVER_CARS + "/{licenseId}")
    public List<Car> getDriverCars(@PathVariable("licenseId") long licenseId) {
        return company.getDriverCars(licenseId);
    }

    @GetMapping(GET_ALL_RECORDS)
    public List<RentRecord> getAllRecords() {
        return company.getAllRecords().collect(Collectors.toList());
    }

    
    @GetMapping(GET_MOST_POPULAR_MODEL_NAMES)
    public List<String> getMostPopularModelNames() {
        return company.getMostPopularModelNames();
    }

    @GetMapping(GET_MODEL_PROFIT + "/{modelName}")
    public double getModelProfit(@PathVariable("modelName") String modelName) {
        return company.getModelProfit(modelName);
    }

    @GetMapping(GET_MOST_PROFITABLE_MODEL_NAMES)
    public List<String> getMostProfitModelNames() {
        return company.getMostProfitModelNames();
    }

    @GetMapping(SAVE)
    public void save() {
        if (company instanceof Persistable)
            ((Persistable) company).saveToFile(fileName);
    }

    @PostMapping(GET_RETURNED_RECORDS_BY_DATES)
    public List<RentRecord> getReturnedRecords(@RequestBody DatesFromTo datesFromTo) {
        return company.getReturnedRecordsByDates(datesFromTo.getFrom(), datesFromTo.getTo()).collect(Collectors.toList());
    }

    @DeleteMapping(REMOVE_CAR+"/{carNumber}")
    public CarsReturnCode removeCar(@PathVariable("carNumber") String carNumber) {
        return company.removeCar(carNumber);
    }

	
	
	
	
}