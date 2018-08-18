package com.example.konkurs.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.konkurs.entities.AdminEntity;
import com.example.konkurs.entities.CitizenshipEntity;
import com.example.konkurs.entities.LanguageEntity;
import com.example.konkurs.entities.RoleEntity;
import com.example.konkurs.repositories.AdminRepository;
import com.example.konkurs.repositories.CitizenshipRepository;
import com.example.konkurs.repositories.LanguageRepository;
import com.example.konkurs.repositories.RoleRepository;

@Component
public class DatabaseInit {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private CitizenshipRepository citizenshipRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private RoleEntity adminRole;
	
	@PostConstruct
	public void init() {
		roleInit();
		userInit();
		citizenshipInit();
		languageInit();
	}
	
	private void roleInit() {
		if (((List<RoleEntity>) roleRepository.findAll()).isEmpty()) {
			adminRole = new RoleEntity("ROLE_ADMIN");
			roleRepository.save(adminRole);
		} else {
			adminRole = roleRepository.findByName("ROLE_ADMIN");
		}
	}
	
	private void userInit() {
		if (((List<AdminEntity>) adminRepository.findAll()).isEmpty()) {
			AdminEntity admin = new AdminEntity();
			admin.setDeleted(false);
			admin.setUsername("admin");
			admin.setPassword(Encryption.getPassEncoded("admin"));
			admin.setRole(adminRole);
			adminRepository.save(admin);
		}
	}
	
	private void citizenshipInit() {
		if (((List<CitizenshipEntity>) citizenshipRepository.findAll()).isEmpty()) {
			String[] citizenships = {"ENGLISH", "FRENCH", "GERMAN", "RUSSIAN", "SERBIAN", "OTHER"};
			for (String citizenship : citizenships) {
				CitizenshipEntity c = new CitizenshipEntity();
				c.setDeleted(false);
				c.setCitizenship(citizenship);
				citizenshipRepository.save(c);
			}
		}
	}
	
	private void languageInit() {
		if (((List<LanguageEntity>) languageRepository.findAll()).isEmpty()) {
			String[] languages = {"ENGLISH", "GERMAN", "FRENCH"};
			String[] levels = {"A1", "A2", "B1", "B2", "C1", "C2"};
			for (String language : languages) {
				for (String level : levels) {
					LanguageEntity l = new LanguageEntity();
					l.setDeleted(false);
					l.setLanguage(language);
					l.setLevel(level);
					languageRepository.save(l);
				}
			}
		}
	}

}
