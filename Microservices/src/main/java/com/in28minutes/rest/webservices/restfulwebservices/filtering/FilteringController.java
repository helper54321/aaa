package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	//Egyiknél se lesz benne a value3, mert a SomeBean classben a JsonIgnore-vel megjelöltük, hogy azt ne adja vissza
	
	//Csak field1, field2-t akarjuk visszaadni json-ként (itt dinamikusan megmondható, tehát metódusonként változhat) 
	@GetMapping("/filtering")
	public MappingJacksonValue retreieveSomeBean() {
		
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		//field1, field2 kivételével mindent zárjon ki a visszaadott json-ből
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		//A SomeBean classben a classnál annotiációval hivatkozunk erre a filterre
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		
		mapping.setFilters(filters);
		
		return mapping;
		
	}
	
	//field2, field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retreieveListOfSomeBeans() {
		
		List<SomeBean> list = Arrays.asList(
				new SomeBean("value1", "value2", "value3"),
				new SomeBean("value11", "value22", "value33"));
		
		//field2, field3 kivételével mindent zárjon ki a visszaadott json-ből
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		
		//A SomeBean classben a classnál annotiációval hivatkozunk erre a filterre
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		
		mapping.setFilters(filters);
		
		return mapping;
		
	}

}
