package com.weego.main.service.impl;

import com.weego.main.constant.PgcType;
import com.weego.main.constant.RecommendType;
import com.weego.main.dao.*;
import com.weego.main.dto.*;
import com.weego.main.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.ParseState;
import org.springframework.stereotype.Service;

import com.weego.main.service.PgcService;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.base.Strings;

import java.util.*;

@Service("pgcService")
public class PgcServiceImpl implements PgcService {

    @Autowired
    private PgcDao pgcDao;

    @Autowired
    private PeopleDao peopleDao;

    @Autowired
    private AttractionDao attractionDao;

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private ShoppingDao shoppingDao;

    @Override
    public List<PgcListDto> getPgcList(String cityId) {
        List<PgcListDto> pgcListPgcDtoList = new ArrayList<>();
        Map<Integer, PgcListDto> map = new TreeMap<>();
        try {
            List<Pgc> pgcList = pgcDao.getPgcByCityId(cityId);
            if(pgcList != null && pgcList.size() > 0) {

                for (Pgc pgc : pgcList) {
                    Integer type = pgc.getType();
                    PgcListContentDto contentDto = new PgcListContentDto();
                    if(PgcType.CELEBRITY.getType().equals(type)
                            && !Strings.isNullOrEmpty(pgc.getPerson())
                            ) {
                        Person person = peopleDao.getPersonById(pgc.getPerson());
                        contentDto.setUserName(person.getUserName());
                        contentDto.setHeadImage(person.getHeadImage());
                    } else {
                        contentDto.setUserName("");
                        contentDto.setHeadImage("");
                    }
                    contentDto.setTitle(pgc.getTitle());
                    contentDto.setCoverImage(pgc.getCoverImage());
                    contentDto.setPgcId(pgc.getId());

                    if(map.get(type) != null) {
                        PgcListDto pgcListDto = map.get(type);
                        pgcListDto.getContent().add(contentDto);
                    } else {
                        String tag = pgc.getTag();
                        PgcListDto pgcListDto = new PgcListDto();
                        pgcListDto.setType(type);
                        pgcListDto.setTag(tag);
                        List<PgcListContentDto> contentDtoList = new ArrayList<>();
                        contentDtoList.add(contentDto);
                        pgcListDto.setContent(contentDtoList);
                        map.put(type, pgcListDto);
                    }
                }
                for(PgcListDto pgcListDto : map.values()) {
                    pgcListPgcDtoList.add(pgcListDto);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return pgcListPgcDtoList;
    }


    @Override
    public PgcDetailDto getPgcDetail(String pgcId) {
        PgcDetailDto pgcDetailDto = new PgcDetailDto();

        try {
            Pgc pgc = pgcDao.getSpecifiedPgc(pgcId);

            pgcDetailDto.setType(pgc.getType());
            pgcDetailDto.setCoverImage(pgc.getCoverImage());
            pgcDetailDto.setTitle(pgc.getTitle());

            if (Strings.isNullOrEmpty(pgc.getPerson())) {
                PgcPersonDto pgcPeopleDto = new PgcPersonDto();
                pgcPeopleDto.setId("");
                pgcPeopleDto.setHeadImage("");
                pgcPeopleDto.setName("");
                pgcPeopleDto.setDesc("");

                pgcDetailDto.setPerson(pgcPeopleDto);
            } else {
                Person person = peopleDao.getPersonById(pgc.getPerson().trim());
                PgcPersonDto pgcPeopleDto = new PgcPersonDto();
                pgcPeopleDto.setId(person.getPersonId());
                pgcPeopleDto.setHeadImage(person.getHeadImage());
                pgcPeopleDto.setName(person.getUserName());
                pgcPeopleDto.setDesc(person.getJobDesc());

                pgcDetailDto.setPerson(pgcPeopleDto);
            }


            pgcDetailDto.setIntroduction(pgc.getIntroduction());
            pgcDetailDto.setOriginal(convertOriginalToDto(pgc.getOriginal()));

            List<PgcPoi> pgcPoiList = pgc.getPoiList();
            List<PgcContentDto> pgcPoiDtoList = new ArrayList<PgcContentDto>();
            if (pgcPoiList != null && pgcPoiList.size() > 0) {
                for (PgcPoi pgcPoi : pgcPoiList) {
                    PgcContentDto pgcContentDto = new PgcContentDto();
                    ParagraphDto paragraphDto = new ParagraphDto();
                    paragraphDto.setTitle(Strings.nullToEmpty(pgcPoi.getParagraphTitle()));
                    paragraphDto.setDesc(Strings.nullToEmpty(pgcPoi.getParagraphDesc()));
                    pgcContentDto.setParagraph(paragraphDto);

                    PgcPoiDto pgcPoiDto = new PgcPoiDto();
                    pgcPoiDto.setId(Strings.nullToEmpty(pgcPoi.getId()));
                    pgcPoiDto.setImage(Strings.nullToEmpty(pgcPoi.getPoiImage()));
                    pgcPoiDto.setType(Strings.nullToEmpty(pgcPoi.getType()));
                    pgcPoiDto.setTitle(Strings.nullToEmpty(pgcPoi.getName()));
                    pgcPoiDto.setTag(getPoiTagbyType(pgcPoi.getId(), pgcPoi.getType()));
                    pgcContentDto.setPoi(pgcPoiDto);

                    PgcImageDto pgcImageDto = new PgcImageDto();
                    pgcImageDto.setUrl(Strings.nullToEmpty(pgcPoi.getImageUrl()));
                    pgcImageDto.setSource(Strings.nullToEmpty(pgcPoi.getImageSource()));
                    pgcContentDto.setImage(pgcImageDto);

                    pgcPoiDtoList.add(pgcContentDto);
                }
            }
            pgcDetailDto.setContent(pgcPoiDtoList);
        } catch (Exception e) {
            return null;
        }
        return pgcDetailDto;
    }

    private String getPoiTagbyType(String id, String type) {
        if(Strings.isNullOrEmpty(type)) {
            return "";
        }

        Integer intType = Integer.parseInt(type);
        if(RecommendType.ATTRACTION.getType().equals(intType)) {
            Attraction attraction = attractionDao.getAttractionById(id);
            List<BasePOITag>  tagList =  attraction.getSubTag();
            if(tagList != null && tagList.size() > 0) {
                return tagList.get(0).getTag();
            }
        } else if(RecommendType.RESTAURANT.getType().equals(intType)) {
            Restaurant restaurant = restaurantDao.getRestaurantById(id);
            List<BasePOITag> tagList = restaurant.getSubTag();
            if(tagList != null && tagList.size() > 0) {
                return tagList.get(0).getTag();
            }
        } else if(RecommendType.SHOPPING.getType().equals(intType)
                || RecommendType.SHOPPINGCIRCLE.getType().equals(intType)) {
            Shopping shopping = shoppingDao.getShoppingById(id);
            List<BasePOITag> tagList = shopping.getSubTag();
            if(tagList != null && tagList.size() > 0) {
                return tagList.get(0).getTag();
            }
        }
        return "";
    }

    private OriginalDto convertOriginalToDto(PgcOriginal original) {
        OriginalDto originalDto = new OriginalDto();
        if(original != null) {
            originalDto.setAuthor(Strings.nullToEmpty(original.getAuthor()));
            originalDto.setDesc(Strings.nullToEmpty(original.getDesc()));
            originalDto.setImage(Strings.nullToEmpty(original.getImage()));
            originalDto.setSource(Strings.nullToEmpty(original.getSource()));
            originalDto.setUrl(Strings.nullToEmpty(original.getUrl()));
        } else {
            originalDto.setAuthor("");
            originalDto.setDesc("");
            originalDto.setImage("");
            originalDto.setSource("");
            originalDto.setUrl("");
        }
        return originalDto;
    }
    
    @Override
    public ModelAndView getSpecifiedPgc(String pgcId) {

        Pgc pgc = pgcDao.getSpecifiedPgc(pgcId);
        if (pgc == null) {
            return null;
        } else {

            ModelAndView mv = new ModelAndView("PGC");
            mv.addObject("author", pgc.getPerson());
            mv.addObject("poi_bg", pgc.getCoverImage());
            mv.addObject("title", pgc.getTitle());
            
            if (Strings.isNullOrEmpty(pgc.getPerson())) {
                mv.addObject("person", null);
            } else {
                Person person = peopleDao.getPersonById(pgc.getPerson().trim());
                if(person!=null){
                    mv.addObject("person", person);
                }else{
                    mv.addObject("person", null);
                }
            }
            PgcOriginal original = pgc.getOriginal();
            if(original != null) {
                mv.addObject("original", original);
            } else {
                mv.addObject("original", null);
            }
            List<PgcPoi> pgcPoiList = pgc.getPoiList();
            List<PgcContentDto> pgcPoiDtoList = new ArrayList<PgcContentDto>();
            if (pgcPoiList != null && pgcPoiList.size() > 0) {
                for (PgcPoi pgcPoi : pgcPoiList) {
                    PgcContentDto pgcContentDto = new PgcContentDto();
                    ParagraphDto paragraphDto = new ParagraphDto();
                    paragraphDto.setTitle(Strings.nullToEmpty(pgcPoi.getParagraphTitle()));
                    paragraphDto.setDesc(Strings.nullToEmpty(pgcPoi.getParagraphDesc()));
                    pgcContentDto.setParagraph(paragraphDto);

                    PgcPoiDto pgcPoiDto = new PgcPoiDto();
                    pgcPoiDto.setId(Strings.nullToEmpty(pgcPoi.getId()));
                    pgcPoiDto.setImage(Strings.nullToEmpty(pgcPoi.getPoiImage()));
                    pgcPoiDto.setType(Strings.nullToEmpty(pgcPoi.getType()));
                    pgcPoiDto.setTitle(Strings.nullToEmpty(pgcPoi.getName()));
                    String tag = getPoiTagbyType(pgcPoi.getId(), pgcPoi.getType());
                    if(tag==null || tag.endsWith("")){
                        pgcPoiDto.setTag(tag);
                    }
                    pgcContentDto.setPoi(pgcPoiDto);

                    PgcImageDto pgcImageDto = new PgcImageDto();
                    pgcImageDto.setUrl(Strings.nullToEmpty(pgcPoi.getImageUrl()));
                    pgcImageDto.setSource(Strings.nullToEmpty(pgcPoi.getImageSource()));
                    pgcContentDto.setImage(pgcImageDto);

                    pgcPoiDtoList.add(pgcContentDto);
                }
            }
            mv.addObject("poilist", pgcPoiDtoList);
            mv.addObject("breif", pgc.getIntroduction());

            return mv;
        }

    }
}