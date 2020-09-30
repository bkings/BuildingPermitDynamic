package com.service.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.JWTToken;
import com.dao.utility.DaoOrganizationMaster;
import com.model.utility.OrganizationMaster;

import model.DB;
import model.DateConvert;
import model.Message;

@Service
public class OrganizationMasterServiceImp implements OrganizationMasterService {

    model.Message message = new model.Message();
    String msg = "", sql;
    int row;
    @Autowired
    DaoOrganizationMaster da;

    @Override
    public Object getRecord() {
        return da.getAll("from OrganizationMaster");
    }

    @Override
    public Object applicationConfiguration() {

        OrganizationMaster obj = new OrganizationMaster();
        obj.setAddress("नमुना");
        obj.setProvince("प्रदेश २");
        obj.setEmail("Organization Email");
        obj.setMayorContactNo("Organization Mayor Contact No");
        obj.setMayorName("Organization Mayor Name");
        obj.setName("नमुना नगरपालिका ");
        obj.setOfficeContactNo("Organization Office Contact No");
        obj.setOfficeName("नगर कार्यपालिकाको कार्यालय");
        obj.setSecretaryContactNo("Organization Secretary Contact No");
        obj.setSecretaryName("Organization Secretary Name");
        obj.setSubMayorContactNo("Organization Sub Mayor Contact No");
        obj.setSubMayorName("Organization Sub Mayor Name");
        obj.setUrl("Organization URL");
        obj.setEmail("Organization Email");
        obj.setId(1);
        da.save(obj);
        sql = "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('G', 'Rajasow G', 1, 'postg_', 'postg_', NULL);\n"
                + "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('D', 'Designer', 1, '', 'designer_', 'डिजाइनर');\n"
                + "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('AD', 'Amin', 1, 'amini_', 'amin_', 'अमिन');\n"
                + "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('R', 'Rajasow', 1, 'rw_', 'rajasow_', 'राजस्व');\n"
                + "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('B', 'Sub Engineer', 2, 'ser_', 'sub_engineer_', 'सब-इन्जिनियर');\n"
                + "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('A', 'Engineer', 3, 'er_', 'engineer_', 'इन्जिनियर');\n"
                + "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('TADM', 'T-ADMIN', 5, '', '', 'प्राविधिक प्रशासक');\n"
                + "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('C', 'Chief', 4, 'chief_', 'chief_', 'प्रमुख प्रशासकिय अधिकृत');\n"
                + "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('ADM', 'ADMIN', 5, '', '', 'एडमिन');\n"
                + "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('F', 'Administrative', 1, 'postf_', 'postf_', 'प्रशासन शाखा');\n"
                + "INSERT INTO user_type_master (id, designation, hierarchy, approve_column, action_status, designation_nepali) VALUES ('E', 'Assistant SubEngineer', 1, 'poste_', 'poste_', 'अ·स·ई');";
        message.db.save(sql);
//        List<UserTypeMaster> list = new ArrayList();
//        list.add(new UserTypeMaster("C", "Chief", 4, "chief_", "CHIEF_"));
//        list.add(new UserTypeMaster("A", "Engineer", 3, "er_", "ENGINEER_"));
//        list.add(new UserTypeMaster("B", "Sub Engineer", 2, "ser_", "SUB_ENGINEER_"));
//        list.add(new UserTypeMaster("D", "Designer", 1, "", "DESIGNER_"));
//        list.add(new UserTypeMaster("AD", "Amin", 1, "amini_", "AMIN_"));
//        list.add(new UserTypeMaster("R", "Rajasow", 1, "rw_", "RAJASOW_"));
//        list.add(new UserTypeMaster("E", "Rajasow", 1, "poste_", "POSTE_"));
//        list.add(new UserTypeMaster("F", "Rajasow", 1, "postf_", "POSTF_"));
//        list.add(new UserTypeMaster("G", "Rajasow", 1, "postg_", "POSTG_"));
//        list.add(new UserTypeMaster("ADM", "ADMIN", 5, "", ""));
//        list.add(new UserTypeMaster("TADM", "T-ADMIN", 5, "", ""));
        /*
        INSERT INTO user_type_master (id, designation, hierarchy)	VALUES ('D', 'Designer', 1);
INSERT INTO user_type_master (id, designation, hierarchy) 	VALUES ('AD', 'Amin', 1);
INSERT INTO user_type_master (id, designation, hierarchy) 	VALUES ('R', 'Rajasow', 1);
INSERT INTO user_type_master (id, designation, hierarchy) 	VALUES ('B', 'Sub Engineer', 2);
INSERT INTO user_type_master (id, designation, hierarchy) 	VALUES ('A', 'Engineer', 3);
INSERT INTO user_type_master (id, designation, hierarchy) 	VALUES ('C', 'Chief', 4);
INSERT INTO user_type_master (id, designation, hierarchy) 	VALUES ('', '', 5);
INSERT INTO user_type_master (id, designation, hierarchy) 	VALUES ('', '', 5);*/
//        for (int i = 0; i < list.size(); i++) {
//            da.save(list.get(i));
//        }

        sql = "INSERT INTO fiscal_year(year_code,year_name,start_date,end_date,status) VALUES('7576','2075-2076','2018-07-17','2019-07-16','N');\n"
                + "INSERT INTO fiscal_year(year_code,year_name,start_date,end_date,status) VALUES('7677','2076-2077','2019-07-17','2020-07-15','N');\n"
                + "INSERT INTO fiscal_year(year_code,year_name,start_date,end_date,status) VALUES('7778','2077-2078','2020-07-16','2021-07-15','N');";
        message.db.save(sql);
        sql = "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (1, 'N', 'N', 'N', 'Y', 'D', 'निवेदन', '1PAGE', 'N', 'Y', 'N', 'N', 'Y', 'building_permit_application', ' /user/forms/map-permit-application-edit');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (2, 'N', 'N', 'N', 'Y', 'D', 'अनुसुची क', 'AKA', 'N', 'N', 'N', 'N', 'Y', 'anusuchi_ka', '/user/forms/design-approval');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (3, 'N', 'N', 'N', 'Y', 'D', 'अनुसुची ख अर्किटेक्चरल B', 'BAKHA', NULL, NULL, NULL, 'N', 'Y', 'architectural_design_b', '/user/forms/architecture-design-classB-form');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (4, 'N', 'N', 'N', 'Y', 'D', 'अनुसुची ख स्ट्रक्चरल B', 'BSKHA', NULL, NULL, NULL, 'N', 'Y', 'structure_design_b', '/user/forms/structure-design-classB-form');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (5, 'N', 'N', 'N', 'Y', 'D', 'अनुसुची ख  अर्किटेक्चरल C', 'CAKHA', NULL, NULL, NULL, 'N', 'Y', 'architectural_design_c', '/user/forms/architecture-design-classc-form');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (6, 'N', 'N', 'N', 'Y', 'D', 'अनुसुची ख स्ट्रक्चरल C', 'CSKHA', NULL, NULL, NULL, 'N', 'Y', 'structure_design_c', '/user/forms/structure-design-classc-form');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (7, 'N', 'N', 'N', 'Y', 'D', 'स्यानिटरी डिजाइन', 'STD', NULL, NULL, NULL, 'N', 'Y', 'sanitary_design', '/user/forms/sanitary-design');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (8, 'N', 'N', 'N', 'Y', 'D', 'इलेक्ट्रिकल डिजाइन', 'ELD', NULL, NULL, NULL, 'N', 'Y', 'electrical_design', '/user/forms/electrical-design');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (9, 'N', 'N', 'N', 'Y', 'D', 'अनुसुची ग', 'AGA', NULL, NULL, NULL, 'N', 'Y', 'anusuchi_ga', '/user/forms/anusuchiga-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (10, 'N', 'N', 'N', 'Y', 'D', 'अनुसुची घ', 'AGHA', NULL, NULL, NULL, 'N', 'Y', 'anusuchi_gha', '/user/forms/anusuchigha-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (11, 'N', 'N', 'N', 'Y', 'B', 'नक्सा पास पछि जाँच गरी प्रतिवेदन', 'NJPP', NULL, NULL, NULL, 'N', 'N', 'map_check_report', '/user/forms/map-check-report-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (12, 'N', 'N', 'N', 'Y', 'B', 'राजस्व विवरण', 'RJB', NULL, NULL, NULL, 'N', 'N', 'rajaswa_entry', '/user/forms/rajaswo-detail-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (13, 'N', 'N', 'N', 'Y', 'B', 'संधियारको नाममा जारी भएको सुचना', 'NNA', NULL, NULL, NULL, 'N', 'N', 'notice_period_for_15_days', '/user/forms/notice-period-for-15-days-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (14, 'N', 'N', 'N', 'Y', 'B', 'सुचना बुझाएको भर्पाई टास तथा मुचुल्का', 'SBBT', NULL, NULL, NULL, 'N', 'N', 'notice_payment_application', '/user/forms/notice-payment-application-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (15, 'N', 'N', 'N', 'Y', 'B', 'सर्जमिन गरी पेश गर्ने सम्बन्धमा', 'SPS', NULL, NULL, NULL, 'N', 'N', 'surjamin_muchulka', '/user/forms/surjamin-muchulka-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (16, 'N', 'N', 'N', 'Y', 'AD', 'घर नक्सा सरजमिन मुचुल्का', 'GNSM', 'Y', 'N', 'N', 'N', 'Y', 'gharnaksha_surjamin_muchulka', '/user/forms/gharnaksha-surjamin-muchulka-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (17, 'N', 'N', 'N', 'Y', 'E', 'प्राविधिक प्रतिवेदन', 'PRAP', 'N', 'N', 'N', 'N', 'Y', 'prabhidik_pratibedhan_pesh', '/user/forms/prabhidik-pratibedhan-pesh-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (18, 'N', 'N', 'N', 'Y', 'D', 'संसोधन गरी निवेदन', 'SMNB1', NULL, NULL, NULL, 'N', 'Y', 'sansodhan_gari_nibedan', '/user/forms/sansodhan-gari-nibedan');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (19, 'N', 'N', 'N', 'Y', 'F', 'संसोधन टिप्पणी र आदेश', 'STPA', 'N', 'N', 'N', 'N', 'Y', 'sansodhan_tippani', '/user/forms/sansodhan-tippani');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (20, 'N', 'N', 'N', 'Y', 'F', 'प्लिन्थ लेभलसम्म निर्माण निमित्त इजाजत प्रधान गर्ने', 'PLNTH', 'Y', 'N', 'N', 'N', 'Y', 'noteorder_pilength_level', '/user/forms/noteorder-pilength-level-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (21, 'N', 'N', 'N', 'Y', 'E', 'प्रथम चरणको अस्थायी निर्माण इजाजत पत्र', 'PCIP', 'N', NULL, NULL, 'N', 'Y', 'allowance_paper', '/user/forms/allowance-paper-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (22, 'N', 'N', 'N', 'Y', 'D', 'सुपरस्ट्रक्चरको निर्माण कार्यको लागि इजाजतबारे', 'SNIPB', 'Y', NULL, NULL, 'N', 'Y', 'super_structure_build', '/user/forms/super-structure-build-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (23, 'N', 'N', 'N', 'Y', 'D', 'प्लिन्थ लेभलसम्मको निर्माण कार्य सम्पन्नबारे घरधनीको तर्फबाट राखिएको सुपरिवेक्षकको प्रतिवेदन', 'PSGPR', 'Y', NULL, NULL, 'N', 'Y', 'plinth_level_owner_representation', '/user/forms/plinthLevel-OwnerRepresentation-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (24, 'N', 'N', 'N', 'Y', 'D', 'संसोधन विवरण (पहिलो चरण)', 'SMNB2', 'Y', NULL, NULL, 'N', 'Y', 'sansodhan_bibaran_pahilo', '/user/forms/sansodhan-bibaran-pahilo');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (25, 'N', 'N', 'N', 'Y', 'F', 'संसोधनको टिप्पणी आदेश', 'STPA2', 'N', NULL, NULL, 'N', 'Y', 'sansodhanko_tippani_adesh', '/user/forms/sansodhanko-tippani-adesh');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (26, 'N', 'N', 'N', 'Y', 'F', 'सुपरस्ट्रक्चर निर्माणका निमित्त इजाजत प्रदान गर्ने टिप्पणी आदेश', 'SNNTP', 'Y', NULL, NULL, 'N', 'Y', 'super_structure_noteorder', '/user/forms/superStructure-noteOrder-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (27, 'N', 'N', 'N', 'Y', 'E', 'दोस्रो चरणको ( सुपरस्ट्रक्चर निर्माण कार्यको ) इजाजत पत्र', 'DCIP', NULL, NULL, NULL, 'N', 'Y', 'super_structure_construction', '/user/forms/superStructure-construction-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (28, 'N', 'N', 'N', 'Y', 'D', 'निर्माण कार्य सम्पन्‍न प्रमाण पत्र बारे', 'NSBN', 'Y', NULL, NULL, 'N', 'Y', 'dosrocharan_abedan', '/user/forms/dosrocharan-abedan');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (29, 'N', 'N', 'N', 'Y', 'D', 'सुपरस्ट्रक्चरको निर्माण कार्य सम्पन्‍नबारे घरधनीको तर्फबाट राखिएको सुपरिवेक्षकका प्रतिवेदन', 'SNSGR', 'Y', NULL, NULL, 'N', 'Y', 'dosrocharan_supervisor', '/user/forms/dosrocharan-supervisor');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (30, 'N', 'N', 'N', 'Y', 'D', 'सुपरस्ट्रक्चरको संसोधन विवरण', 'SSSB', 'Y', NULL, NULL, 'N', 'Y', 'superstructureko_tippaniadesh', '/user/forms/superstructureko-tippaniadesh');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (31, 'N', 'N', 'N', 'Y', 'E', 'संसोधन/सुपरस्ट्रक्चर इजाजत प्रतिवेदन पेश गरेको बारे', 'SIPP', NULL, NULL, NULL, 'N', 'Y', 'samsodhit_super_structure_ijajat_pratibedan', '/user/forms/samsodhit-Super-structure-Ijajat-Pratibedan');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (32, 'N', 'N', 'N', 'Y', 'F', 'संसोधन सुपरस्ट्रक्चर इजाजत सम्बन्धमा', 'SSIS', NULL, NULL, NULL, 'N', 'Y', 'sansodhan_super_structure_ijjaat', '/user/forms/sansodhan-super-structure-ijjaat');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (33, 'N', 'N', 'N', 'Y', 'F', 'संसोधन सुपरस्ट्रक्चर टिप्पणी आदेश', 'SSTP', NULL, NULL, NULL, 'N', 'Y', 'sansodhansuper_tippani_adesh', '/user/forms/sansodhansuper-tippani-adesh');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (34, 'N', 'N', 'N', 'Y', 'F', 'निर्माण कार्य सम्पन्न प्रमाण-पत्र दिने टिप्पणी आदेश', 'NSPT', 'Y', NULL, NULL, 'N', 'Y', 'certificate_instruction', '/user/forms/certificate-instruction-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (35, 'N', 'N', 'N', 'Y', 'E', 'भवन निर्माण सम्पन्‍न प्रमाण पत्र', 'BNPP', NULL, NULL, NULL, 'N', 'Y', 'building_finish_certificate', '/user/forms/building-finish-certificate-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (36, 'N', 'N', 'N', 'Y', 'B', 'घर नक्सा नामसारी सम्बन्धमा', 'GNNS', NULL, NULL, NULL, 'N', 'N', 'certificate_note', '/user/forms/certificate-note');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (37, 'N', 'N', 'N', 'Y', 'B', 'नामसारी टिप्पणी आदेश', 'NSTA', NULL, NULL, NULL, 'N', 'N', 'namsari_tippani_ades', '/user/forms/namsari-tippani-ades-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (38, 'N', 'Y', 'N', 'Y', 'B', 'नामसारी प्रमाण-पत्र', 'NSPP', NULL, NULL, NULL, 'N', 'N', 'building_build_certificate', '/user/forms/building-build-certificate-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (39, 'N', 'N', 'N', 'Y', 'D', 'नक्सा बनाउने प्रविधिकले गर्नु पर्ने विवरण', 'NBPGB', NULL, NULL, NULL, 'N', 'Y', 'map_technical_description', '/user/forms/map-technical-description-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (40, 'N', 'N', 'N', 'Y', 'E', 'प्लिन्थ लेभल सम्म निर्माण सम्पन्‍न बारे न.पा. प्राविधिकको राय', 'PSNPR', NULL, NULL, NULL, 'N', 'Y', 'plinth_level_tech_application', '/user/forms/plinthLevel-techapplication-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (41, 'N', 'N', 'N', 'Y', 'E', 'भवन निर्माण सम्पन्‍न प्रतिवेदन सम्बन्धमा', 'BNSP', NULL, NULL, NULL, 'N', 'Y', 'dosrocharan_prabidhik', '/user/forms/dosrocharan-prabidhik-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (42, 'Y', 'Y', 'Y', 'Y', 'B', 'म्याद थप सम्बन्धमा', NULL, NULL, NULL, NULL, 'Y', 'Y', 'MyadThapTippani', '/user/forms/myadThap-tippani');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (43, 'N', 'N', 'N', 'Y', 'AD', 'अमिन  प्रतिवेदन', 'ADSM', NULL, 'N', NULL, 'N', 'Y', 'aminko_pratibedan', ' /user/forms/aminko-pratibedan');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (44, 'N', 'N', 'N', 'Y', 'AD', 'घर नक्सा कबुलियतमा', 'GCWAL', 'Y', 'Y', 'N', 'N', 'Y', 'ghar_naksako_kabuliyatnama', '/user/forms/ghar-naksako-kabuliyatnama');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (45, 'N', 'N', 'N', 'Y', 'AD', 'अमिन कबुलियतमा', 'AGNKA', NULL, NULL, NULL, 'N', 'Y', 'ghar_compoundwallko_naap', '/user/forms/ghar-compoundwallko-naap');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (46, 'N', 'N', 'N', 'Y', 'R', 'संसोधन बिल भुक्तानी', 'SBV', 'N', 'Y', NULL, 'N', 'Y', 'sansodhan_bill_vuktani', '/user/forms/samsodhan-bill-vuktani');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (47, 'N', 'N', 'N', 'Y', 'R', 'पहिलो चरण संसोधन बिल भुक्तानी', 'FSBIL', 'Y', 'Y', NULL, 'N', 'Y', 'pahilocharan_bill_vuktani', '/user/forms/pahilo-charan-bill-vuktani');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (48, 'N', 'N', 'N', 'Y', 'R', 'दोस्रो  चरण संसोधन बिल भुक्तानी', 'DCBIL', NULL, NULL, NULL, 'N', 'Y', 'dosrocharan_bill_vuktani', '/user/forms/dosrocharan-bill-vuktani');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (49, 'N', 'N', 'N', 'Y', 'R', 'राजस्व विभागले राजश्‍वको भौचर राख्ने', NULL, NULL, NULL, NULL, 'N', 'Y', 'rajaswa_voucher', '/user/forms/rajaswo-voucher');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (50, 'N', 'N', 'N', 'Y', 'F', 'पुरानो घर निर्माण कार्य सम्पन्न प्रमाण-पत्र दिने टिप्पणी आदेश', NULL, 'Y', 'N', 'N', 'N', 'Y', 'purano_ghar_nirman_tippani', '/user/forms/purano-ghar-nirman-tippani');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (51, 'N', 'N', 'N', 'Y', 'E', 'पुरानो घर भवन निर्माण सम्पन्‍न प्रमाण पत्र', NULL, 'N', NULL, 'N', 'N', 'Y', 'purano_ghar_sampanna_praman_patra', '/user/forms/purano-ghar-sampanna-pramanpatra');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (52, 'N', 'N', 'N', 'Y', 'E', 'सुपरस्ट्रक्चरको निर्माण कार्य सम्पन्न बारे स्थलगत निरीक्षण गर्ने न. पा. प्रविधिको राय', NULL, 'N', NULL, NULL, 'N', 'Y', 'superstructureko_nirman_karya', '/user/forms/superstructureko-nirmaan-karya');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (53, 'Y', 'Y', 'Y', 'Y', 'B', 'म्याद थप गरिएको सम्बन्धमा', NULL, NULL, NULL, NULL, 'Y', 'Y', 'myad_thap_approval', '/user/forms/myadThap-approval');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (54, 'N', 'N', 'N', 'N', 'B', 'वारेसनामा', NULL, NULL, NULL, NULL, 'N', 'N', 'waresnama', '/user/forms/waresnama-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (55, 'N', 'N', 'N', 'Y', 'B', 'मन्जुरीनामा', NULL, NULL, NULL, NULL, 'N', 'N', 'manjurinama', '/user/forms/manjurinama-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (56, 'N', 'N', 'N', 'Y', 'B', 'कवुलियतनामा', NULL, NULL, NULL, NULL, 'N', 'N', 'kabuliyatnama', '/user/forms/kabuliyatnama-view');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (57, 'N', 'N', 'N', 'Y', 'AD', 'अमिनको स्थलगत निरीक्षण प्रतिवेदन', NULL, 'Y', 'N', 'N', 'N', 'Y', 'amin_ko_sthalgat_pratibedan', '/user/forms/aminko-sthalgat-pratibedan');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (58, 'N', 'N', 'N', 'Y', 'F', 'तला थप गर्नका निमित्त इजाजत प्रदान गर्न टिप्पणी आदेश', NULL, 'Y', NULL, NULL, 'N', 'Y', 'talla_thap_ijajat_tippaniades', '/user/forms/talla-thap-ijajat-tippani-ades');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (59, 'N', 'N', 'N', 'Y', 'D', 'तला थपका लागि इजाजत-पत्र पाउँ', NULL, NULL, NULL, NULL, 'N', 'Y', 'talla_thap_ijajat_request', '/user/forms/talla-thap-ijajat-request');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (60, 'N', 'N', 'N', 'Y', 'E', 'तला थप (सुपर स्ट्रक्चरको) निर्माण इजाजत-पत्र', NULL, NULL, NULL, NULL, 'N', 'Y', 'talla_thap_ijajat_patra', '/user/forms/talla-thap-ijajat-patra');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (61, 'N', 'Y', 'N', 'Y', 'B', 'No Objection Sheet', NULL, NULL, NULL, NULL, 'N', 'N', 'no_objection_sheet', '/user/forms/no-objection-sheet');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (62, 'N', 'N', 'N', 'Y', 'D', 'ले-आउट गरी प्रतिवेदन पेश गर्ने बारे', NULL, NULL, NULL, NULL, 'N', 'Y', 'layout_gari_pratibedan_request', '/user/forms/layout-gari-pratibedan-request');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (63, 'N', 'N', 'N', 'Y', 'B', 'ले-आउट गरी प्रतिवेदन पेश गरेको बारे', NULL, NULL, NULL, NULL, 'N', 'N', 'layout_gari_pratibedan_ijajat', '/user/forms/layout-gari-pratibedan-ijajat');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (64, 'N', 'N', 'N', 'Y', 'B', 'भवन नक्सा फायल पठाएको बारे ', NULL, NULL, NULL, NULL, 'N', 'N', 'naksa_file_pathayeko_bare', '/user/forms/naksa-file-pathayeko-bare');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (65, 'N', 'N', 'N', 'Y', 'R', 'नामसारी बिल भुक्तानी', NULL, NULL, NULL, NULL, 'N', 'Y', 'namsari_bill_vuktani', '/user/forms/namsari-bill-vuktani');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (66, 'Y', 'Y', 'Y', 'Y', 'D', 'म्याद थप गरी दिनु हुन गरी', NULL, NULL, NULL, NULL, 'Y', 'Y', 'myad_thap_request', '/user/forms/myadThap-request');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (67, 'N', 'N', 'N', 'Y', 'D', 'नयाँ रेकर्ड पठाएको/विवरण पठाएको/नामसारी सम्बन्धमा', NULL, NULL, NULL, NULL, 'N', 'N', 'naya_record_pathayeko_namsari', '/user/forms/naya-record-sambandhama');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (68, 'N', 'N', 'N', 'N', 'D', 'निर्माण कार्य सपन्नको प्रमाण पत्र सहित धरौटी फिर्ता दिने टिप्पणी आदेश', NULL, NULL, NULL, NULL, 'N', 'N', 'dharauti_phirta_tippani_ades', '/user/forms/dharauti-firta-tippani-aadesh');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (69, 'N', 'N', 'N', 'Y', 'B', 'धरौटी रकम फिर्ता दिने बारे', NULL, 'Y', 'N', 'N', 'N', 'N', 'dharauti_phirta_bare', '/user/forms/dharauti-rakam-phirta-bare');\n"
                + "INSERT INTO form_name_master (id, amin_approval, chief_approval, designer_approval, engr_approval, enter_by, \"name\", page_code, poste_approval, postf_approval, postg_approval, rajasow_approval, sub_engr_approval, \"table_name\", view_url) VALUES (70, 'N', 'N', 'N', 'Y', 'B', 'निर्माण कार्य सम्पन्न प्रमाण पत्र तथा धरौटी फिर्ता बारे', NULL, 'Y', 'N', 'N', 'N', 'N', 'naya_nirman_praman_patra_dharauti_phirta', '/user/forms/nirman-pramanpatra-dharauti');";
        message.db.save(sql);
        sql = "INSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('1', 'जग्गाको लम्बाई', '', 1);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('2', 'जग्गाको चौडाई', '', 2);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('3', 'भवनको वर्गिकरण', '',3);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('4', 'भवनको प्रयोग', '', 4);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('5', 'प्लिन्थ एरिया', '', 5);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('6', 'तला संख्या', '', 6);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('7', 'घरको कुल उचाई', '', 7);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('8', 'भवन निर्माणको किसिम', '', 8);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9', 'भवनको स्ट्रक्चरल सिस्टम', '', 9);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('10', 'माटोको प्रकार', '', 10);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('11', 'माटोको भार वहन क्षमता (सोयल वियरिङ क्यापासिटि)', '', 11);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.1.1', 'जगको प्रकार', 'P', 12);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.1.2', 'जगको गहिराइ', 'P', 13);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.1.3', 'जगको साइजहरू', 'P', 14);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.1.4', 'पिलरका साइजहरू', 'P', 15);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.1.5', 'पिलरमा प्रयोग गर्ने डण्डीका साइज र संख्या', 'P', 16);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.1.6', 'विमको स्थान', 'P', 17);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.1.7', 'विमको साइजहरु', 'P', 18);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.1.8', 'स्ल्याबको मोटाई/staircase', 'P', 19);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.1.9', 'कंक्रिटको ग्रेड (सिमेन्टःबालुवाःरोडा)', 'P', 20);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.1.10', 'कंक्रिट ब्याण्डहरु राखिएको छ/छैन', 'P', 21);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.1', 'जगको गहिराइ', 'G', 22);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.2', 'जगको चौडाई', 'G', 23);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.3', 'इट्टाको क्रसिङ्ग स्ट्रेन्थ', 'G', 24);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.4', 'कंक्रिटको ग्रेड (सिमेन्टःबालुवाःरोडा)', 'G', 25);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.5', 'स्ल्याबको मोटाई', 'G', 26);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.6', 'फ्लोर विमको साइजहरु', 'G', 27);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.7', 'गारोमा सिमेन्ट, बालुवाको भाग', 'G', 28);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.8', 'गारोको विवरण', 'G', 29);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.9', 'कंक्रिट ब्याण्डरु राखिएको छ/छैन', 'G', 30);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.10', 'भर्टिकल डण्डीको साईज (कुना र कर्नर ज्वाईन्टहरुमा)', 'G', 31);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.11', 'कर्नर स्टिचिङ कंक्रिट व्याण्डहरु राखिएको छ/छैन', 'G', 32);"
                + "\nINSERT INTO map_check_report_master (SN, BUILDING_DESCRIPTION, TYPE, ID) VALUES ('9.2.12', 'अन्य', 'G', 33);";
        message.db.save(sql);
        sql = "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.1', 'Min. tread width of staircase', '1', 'Staircase', 'mm excluding nosing');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.2', 'Riser height of staircase', '1', 'Staircase', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.3', 'Clear width of staircase for', '1', 'Staircase', '');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.301', 'Hospital', '1', 'Staircase', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.302', 'Auditorium', '1', 'Staircase', '');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.30201', 'below 500 capacity', '1', 'Staircase', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.30202', 'above 500 capacity', '1', 'Staircase', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.303', 'Others', '1', 'Staircase', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.4', 'Height of handrial', '1', 'Staircase', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.5', 'Max. no of riser in one Single flight', '1', 'Staircase', 'Nos.');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('1.6', 'Max. head room under staircase from the nosing of the tread', '1', 'Staircase', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('2.1', 'Max. travel distance to exit point in each floor', '2', 'Exit', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('2.2', 'Min. width of exit door including frame', '2', 'Exit', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('2.3', 'Min. height of exit door including frame', '2', 'Exit', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('2.4', 'Shutter opening of exit door to staircase & public passage', '2', 'Exit', '');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('2.5', 'Total width of exit door', '2', 'Exit', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('3.1', 'Min. opening area of window for lighting largest habitable room from external wall', '3', 'Light and Ventilation', 'sq.m.');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('3.2', 'Min. opening area of natural ventilator for largest habitation room from external wall', '3', 'Light and Ventilation', 'sq.m.');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('3.3', 'Min. size of ventilator for water closets and bathroom', '3', 'Light and Ventilation', 'sq.m.');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('4.1', 'Total height of building', '4', 'Lifts', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('4.2', 'Provision for lift', '4', 'Lifts', '');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('4.3', 'No. of lift per bank', '4', 'Lifts', 'Nos.');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('5.1', 'Is there a provision of separate entrance for disabled people next to the primary entrance of a building', '5', 'Requirements for the physically disabled', '');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('5.2', 'Max. gradient for wheen chair ramp at entrance of building', '5', 'Requirements for the physically disabled', '');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('5.3', 'Min. width of wheel chair ramp at entrance of building', '5', 'Requirements for the physically disabled', 'mm');\n"
                + "INSERT INTO architectural_design_b_master (id, building_elements, group_id, group_name, unit) VALUES ('6.1', 'The height of parafet wall & balcony handrill', '6', 'Parapet heights', 'mm');";
        message.db.save(sql);
        sql = "INSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('1.1','Min. tread width of staircase','Staircase','mm excluding nosing','1');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('1.2','Riser height of staircase','Staircase','mm','1');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('1.3','Clear width of staircase','Staircase','mm','1');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('1.4','Height of handrial','Staircase','mm','1');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('1.5','Max. no of riser in one Single flight','Staircase','Nos.','1');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('1.6','Max. head room under staircase from the nosing of the tread','Staircase','mm','1');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('2.1','Max. travel distance to exit point in each floor','Exit','mm','2');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('2.2','Min. width of exit door including frame','Exit','mm','2');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('2.3','Min. height of exit door including frame','Exit','mm','2');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('2.4','Total width of exit door','Exit','mm','2');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('3.1','Min. opening area of window for lighting largest habitable room from external wall','Light and Ventilation','sq.m.','3');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('3.2','Min. opening area of natural ventilator for largest habitation room from external wall','Light and Ventilation','sq.m.','3');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('3.3','Min. size of ventilator for water closets and bathroom','Light and Ventilation','sq.m.','3');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('4.1','Is there a provision of separate entrance for disabled people next to the primary entrance of a building','Requirements for the physically disabled','','4');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('4.2','Max. gradient for wheen chair ramp at entrance of building','Requirements for the physically disabled','','4');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('4.3','Min. width of wheel chair ramp at entrance of building','Requirements for the physically disabled','mm','4');"
                + "\nINSERT INTO architectural_design_c_master(ID,BUILDING_ELEMENTS,GROUP_NAME,UNIT,GROUP_ID) VALUES ('5.1','The height of parafet wall & balcony handrill','Parapet heights','mm','5');";
        message.db.save(sql);

        sql = "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\")	VALUES ('1.0', 'Rating and size', '', '');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('1.1', 'Rating and size', 'sq.mm.', 'Minimum size (sq.mm) of copper cable for light circuit');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('1.2', 'Rating and size', 'sq.mm.', 'Minimum size (sq.mm) of copper cable for power circuit');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('1.3', 'Rating and size', 'watt', 'Wattage of ordinary power socket (2 pin) estimated as');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('1.4', 'Rating and size', 'watt', 'Wattage of ordinary power socket (3 pin) estimated as');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('1.5', 'Rating and size', 'mm.', 'Wall thickness of cast iron switch or regulator boxes');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\")	VALUES ('1.6', 'Rating and size', 'mm.', 'Wall thickness of mild steel sheet switch or regulator boxes for upto 20cm.x30cm.');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('1.7', 'Rating and size', 'mm.', '	Wall thickness of mild steel sheet switch or regulator boxes for upto 20cm.x30cm.');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('1.8', 'Rating and size', 'mm.', 'Depth of the switch or regulator boxes');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\")	VALUES ('2.0', 'Maximum number of Cables in a Condut', '', '');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\")	VALUES ('2.1', 'Maximum number of Cables in a Condut', 'Nos. of cables', 'No. of 2.5 sq.mm. cross-sectional area cable in 20 mm. dia');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('2.2', 'Maximum number of Cables in a Condut', 'Nos. of cables', 'No. of 4 sq.mm. cross-sectional area cable in 20 mm. dia conduit');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('2.3', 'Maximum number of Cables in a Condut', 'Nos. of cables', 'No. of 6 sq.mm. cross-sectional area cable in 20 mm. dia conduit');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('2.4', 'Maximum number of Cables in a Condut', 'Nos. of cables', 'No. of 2.5 sq.mm. cross-sectional area cable in 25 mm.');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\")	VALUES ('2.5', 'Maximum number of Cables in a Condut', 'Nos. of cables', 'No. of 4 sq.mm. cross-sectional area cable in 25 mm. dia conduit	');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('2.6', 'Maximum number of Cables in a Condut', 'Nos. of cables', 'No. of 6 sq.mm. cross-sectional area cable in 25 mm. dia conduit');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\")	VALUES ('2.7', 'Maximum number of Cables in a Condut', 'Nos. of cables', 'No. of 2.5 sq.mm. cross-sectional area cable in 32 mm. dia');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('2.8', 'Maximum number of Cables in a Condut', 'Nos. of cables', 'No. of 4 sq.mm. cross-sectional area cable in 32 mm. dia conduit');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('2.9', 'Maximum number of Cables in a Condut', 'Nos. of cables', 'No. of 6 sq.mm. cross-sectional area cable in 32 mm. dia conduit	');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.0', 'Earthing', '', '');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.1', 'Earthing', '', 'Specified');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.2', 'Earthing', '', 'Diameter of rod electrodes of steel or galvanised iron');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.3', 'Earthing', '', 'Diameter of rod electrodes of copper');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.4', 'Earthing', 'mm.', 'Internal diameter of pipe electrodes of galvanised iron or steel');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.5', 'Earthing', 'mm.', 'Internal diameter of pipe electrodes of cast iron');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.6', 'Earthing', 'mm.', 'The B17length of the rod & pipe electrodes');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.7', 'Earthing', 'mm.', 'Thickness of plate electrodes of galvanised iron or steel');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.8', 'Earthing', 'mm.', 'Thickness of plate electrodes of copper');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.9', 'Earthing', 'mm.', 'Size of plate electrodes of galvanised iron or steel of copper');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('3.10', 'Earthing', 'mm.', 'Depth of the top edge of plate electrodes buried from ground');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('4.0', 'Testing', '', '');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('4.1', 'Testing', '1.5 m.', 'Insulation resistance (Mohm) between earth and the whole system of conductor or any section thereof');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('4.2', 'Testing', '', 'Insulation resistance (Mohm) between the metallic case and all live parts of each rheostat, appliance and sign when they are disconnected.');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('4.3', 'Testing', '', 'Insulation resistance (Mohm) between all the conductors connected to one pole or phase conductor and all the conductor connected to the middle wire or to the neutral or to the other pole of phase conductor');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\") VALUES ('4.4', 'Testing', '', 'The applied dc voltage (Volt) of meggering');\n"
                + "INSERT INTO electrical_design_requirement_master (id, group_name, unit, \"data\")	VALUES ('4.5', 'Testing', 'Mohm', 'Each switch is placed in phase or neutral ?');\n";
        message.db.save(sql);
        sql = "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(2,2,'सुचनाचरण');\n"
                + "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(9,3,'सरजमिन');\n"
                + "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(11,4,'संसोधितबिलभुक्तानी');\n"
                + "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(10,6,'दोस्रोचरण');\n"
                + "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(16,7,'पहिलोसंसोधितबिलभुक्तानी');\n"
                + "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(12,8,'अन्तिमचरण');\n"
                + "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(13,10,'निर्माणसम्पन्‍नप्रमाणपत्र');\n"
                + "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(14,11,'नामसारी');\n"
                + "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(1,1,'निबेदन');\n"
                + "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(15,5,'पहिलोचरण');\n"
                + "INSERT INTO form_group_master(id,group_position,\"name\") VALUES(17,9,'दोस्रोचरणबिलभुक्तानी');";
        message.db.save(sql);
        sql = "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (44, 1.0, 1, 1, 'D', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (205, 3.4, 8, 1, 'D', 7, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (55, 1.0, 1, 1, 'A', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (57, 3.1, 3, 1, 'A', 2, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (88, 23.0, 22, 10, 'B', 21, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (89, 24.0, 23, 10, 'B', 22, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (1, 1.0, 1, 1, 'D', NULL, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (92, 27.0, 25, 10, 'B', 40, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (144, 4.2, 6, 1, 'A', 5, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (94, 29.0, 26, 10, 'B', 40, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (211, 3.4, 8, 1, 'B', 7, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (2, 2.0, 2, 1, 'D', 1, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (97, 9.0, 12, 15, 'D', 11, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (106, 18.0, 17, 15, 'D', 43, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (107, 19.0, 19, 15, 'D', 17, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (4, 1.0, 1, 1, 'B', NULL, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (5, 2.0, 2, 1, 'B', 1, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (108, 20.0, 46, 15, 'D', 19, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (109, 21.0, 20, 15, 'D', 17, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (110, 22.0, 21, 15, 'D', 20, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (112, 24.0, 23, 10, 'D', 22, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (113, 25.0, 24, 10, 'D', 23, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (119, 31.0, 28, 12, 'D', 27, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (120, 32.0, 29, 12, 'D', 28, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (121, 33.0, 30, 12, 'D', 23, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (6, 3.0, 39, 1, 'B', 2, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (123, 32.0, 29, 12, 'B', 28, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (124, 31.0, 28, 12, 'B', 27, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (122, 33.0, 30, 12, 'B', 29, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (9, 1.0, 1, 1, 'A', NULL, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (135, 37.0, 32, 13, 'D', 31, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (10, 2.0, 2, 1, 'A', 1, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (137, 39.0, 34, 13, 'D', 32, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (11, 3.0, 39, 1, 'A', 2, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (12, 4.0, 11, 1, 'A', 39, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (153, 11.0, 13, 2, 'A', 49, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (154, 12.0, 14, 2, 'A', 13, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (173, 31.0, 28, 12, 'A', 27, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (174, 32.0, 29, 12, 'A', 28, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (176, 34.0, 31, 12, 'A', 29, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (177, 35.0, 33, 12, 'A', 30, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (13, 5.0, 12, 1, 'A', 11, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (192, 35.0, 33, 12, 'C', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (136, 38.0, 41, 13, 'D', 32, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (198, 19.0, 19, 15, 'R', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (199, 20.0, 46, 15, 'R', 19, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (155, 13.0, 15, 2, 'A', 14, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (33, 2.0, 51, 13, 'C', 50, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (93, 28.0, 47, 10, 'B', 25, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (95, 30.0, 27, 10, 'B', 26, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (130, 39.0, 34, 12, 'B', 41, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (179, 37.0, 32, 12, 'A', 48, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (193, 36.0, 48, 12, 'C', 33, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (194, 37.0, 32, 12, 'C', 48, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (195, 38.0, 41, 12, 'C', 32, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (190, 29.0, 26, 10, 'C', 47, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (191, 30.0, 27, 10, 'C', 26, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (148, 6.0, 10, 1, 'A', 9, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (149, 7.0, 39, 1, 'A', 10, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (213, 18.0, 17, 15, 'C', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (185, 19.0, 19, 15, 'C', 17, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (3, 4.0, 39, 1, 'D', 2, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (43, 14.0, 51, 13, 'D', 50, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (41, 12.0, 17, 13, 'D', 16, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (37, 8.0, 13, 13, 'D', 49, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (35, 6.0, 12, 13, 'D', 11, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (16, 6.0, 49, 2, 'B', 12, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (17, 7.0, 13, 2, 'B', 49, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (18, 8.0, 14, 2, 'B', 13, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (19, 9.0, 15, 2, 'B', 14, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (23, 13.0, 51, 13, 'B', 50, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (34, 5.0, 11, 13, 'D', 39, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (24, 6.0, 49, 2, 'A', 12, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (25, 7.0, 13, 2, 'A', 49, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (26, 8.0, 14, 2, 'A', 13, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (20, 10.0, 16, 9, 'B', 15, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (22, 12.0, 50, 9, 'B', 17, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (27, 9.0, 15, 2, 'A', 14, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (28, 10.0, 16, 9, 'A', 15, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (29, 11.0, 17, 9, 'A', 16, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (30, 12.0, 50, 9, 'A', 17, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (31, 13.0, 51, 13, 'A', 50, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (32, 1.0, 50, 9, 'C', NULL, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (7, 4.0, 11, 1, 'B', 39, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (45, 2.0, 2, 1, 'D', 1, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (8, 5.0, 12, 1, 'B', 11, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (14, 1.0, 12, 1, 'R', NULL, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (46, 3.1, 3, 1, 'D', 2, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (47, 3.2, 4, 1, 'D', 3, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (56, 2.0, 2, 1, 'A', 1, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (48, 4.1, 5, 1, 'D', 2, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (49, 4.2, 6, 1, 'D', 5, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (58, 1.0, 1, 1, 'B', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (59, 2.0, 2, 1, 'B', 1, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (60, 3.1, 3, 1, 'B', 2, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (61, 3.2, 4, 1, 'B', 3, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (90, 25.0, 24, 10, 'B', 23, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (65, 4.2, 6, 1, 'B', 5, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (21, 11.0, 17, 9, 'B', 16, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (67, 6.0, 10, 1, 'B', 9, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (68, 7.0, 39, 1, 'B', 10, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (69, 8.0, 11, 1, 'B', 39, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (70, 9.0, 12, 1, 'B', 11, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (71, 10.0, 49, 2, 'B', 12, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (72, 11.0, 13, 2, 'B', 49, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (73, 12.0, 14, 2, 'B', 13, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (74, 13.0, 15, 2, 'B', 14, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (147, 3.3, 7, 1, 'D', 2, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (66, 5.0, 9, 1, 'A', 2, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (208, 3.3, 7, 1, 'A', 4, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (81, 9.0, 12, 1, 'R', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (82, 10.0, 49, 1, 'R', 12, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (104, 16.0, 45, 15, 'D', 44, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (105, 17.0, 43, 15, 'D', 45, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (111, 23.0, 22, 10, 'D', 21, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (125, 34.0, 31, 12, 'B', 29, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (126, 35.0, 33, 12, 'B', 31, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (64, 4.1, 5, 1, 'B', 2, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (212, 3.4, 8, 1, 'A', 7, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (139, 41.0, 36, 14, 'B', 35, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (140, 42.0, 37, 14, 'B', 36, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (116, 28.0, 47, 16, 'D', 40, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (117, 29.0, 26, 16, 'D', 40, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (118, 30.0, 27, 16, 'D', 26, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (180, 38.0, 41, 12, 'A', 31, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (181, 39.0, 34, 12, 'A', 41, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (196, 39.0, 34, 12, 'C', 41, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (214, 3.1, 3, 1, 'D', 2, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (162, 20.0, 46, 15, 'A', 19, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (163, 21.0, 20, 15, 'A', 17, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (164, 22.0, 21, 15, 'A', 20, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (165, 23.0, 22, 10, 'A', 21, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (168, 26.0, 40, 10, 'A', 23, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (215, 3.2, 4, 1, 'D', 3, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (216, 3.3, 5, 1, 'D', 2, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (217, 3.4, 6, 1, 'D', 5, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (182, 40.0, 35, 13, 'A', 34, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (183, 41.0, 36, 14, 'A', 35, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (201, 28.0, 47, 10, 'R', 25, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (42, 13.0, 50, 13, 'D', 17, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (200, 27.0, 25, 10, 'R', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (127, 36.0, 48, 12, 'B', 33, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (128, 37.0, 32, 12, 'B', 33, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (129, 38.0, 41, 12, 'B', 32, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (39, 11.0, 15, 13, 'D', 14, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (75, 14.0, 16, 15, 'B', 15, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (76, 15.0, 44, 15, 'B', 16, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (77, 16.0, 45, 15, 'B', 44, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (78, 17.0, 43, 15, 'B', 45, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (79, 18.0, 17, 15, 'B', 43, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (80, 19.0, 19, 15, 'B', 17, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (156, 14.0, 16, 15, 'A', 15, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (158, 16.0, 45, 15, 'A', 44, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (157, 15.0, 44, 15, 'A', 16, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (160, 18.0, 17, 15, 'A', 43, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (161, 19.0, 19, 15, 'A', 17, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (159, 17.0, 43, 15, 'A', 45, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (171, 29.0, 26, 16, 'A', 40, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (40, 10.0, 16, 13, 'D', 15, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (38, 9.0, 14, 13, 'D', 13, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (36, 7.0, 49, 13, 'D', 12, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (15, 2.0, 49, 1, 'R', 12, '2');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (188, 25.0, 25, 10, 'C', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (87, 4.1, 5, 1, 'A', 2, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (52, 5.0, 9, 1, 'D', 2, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (53, 6.0, 10, 1, 'D', 9, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (54, 7.0, 39, 1, 'D', 10, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (209, 5.0, 9, 1, 'B', 2, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (210, 3.3, 7, 1, 'B', 4, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (83, 20.0, 46, 15, 'B', 19, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (84, 3.2, 4, 1, 'A', 3, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (86, 22.0, 21, 15, 'B', 20, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (91, 26.0, 40, 10, 'B', 23, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (96, 8.0, 11, 15, 'D', 39, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (98, 10.0, 49, 15, 'D', 12, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (99, 11.0, 13, 15, 'D', 49, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (100, 12.0, 14, 15, 'D', 13, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (101, 13.0, 15, 15, 'D', 14, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (102, 14.0, 16, 15, 'D', 15, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (103, 15.0, 44, 15, 'D', 16, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (85, 21.0, 20, 15, 'B', 17, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (131, 40.0, 35, 13, 'B', 34, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (114, 26.0, 40, 16, 'D', 23, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (115, 27.0, 25, 16, 'D', 40, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (134, 34.0, 31, 13, 'D', 29, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (132, 35.0, 33, 13, 'D', 31, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (133, 36.0, 48, 13, 'D', 33, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (138, 40.0, 35, 13, 'D', 34, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (141, 41.0, 36, 14, 'D', 35, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (142, 42.0, 37, 14, 'D', 36, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (143, 43.0, 38, 14, 'D', 38, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (150, 8.0, 11, 1, 'A', 39, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (151, 9.0, 12, 1, 'A', 11, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (152, 10.0, 49, 2, 'A', 12, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (166, 24.0, 23, 10, 'A', 22, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (167, 25.0, 24, 10, 'A', 23, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (169, 27.0, 25, 10, 'A', 24, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (178, 36.0, 48, 12, 'A', 25, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (189, 26.0, 47, 10, 'C', 25, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (175, 33.0, 30, 12, 'A', 29, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (184, 42.0, 37, 14, 'A', 36, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type) 	VALUES (187, 22.0, 21, 15, 'C', 20, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (204, 20.0, 46, 15, 'C', 19, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (186, 21.0, 20, 15, 'C', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (197, 40.0, 35, 13, 'C', 34, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (202, 35.0, 33, 12, 'R', NULL, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (203, 36.0, 48, 12, 'R', 33, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (170, 28.0, 47, 16, 'A', 25, '1');\n"
                + "INSERT INTO form_group (id, group_position, form_id, group_id, user_type, previous_form, group_type)    VALUES (172, 30.0, 27, 16, 'A', 26, '1');";
        message.db.save(sql);
        sql = "    INSERT INTO menu_master (id, menu, menu_type, url) VALUES (4, 'Add Building Permit', 'P', '/user/building-permit');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (3, 'File Storage Category Setup', 'S', '/admin/file-storage-category');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (5, 'Purano Ghar Building Permit Add', 'P', '/user/building-permit-purano');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (6, 'Add Storey Task', 'P', '/user/completed-list');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (7, 'Namsari Task List', 'P', '/user/task-search/namsari');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (8, 'Add Storey Task List', 'P', '/user/task-search/talla-thap');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (9, 'Naya Nirman Task List', 'P', '/user/task-search/naya-nirman');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (10, 'Purano Ghar Task List', 'P', '/user/task-search/purano-ghar');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (11, 'Add User', 'S', '/admin/add-user');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (12, 'Add Designer', 'S', '/admin/designer');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (13, 'Change Designer', 'S', '/admin/change-designer');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (14, 'Add FAQ', 'S', '/admin/add-faq');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (15, 'Add Contacts', 'S', '/admin/add-contacts');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (16, 'Add Downloads', 'S', '/admin/downloads');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (17, 'Add Masons', 'S', '/admin/masons-list');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (18, 'Add Form Group', 'S', '/admin/form-group');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (19, 'Form Name', 'S', '/admin/form-name');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (20, 'Forwarding Setup', 'S', '/admin/forward');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (21, 'Road Set Back', 'S', '/admin/road-set-back');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (22, 'Other Set Back', 'S', '/admin/other-set-back');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (23, 'Class Group Setup', 'S', '/admin/class-group');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (24, 'Has Revised Setup', 'S', '/admin/has-revised');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (25, 'Architecture Class B Setup', 'S', '/admin/archi-b-master');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (26, 'Architecture Class C Setup', 'S', '/admin/archi-c-master');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (27, 'Electrical Design Setup', 'S', '/admin/electrical-design-master');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (28, 'Map Check Master Setup', 'S', '/admin/map-check-master');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (29, 'Rajasow Rate Setup', 'S', '/admin/rajasow-setup');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (31, 'Organization Setup', 'S', '/admin/organization');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (32, 'Add Fiscal Year', 'S', '/admin/add-fiscal-year');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (33, 'Ward Setup', 'S', '/admin/ward-setup');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (2, 'Talla Thap Setup', 'P', '/user/talla-thap-setup');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (1, 'Namsari Setup', 'P', '/user/namsari-setup');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (30, 'Discard Application', 'P', '/user/discard-setup');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (34, 'म्याद थप्ने सेटप', 'P', '/user/myad-setup');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (35, 'म्याद थप्ने स्वीकृत', 'P', '/user/myadApprove-setup');\n"
                + "INSERT INTO menu_master (id, menu, menu_type, url) VALUES (36, 'User Type Setup', 'S', '/admin/user-type-setup');";
        message.db.save(sql);
        sql = "INSERT INTO organization_user (login_id, address, db_password, education_qualification, email, id, join_date, license_no, mobile, municipal_reg_no, photo, signature, stamp, status, token, user_name, valid_date, user_type,  consultancy_name) VALUES ('CHIEF', 'naya thay', 'CHIEFADMIN@987', '123', 'CHIEF', 1, '2019-10-23', '12', '12', '12', '/userdocument/id1/photo.png', '/userdocument/id1/signature.png', '/userdocument/id1/stamp.png', 'Y', NULL, 'ADMIN', NULL, 'C', 'N/A');";
        message.db.save(sql);
        sql = "INSERT INTO organization_user (login_id, address, db_password, education_qualification, email, id, join_date, license_no, mobile, municipal_reg_no, photo, signature, stamp, status, token, user_name, valid_date, user_type,consultancy_name) VALUES ('DESIGNER', 'designer', 'designer@987', 'designer', 'designer', 3, '2019-04-01', '9199191', '981918', '929292', '/userdocument/id3/photo.png', NULL, NULL, 'Y', NULL, 'designer', NULL, 'D', 'N/A');";
        message.db.save(sql);
        sql = "INSERT INTO organization_user (login_id, address, db_password, education_qualification, email, id, join_date, license_no, mobile, municipal_reg_no, photo, signature, stamp, status, token, user_name, valid_date, user_type,consultancy_name) VALUES ('SUBENGINEER', 'SubEngineer', 'SubEngineer@987', 'SubEngineer', 'SubEngineer', 2, '2012-12-12', '988282', '9818228228', '98181828', '/userdocument/id2/photo.png', NULL, NULL, 'Y', NULL, 'SubEngineer', NULL, 'B', 'N/A');";
        message.db.save(sql);
        sql = "INSERT INTO organization_user (login_id, address, db_password, education_qualification, email, id, join_date, license_no, mobile, municipal_reg_no, photo, signature, stamp, status, token, user_name, valid_date, user_type,consultancy_name) VALUES ('ENGINEER', 'Engineer', 'Engineer@987@987', 'Engineer@987', 'Engineer', 5, '2076-12-12', '09292', '1111111', '92929', NULL, NULL, NULL, 'Y', '', 'Engineer', NULL, 'A', 'N/A');";
        message.db.save(sql);
        sql = "INSERT INTO organization_user (login_id, address, db_password, education_qualification, email, id, join_date, license_no, mobile, municipal_reg_no, photo, signature, stamp, status, token, user_name, valid_date, user_type,consultancy_name) VALUES ('RAJASOW', 'Rajasow', 'Rajasow@987', 'Rajasow', 'Rajasow', 6, '2012-12-12', '9898', '998', '9898', NULL, NULL, NULL, 'Y', '', 'Rajasow', NULL, 'R', 'N/A');";
        message.db.save(sql);
        sql = "INSERT INTO organization_user (login_id, address, db_password, education_qualification, email, id, join_date, license_no, mobile, municipal_reg_no, photo, signature, stamp, status, token, user_name, valid_date, user_type,consultancy_name) VALUES ('ADMIN', 'ADMIN', 'EBPSADMIN@987', 'ADMIN', 'ADMIN', 6, '2012-12-12', '9898', '998', '9898', NULL, NULL, NULL, 'Y', '', 'ADMIN', NULL, 'ADM', 'N/A');";
        message.db.save(sql);
        sql = "INSERT INTO organization_user (login_id, address, db_password, education_qualification, email, id, join_date, license_no, mobile, municipal_reg_no, photo, signature, stamp, status, token, user_name, valid_date, user_type,consultancy_name) VALUES ('TADMIN', 'TADMIN', 'TEBPSADMIN@987', 'TADMIN', 'TADMIN', 6, '2012-12-12', '9898', '998', '9898', NULL, NULL, NULL, 'Y', '', 'TADMIN', NULL, 'TADM', 'N/A');";
        message.db.save(sql);
        sql = "INSERT INTO organization_user (login_id, address, db_password, education_qualification, email, id, join_date, license_no, mobile, municipal_reg_no, photo, signature, stamp, status, token, user_name, valid_date, user_type,consultancy_name) VALUES ('AMIN', 'KTM', 'AMIN@987', 'MASTER', 'amin@gmail.com', 7, '2019-04-01', '123', '9847012345', '3', NULL, NULL, NULL, 'Y', '', 'Amin', NULL, 'AD', 'N/A');";
        message.db.save(sql);

        sql = "CREATE TABLE public.ad_bs_calender (ad_date date, bs_date varchar(10) NOT NULL, \"day\" varchar(3), PRIMARY KEY (ad_date));";
        message.db.save(sql);
        System.out.println(message.db.getMsg());
        if (!message.db.getMsg().equalsIgnoreCase("already exists")) {

            sql = "DELETE FROM public.ad_bs_calender";
            message.db.save(sql);
            Date date = DateConvert.toDate("1943-04-14");
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            String adDate, bsDate;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat day = new SimpleDateFormat("EEE");

            try {
                Runtime.getRuntime().gc();
            } catch (Exception e) {
            }
            try {
                System.gc();
            } catch (Exception e) {
            }
            sql = "";
            while (true) {
                c.add(Calendar.DATE, 1);
                date = c.getTime();
                adDate = df.format(c.getTime());
                bsDate = DateConvert.adtobsDate(adDate);

                if (bsDate.contains("01-01")) {
                    message.db.save(sql);
                    System.out.println(bsDate);
                    sql = "";

                    try {
                        Runtime.getRuntime().gc();
                    } catch (Exception e) {
                    }
                }
                sql = sql + "\nINSERT INTO public.ad_bs_calender (ad_date,bs_date,day) VALUES ('" + adDate + "', '" + bsDate + "', '" + day.format(date) + "');";

                if (adDate.equalsIgnoreCase("2024-04-12")) {
                    message.db.save(sql);
                    break;
                }
            }
        }
        System.out.println(row);
        sql = "CREATE OR REPLACE FUNCTION get_bsdate(\n"
                + "	addate character varying)\n"
                + "    RETURNS character varying\n"
                + "    LANGUAGE 'plpgsql'\n"
                + "\n"
                + "    COST 100\n"
                + "    VOLATILE \n"
                + "AS $BODY$\n"
                + "DECLARE bsdate varchar(10);\n"
                + "BEGIN\n"
                + "SELECT bs_date INTO bsdate FROM public.ad_bs_calender WHERE to_char(ad_date, 'YYYY-MM-DD')=addate;\n"
                + "\n"
                + "RETURN bsdate;\n"
                + "END; $BODY$;";
        message.db.save(sql);
        sql = "CREATE OR REPLACE FUNCTION get_bsdate(\n"
                + "	addate date)\n"
                + "    RETURNS character varying\n"
                + "    LANGUAGE 'plpgsql'\n"
                + "\n"
                + "    COST 100\n"
                + "    VOLATILE \n"
                + "AS $BODY$\n"
                + "DECLARE bsdate varchar(10);\n"
                + "BEGIN\n"
                + "SELECT bs_date INTO bsdate FROM public.ad_bs_calender WHERE ad_date=addate;\n"
                + "\n"
                + "RETURN bsdate;\n"
                + "END; $BODY$;";
        message.db.save(sql);
        sql = "CREATE OR REPLACE FUNCTION get_bsdate(\n"
                + "	addate timestamp without time zone)\n"
                + "    RETURNS character varying\n"
                + "    LANGUAGE 'plpgsql'\n"
                + "\n"
                + "    COST 100\n"
                + "    VOLATILE \n"
                + "AS $BODY$\n"
                + "DECLARE bsdate varchar(10);\n"
                + "BEGIN\n"
                + "SELECT bs_date INTO bsdate FROM public.ad_bs_calender WHERE to_char(ad_date, 'YYYY-MM-DD')=to_char(addate, 'YYYY-MM-DD');\n"
                + "\n"
                + "RETURN bsdate;\n"
                + "END; $BODY$;";
        message.db.save(sql);
        message.list = da.getAll("from OrganizationMaster");

        return message.list.get(0);
    }

    @Override
    public Object Save(OrganizationMaster obj, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isValid()) {
            return new Message().respondWithError("Authorization Error");
        }
        if (!td.getUserType().equalsIgnoreCase("C")) {
            return message.respondWithError("you have not access to use this feature");
        }
        int id = 0;
        try {
            try {
                if (obj.getId() > 0) {
                    id = obj.getId();
                }
            } catch (Exception e) {
            }
            if (id == 0) {
                try {
                    sql = "SELECT coalesce(MAX(ID),0)+1 AS id FROM form_group_master";
                    message.map = (Map) new DB().getRecord(sql).get(0);
                    obj.setId(Integer.parseInt(message.map.get("id").toString()));
                } catch (Exception e) {
                    return message.respondWithError("connection error or invalid table name");
                }
            }
            row = da.save(obj);
            msg = da.getMsg();
            if (row > 0) {
                return message.respondWithMessage("Success");
            } else {
                if (msg.indexOf("PRIMARY") >= 0) {
                    msg = "This Record Alredy Exist";
                }
                return message.respondWithError(msg);
            }

        } catch (Exception e) {
            return message.respondWithError(e.getMessage());
        }
    }

    @Override
    public Object update(OrganizationMaster obj, String Authorization) {
        JWTToken td = new JWTToken(Authorization);
        if (!td.isStatus()) {
            return message.respondWithError("invalid token");
        }
        obj.setId(1);
        row = da.update(obj);
        msg = da.getMsg();
        if (row > 0) {
            return message.respondWithMessage("Success");
        } else if (msg.contains("Duplicate entry")) {
            msg = "This record already exist";
        } else if (msg.contains("foreign key")) {
            msg = "this record already used in reference tables, Cannot delete of this record";
        }
        return message.respondWithError(msg);
    }

}
