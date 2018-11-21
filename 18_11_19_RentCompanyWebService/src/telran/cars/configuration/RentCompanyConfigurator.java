package telran.cars.configuration;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import telran.cars.service.IRentCompany;
import telran.cars.service.RentCompanyEmbedded;
import telran.utils.Persistable;

@Configuration
@ManagedResource
public class RentCompanyConfigurator {
	
	
	
	@Value("${fileName:data}")
    private String fileName;

	@Autowired
	IRentCompany company;

    @Bean
    public IRentCompany company(){
        return RentCompanyEmbedded.restoreFromFile(fileName);
    }

	@PreDestroy
    public void saveToFile() {
        if (company instanceof Persistable)
            ((Persistable) company).saveToFile(fileName);
    }
	
	@ManagedAttribute
	public String getFileName() {
		return fileName;
	}
	
	@ManagedAttribute
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
	
    

    

    
    
}
