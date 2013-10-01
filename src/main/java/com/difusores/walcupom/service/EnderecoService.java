package com.difusores.walcupom.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.difusores.walcupom.data.model.Endereco;
import com.difusores.walcupom.data.repo.EnderecoRepository;
import com.difusores.walcupom.util.mapper.EnderecoMapper;
import com.difusores.walcupom.web.data.EnderecoUI;
import com.difusores.walcupom.web.data.PessoaFisicaUI;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository repo;
	@Autowired
	PessoaFisicaService service;
	@Autowired
	UserService userService;
	@Autowired
	MongoTemplate template;
	EnderecoMapper mapper = new EnderecoMapper();
	
	public EnderecoUI find(String id){
		return mapper.toUIBean(repo.findOne(id));
	}
	
	public List<EnderecoUI> findByUser(String userName){
		Query q = new Query(Criteria.where("user").is(userName));
		List<Endereco> enderecos = template.find(q, Endereco.class);
		return mapper.toUIBean(enderecos);
	}
	
	public EnderecoUI create(EnderecoUI endereco){
		if(endereco != null){
			Endereco end = repo.save(mapper.toPersistenceBean(endereco));
			PessoaFisicaUI pf = 
					service.findByUser(userService.findByUsername(endereco.getUser()));
			
			List<EnderecoUI> enderecos = pf.getEnderecos();
			if(enderecos == null)
				enderecos = new ArrayList<EnderecoUI>();
			
			enderecos.add(mapper.toUIBean(end));
			pf.setEnderecos(enderecos);
			service.create(pf);
			
		}
		return endereco;	
	}
	
	public boolean delete(String enderecoId){
		Endereco endereco = repo.findOne(enderecoId);
		
		if(endereco != null){
			repo.delete(endereco);
			return true;
		}
		else
			return false;
	}
	
	public EnderecoUI insertLocation(EnderecoUI endereco){
		String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + endereco.getNumero();
		url += "+" + endereco.getRua();
		url += "+" + endereco.getCep();
		url += "," + endereco.getAvenida();
		url += "+" + endereco.getCidade();
		url += "+" + endereco.getEstado();
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
				
				endereco.setLatitude(latitude);
				endereco.setLongitude(longitude);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		return endereco;
	}

}
