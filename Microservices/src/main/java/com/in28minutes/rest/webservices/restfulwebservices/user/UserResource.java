package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveAllUsers(@PathVariable int id){
		User user = service.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		//HATEOAS-t használunk, mivel ebben a példában nemcsak az user-t akarjuk visszaadni, hanem link(ek)et egyéb műveletekhez
		EntityModel<User> resource = EntityModel.of(user);
		
		//Itt megmondjuk, hogy ennek a classnek a retrieveAllUsers metódusához fogunk linket visszaadni
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		//Itt pedig hozzáadjuk, és megmondjuk hogy milyen néven hivatkozzon a linkre
		resource.add(linkTo.withRel("all-users"));
		
		//Tehát itt már nem a sima usert adjuk vissza
		return resource;
	}
	
	
	//A @RequestBody jelentése, hogy a request bodyjában kapott értékeket bindolja a változóba
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		//Amit vissza akarunk adni URI-ként az a /users/{id} (az id helyére pedig nyilván a most létrehozott user id-je). Ehhez használhatjuk a ServletUriComponentsBuilder-t
		//A fromCurrentRequest maga az a path ami ehhez a metódushoz tartozik, tehát most /users
		//Azután a path-nál megmondjuk, hogy mit fűzzön annak az uri-nek a végére (tehát /{id}), majd a buildAndExpand helyére ennek a tényleges értéke kerül, végül pedig a toUri kb mint a toString
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(user.getId()).toUri();
		
		//Visszaadjuk az Uri-t és a státuszkódot (létrehozásnál 201-et)
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id){
		User user = service.deleteById(id);
		
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
	}

}
