/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.processing;

import java.util.List;

import com.model.processing.AllowancePaper;
import com.model.processing.AminkoPratibedan;
import com.model.processing.BuildingBuildCertificate;
import com.model.processing.BuildingFinishCertificate;
import com.model.processing.CertificateInstruction;
import com.model.processing.CertificateNote;
import com.model.processing.CertificateSupervisionReport;
import com.model.processing.DharautiPhirtaBare;
import com.model.processing.DharautiPhirtaTippaniAdes;
import com.model.processing.DosrocharanAbedan;
import com.model.processing.DosrocharanBillVuktani;
import com.model.processing.DosrocharanPrabidhik;
import com.model.processing.DosrocharanSupervisor;
import com.model.processing.EarthquakeSafetyNoObjectionSheet;
import com.model.processing.FormToBeFilledByDesigner;
import com.model.processing.GharCompoundwallkoNaap;
import com.model.processing.GharNaksakoKabuliyatnama;
import com.model.processing.GharnakshaSurjaminMuchulka;
import com.model.processing.HighRisedBuildings;
import com.model.processing.KaryalayaPrayojan15DaysNotice;
import com.model.processing.LandAreaCalculation;
import com.model.processing.LayoutTathaSarjaminGariFileFirta;
import com.model.processing.MultiStoreyForensic;
import com.model.processing.MyadThapApproval;
import com.model.processing.MyadThapRequest;
import com.model.processing.MyadThapTippani;
import com.model.processing.NaksaPassCertificate;
import com.model.processing.NamsariBillVuktani;
import com.model.processing.NamsariTippaniAdes;
import com.model.processing.NayaNirmanPramanPatraDharautiPhirta;
import com.model.processing.NayaRecordPathayekoNamsari;
import com.model.processing.NirmanSampannaPramanPatraAndDharautiFirta;
import com.model.processing.NoteorderPilengthLevel;
import com.model.processing.NoticePaymentApplication;
import com.model.processing.PahilocharanBillVuktani;
import com.model.processing.PlinthLevelOwnerRepresentation;
import com.model.processing.PlinthLevelTechapplication;
import com.model.processing.PrabhidikPratibedhanPesh;
import com.model.processing.PrathamCharanKaamGarneAnumati;
import com.model.processing.PratibeydanPeshGarekoBare;
import com.model.processing.PratibeydanSambandhama;
import com.model.processing.PuranoGharNirmanTippani;
import com.model.processing.PuranoGharSampannaPramanPatra;
import com.model.processing.SamsodhitSuperStructureIjajatPratibedan;
import com.model.processing.SansodhanBibaranPahilo;
import com.model.processing.SansodhanBillVuktani;
import com.model.processing.SansodhanGariNibedan;
import com.model.processing.SansodhanSuperStructureIjjaat;
import com.model.processing.SansodhanTippani;
import com.model.processing.SansodhankoTippaniAdesh;
import com.model.processing.SansodhansuperTippaniAdesh;
import com.model.processing.SansodhitSuperStructureIjajatSambandhama;
import com.model.processing.SuchanaTaasGariMuchulka;
import com.model.processing.SuperStructureBuild;
import com.model.processing.SuperStructureConstruction;
import com.model.processing.SuperStructureNoteOrder;
import com.model.processing.SuperStructureSupervisionReport;
import com.model.processing.SuperstructurekoNirmanKarya;
import com.model.processing.SuperstructurekoTippaniadesh;
import com.model.processing.SurjaminMuchulka;

public interface EBPSDao {

	public String getMsg();

	public List getAll(String hql);

	public int save(NoticePaymentApplication obj);

	public int save(SurjaminMuchulka obj);

	public int save(GharnakshaSurjaminMuchulka obj);

	public int save(GharNaksakoKabuliyatnama obj);

	public int save(AminkoPratibedan obj);

	public int save(PrabhidikPratibedhanPesh obj);

	public int save(NoteorderPilengthLevel obj);

	public int save(AllowancePaper obj);

	public int save(SuperStructureBuild obj);

	public int save(PlinthLevelOwnerRepresentation obj);

	public int save(PlinthLevelTechapplication obj);

	public int save(SuperStructureNoteOrder obj);

	public int save(SuperStructureConstruction obj);

	public int save(DosrocharanAbedan obj);

	public int save(DosrocharanSupervisor obj);

	public int save(DosrocharanPrabidhik obj);

	public int save(CertificateInstruction obj);

	public int save(SamsodhitSuperStructureIjajatPratibedan obj);

	public int save(SansodhansuperTippaniAdesh obj);

	public int save(SansodhanSuperStructureIjjaat obj);

	public int save(GharCompoundwallkoNaap obj);

	public int save(SansodhankoTippaniAdesh obj);

	public int save(SuperstructurekoTippaniadesh obj);

	public int save(SansodhanTippani obj);

	public int save(SansodhanBibaranPahilo obj);

	public int save(SansodhanGariNibedan obj);

	public int save(BuildingBuildCertificate obj);

	public int save(BuildingFinishCertificate obj);

	public int save(DosrocharanBillVuktani obj);

	public int save(PahilocharanBillVuktani obj);

	public int save(SansodhanBillVuktani obj);

	public int save(PuranoGharSampannaPramanPatra obj);

	public int save(PuranoGharNirmanTippani obj);

	public int save(NamsariTippaniAdes obj);

	public int save(CertificateNote obj);

	public int save(SuperstructurekoNirmanKarya obj);

	public int save(NamsariBillVuktani obj);

	public int save(MyadThapApproval obj);

	public int save(MyadThapRequest obj);

	public int save(MyadThapTippani obj);

	public int save(NayaRecordPathayekoNamsari obj);

	public int save(DharautiPhirtaTippaniAdes obj);

	public int save(DharautiPhirtaBare obj);

	public int save(NayaNirmanPramanPatraDharautiPhirta obj);

	public int save(SansodhitSuperStructureIjajatSambandhama obj);

	public int save(SuperStructureSupervisionReport obj);

	public int save(CertificateSupervisionReport obj);

	public int save(EarthquakeSafetyNoObjectionSheet obj);

	public int save(LandAreaCalculation obj);

	public int save(FormToBeFilledByDesigner obj);

	public int save(SuchanaTaasGariMuchulka obj);

	public int save(PrathamCharanKaamGarneAnumati obj);

	public int save(NirmanSampannaPramanPatraAndDharautiFirta obj);

	public int save(PratibeydanSambandhama obj);

	public int save(PratibeydanPeshGarekoBare obj);

	public int save(KaryalayaPrayojan15DaysNotice obj);

	public int save(HighRisedBuildings obj);

	public int save(MultiStoreyForensic obj);

	public int save(NaksaPassCertificate obj);

	public int save(LayoutTathaSarjaminGariFileFirta obj);

}
