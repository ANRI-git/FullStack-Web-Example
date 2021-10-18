package com.library.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entities.Book;
import com.library.entities.Publisher;
import com.library.errors.ErrorService;
import com.library.repositories.PublisherRepository;

@Service
public class PublisherService {

	@Autowired
	PublisherRepository publisherRepository;

	public void createPublisher(String name) throws ErrorService {
		validateInformation(name);

		Publisher publisher = new Publisher();
		publisher.setName(name);
		publisher.setRegistered(true);

		publisherRepository.save(publisher);
	}

	public Publisher queryPublisher(String id) throws ErrorService {
		if (id == null || id.isEmpty()) {
			throw new ErrorService("The id field is empty!");
		}

		Optional<Publisher> result = publisherRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new ErrorService("The publisher could not be found!");
		}
	}

	public void modifyPublisher(String id, String name) throws ErrorService {
		validateInformation(name);

		Optional<Publisher> result = publisherRepository.findById(id);
		if (result.isPresent()) {
			Publisher p = result.get();
			p.setName(name);

			publisherRepository.save(p);
		} else {
			throw new ErrorService("Publisher could not be found!");
		}
	}

	private void validateInformation(String name) throws ErrorService {
		if (name == null || name.isEmpty()) {
			throw new ErrorService("The name field is empty!");
		}
	}

	public void registerPublisher(String id) throws ErrorService {
		Optional<Publisher> result = publisherRepository.findById(id);
		if (result.isPresent()) {
			Publisher p = result.get();
			p.setRegistered(!p.getRegistered());

			publisherRepository.save(p);
		} else {
			throw new ErrorService("Publisher could not be found!");
		}
	}
	
	public void deletePublisher(String id) throws ErrorService {
		Optional<Publisher> result = publisherRepository.findById(id);
		if(result.isPresent()) {
			publisherRepository.delete(result.get());
		} else {
			throw new ErrorService("Publisher not found!");
		}
	}
}
