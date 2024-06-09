/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.mapper;

import com.epm.dto.response.ActivityResponse;
import com.epm.pojo.Activity;
import org.mapstruct.Mapper;

/**
 *
 * @author ACER
 */
@Mapper(componentModel = "spring")
public interface ActivityMapper {
    ActivityResponse toActivityResponse(Activity activity);
}
