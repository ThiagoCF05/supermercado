package com.difusores.supermercado.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.difusores.supermercado.data.model.Supermercado;
import com.difusores.supermercado.data.repo.SupermercadoRepository;
import com.difusores.supermercado.util.mapper.SupermercadoMapper;
import com.difusores.supermercado.web.data.SupermercadoUI;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SupermercadoService {
	@Autowired
	SupermercadoRepository repo;
	@Autowired
	UserService userService;
	@Autowired
	MongoTemplate template;
	SupermercadoMapper mapper = new SupermercadoMapper();
	
	public List<SupermercadoUI> findAll(){
		return mapper.toUIBean(repo.findAll());
	} 
	
	public Page<SupermercadoUI> findAll(Pageable pageable){
		return mapper.toUIBean(repo.findAll(pageable), pageable);
	}
	
	public SupermercadoUI find(String id){
		return mapper.toUIBean(repo.findOne(id));
	}
	
	public List<SupermercadoUI> findByUser(String userName){
		Query q = new Query(Criteria.where("user").is(userName));
		List<Supermercado> enderecos = template.find(q, Supermercado.class);
		return mapper.toUIBean(enderecos);
	}
	
	public List<SupermercadoUI> findByCepAndNumero(String cep, int numero){
		return mapper.toUIBean(repo.findByCepAndNumero(cep, numero));
	}
	
	public SupermercadoUI create(SupermercadoUI supermercadoUI){
		if(supermercadoUI != null)
			repo.save(mapper.toPersistenceBean(supermercadoUI));
		return supermercadoUI;	
	}
	
	public boolean delete(String supermercadoId){
		Supermercado supermercado = repo.findOne(supermercadoId);
		
		if(supermercado != null){
			repo.delete(supermercado);
			return true;
		}
		else
			return false;
	}
	
	public SupermercadoUI insertLocation(SupermercadoUI supermercadoUI){
		String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + supermercadoUI.getNumero();
		url += "+" + supermercadoUI.getRua();
		url += "+" + supermercadoUI.getCep();
		url += "," + supermercadoUI.getBairro();
		url += "+" + supermercadoUI.getCidade();
		url += "+" + supermercadoUI.getEstado();
		url += "&sensor=false";
		
		url = url.replaceAll(" ", "%20");
		
		try{
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			int response = conn.getResponseCode();
			if(response == HttpURLConnection.HTTP_OK){
				BufferedReader in = 
			            new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
			 
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				
				ObjectMapper mapper = new ObjectMapper();
				JsonFactory factory = mapper.getFactory(); 
				JsonParser jp = factory.createJsonParser(content.toString());
				JsonNode json = mapper.readTree(jp);
				
				json = json.findValue("location");
				
				double latitude = json.get("lat").asDouble();
				double longitude = json.get("lng").asDouble();
				
				supermercadoUI.setLatitude(latitude);
				supermercadoUI.setLongitude(longitude);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		return supermercadoUI;
	}

}
