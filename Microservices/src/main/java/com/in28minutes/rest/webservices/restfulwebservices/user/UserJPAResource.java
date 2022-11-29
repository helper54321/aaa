package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa")
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveAllUsers(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		//HATEOAS-t használunk, mivel ebben a példában nemcsak az user-t akarjuk visszaadni, hanem link(ek)et egyéb műveletekhez
		EntityModel<User> resource = EntityModel.of(user.get());
		
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
		User savedUser = userRepository.save(user);
		
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
		userRepository.deleteById(id);
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable int id){
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return userOptional.get().getPosts();
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		User user = userOptional.get();
		
		post.setUser(user);
		
		postRepository.save(post);
		
		//Amit vissza akarunk adni URI-ként az a /users/{id} (az id helyére pedig nyilván a most létrehozott user id-je). Ehhez használhatjuk a ServletUriComponentsBuilder-t
		//A fromCurrentRequest maga az a path ami ehhez a metódushoz tartozik, tehát most /users
		//Azután a path-nál megmondjuk, hogy mit fűzzön annak az uri-nek a végére (tehát /{id}), majd a buildAndExpand helyére ennek a tényleges értéke kerül, végül pedig a toUri kb mint a toString
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(post.getId()).toUri();
		
		//Visszaadjuk az Uri-t és a státuszkódot (létrehozásnál 201-et)
		return ResponseEntity.created(location).build();
	}

}
