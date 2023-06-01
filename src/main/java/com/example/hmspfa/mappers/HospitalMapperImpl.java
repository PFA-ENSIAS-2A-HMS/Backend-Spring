package com.example.hmspfa.mappers;

import com.example.hmspfa.dtos.HospitalDTO;
import com.example.hmspfa.entities.Hospital;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class HospitalMapperImpl {
    public HospitalDTO fromHospital(Hospital hospital){
      HospitalDTO hospitalDTO = new HospitalDTO();
      BeanUtils.copyProperties(hospital,hospitalDTO);
        return  hospitalDTO;
    }
}
