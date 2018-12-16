package com.areatechservices.fieldreportapp.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.areatechservices.fieldreportapp.Converters.DateConverter;

import java.util.Date;
import java.util.List;

/**
 * Created by djbabs on 12/2/18.
 */
@Entity
public class Survey {

    @NonNull
    @PrimaryKey
    private Long id;
    private String geo;

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    private int updated;
    private Integer status;
    private String startDate;
    private String surveyCompleted;
    private String equipPickupSuplierCivilWorks;
    private String personelDptCivilWorks;
    private String personelArvCivilWorks;
    private String startDateCivilWorks;
    private String equipOnSiteCivilWorks;
    private String allExcavationCompleted;
    private String fencingCivilCompleted;
    private String pylonCivilCompleted;
    private String equipPickupSuplierFencingPylon;
    private String personelDptFencing;
    private String personelArvFencing;
    private String startDateFencing;
    private String equipOnSiteFencing;
    private String installPylonComplete;
    private String installFencingComplete;
    private String civilVsatComplete;
    private String civilSolarComplete;
    private String equipPickupWarehouseSolar;
    private String personnelDepSolar;
    private String personnelArvSolar;
    private String startDateSolar;
    private String equipOnSiteSolar;
    private String installSolarCompleted;
    private String installVsatComplete;
    private String installBtsComplete;
    private String installWifiComplete;
    private String personnelDepCommisioning;
    private String personnelArvCommisioning;
    private String startDateCommisioning;
    private String commisioningSolar;
    private String CommisioningVsat;
    private String commisioningBts;
    private String commisioningWifi;
    private String personnelDepAcceptance;
    private String personnelArvAcceptance;
    private String startDateAcceptance;
    private String acceptanceFencing;
    private String acceptancePylon;
    private String acceptanceSolar;

    private String acceptanceVsat;
    private String acceptance3G;
    private String acceptanceWifi;


    public List<SurveyImages> getSurveyImages() {
        return surveyImages;
    }

    public void setSurveyImages(List<SurveyImages> surveyImages) {
        this.surveyImages = surveyImages;
    }

    @Ignore
    public List<SurveyImages> surveyImages;

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getSurveyCompleted() {
        return surveyCompleted;
    }

    public void setSurveyCompleted(String surveyCompleted) {
        this.surveyCompleted = surveyCompleted;
    }

    public String getEquipPickupSuplierCivilWorks() {
        return equipPickupSuplierCivilWorks;
    }

    public void setEquipPickupSuplierCivilWorks(String equipPickupSuplierCivilWorks) {
        this.equipPickupSuplierCivilWorks = equipPickupSuplierCivilWorks;
    }

    public String getPersonelDptCivilWorks() {
        return personelDptCivilWorks;
    }

    public void setPersonelDptCivilWorks(String personelDptCivilWorks) {
        this.personelDptCivilWorks = personelDptCivilWorks;
    }

    public String getPersonelArvCivilWorks() {
        return personelArvCivilWorks;
    }

    public void setPersonelArvCivilWorks(String personelArvCivilWorks) {
        this.personelArvCivilWorks = personelArvCivilWorks;
    }

    public String getStartDateCivilWorks() {
        return startDateCivilWorks;
    }

    public void setStartDateCivilWorks(String startDateCivilWorks) {
        this.startDateCivilWorks = startDateCivilWorks;
    }

    public String getEquipOnSiteCivilWorks() {
        return equipOnSiteCivilWorks;
    }

    public void setEquipOnSiteCivilWorks(String equipOnSiteCivilWorks) {
        this.equipOnSiteCivilWorks = equipOnSiteCivilWorks;
    }

    public String getAllExcavationCompleted() {
        return allExcavationCompleted;
    }

    public void setAllExcavationCompleted(String allExcavationCompleted) {
        this.allExcavationCompleted = allExcavationCompleted;
    }

    public String getFencingCivilCompleted() {
        return fencingCivilCompleted;
    }

    public void setFencingCivilCompleted(String fencingCivilCompleted) {
        this.fencingCivilCompleted = fencingCivilCompleted;
    }

    public String getPylonCivilCompleted() {
        return pylonCivilCompleted;
    }

    public void setPylonCivilCompleted(String pylonCivilCompleted) {
        this.pylonCivilCompleted = pylonCivilCompleted;
    }

    public String getEquipPickupSuplierFencingPylon() {
        return equipPickupSuplierFencingPylon;
    }

    public void setEquipPickupSuplierFencingPylon(String equipPickupSuplierFencingPylon) {
        this.equipPickupSuplierFencingPylon = equipPickupSuplierFencingPylon;
    }

    public String getPersonelDptFencing() {
        return personelDptFencing;
    }

    public void setPersonelDptFencing(String personelDptFencing) {
        this.personelDptFencing = personelDptFencing;
    }

    public String getPersonelArvFencing() {
        return personelArvFencing;
    }

    public void setPersonelArvFencing(String personelArvFencing) {
        this.personelArvFencing = personelArvFencing;
    }

    public String getStartDateFencing() {
        return startDateFencing;
    }

    public void setStartDateFencing(String startDateFencing) {
        this.startDateFencing = startDateFencing;
    }

    public String getEquipOnSiteFencing() {
        return equipOnSiteFencing;
    }

    public void setEquipOnSiteFencing(String equipOnSiteFencing) {
        this.equipOnSiteFencing = equipOnSiteFencing;
    }

    public String getInstallPylonComplete() {
        return installPylonComplete;
    }

    public void setInstallPylonComplete(String installPylonComplete) {
        this.installPylonComplete = installPylonComplete;
    }

    public String getInstallFencingComplete() {
        return installFencingComplete;
    }

    public void setInstallFencingComplete(String installFencingComplete) {
        this.installFencingComplete = installFencingComplete;
    }

    public String getCivilVsatComplete() {
        return civilVsatComplete;
    }

    public void setCivilVsatComplete(String civilVsatComplete) {
        this.civilVsatComplete = civilVsatComplete;
    }

    public String getCivilSolarComplete() {
        return civilSolarComplete;
    }

    public void setCivilSolarComplete(String civilSolarComplete) {
        this.civilSolarComplete = civilSolarComplete;
    }

    public String getEquipPickupWarehouseSolar() {
        return equipPickupWarehouseSolar;
    }

    public void setEquipPickupWarehouseSolar(String equipPickupWarehouseSolar) {
        this.equipPickupWarehouseSolar = equipPickupWarehouseSolar;
    }

    public String getPersonnelDepSolar() {
        return personnelDepSolar;
    }

    public void setPersonnelDepSolar(String personnelDepSolar) {
        this.personnelDepSolar = personnelDepSolar;
    }

    public String getPersonnelArvSolar() {
        return personnelArvSolar;
    }

    public void setPersonnelArvSolar(String personnelArvSolar) {
        this.personnelArvSolar = personnelArvSolar;
    }

    public String getStartDateSolar() {
        return startDateSolar;
    }

    public void setStartDateSolar(String startDateSolar) {
        this.startDateSolar = startDateSolar;
    }

    public String getEquipOnSiteSolar() {
        return equipOnSiteSolar;
    }

    public void setEquipOnSiteSolar(String equipOnSiteSolar) {
        this.equipOnSiteSolar = equipOnSiteSolar;
    }

    public String getInstallSolarCompleted() {
        return installSolarCompleted;
    }

    public void setInstallSolarCompleted(String installSolarCompleted) {
        this.installSolarCompleted = installSolarCompleted;
    }

    public String getInstallVsatComplete() {
        return installVsatComplete;
    }

    public void setInstallVsatComplete(String installVsatComplete) {
        this.installVsatComplete = installVsatComplete;
    }

    public String getInstallBtsComplete() {
        return installBtsComplete;
    }

    public void setInstallBtsComplete(String installBtsComplete) {
        this.installBtsComplete = installBtsComplete;
    }

    public String getInstallWifiComplete() {
        return installWifiComplete;
    }

    public void setInstallWifiComplete(String installWifiComplete) {
        this.installWifiComplete = installWifiComplete;
    }

    public String getPersonnelDepCommisioning() {
        return personnelDepCommisioning;
    }

    public void setPersonnelDepCommisioning(String personnelDepCommisioning) {
        this.personnelDepCommisioning = personnelDepCommisioning;
    }

    public String getPersonnelArvCommisioning() {
        return personnelArvCommisioning;
    }

    public void setPersonnelArvCommisioning(String personnelArvCommisioning) {
        this.personnelArvCommisioning = personnelArvCommisioning;
    }

    public String getStartDateCommisioning() {
        return startDateCommisioning;
    }

    public void setStartDateCommisioning(String startDateCommisioning) {
        this.startDateCommisioning = startDateCommisioning;
    }

    public String getCommisioningSolar() {
        return commisioningSolar;
    }

    public void setCommisioningSolar(String commisioningSolar) {
        this.commisioningSolar = commisioningSolar;
    }

    public String getCommisioningVsat() {
        return CommisioningVsat;
    }

    public void setCommisioningVsat(String commisioningVsat) {
        CommisioningVsat = commisioningVsat;
    }

    public String getCommisioningBts() {
        return commisioningBts;
    }

    public void setCommisioningBts(String commisioningBts) {
        this.commisioningBts = commisioningBts;
    }

    public String getCommisioningWifi() {
        return commisioningWifi;
    }

    public void setCommisioningWifi(String commisioningWifi) {
        this.commisioningWifi = commisioningWifi;
    }

    public String getPersonnelDepAcceptance() {
        return personnelDepAcceptance;
    }

    public void setPersonnelDepAcceptance(String personnelDepAcceptance) {
        this.personnelDepAcceptance = personnelDepAcceptance;
    }

    public String getPersonnelArvAcceptance() {
        return personnelArvAcceptance;
    }

    public void setPersonnelArvAcceptance(String personnelArvAcceptance) {
        this.personnelArvAcceptance = personnelArvAcceptance;
    }

    public String getStartDateAcceptance() {
        return startDateAcceptance;
    }

    public void setStartDateAcceptance(String startDateAcceptance) {
        this.startDateAcceptance = startDateAcceptance;
    }

    public String getAcceptanceFencing() {
        return acceptanceFencing;
    }

    public void setAcceptanceFencing(String acceptanceFencing) {
        this.acceptanceFencing = acceptanceFencing;
    }

    public String getAcceptancePylon() {
        return acceptancePylon;
    }

    public void setAcceptancePylon(String acceptancePylon) {
        this.acceptancePylon = acceptancePylon;
    }

    public String getAcceptanceSolar() {
        return acceptanceSolar;
    }

    public void setAcceptanceSolar(String acceptanceSolar) {
        this.acceptanceSolar = acceptanceSolar;
    }

    public String getAcceptanceVsat() {
        return acceptanceVsat;
    }

    public void setAcceptanceVsat(String acceptanceVsat) {
        this.acceptanceVsat = acceptanceVsat;
    }

    public String getAcceptance3G() {
        return acceptance3G;
    }

    public void setAcceptance3G(String acceptance3G) {
        this.acceptance3G = acceptance3G;
    }

    public String getAcceptanceWifi() {
        return acceptanceWifi;
    }

    public void setAcceptanceWifi(String acceptanceWifi) {
        this.acceptanceWifi = acceptanceWifi;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public boolean isEmpty() {
        return (startDate.isEmpty()&&surveyCompleted.isEmpty()&&equipPickupSuplierCivilWorks.isEmpty()&&personelDptCivilWorks.isEmpty()&&personelArvCivilWorks.isEmpty());

    }
}
