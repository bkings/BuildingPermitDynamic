package com;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.createMultipartConfig();
        factory.setMaxFileSize(DataSize.ofGigabytes(1l));
        factory.setMaxRequestSize(DataSize.ofGigabytes(1l));
        return factory.createMultipartConfig();
    }
    
    @Bean
    public PasswordEncoder encoder() {
    	return new BCryptPasswordEncoder();
    }
}

/*
  
DELETE FROM forward_status;
  DELETE FROM allowance_paper; DELETE FROM amini_inquiry;
  DELETE FROM amini_kabuliyatnama; DELETE FROM
  aminko_pratibedan; DELETE FROM anusuchi_ga; DELETE FROM
  anusuchi_gha; DELETE FROM anusuchi_ka; DELETE FROM
  application_action; DELETE FROM application_activity;
  DELETE FROM application_history;; DELETE FROM
  applications_comments; DELETE FROM
  architectural_design_b; DELETE FROM
  architectural_design_b_details; DELETE FROM
  architectural_design_c; DELETE FROM
  architectural_design_c_details; DELETE FROM
  building_build_certificate; DELETE FROM
  building_finish_certificate; DELETE FROM
  building_member_details; DELETE FROM
  building_permit_application; DELETE FROM
  building_permit_floor; DELETE FROM
  building_permit_surrounding; DELETE FROM
  certificate_instruction; DELETE FROM certificate_note;
  DELETE FROM check_class_group; DELETE FROM contacts;
  DELETE FROM designer_renew_status; DELETE FROM
  dosrocharan_abedan; DELETE FROM dosrocharan_bill_vuktani;
  DELETE FROM dosrocharan_prabidhik; DELETE FROM
  dosrocharan_supervisor; DELETE FROM electrical_design;
  DELETE FROM electrical_design_details; DELETE FROM
  electrical_design_requirement_master; DELETE FROM
  file_storage_application; DELETE FROM
  frequently_asked_question; DELETE FROM
  ghar_compound_wall; DELETE FROM ghar_compoundwallko_naap;
  DELETE FROM ghar_naksako_kabuliyatnama; DELETE FROM
  gharnaksha_surjamin_muchulka; DELETE FROM
  map_check_report; DELETE FROM map_check_report_details;
  DELETE FROM map_technical_description; DELETE FROM
  namsari_tippani_ades_view; DELETE FROM
  noteorder_pilength_level; DELETE FROM
  notice_payment_application; DELETE FROM
  notice_period_for_15_days; DELETE FROM
  other_set_back_master; DELETE FROM
  plinth_level_owner_representation; DELETE FROM
  pahilocharan_bill_vuktani; DELETE FROM
  plinth_level_tech_application; DELETE FROM
  public_downloads; DELETE FROM prabhidik_pratibedhan_pesh;
  DELETE FROM purano_ghar_nirman_tippani; DELETE FROM
  purano_ghar_sampanna_praman_patra; DELETE FROM
  rajaswa_entry; DELETE FROM rajaswa_voucher; DELETE FROM
  samsodhit_super_structure_ijajat_pratibedan; DELETE FROM
  sanitary_design; DELETE FROM sansodhan_bill_vuktani;
  DELETE FROM sansodhan_bibaran_pahilo; DELETE FROM
  sansodhan_gari_nibedan; DELETE FROM
  sansodhan_super_structure_ijjaat; DELETE FROM
  sansodhan_tippani; DELETE FROM sansodhanko_tippani_adesh;
  DELETE FROM sansodhansuper_tippani_adesh; DELETE FROM
  structure_design_c; DELETE FROM structure_design_b;
  DELETE FROM super_structure_build; DELETE FROM
  super_structure_construction; DELETE FROM
  super_structure_noteorder; DELETE FROM
  superstructureko_nirman_karya; DELETE FROM
  superstructureko_tippaniadesh; DELETE FROM
  surjamin_muchulka;
  
 */
