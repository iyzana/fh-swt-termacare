package de.adesso.termacare.gui.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DtoMedication extends DtoAbstractData{
    long patientId;
    String patientName;
    List<Long> doctorIds;
    String doctorNames;
    String type;
    String time;

    public DtoMedication(
            long id, long patientId, String patientName, List<Long> doctorIds, String doctorNames, String type,
            String time){
        super(id);
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorIds = doctorIds;
        this.doctorNames = doctorNames;
        this.type = type;
        this.time = time;
    }
}
