package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationForm {
//SELECT CONCAT(' {"',id,'", "',page_code,'", "',"name",'", "',"position",'", "',view_url,'"},') FROM form_name_master ORDER BY id

public static String data[][] = {
    {"1", "1PAGE", "भवन/घर नक्सा निर्माण कार्यको इजाजत सन्बन्‍धमा निवेदन", "1", " /user/forms/map-permit-application-edit"},
    {"2", "AKA", "अनुसुची क", "2", "/user/forms/design-approval"},
    {"3", "BAKHA", "अनुसुची ख अर्किटेक्चरल B", "3.10", "/user/forms/architecture-design-classB-form"},
    {"4", "BSKHA", "अनुसुची ख स्ट्रक्चरल B", "3.11", "/user/forms/structure-design-classB-form"},
    {"5", "CAKHA", "अनुसुची ख  अर्किटेक्चरल C", "3.21", "/user/forms/architecture-design-classc-form"},
    {"6", "CSKHA", "अनुसुची ख स्ट्रक्चरल C", "3.22", "/user/forms/structure-design-classc-form"},
    {"7", "STD", "स्यानिटरी डिजाइन", "3.33", "/user/forms/sanitary-design"},
    {"8", "ELD", "इलेक्ट्रिकल डिजाइन", "3.31", "/user/forms/electrical-design"},
    {"9", "AGA", "अनुसुची ग", "4", "/user/forms/anusuchiga-view"},
    {"10", "AGHA", "अनुसुची घ", "5", "/user/forms/anusuchigha-view"},
    {"11", "NJPP", "नक्सा पास पछि जाँच गरी प्रतिवेदन", "7", "/user/forms/map-check-report-view"},
    {"12", "RJB", "राजस्व विवरण", "8", "/user/forms/rajaswo-detail-view"},
    {"13", "NNA", "संधियारको नाममा जारी भएको सुचना", "12", "/user/forms/notice-period-for-15-days-view"},
    {"14", "SBBT", "सुचना बुझाएको भर्पाई टास तथा मुचुलका", "13", "/user/forms/notice-payment-application-view"},
    {"15", "SPS", "सर्जमिन गरी पेश गर्ने सम्बन्धमा", "14", "/user/forms/surjamin-muchulka-view"},
    {"16", "GNSM", "घर नक्सा सरजमिन मुचुल्का", "16", "/user/forms/gharnaksha-surjamin-muchulka-view"},
    {"17", "PRAP", "प्राविधिक प्रतिवेदन", "20", "/user/forms/prabhidik-pratibedhan-pesh-view"},
    {"18", "SMNB1", "संसोधन विवरण बिल भुक्तानी", "21", "/user/forms/sansodhan-gari-nibedan"},
    {"19", "STPA", "संसोधन टिप्पणी र आदेश", "22", "/user/forms/sansodhan-tippani"},
    {"20", "PLNTH", "प्लिन्थ लेभलसम्म निर्माण निमित्त इजाजत प्रधान गर्ने", "25", "/user/forms/noteorder-pilength-level-view"},
    {"21", "PCIP", "प्रथम चरणको अस्थायी निर्माण इजाजत पत्र", "26", "/user/forms/allowance-paper-view"},
    {"22", "SNIPB", "सुपरस्ट्रक्चरको निर्माण कार्यको लागि इजाजतबारे", "27", "/user/forms/super-structure-build-view"},
    {"23", "PSGPR", "प्लिन्थ लेभलसम्मको निर्माण कार्य सम्पन्नबारे घरधनीको तर्फबाट राखिएको सुपरिवेक्षकको प्रतिवेदन", "28", "/user/forms/plinthLevel-OwnerRepresentation-view"},
    {"24", "SMNB2", "संसोधन  गरी निवेदन विवरण (पहिलो चरण)", "29", "/user/forms/sansodhan-bibaran-pahilo"},
    {"25", "STPA2", "संसोधनको टिप्पणी आदेश", "33", "/user/forms/sansodhanko-tippani-adesh"},
    {"26", "SNNTP", "सुपरस्ट्रक्चर निर्माणका निमित्त इजाजत प्रदान गर्ने टिप्पणी आदेश", "28.2199993", "/user/forms/superStructure-noteOrder-view"},
    {"27", "DCIP", "दोस्रो चरणको इजाजत पत्र", "29.1100006", "/user/forms/superStructure-construction-view"},
    {"28", "NSBN", "निर्माण कार्य सम्पन्‍न प्रमाण पत्र बारे", "301", "/user/forms/dosrocharan-abedan"},
    {"29", "SNSGR", "सुपरस्ट्रक्चरको निर्माण कार्य सम्पन्‍नबारे घरधनीको तर्फबाट राखिएको सुपरिवेक्षकका प्रतिवेदन", "311", "/user/forms/dosrocharan-supervisor"},
    {"30", "SSSB", "सुपरस्ट्रक्चरको संसोधन विवरण", "321", "/user/forms/superstructureko-tippaniadesh"},
    {"31", "SIPP", "संसोधन/सुपरस्ट्रक्चर इजाजत प्रतिवेदन पेश गरेको बारे", "31", "/user/forms/Samsodhit-Super-structure-Ijajat-Pratibedan"},
    {"32", "SSIS", "संसोधन सुपरस्ट्रक्चर इजाजत सम्बन्धमा", "35", "/user/forms/sansodhan-super-structure-ijjaat"},
    {"33", "SSTP", "संसोधन सुपरस्ट्रक्चर टिप्पणी आदेश", "32", "/user/forms/sansodhansuper-tippani-adesh"},
    {"34", "NSPT", "निर्माण कार्य सम्पन्न प्रमाण-पत्र दिने टिप्पणी आदेश", "37", "/user/forms/certificate-instruction-view"},
    {"35", "BNPP", "भवन निर्माण सम्पन्‍न प्रमाण पत्र", "38", "/user/forms/building-finish-certificate-view"},
    {"36", "GNNS", "घर नक्सा नामसारी सम्बन्धमा", "39", "/user/forms/certificate-note"},
    {"37", "NSTA", "नामसारी टिप्पणी आदेश", "40", "/user/forms/namsari-tippani-ades-view"},
    {"38", "NSPP", "नामसारी प्रमाण-पत्र", "41", "/user/forms/building-build-certificate-view"},
    {"39", "NBPGB", "नक्सा बनाउने प्रविधिकले गर्नु पर्ने विवरण", "6", "/user/forms/map-technical-description-view"},
    {"40", "PSNPR", "प्लिन्थ लेभल सम्म निर्माण सम्पन्‍न बारे न.पा. प्राविधिकको राय", "30", "/user/forms/plinthLevel-techapplication-view"},
    {"41", "BNSP", "भवन निर्माण सम्पन्‍न प्रतिवेदन सम्बन्धमा", "36", "/user/forms/dosrocharan-prabidhik-view"},
    {"43", "ADSM", "अमिनको स्थलगत निरीक्षण प्रतिबेदन", "19", "   /user/forms/gharnaksha-surjamin-muchulka-view"},
    {"44", "GCWAL", "घर नक्सा कबुलियतमा", "17", "/user/forms/ghar-compoundwallko-naap"},
    {"45", "AGNKA", "अमिन कबुलियतमा", "18", "/user/forms/ghar-naksako-kabuliyatnama"},
    {"46", "SBV", "संसोधन बिल भुक्तानी", "0", "/user/forms/samsodhan-bill-vuktani"},
    {"47", "", "पहिलो चरण संसोधन बिल भुक्तानी", "0", "/user/forms/pahilo-charan-bill-vuktani"},
    {"48", "", "दोस्रो  चरण संसोधन बिल भुक्तानी", "0", "/user/forms/dosrocharan-bill-vuktani"},
    {"49", "", "राजस्व विभागले राजश्‍वको भौचर राख्ने", "10", "/user/forms/rajaswo-voucher"},   
    {"50","","पुरानो घर निर्माण कार्य सम्पन्न प्रमाण-पत्र दिने टिप्पणी आदेश","","/user/forms/purano-ghar-nirman-tippani"},
    {"51","","पुरानो घर भवन निर्माण सम्पन्‍न प्रमाण पत्र","","/user/forms/purano-ghar-sampanna-pramanpatra"},
    {"52","","सुपरस्ट्रक्चरको निर्माण कार्य सम्पन्न बारे स्थलगत निरीक्षण गर्ने न. पा. प्रविधिको राय","","/user/forms/superstructureko-nirmaan-karya"},
  {"53","","म्याद थप गरिएको सम्बन्धमा","","/user/forms/myadThap-approval"},
  {"54","","वारेसनामा","","/user/forms/waresnama-view"},
  {"55","","मन्जुरीनामा","","/user/forms/manjurinama-view"},
  {"56","","कवुलियतनामा","","/user/forms/kabuliyatnama-view"},
  {"57","","अमिनको स्थलगत निरीक्षण प्रतिवेदन","","/user/forms/aminko-sthalgat-pratibedan"},
  {"58","","तला थप गर्नका निमित्त इजाजत प्रदान गर्न टिप्पणी आदेश","","/user/forms/talla-thap-ijajat-tippani-ades"},
  {"59","","तला थपका लागि इजाजत-पत्र पाउँ","","/user/forms/talla-thap-ijajat-request"},
  {"60","","तला थप (सुपर स्ट्रक्चरको) निर्माण इजाजत-पत्र","","/user/forms/talla-thap-ijajat-patra"},
  {"61","","No Objection Sheet","","/user/forms/no-objection-sheet"},
  {"62","","ले-आउट गरी प्रतिवेदन पेश गर्ने बारे","","/user/forms/layout-gari-pratibedan-request"},
  {"63","","ले-आउट गरी प्रतिवेदन पेश गरेको बारे","","/user/forms/layout-gari-pratibedan-ijajat"},
  {"64","","भवन नक्सा फायल पठाएको बारे ","","/user/forms/naksa-file-pathayeko-bare"},
  {"65","","नामसारी बिल भुक्तानी","","/user/forms/namsari-bill-vuktani"},
  {"66","","म्याद थप गरी दिनु हुन गरी","","/user/forms/myadThap-request"},
  {"67","","नयाँ रेकर्ड पठाएको/विवरण पठाएको/नामसारी सम्बन्धमा","","/user/forms/naya-record-sambandhama"},
  {"68","","निर्माण कार्य सपन्नको प्रमाण पत्र सहित धरौटी फिर्ता दिने टिप्पणी आदेश","","/user/forms/dharauti-firta-tippani-aadesh"},
  {"69","","धरौटी रकम फिर्ता दिने बारे","","/user/forms/dharauti-rakam-phirta-bare"},
  {"70","","निर्माण कार्य सम्पन्न प्रमाण पत्र तथा धरौटी फिर्ता बारे","","/user/forms/nirman-pramanpatra-dharauti"},
  {"71","","नक्शा बनाउने प्राविधिक दारा मन्जुरी पत्र","","/user/forms/architecture-designer-manjuri-patra"},
  {"72","","भवन डिजाइन गर्ने प्राविधिकद्धारा मन्जुरी पत्र","","/user/forms/structural-designer-manjuri-patra"},
  {"73","","संसोधित सुपरस्ट्रक्चर ईजाजत सम्बन्धमा","","/user/forms/sansodhit-super-structure-ijajat-sambandhama"},
  {"74","","Super Structure Supervision Report","","/user/forms/super-structure-supervision-report"},
  {"75","","Certificate Supervision Reportर","","/user/forms/certificate-supervision-report"},
  {"76","","Earthquake Safety No Objection Sheet","","/user/forms/earthquake-safety-no-objection-sheet"},
  {"77","","Land Area Calculation","","/user/forms/land-area-calculation-of-site-plan"},
  {"78","","Form to be filled by Designer","","/user/forms/form-to-be-filled-by-designer"},
  {"79","","Suchana Taas Gari Muchulka","","/user/forms/suchana-tas-gari-muchulka-pathaidina"},
  {"80","","Pratham Charan Kaam Garne Anumati","","/user/forms/pratham-charan-anumati-request"},
  {"81","","Nirman Sampanna Praman Patra and Dharauti firta","",""},
  {"82","","Pratibeydan Sambandhama","","/user/forms/certificate-pratibedan-sambandhama"},
  {"83","","Pratibeydan Pesh Gareko Bare","","/user/forms/certificate-pratibedan-pesh-bare"},
  {"84","","Karyalaya Prayojan 15 Days Notice","","/user/forms/notice-15-days-karyalaya-prayojan"},
  {"85","","High Rised Buildings","","/user/forms/for-high-rised-buildings"},
  {"86","","Multi Storey Forensic","","/user/forms/for-multi-storey"},
  {"87","","No Objection Certificate","","/user/forms/no-objection-certificate"},
  {"88","","Sthalgat Nirikshyan Gari Pesh","","/user/forms/sthalgat-nirikshyan-gari-pesh"},
  {"89","","Naksa Pass Certificate","","/user/forms/naksa-pass-certificate"},
  {"90","","Layout Tatha Sarjamin Gari File Firta","","/user/forms/layout-tatha-sarjmin-gari-file-firta"}
};


private static Map formId = new HashMap();
private static Map formCode = new HashMap();

static {
    for (int i = 0; i < data.length; i++) {
        formId.put(data[i][0], data[i]);
        formCode.put(data[i][1], data[i]);
    }
}

public static Map getById(String id) {
    Map map = new HashMap();
    try {
        String dd[] = (String[]) formId.get(id);
        map.put("id", dd[0]);
        map.put("code", dd[1]);
        map.put("formName", dd[2]);
        map.put("position", dd[3]);
        map.put("viewURL", dd[4]);
    } catch (Exception e) {
        String dd[] = (String[]) formId.get("1");
        map.put("id", dd[0]);
        map.put("code", dd[1]);
        map.put("formName", dd[2]);
        map.put("position", dd[3]);
        map.put("viewURL", dd[4]);
    }
    return map;
}

public static Map getByCode(String code) {
    Map map = new HashMap();
    try {
        String dd[] = (String[]) formCode.get(code);
        map.put("id", dd[0]);
        map.put("code", dd[1]);
        map.put("formName", dd[2]);
        map.put("position", dd[3]);
        map.put("viewURL", dd[4]);
    } catch (Exception e) {
        map.put("error", "invalid form code");
    }
    return map;
}

public static List getMenu() {
    Map map;
    List l = new ArrayList();
    for (int i = 0; i < data.length; i++) {
        map = new HashMap();
        map.put("id", data[i][0]);
        map.put("formName", data[i][2]);
        map.put("viewURL", data[i][4]);
        l.add(map);
    }

    return l;
}
}
