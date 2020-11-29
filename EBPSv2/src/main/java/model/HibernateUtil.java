package model;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.application.ApplicationHistory;
import com.model.application.ApplicationsComments;
import com.model.application.BuildingMemberDetails;
import com.model.application.BuildingPermitApplication;
import com.model.application.BuildingPermitFloor;
import com.model.application.BuildingPermitSurrounding;
import com.model.application.forwardStatus;
import com.model.dynamic.ApplicationStatus;
import com.model.dynamic.EbpsColumns;
import com.model.dynamic.EbpsTables;
import com.model.dynamic.FormFields;
import com.model.dynamic.FormPermissions;
import com.model.dynamic.Status;
import com.model.dynamic.vocabulary.Vocabulary;
import com.model.dynamic.vocabulary.VocabularyDetails;
import com.model.processing.FileStorageApplication;
import com.model.processing.RajaswaEntry;
import com.model.setup.WardMaster;
import com.model.utility.ApplicationForwardingSetup;
import com.model.utility.ChangeDesigner;
import com.model.utility.CheckClassGroup;
import com.model.utility.FileStorageCategory;
import com.model.utility.FiscalYear;
import com.model.utility.FormGroup;
import com.model.utility.FormGroupMaster;
import com.model.utility.FormNameMaster;
import com.model.utility.OrganizationMaster;
import com.model.utility.OrganizationUser;
import com.model.utility.OrganizationUserRequest;
import com.model.utility.UserTypeMaster;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	private static Properties props;

	public static void init() {
		try {
			Properties prop = new Properties();

			prop.setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/phoenix?currentSchema=stg_test_ebps&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=true");
			prop.setProperty("hibernate.connection.username", "phoenix");
			prop.setProperty("hibernate.connection.password", "manager@123");
			
//			prop.setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/ebpsv2?currentSchema=test_ebps&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=true");
//            prop.setProperty("hibernate.connection.username", "root");
//            prop.setProperty("hibernate.connection.password", "root");
            
			prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
			prop.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			prop.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
			prop.setProperty("hibernate.default_schema", "stg_test_ebps");
			prop.setProperty("hibernate.hbm2ddl.auto", "update");
			prop.setProperty("hibernate.show_sql", "true");

			/*sessionFactory = new Configuration().addProperties(prop)
					.addAnnotatedClass(BuildingPermitApplicationNameTransafer.class)
					.addAnnotatedClass(ApplicationActivity.class).addAnnotatedClass(BuildingPermitApplication.class)
					.addAnnotatedClass(BuildingPermitSurrounding.class).addAnnotatedClass(BuildingMemberDetails.class)
					.addAnnotatedClass(BuildingPermitFloor.class).addAnnotatedClass(ApplicationsComments.class)
					.addAnnotatedClass(ApplicationHistory.class).addAnnotatedClass(OrganizationMaster.class)
					.addAnnotatedClass(OrganizationUser.class).addAnnotatedClass(UserTypeMaster.class)
					.addAnnotatedClass(FiscalYear.class).addAnnotatedClass(DesignerRenewStatus.class)
					.addAnnotatedClass(FormGroup.class).addAnnotatedClass(FileStorageCategory.class)
					.addAnnotatedClass(FormNameMaster.class).addAnnotatedClass(FormGroupMaster.class)
					.addAnnotatedClass(ArchitecturalDesignBMaster.class)
					.addAnnotatedClass(ArchitecturalDesignCMaster.class)
					.addAnnotatedClass(ElectricalDesignRequirementMaster.class).addAnnotatedClass(SanitaryDesign.class)
					.addAnnotatedClass(AminiKabuliyatnama.class).addAnnotatedClass(AnusuchiKa.class)
					.addAnnotatedClass(ArchitecturalDesignB.class).addAnnotatedClass(ArchitecturalDesignBDetails.class)
					.addAnnotatedClass(ArchitecturalDesignC.class).addAnnotatedClass(ArchitecturalDesignCDetails.class)
					.addAnnotatedClass(StructureDesignB.class).addAnnotatedClass(StructureDesignC.class)
					.addAnnotatedClass(ElectricalDesign.class).addAnnotatedClass(ElectricalDesignDetails.class)
					.addAnnotatedClass(AnusuchiGa.class).addAnnotatedClass(AnusuchiGha.class)
					.addAnnotatedClass(UnitConversion.class).addAnnotatedClass(RajaswaEntry.class)
					.addAnnotatedClass(MapCheckReportMaster.class).addAnnotatedClass(MapCheckReport.class)
					.addAnnotatedClass(MapCheckReportDetails.class).addAnnotatedClass(MapTechnicalDescription.class)
					.addAnnotatedClass(NoticePeriodFor15Days.class).addAnnotatedClass(AminiInquiry.class)
					.addAnnotatedClass(GharCompoundWall.class).addAnnotatedClass(AminiKabuliyatnama.class)
					.addAnnotatedClass(FileStorageApplication.class).addAnnotatedClass(NoticePaymentApplication.class)
					.addAnnotatedClass(SurjaminMuchulka.class).addAnnotatedClass(GharnakshaSurjaminMuchulka.class)
					.addAnnotatedClass(GharNaksakoKabuliyatnama.class).addAnnotatedClass(AminkoPratibedan.class)
					.addAnnotatedClass(PrabhidikPratibedhanPesh.class).addAnnotatedClass(NoteorderPilengthLevel.class)
					.addAnnotatedClass(AllowancePaper.class).addAnnotatedClass(SuperStructureBuild.class)
					.addAnnotatedClass(PlinthLevelOwnerRepresentation.class)
					.addAnnotatedClass(PlinthLevelTechapplication.class)
					.addAnnotatedClass(SuperStructureNoteOrder.class)
					.addAnnotatedClass(SuperStructureConstruction.class).addAnnotatedClass(DosrocharanSupervisor.class)
					.addAnnotatedClass(DosrocharanAbedan.class).addAnnotatedClass(DosrocharanPrabidhik.class)
					.addAnnotatedClass(CertificateInstruction.class)
					.addAnnotatedClass(SamsodhitSuperStructureIjajatPratibedan.class)
					.addAnnotatedClass(SansodhansuperTippaniAdesh.class)
					.addAnnotatedClass(SansodhanSuperStructureIjjaat.class)
					.addAnnotatedClass(GharCompoundwallkoNaap.class).addAnnotatedClass(SansodhanGariNibedan.class)
					.addAnnotatedClass(SansodhanTippani.class).addAnnotatedClass(SansodhanBibaranPahilo.class)
					.addAnnotatedClass(SansodhankoTippaniAdesh.class)
					.addAnnotatedClass(SuperstructurekoTippaniadesh.class).addAnnotatedClass(ApplicationAction.class)
					.addAnnotatedClass(RajaswaVoucher.class).addAnnotatedClass(ApplicationForwardingSetup.class)
					.addAnnotatedClass(BuildingBuildCertificate.class)
					.addAnnotatedClass(BuildingFinishCertificate.class).addAnnotatedClass(DosrocharanBillVuktani.class)
					.addAnnotatedClass(PahilocharanBillVuktani.class).addAnnotatedClass(SansodhanBillVuktani.class)
					.addAnnotatedClass(PuranoGharNirmanTippani.class)
					.addAnnotatedClass(PuranoGharSampannaPramanPatra.class).addAnnotatedClass(NamsariTippaniAdes.class)
					.addAnnotatedClass(CertificateNote.class).addAnnotatedClass(SuperstructurekoNirmanKarya.class)
					.addAnnotatedClass(RoadSetBackMaster.class).addAnnotatedClass(OtherSetBackMaster.class)
					.addAnnotatedClass(FrequentlyAskedQuestion.class).addAnnotatedClass(PublicDownloads.class)
					.addAnnotatedClass(Contacts.class).addAnnotatedClass(CheckClassGroup.class)
					.addAnnotatedClass(forwardStatus.class).addAnnotatedClass(ChangeDesigner.class)
					.addAnnotatedClass(HasRevised.class).addAnnotatedClass(MasonList.class)
					.addAnnotatedClass(Waresnama.class).addAnnotatedClass(Manjurinama.class)
					.addAnnotatedClass(Kabuliyatnama.class).addAnnotatedClass(AminKoSthalgatPratibedan.class)
					.addAnnotatedClass(NoObjectionSheet.class).addAnnotatedClass(TallaThapIjajatPatra.class)
					.addAnnotatedClass(TallaThapIjajatRequest.class).addAnnotatedClass(TallaThapIjajatTippaniades.class)
					.addAnnotatedClass(MenuMaster.class).addAnnotatedClass(MenuUserAccess.class)
					.addAnnotatedClass(RajashowMaster.class).addAnnotatedClass(LayoutGariPratibedanIjajat.class)
					.addAnnotatedClass(LayoutGariPratibedanRequest.class)
					.addAnnotatedClass(NaksaFilePathayekoBare.class).addAnnotatedClass(WardMaster.class)
					.addAnnotatedClass(EmailSendingPanding.class).addAnnotatedClass(NamsariBillVuktani.class)
					.addAnnotatedClass(MyadThapApproval.class).addAnnotatedClass(MyadThapRequest.class)
					.addAnnotatedClass(MyadThapTippani.class).addAnnotatedClass(OrganizationUserRequest.class)
					.addAnnotatedClass(NayaRecordPathayekoNamsari.class)
					.addAnnotatedClass(DharautiPhirtaTippaniAdes.class).addAnnotatedClass(DharautiPhirtaBare.class)
					.addAnnotatedClass(NayaNirmanPramanPatraDharautiPhirta.class)
					.addAnnotatedClass(StructuralDesignerManjuriPatra.class)
					.addAnnotatedClass(ArchitectureDesignerManjuriPatra.class).addAnnotatedClass(SansodhitSuperStructureIjajatSambandhama.class)
					.addAnnotatedClass(SuperStructureSupervisionReport.class).addAnnotatedClass(CertificateSupervisionReport.class)
					.addAnnotatedClass(EarthquakeSafetyNoObjectionSheet.class).addAnnotatedClass(LandAreaCalculation.class)
					.addAnnotatedClass(FormToBeFilledByDesigner.class).addAnnotatedClass(SuchanaTaasGariMuchulka.class)
					.addAnnotatedClass(PrathamCharanKaamGarneAnumati.class).addAnnotatedClass(NirmanSampannaPramanPatraAndDharautiFirta.class)
					.addAnnotatedClass(PratibeydanSambandhama.class).addAnnotatedClass(PratibeydanPeshGarekoBare.class)
					.addAnnotatedClass(KaryalayaPrayojan15DaysNotice.class).addAnnotatedClass(HighRisedBuildings.class)
					.addAnnotatedClass(MultiStoreyForensic.class).addAnnotatedClass(NoObjectionCertificate.class).addAnnotatedClass(NoObjectionCertificateDetails.class)
					.addAnnotatedClass(SthalgatNiirikshyanGariPesh.class).addAnnotatedClass(SthalgatNiirikshyanGariPeshDetails.class)
					.addAnnotatedClass(NaksaPassCertificate.class).addAnnotatedClass(LayoutTathaSarjaminGariFileFirta.class)
					.buildSessionFactory();*/
			
			sessionFactory = new Configuration().addProperties(prop)
					.addAnnotatedClass(EbpsTables.class).addAnnotatedClass(EbpsColumns.class)
					.addAnnotatedClass(FormNameMaster.class).addAnnotatedClass(FormFields.class)
					.addAnnotatedClass(FormPermissions.class).addAnnotatedClass(UserTypeMaster.class)
					.addAnnotatedClass(OrganizationUserRequest.class).addAnnotatedClass(OrganizationUser.class).addAnnotatedClass(OrganizationMaster.class)
					.addAnnotatedClass(Status.class)
					.addAnnotatedClass(ApplicationStatus.class)
					.addAnnotatedClass(BuildingPermitApplication.class).addAnnotatedClass(BuildingPermitFloor.class).addAnnotatedClass(BuildingPermitSurrounding.class).addAnnotatedClass(BuildingMemberDetails.class)
					.addAnnotatedClass(RajaswaEntry.class)
					.addAnnotatedClass(FiscalYear.class)
					.addAnnotatedClass(ApplicationsComments.class).addAnnotatedClass(ApplicationHistory.class)
					.addAnnotatedClass(forwardStatus.class).addAnnotatedClass(FormGroupMaster.class).addAnnotatedClass(FormGroup.class)
					.addAnnotatedClass(ApplicationForwardingSetup.class).addAnnotatedClass(FileStorageCategory.class).addAnnotatedClass(WardMaster.class)
					.addAnnotatedClass(ChangeDesigner.class)
					.addAnnotatedClass(Vocabulary.class).addAnnotatedClass(VocabularyDetails.class)
					.addAnnotatedClass(CheckClassGroup.class).addAnnotatedClass(FileStorageApplication.class)
					.buildSessionFactory();

			setProps(prop);
			
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}

	public static SessionFactory getSessionFactory() {
		try {
			if ((sessionFactory.isClosed()) || sessionFactory == null) {
				init();
			}
		} catch (Exception e) {
			init();
		}
		return sessionFactory;
	}
	
	public static Properties getProps() {
		return props;
	}

	public static void setProps(Properties props) {
		HibernateUtil.props = props;
	}
}
