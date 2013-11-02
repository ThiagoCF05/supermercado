package com.difusores.supermercado.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
import com.mongodb.DBCollection;

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
	
	public Page<SupermercadoUI> findByBairro(String bairro, Pageable pageable){
		Page<Supermercado> supermercados = repo.findByBairro(bairro, pageable);
		return mapper.toUIBean(supermercados, pageable);
	}
	
	public Page<SupermercadoUI> findByCidade(String cidade, Pageable pageable){
		return mapper.toUIBean(repo.findByCidade(cidade, pageable), pageable);
	}
	
	public Page<SupermercadoUI> findByRede(String rede, Pageable pageable){
		return mapper.toUIBean(repo.findByNomeEstabelecimento(rede, pageable), pageable);
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
	
	public List<SupermercadoUI> findByBoundery(SupermercadoUI supermercado, int distanceInMeters){
		double[] boundingBox = this.getBoundingBox(supermercado.getLatitude(), supermercado.getLongitude(), distanceInMeters);
		
		List<Supermercado> supermercados = new ArrayList<Supermercado>();
		
		try{
			Query query = new Query(Criteria.where("latitude").lte(boundingBox[0])
					.and("latitude").gte(boundingBox[2])
					.and("longitute").lte(boundingBox[1])
					.and("longitude").gte(boundingBox[3]));
			supermercados = template.find(query, Supermercado.class);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		return mapper.toUIBean(supermercados);
	}
	
	public List<String> findBairros(){
		List<String> bairros = new ArrayList<String>();
		
		try{
			DBCollection myCol = template.getCollection("supermercado");
			bairros = myCol.distinct("bairro");
			java.util.Collections.sort(bairros);
		} catch(Exception ex){
			
		}
		
		return bairros;
	}
	
	public List<String> findRedes(){
		List<String> redes = new ArrayList<String>();
		
		try{
			DBCollection myCol = template.getCollection("supermercado");
			redes = myCol.distinct("nomeEstabelecimento");
			java.util.Collections.sort(redes);
		} catch(Exception ex){
			
		}
		
		return redes;
	}
	
	public List<String> findCidades(){
		List<String> cidades = new ArrayList<String>();
		
		try{
			DBCollection myCol = template.getCollection("supermercado");
			cidades = myCol.distinct("cidade");
			java.util.Collections.sort(cidades);
		} catch(Exception ex){
			
		}
		
		return cidades;
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
	
	private double[] getBoundingBox(final double pLatitude, final double pLongitude, final int pDistanceInMeters) {

        final double[] boundingBox = new double[4];

        final double latRadian = Math.toRadians(pLatitude);

        final double degLatKm = 110.574235;
        final double degLongKm = 110.572833 * Math.cos(latRadian);
        final double deltaLat = pDistanceInMeters / 1000.0 / degLatKm;
        final double deltaLong = pDistanceInMeters / 1000.0 /
                        degLongKm;

        final double minLat = pLatitude - deltaLat;
        final double minLong = pLongitude - deltaLong;
        final double maxLat = pLatitude + deltaLat;
        final double maxLong = pLongitude + deltaLong;

        boundingBox[0] = minLat;
        boundingBox[1] = minLong;
        boundingBox[2] = maxLat;
        boundingBox[3] = maxLong;

        return boundingBox;
        
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
