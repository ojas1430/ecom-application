package com.app.ecom.service;

import com.app.ecom.dto.AddressDTO;
import com.app.ecom.dto.userRequest;
import com.app.ecom.dto.userResponse;
import com.app.ecom.model.Address;
import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    //   public List<User> userList = new ArrayList<User>();

    public List<userResponse> fetchAllUsers(){
        List<User> userList =  userRepository.findAll();
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void addUser( userRequest userRequest){
        User user = new User();
        updateUserFromRequest(user,userRequest);
        userRepository.save(user);
    }

    public Optional<User> fetchUser(Long id) {
        return userRepository.findById(id);
    }
    private userResponse mapToUserResponse(User user){
        userResponse userResponse = new userResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setUserRole(user.getUserRole());
        if(userResponse.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(userResponse.getAddress().getStreet());
            addressDTO.setCity(userResponse.getAddress().getCity());
            addressDTO.setState(userResponse.getAddress().getState());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            addressDTO.setCountry(userResponse.getAddress().getCountry());
            addressDTO.setId(userResponse.getAddress().getId());
            userResponse.setAddress(addressDTO);
        }
        return userResponse;
    }

    private void updateUserFromRequest(User user, userRequest userRequest){
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddress() != null){
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setZipcode(userRequest.getAddress().getZipcode());
            address.setCountry(userRequest.getAddress().getCountry());
            user.setAddress(address);

        }
    }

    public boolean updateUser(Long id, User updateUser){
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updateUser.getFirstName());
                    existingUser.setLastName(updateUser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

}