package mcgill.ecse321.grocerystore.service;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import javax.transaction.Transactional;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessHourService {
    @Autowired
    BusinessHourRepository businessHourRepository;
    

    //BusinessHour
    @Transactional
    public BusinessHour createBusinessHourforEmployee(WeekDay day, Time startTime, Time endTime, boolean working, Employee employee){
        if (day == null){
            throw new IllegalArgumentException("Week day cannot be empty");
        }
        if (startTime == null){
            throw new IllegalArgumentException("Start time cannot be empty");
        }
        if (endTime == null){
            throw new IllegalArgumentException("End time cannot be empty");
        }
        if (startTime.toLocalTime().isAfter(endTime.toLocalTime())){
            throw new IllegalArgumentException("End time cannot be earlier than Start time");
        }
        if (startTime.toLocalTime().equals(endTime.toLocalTime())){
            throw new IllegalArgumentException("End time cannot be the same as Start time");
        }
        if(employee==null) {
        	throw new IllegalArgumentException("Employee cannot be empty");
        }
        BusinessHour businessHour = new BusinessHour();
        businessHour.setDay(day);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHour.setWorking(working);
        businessHour.setEmployee(employee);
        businessHourRepository.save(businessHour);
        return businessHour;
    }

    @Transactional
    public BusinessHour createBusinessHourforGroceryStoreSystem(WeekDay day, Time startTime, Time endTime, boolean working, GroceryStoreSystem groceryStoreSystem){
        if (day == null){
            throw new IllegalArgumentException("Week day cannot be empty");
        }
        if (startTime == null){
            throw new IllegalArgumentException("Start time cannot be empty");
        }
        if (endTime == null){
            throw new IllegalArgumentException("End time cannot be empty");
        }
        if (startTime.toLocalTime().isAfter(endTime.toLocalTime())){
            throw new IllegalArgumentException("End time cannot be earlier than Start time");
        }
        if (startTime.toLocalTime().equals(endTime.toLocalTime())){
            throw new IllegalArgumentException("End time cannot be the same as Start time");
        }
        if (groceryStoreSystem==null) {
        	throw new IllegalArgumentException("Grocery Store System cannot be empty");
        }
        BusinessHour businessHour = new BusinessHour();
        businessHour.setDay(day);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHour.setWorking(working);
        businessHour.setGroceryStoreSystem(groceryStoreSystem);
        businessHourRepository.save(businessHour);
        return businessHour;
    }

    @Transactional
	public List<BusinessHour> getAllBusinessHours(){
		return toList(businessHourRepository.findAll());
	}
	
    @Transactional
	public BusinessHour getBusinessHoursbyID(int id){
    	if(id<0) {
    		throw new IllegalArgumentException("Id cannot be negative");
    	}
        BusinessHour businesshour = businessHourRepository.findBusinessHourById(id);
        if (businesshour==null){
            throw new IllegalArgumentException("No such id " + id + " exists");
        }
		return businesshour;
	}

    @Transactional
	public List<BusinessHour> getBusinessHoursbyDay(WeekDay day){
    	if(day==null) {
    		throw new IllegalArgumentException("Day is empty");
    	}
        List<BusinessHour> businessHours = businessHourRepository.findBusinessHourByDay(day);
        if (businessHours == null || businessHours.isEmpty()){
            throw new IllegalArgumentException("No such business hour with weekday " + day + " exists");
        }
		return businessHours;
	}

    @Transactional
	public List<BusinessHour> getBusinessHoursbyWorking(Boolean working){
        List<BusinessHour> businessHours = businessHourRepository.findBusinessHourByWorking(working);
        if (businessHours == null || businessHours.isEmpty()){
            throw new IllegalArgumentException("No such business hour with working status " + working + " exists");
        }
		return businessHours;
	}

    @Transactional
	public List<BusinessHour> getBusinessHoursbyStartTimebetween(Time startTime, Time endTime){
        if(startTime==null) {
        	throw new IllegalArgumentException("Start time is Empty");
        }
        if(endTime==null) {
        	throw new IllegalArgumentException("End time is Empty");
        }
    	List<BusinessHour> businessHours = businessHourRepository.findBusinessHourByStartTimeBetween(startTime,endTime);
        if (startTime.toLocalTime().isAfter(endTime.toLocalTime())){
            throw new IllegalArgumentException("Start time is later than end time");
        }
        if (endTime.toLocalTime().equals(startTime.toLocalTime())){
            throw new IllegalArgumentException("Start time cannot be the same as end time");
        }
		return businessHours;
	}

    @Transactional
	public List<BusinessHour> getBusinessHoursbyEmployee(Employee employee){
    	if (employee==null) {
    		throw new IllegalArgumentException("Employee cannot be empty");
    	}
		return businessHourRepository.findBusinessHoursByEmployee(employee);
	}

    @Transactional
	public List<BusinessHour> getOpeningHours(GroceryStoreSystem system){
    	if (system==null) {
    		throw new IllegalArgumentException("Grocery Store System cannot be empty");
    	}
    	return businessHourRepository.findBusinessHoursByGroceryStoreSystem(system);
	}

    @Transactional
    public BusinessHour updateBusinessHour(int id, GroceryStoreSystem groceryStoreSystem, Employee employee, WeekDay day, Time startTime, Time endTime, boolean working) {
        System.out.println("employee is " + employee);
    	if(day == null) {
            throw new IllegalArgumentException("Week day cannot be empty");
        }
        if (startTime == null){
            throw new IllegalArgumentException("Start time cannot be empty");
        }
        if (endTime == null){
            throw new IllegalArgumentException("End time cannot be empty");
        }
        if (startTime.toLocalTime().isAfter(endTime.toLocalTime())){
            throw new IllegalArgumentException("End time cannot be earlier than Start time");
        }
        if (startTime.toLocalTime().equals(endTime.toLocalTime())){
            throw new IllegalArgumentException("End time cannot be the same as Start time");
        }
        BusinessHour businessHour = businessHourRepository.findBusinessHourById(id);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHour.setWorking(working);
    	businessHour.setGroceryStoreSystem(groceryStoreSystem);
    	businessHour.setEmployee(employee);
        businessHourRepository.save(businessHour);
        return businessHour;
    }

    @Transactional
	public boolean deleteBusinessHourbyID(int id){
        if (id<0){
            return false;
        }
        else{
            BusinessHour businesshour=businessHourRepository.findBusinessHourById(id);
            businessHourRepository.delete(businesshour);
            return true;
        }
		
	}

    @Transactional
	public boolean deleteBusinessHourbyDay(WeekDay day){
        if (day==null){
            return false;
        }
        else{
            List<BusinessHour> allbusinesshour=toList(businessHourRepository.findAll());
            List<BusinessHour> businesshour=businessHourRepository.findBusinessHourByDay(day);
            allbusinesshour.removeAll(businesshour);
            return true;
        }
	}

    @Transactional
	public boolean deleteBusinessHourbyWoring(Boolean working){
        if (working==null){
            return false;
        }
        else{
            List<BusinessHour> allbusinesshour=toList(businessHourRepository.findAll());
            List<BusinessHour> businesshour=businessHourRepository.findBusinessHourByWorking(working);
            allbusinesshour.removeAll(businesshour);
            return true;
        }
	}

    @Transactional
	public boolean deleteBusinessHourbyTime(Time startTime, Time endTime){
        if (startTime==null||endTime==null){
            return false;
        }
        else{
            List<BusinessHour> allbusinesshour=toList(businessHourRepository.findAll());
            List<BusinessHour> businesshour=businessHourRepository.findBusinessHourByStartTimeBetween(startTime,endTime);
            allbusinesshour.removeAll(businesshour);
            return true;
        }
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}