package com.dang.booking.service;

import com.dang.booking.dto.HotelDetailDto;
import com.dang.booking.dto.HotelListDto;
import com.dang.booking.exception.NotFoundException;
import com.dang.booking.mapper.HotelMapper;
import com.dang.booking.model.Hotel;
import com.dang.booking.model.HotelAddress;
import com.dang.booking.model.HotelImage;
import com.dang.booking.model.User;
import com.dang.booking.payload.request.InsertHotelRequest;
import com.dang.booking.payload.request.UpdateHotelRequest;
import com.dang.booking.repository.HotelAddressRepository;
import com.dang.booking.repository.HotelImageRepository;
import com.dang.booking.repository.HotelRepository;
import com.dang.booking.repository.UserRepository;
import com.dang.booking.service.imp.FileServiceImp;
import com.dang.booking.service.imp.HotelServiceImp;
import com.dang.booking.utils.Helper;
import com.dang.booking.validation.ValidationUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService implements HotelServiceImp {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelAddressRepository hotelAddressRepository;

    @Override
    public List<HotelListDto> getHotels(){
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelListDto> hotelListDto = new ArrayList<>();
        for(Hotel hotel: hotels){
            hotelListDto.add(hotelMapper.hotelConvertToHotelListDto(hotel));
        }
        return hotelListDto;
    }

    @Override
    public HotelDetailDto getHotelDetail(int id){
        HotelDetailDto hotelDetailDto = new HotelDetailDto();
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new NotFoundException("Not found hotel with id"));
        return hotelMapper.hotelConvertHotelDetailDto(hotel,hotelDetailDto);
    }

    @Override
    public  List<HotelDetailDto> getHotelsByUserId(Long userId){

        HotelDetailDto hotelDetailDto = new HotelDetailDto();
        List<HotelDetailDto> hotelDetailDtoList = new ArrayList<>();
        List<Hotel> hotels = hotelRepository.findByUserId(userId);

        for (Hotel hotel: hotels)
        {
            hotelDetailDtoList.add(hotelMapper.hotelConvertHotelDetailDto(hotel,hotelDetailDto));
        }
        return hotelDetailDtoList;
    }


    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private Helper helper;

    @Autowired
    private HotelImageRepository hotelImageRepository;

    //    @Transactional

    @Transactional
    @Override
    public Hotel insertHotel(HttpServletRequest request , InsertHotelRequest hotelRequest){
        ValidationUtil.validateNotBlank(hotelRequest.getName(),"Name is mandatory");
        ValidationUtil.validateNotBlank(hotelRequest.getDescription(), "Description is mandatory");
        ValidationUtil.validatePhoneNumber(hotelRequest.getPhone(), "Phone number must be 10 digits");
        ValidationUtil.validateRating(hotelRequest.getRating(),  new BigDecimal("0"), new BigDecimal("5"),  "Rating should be between 0 and 5");
        ValidationUtil.validateTime(hotelRequest.getOpenTime(), "Open time is mandatory");
        ValidationUtil.validateTime(hotelRequest.getCloseTime(), "Close time is mandatory");
        ValidationUtil.validateTime(hotelRequest.getCheckInTime(), "Check-in time is mandatory");
        ValidationUtil.validateTime(hotelRequest.getCheckOutTime(), "Check-out time is mandatory");
        ValidationUtil.validateNotNull(hotelRequest.getAvatar(), "Avatar is mandatory");


        ValidationUtil.validateNotBlank(hotelRequest.getCity(), "City is mandatory");
        ValidationUtil.validateNotBlank(hotelRequest.getDistrict(), "District is mandatory");
        ValidationUtil.validateNotBlank(hotelRequest.getCountry(), "Country is mandatory");
        ValidationUtil.validateNotBlank(hotelRequest.getProvince(), "Province is mandatory");
        ValidationUtil.validateNotBlank(hotelRequest.getStreetName(), "Street name is mandatory");
        ValidationUtil.validateNotBlank(String.valueOf(hotelRequest.getStreetNumber()), "Street number is mandatory");

        ValidationUtil.validateNotBlank(hotelRequest.getImagePath(), "Image path is mandatory");
        ValidationUtil.validateNotBlank(hotelRequest.getImageTitle(), "Image title is mandatory");
        ValidationUtil.validateNotNull(hotelRequest.getUploadDate(), "Upload date is mandatory");

        fileServiceImp.saveFile(hotelRequest.getAvatar());

        Hotel hotelEntity = new Hotel();

        hotelEntity.setName(hotelRequest.getName());
        hotelEntity.setDescription(hotelRequest.getDescription());
        hotelEntity.setPhone(hotelRequest.getPhone());
        hotelEntity.setRating(hotelRequest.getRating());
        hotelEntity.setOpenTime(hotelRequest.getOpenTime());
        hotelEntity.setCloseTime(hotelRequest.getCloseTime());
        hotelEntity.setCheckinTime(hotelRequest.getCheckInTime());
        hotelEntity.setCheckoutTime(hotelRequest.getCheckOutTime());
        hotelEntity.setAvatar(hotelRequest.getAvatar().getOriginalFilename());

        User user = userRepository.findById(helper.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        hotelEntity.setUser(user);


        HotelAddress hotelAddress = new HotelAddress();
        hotelAddress.setHotel(hotelRepository.save(hotelEntity));
        hotelAddress.setCity(hotelRequest.getCity());
        hotelAddress.setDistrict(hotelRequest.getDistrict());
        hotelAddress.setCountry(hotelRequest.getCountry());
        hotelAddress.setProvince(hotelRequest.getProvince());
        hotelAddress.setStreetName(hotelRequest.getStreetName());
        hotelAddress.setStreetNumber(hotelRequest.getStreetNumber());
        hotelAddressRepository.save(hotelAddress);

        HotelImage hotelImage = new HotelImage();
        hotelImage.setImageDescription(hotelRequest.getImageDesc());
        hotelImage.setImagePath(hotelRequest.getImagePath());
        hotelImage.setImageTitle(hotelRequest.getImageTitle());
        hotelImage.setUploadDate(hotelRequest.getUploadDate());
          hotelImage.setHotel(hotelRepository.save(hotelEntity));
        hotelImageRepository.save(hotelImage);





        return hotelRepository.save(hotelEntity);










    }

    @Transactional
    @Override
    public Hotel updateHotel(HttpServletRequest request, UpdateHotelRequest updateHotelRequest) {

        ValidationUtil.validateNotBlank(updateHotelRequest.getName(),"Name is mandatory");
        ValidationUtil.validateNotBlank(updateHotelRequest.getDescription(), "Description is mandatory");
        ValidationUtil.validatePhoneNumber(updateHotelRequest.getPhone(), "Phone number must be 10 digits");
        ValidationUtil.validateRating(updateHotelRequest.getRating(),  new BigDecimal("0"), new BigDecimal("5"),  "Rating should be between 0 and 5");
        ValidationUtil.validateTime(updateHotelRequest.getOpenTime(), "Open time is mandatory");
        ValidationUtil.validateTime(updateHotelRequest.getCloseTime(), "Close time is mandatory");
        ValidationUtil.validateTime(updateHotelRequest.getCheckInTime(), "Check-in time is mandatory");
        ValidationUtil.validateTime(updateHotelRequest.getCheckOutTime(), "Check-out time is mandatory");
        ValidationUtil.validateNotNull(updateHotelRequest.getAvatar(), "Avatar is mandatory");


        ValidationUtil.validateNotBlank(updateHotelRequest.getCity(), "City is mandatory");
        ValidationUtil.validateNotBlank(updateHotelRequest.getDistrict(), "District is mandatory");
        ValidationUtil.validateNotBlank(updateHotelRequest.getCountry(), "Country is mandatory");
        ValidationUtil.validateNotBlank(updateHotelRequest.getProvince(), "Province is mandatory");
        ValidationUtil.validateNotBlank(updateHotelRequest.getStreetName(), "Street name is mandatory");
        ValidationUtil.validateNotBlank(String.valueOf(updateHotelRequest.getStreetNumber()), "Street number is mandatory");

        ValidationUtil.validateNotBlank(updateHotelRequest.getImagePath(), "Image path is mandatory");
        ValidationUtil.validateNotBlank(updateHotelRequest.getImageTitle(), "Image title is mandatory");
        ValidationUtil.validateNotNull(updateHotelRequest.getUploadDate(), "Upload date is mandatory");

       Hotel hotelEntity =   hotelRepository.findById(updateHotelRequest.getHotelID()).orElseThrow(()-> new RuntimeException("Hotel not found"));


        hotelEntity.setName(updateHotelRequest.getName());
        hotelEntity.setDescription(updateHotelRequest.getDescription());
        hotelEntity.setPhone(updateHotelRequest.getPhone());
        hotelEntity.setRating(updateHotelRequest.getRating());
        hotelEntity.setOpenTime(updateHotelRequest.getOpenTime());
        hotelEntity.setCloseTime(updateHotelRequest.getCloseTime());
        hotelEntity.setCheckinTime(updateHotelRequest.getCheckInTime());
        hotelEntity.setCheckoutTime(updateHotelRequest.getCheckOutTime());

        if (updateHotelRequest.getAvatar() != null && !updateHotelRequest.getAvatar().isEmpty()) {
            fileServiceImp.saveFile(updateHotelRequest.getAvatar());
            hotelEntity.setAvatar(updateHotelRequest.getAvatar().getOriginalFilename());
        }


        HotelAddress hotelAddress = hotelAddressRepository.findByHotel(hotelEntity)
                .orElse(new HotelAddress());


        hotelAddress.setCity(updateHotelRequest.getCity());
        hotelAddress.setDistrict(updateHotelRequest.getDistrict());
        hotelAddress.setCountry(updateHotelRequest.getCountry());
        hotelAddress.setProvince(updateHotelRequest.getProvince());
        hotelAddress.setStreetName(updateHotelRequest.getStreetName());
        hotelAddress.setStreetNumber(updateHotelRequest.getStreetNumber());
        hotelAddress.setHotel(hotelRepository.save(hotelEntity));


        HotelImage hotelImage = hotelImageRepository.findById(hotelEntity)
                .orElse(new HotelImage());
        hotelImage.setImageDescription(updateHotelRequest.getImageDesc());
        hotelImage.setImagePath(updateHotelRequest.getImagePath());
        hotelImage.setImageTitle(updateHotelRequest.getImageTitle());
        hotelImage.setUploadDate(updateHotelRequest.getUploadDate());
        hotelImage.setHotel(hotelEntity);

//        hotelRepository.save(hotelEntity);
        hotelAddressRepository.save(hotelAddress);
        hotelImageRepository.save(hotelImage);


        return hotelRepository.save(hotelEntity);
    }

    @Transactional
    public void deleteHotel(Long hotelId) {
        // Find the existing hotel by id
        Hotel hotelEntity = hotelRepository.findById(Math.toIntExact(hotelId))
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        // Delete related hotel address
        HotelAddress hotelAddress = hotelAddressRepository.findByHotel(hotelEntity)
                .orElseThrow(() -> new RuntimeException("Hotel address not found"));
        hotelAddressRepository.delete(hotelAddress);

        // Delete related hotel images
        HotelImage hotelImage = hotelImageRepository.findByHotel(hotelEntity)
                .orElseThrow(() -> new RuntimeException("Hotel image not found"));
        hotelImageRepository.delete(hotelImage);

        // Delete the hotel itself
        hotelRepository.delete(hotelEntity);
    }




}
