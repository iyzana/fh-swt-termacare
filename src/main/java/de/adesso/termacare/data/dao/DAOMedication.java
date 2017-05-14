package de.adesso.termacare.data.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by kaiser on 13.05.2017.
 */

@Data
@AllArgsConstructor
public class DAOMedication implements DAOData{
    long id;
    long patientId;
    String patientName;
    List<Long> doctorIds;
    String doctorNames;
    String type;
    String time;
}
