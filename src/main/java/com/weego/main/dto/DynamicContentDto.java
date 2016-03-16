package com.weego.main.dto;

import com.weego.main.model.RecommendContent;
import org.mongojack.Id;

import java.util.Date;

/**
 * Created by liuniandxx on 16-3-16.
 */
public class DynamicContentDto {
    private String id;

    private String cityId;

    private String cityName;

    private RecommendContent recommendContent;

    private String type;

    private Date time;
}
