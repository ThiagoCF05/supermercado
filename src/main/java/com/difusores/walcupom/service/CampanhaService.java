package com.difusores.walcupom.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.difusores.walcupom.data.model.Campanha;
import com.difusores.walcupom.data.repo.CampanhaRepository;
import com.difusores.walcupom.util.mapper.CampanhaMapper;
import com.difusores.walcupom.util.mapper.UserMapper;
import com.difusores.walcupom.web.data.CampanhaUI;
import com.difusores.walcupom.web.data.UserUI;

@Service
public class CampanhaService {
	@Autowired
	CampanhaRepository repo;
	@Autowired
	MongoTemplate template;
	CampanhaMapper mapper = new CampanhaMapper();
	UserMapper userMapper = new UserMapper();
	
	public CampanhaUI create(CampanhaUI campanhaUI){
		Campanha campanha = mapper.toPersistenceBean(campanhaUI);
		if(campanha != null){
			repo.save(campanha);
			return campanhaUI;
		} else
			return null;
	}
	
	public List<CampanhaUI> findAll(){
		return mapper.toUIBean(repo.findAll());
	}
	
	public Page<CampanhaUI> findAll(Pageable pageable){
		return mapper.toUIBean(repo.findAll(pageable), pageable);
	}
	
	public List<CampanhaUI> findByType(UserUI userUI, String type, Pageable pageable){
		if(type != null){
			if(type.equals("aberta")){
				Date now = new Date(System.currentTimeMillis());
				Query query = new Query();
				query.addCriteria(
						Criteria.where("dataEncerramento").gte(now).andOperator(
								Criteria.where("user.$id").is(new ObjectId(userUI.getId()))));
				query.limit(pageable.getPageSize());
				query.skip(pageable.getOffset());
				List<Campanha> campanhas = template.find(query, Campanha.class);
				
				return mapper.toUIBean(campanhas);
			}
			else if(type.equals("encerradas")){
				Date now = new Date(System.currentTimeMillis());
				Query query = new Query();
				query.addCriteria(
						Criteria.where("dataEncerramento").lt(now).andOperator(
								Criteria.where("user.$id").is(new ObjectId(userUI.getId()))));
				query.limit(pageable.getPageSize());
				query.skip(pageable.getOffset());
				List<Campanha> campanhas = template.find(query, Campanha.class);
				
				return mapper.toUIBean(campanhas);
			}
			else
				return this.findByUser(userUI);
		}
		else
			return this.findByUser(userUI);
	}
	
	public List<CampanhaUI> findByUser(UserUI userUI){
		return mapper.toUIBean(repo.findByUser(
				userMapper.toPersistenceBean(userUI)));
	}
	
	public Page<CampanhaUI> findByUser(UserUI userUI, Pageable pageable){
		return mapper.toUIBean(repo.findByUser(userMapper.toPersistenceBean(userUI), pageable), pageable);
	}
	
	public CampanhaUI find(String id){
		return mapper.toUIBean(repo.findOne(id));
	}
	
	public CampanhaUI update(CampanhaUI campanhaUI){
		Campanha campanha = mapper.toPersistenceBean(campanhaUI);
		if(campanhaUI != null){
			repo.save(campanha);
			return campanhaUI;
		}
		else
			return null;
	}
	
	public boolean delete(String id){
		Campanha campanha = repo.findOne(id);
		
		if(campanha != null){
			repo.delete(campanha);
			return true;
		} else
			return false;
	}

}
