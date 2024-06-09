/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.dto.response;

import com.epm.pojo.Faculty;
import com.epm.pojo.Semester;
import com.epm.pojo.Term;
import com.epm.pojo.User;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 *
 * @author ACER
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityResponse {
    Integer id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String description;
    private Boolean active;
    private String image;
    private int slots;
    private Boolean close;
}
